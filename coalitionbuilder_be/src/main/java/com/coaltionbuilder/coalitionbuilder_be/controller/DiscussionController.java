package com.coaltionbuilder.coalitionbuilder_be.controller;


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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/discussion")
@RequiredArgsConstructor
public class DiscussionController {

  private final DiscussionService discussionService;

  @GetMapping
  @ResponseBody
  public ResponseEntity<List<Post>> getAll() {
    return ResponseEntity.ok(this.discussionService.retrieveAllPosts());
  }

  @PostMapping("/post")
  public ResponseEntity<PostResponse> postPost(@RequestBody PostDto postDto) {

    Post post = this.discussionService.savePost(postDto);

    // Build postresponse
    PostResponse postResponse = PostResponse.builder()
            .id(post.getId())
            .title(post.getTitle())
            .description(post.getDescription())
            .creationDate(post.getCreationDate())
            .author(post.getAuthor().getFirstname())
            .rootComments(this.discussionService.retrieveRootCommentsByPost(post))
            .build();

    return ResponseEntity.ok(postResponse);
  }

  @PostMapping("/comment")
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

  @GetMapping("/post/{id}")
  public ResponseEntity<PostResponse> getPost(@PathVariable Integer id, @RequestParam(defaultValue = "false") boolean allChildComments) {

    // Retrieve post
    Post post = this.discussionService.retrievePostById(id);


    // Build postresponse
    PostResponse postResponse = PostResponse.builder()
            .id(post.getId())
            .title(post.getTitle())
            .description(post.getDescription())
            .creationDate(post.getCreationDate())
            .author(post.getAuthor().getFirstname())
            .rootComments(this.discussionService.retrieveRootCommentsByPost(post))
            .build();

    return ResponseEntity.ok(postResponse);
  }

  @GetMapping("/comment/{id}")
  public ResponseEntity<ChildCommentsResponse> getComment(@PathVariable Integer id, @RequestParam(defaultValue = "false") boolean allChildComments) {

    // Retrieve comment
    Comment comment = this.discussionService.retrieveCommentById(id);

    // build childcommentresponse
    ChildCommentsResponse childCommentsResponse = ChildCommentsResponse.builder()
            .comment(comment)
            .childComments(comment.getChildComments())
            .build();

    return ResponseEntity.ok(childCommentsResponse);
  }

}
