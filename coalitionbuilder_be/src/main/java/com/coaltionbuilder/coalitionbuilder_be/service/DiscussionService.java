package com.coaltionbuilder.coalitionbuilder_be.service;

import com.coaltionbuilder.coalitionbuilder_be.exception.CommentNotFoundException;
import com.coaltionbuilder.coalitionbuilder_be.exception.PostNotFoundException;
import com.coaltionbuilder.coalitionbuilder_be.mapper.PostDTOMapper;
import com.coaltionbuilder.coalitionbuilder_be.model.*;
import com.coaltionbuilder.coalitionbuilder_be.repository.PostRepository;
import com.coaltionbuilder.coalitionbuilder_be.repository.CommentRepository;
import com.coaltionbuilder.coalitionbuilder_be.repository.UserRepository;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.stream.Collectors;

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
            .lastname("Roemer")
            .role(Role.USER)
            .password("asdf")
            .build();
    this.userRepository.save(user);

    // Make post for user

    for (int i = 1; i <= rand.nextInt(5) + 1; i++) {

      Post post = Post.builder()
              .title("Title " + i)
              .description("Testpost " + i)
              .author(user)
              .build();
      this.posts.add(post);

      this.postRepository.save(post);
    }

    // Add comments to posts
    for(Post post : posts) {

      for(int i = 1; i <= rand.nextInt(5) + 1; i++) {
        Comment comment = Comment.builder()
                .author(user)
                .post(post)
                .message("Example comment " + i)
                .build();

        this.commentRepository.save(comment);

        for(int j = 1; j <= rand.nextInt(5) + 1; j++) {
          Comment childComment = Comment.builder()
                  .author(user)
                  .post(post)
                  .parentComment(comment)
                  .message("Example childcomment " + i)
                  .build();
          this.commentRepository.save(childComment);

          for(int h = 1; h <= rand.nextInt(5) + 1; h++) {

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

  public List<Comment> retrieveCommentsByUser(User user) {
    return this.commentRepository.findByAuthor(user);
  }

  public List<Comment> retrieveChildComments(Comment comment) {
    return this.commentRepository.findByParentComment(comment);
  }

//  public List<Comment> retrieveChildCommentsById(Integer id) {
//    return this.commentRepository.findChildCommentsById(id);
//  }

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
}
