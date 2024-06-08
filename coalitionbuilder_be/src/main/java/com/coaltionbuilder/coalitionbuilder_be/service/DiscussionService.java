package com.coaltionbuilder.coalitionbuilder_be.service;

import com.coaltionbuilder.coalitionbuilder_be.exception.CommentNotFoundException;
import com.coaltionbuilder.coalitionbuilder_be.exception.PostNotFoundException;
import com.coaltionbuilder.coalitionbuilder_be.exception.ResourceInvalidException;
import com.coaltionbuilder.coalitionbuilder_be.exception.UserNotFoundException;
import com.coaltionbuilder.coalitionbuilder_be.model.*;
import com.coaltionbuilder.coalitionbuilder_be.repository.PostRepository;
import com.coaltionbuilder.coalitionbuilder_be.repository.CommentRepository;
import com.coaltionbuilder.coalitionbuilder_be.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;

@Service
public class DiscussionService {

  private final PostRepository postRepository;

  private final CommentRepository commentRepository;

  private final UserRepository userRepository;

  private List<Post> posts = new ArrayList<>();

  private User user;

  public DiscussionService(PostRepository postRepository, CommentRepository commentRepository, UserRepository userRepository) {
    this.postRepository = postRepository;
    this.commentRepository = commentRepository;
    this.userRepository = userRepository;
  }

  @PostConstruct
  public void init() {

    Random rand = new Random();

    // Make user
    this.user = User.builder()
            .email("roemerderuiter@gmail.com")
            .firstname("Roemer")
            .lastname("De Ruiter")
            .role(Role.USER)
            .password("$2a$10$5Lqk5/IfYt16QhVZIWM/9uVTE131JCVdkbypHvexdFXHX8W43UpUO") // hallo123
            .build();
    this.userRepository.save(user);

    // Make post for user

    for (int i = 1; i <= 15; i++) {

      Post post = Post.builder()
              .title("Title " + i)
              .description("Lorem ipsum dolor sit amet, consectetur adipiscing elit. Sed sit amet urna non orci volutpat dictum eget nec arcu. Sed commodo, ante quis finibus finibus, sapien mauris venenatis nulla, sed interdum ipsum magna eget neque. Sed pretium placerat ipsum, et semper sem bibendum eu. Sed ultrices ex vitae molestie venenatis. Nunc scelerisque quam leo, id euismod nisl eleifend sit amet. Donec non lectus non magna efficitur tempor. Duis et nibh risus. Donec egestas, purus sed convallis luctus, risus leo mattis justo, bibendum elementum ex mauris in magna. In vehicula justo id facilisis malesuada. Vivamus pulvinar tristique est vitae euismod. Curabitur condimentum eu enim ac mollis. Fusce sodales vehicula velit, a gravida quam mattis consectetur. Sed diam justo, tempor tristique commodo sit amet, luctus quis lorem." )
              .author(user)
              .build();
      this.posts.add(post);

      this.postRepository.save(post);
    }

    // Add comments to posts
    for(Post post : posts) {

      for(int i = 1; i <= rand.nextInt(15) + 1; i++) {
        Comment comment = Comment.builder()
                .author(user)
                .post(post)
                .message("Example comment " + i)
                .build();

        this.commentRepository.save(comment);

        for(int j = 1; j <= rand.nextInt(15); j++) {
          Comment childComment = Comment.builder()
                  .author(user)
                  .post(post)
                  .parentComment(comment)
                  .message("Example childcomment " + i)
                  .build();
          this.commentRepository.save(childComment);

          for(int h = 1; h <= rand.nextInt(15); h++) {

            Comment subChildComment = Comment.builder()
                    .author(user)
                    .post(post)
                    .parentComment(childComment)
                    .message("Example subchildcomment " + i)
                    .build();
            this.commentRepository.save(subChildComment);
          }

        }
      }
    }
  }

  public List<Post> retrieveAllPosts() {
    return this.postRepository.findAll();
  }

  public List<Post> retrievePostsByUser(User user)  {
    return this.postRepository.findByAuthor(user);
  }

  public List<Comment> retrieveRootCommentsByPost(Post post) {
    return this.commentRepository.findByPostAndParentCommentIsNull(post);
  }

  public Comment retrieveCommentById(Integer id) {
    return this.commentRepository.findById(id).orElseThrow(
            () -> new CommentNotFoundException("Comment with ID=" + id + " does not exist.")
    );
  }

  public Post retrievePostById(Integer id) {
    return this.postRepository.findById(id).orElseThrow(
            () -> new PostNotFoundException("Post with ID=" + id + " does not exist.")
    );
  }

  public Post savePost(PostDto postDto) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    Post post = new Post();
    post.setTitle(postDto.getTitle());
    post.setDescription(postDto.getDescription());
    post.setAuthor(this.userRepository.findByEmail(username).orElseThrow(
            () -> new UserNotFoundException("User with email " + username + " does not exist.")
    ));

    this.postRepository.save(post);

    return post;
  }

  public Comment saveComment(CommentDto commentDto) {

    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();

    Comment comment = new Comment();
    comment.setMessage(commentDto.getMessage());
    comment.setAuthor(this.userRepository.findByEmail(username).orElseThrow(
            () -> new UserNotFoundException("User with email " + username + " does not exist.")
    ));
    comment.setPost(
            this.postRepository.findById(commentDto.getPost().getId()).orElseThrow(
                    () -> new PostNotFoundException("Post with ID=" + commentDto.getPost().getId() + " does not exist.")
            )
    );

    if(commentDto.getParentCommentId() != null) {
      comment.setParentComment(
              this.commentRepository.findById(commentDto.getParentCommentId()).orElseThrow(
                      () -> new CommentNotFoundException("Comment with ID=" + commentDto.getParentCommentId() + " does not exist.")
              )
      );

      if(!Objects.equals(comment.getPost().getId(), comment.getParentComment().getPost().getId())) {
        throw new ResourceInvalidException("Id of post and parentcomment post do not match. ");
      }

    }


    this.commentRepository.save(comment);

    return comment;
  }
}
