package com.coaltionbuilder.coalitionbuilder_be.controller;


import com.coaltionbuilder.coalitionbuilder_be.mapper.DiscussionMapper;
import com.coaltionbuilder.coalitionbuilder_be.model.Comment;
import com.coaltionbuilder.coalitionbuilder_be.model.CommentDto;
import com.coaltionbuilder.coalitionbuilder_be.model.Post;
import com.coaltionbuilder.coalitionbuilder_be.model.PostDto;
import com.coaltionbuilder.coalitionbuilder_be.response.ChildCommentsResponse;
import com.coaltionbuilder.coalitionbuilder_be.response.CommentResponse;
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

  @GetMapping("/posts")
  @ResponseBody
  public ResponseEntity<List<PostDto>> getAll() {
    return ResponseEntity.ok(
            this.discussionService.retrieveAllPosts().stream().map(DiscussionMapper.INSTANCE::PostToPostDto).toList()
    );
  }

  @PostMapping("/posts")
  public ResponseEntity<PostDto> postPost(@RequestBody PostDto postDto) {
    return ResponseEntity.ok(
            DiscussionMapper.INSTANCE.PostToPostDto(this.discussionService.savePost(postDto))
    );
  }

  @PostMapping("/comments")
  public ResponseEntity<CommentDto> postComment(@RequestBody CommentDto commentDto) {
    return ResponseEntity.ok(
            DiscussionMapper.INSTANCE.CommentDtoToComment(this.discussionService.saveComment(commentDto))
    );
  }

  @GetMapping("/posts/{id}")
  public ResponseEntity<PostDto> getPost(@PathVariable Integer id, @RequestParam(defaultValue = "false") boolean allChildComments) {

    // Retrieve post
    Post post = this.discussionService.retrievePostById(id);

//    List<CommentResponse> comments = this.discussionService.retrieveRootCommentsByPost(post).stream().map(
//            comment -> CommentResponse.builder().message(comment.getMessage()).author(comment.getAuthor().getFirstname())
//                    .creationDate(comment.getCreationDate()).id(comment.getId()).numChildComments(
//                            comment.getChildComments() == null ? 0 : comment.getChildComments().size()
//                    ).build()
//    ).toList();
//
//    // Build postresponse
//    PostResponse postResponse = PostResponse.builder()
//            .id(post.getId())
//            .title(post.getTitle())
//            .description(post.getDescription())
//            .creationDate(post.getCreationDate())
//            .author(post.getAuthor().getFirstname())
//            .rootComments(comments)
//            .build();

    return ResponseEntity.ok(
            DiscussionMapper.INSTANCE.PostToPostDto(this.discussionService.retrievePostById(id))
    );
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
