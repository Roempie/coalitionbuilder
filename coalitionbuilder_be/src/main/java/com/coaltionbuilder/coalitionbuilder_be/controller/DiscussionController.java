package com.coaltionbuilder.coalitionbuilder_be.controller;


import com.coaltionbuilder.coalitionbuilder_be.mapper.Mapper;
import com.coaltionbuilder.coalitionbuilder_be.model.Comment;
import com.coaltionbuilder.coalitionbuilder_be.model.CommentDto;
import com.coaltionbuilder.coalitionbuilder_be.model.Post;
import com.coaltionbuilder.coalitionbuilder_be.model.PostDto;
import com.coaltionbuilder.coalitionbuilder_be.response.ChildCommentsResponse;
import com.coaltionbuilder.coalitionbuilder_be.response.CommentResponse;
import com.coaltionbuilder.coalitionbuilder_be.response.PostResponse;
import com.coaltionbuilder.coalitionbuilder_be.service.DiscussionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discussion")
@RequiredArgsConstructor
public class DiscussionController {

  private final DiscussionService discussionService;

  private final Mapper dtomapper;

  @GetMapping("/posts")
  @ResponseBody
  public ResponseEntity<List<Post>> getAll() {
    return ResponseEntity.ok(this.discussionService.retrieveAllPosts());
  }

  @PostMapping("/posts")
  public ResponseEntity<PostResponse> postPost(@RequestBody PostDto postDto) {

    Post post = this.discussionService.savePost(postDto);

    List<CommentResponse> comments = this.discussionService.retrieveRootCommentsByPost(post).stream().map(
            comment -> CommentResponse.builder().message(comment.getMessage()).author(comment.getAuthor().getFirstname())
                    .creationDate(comment.getCreationDate()).id(comment.getId()).numChildComments(
                            comment.getChildComments() == null ? 0 : comment.getChildComments().size()
                    ).build()
    ).toList();


    // Build postresponse
    PostResponse postResponse = PostResponse.builder()
            .id(post.getId())
            .title(post.getTitle())
            .description(post.getDescription())
            .creationDate(post.getCreationDate())
            .author(post.getAuthor().getFirstname())
            .rootComments(comments)
            .build();

    return ResponseEntity.ok(postResponse);
  }

  @PostMapping("/comments")
  public ResponseEntity<CommentResponse> postComment(@RequestBody CommentDto commentDto) {

    Comment comment = this.discussionService.saveComment(commentDto);

    // Build commentresponse
    CommentResponse commentResponse = CommentResponse.builder()
            .id(comment.getId())
            .message(comment.getMessage())
            .author(comment.getAuthor().getFirstname())
            .build();

    return ResponseEntity.ok(commentResponse);
  }

  @GetMapping("/posts/{id}")
  public ResponseEntity<PostResponse> getPost(@PathVariable Integer id, @RequestParam(defaultValue = "false") boolean allChildComments) {

    // Retrieve post
    Post post = this.discussionService.retrievePostById(id);

    List<CommentResponse> comments = this.discussionService.retrieveRootCommentsByPost(post).stream().map(
            comment -> CommentResponse.builder().message(comment.getMessage()).author(comment.getAuthor().getFirstname())
                    .creationDate(comment.getCreationDate()).id(comment.getId()).numChildComments(
                            comment.getChildComments() == null ? 0 : comment.getChildComments().size()
                    ).build()
    ).toList();

    // Build postresponse
    PostResponse postResponse = PostResponse.builder()
            .id(post.getId())
            .title(post.getTitle())
            .description(post.getDescription())
            .creationDate(post.getCreationDate())
            .author(post.getAuthor().getFirstname())
            .rootComments(comments)
            .build();

    return ResponseEntity.ok(postResponse);
  }

  @GetMapping("/comments/{id}")
  public ResponseEntity<ChildCommentsResponse> getComment(@PathVariable Integer id, @RequestParam(defaultValue = "false") boolean allChildComments) {

    // Retrieve comment
    Comment comment = this.discussionService.retrieveCommentById(id);

    List<CommentResponse> comments = comment.getChildComments().stream().map(
            commenta -> CommentResponse.builder().message(commenta.getMessage()).author(commenta.getAuthor().getFirstname())
                    .creationDate(commenta.getCreationDate()).id(commenta.getId()).numChildComments(
                            commenta.getChildComments() == null ? 0 : commenta.getChildComments().size()
                    ).build()
    ).toList();

    // build childcommentresponse
    ChildCommentsResponse childCommentsResponse = ChildCommentsResponse.builder()
            .comment(comment)
            .childComments(comments)
            .numChildComments(comment.getChildComments() == null ? 0 : comment.getChildComments().size())
            .build();

    return ResponseEntity.ok(childCommentsResponse);
  }

}
