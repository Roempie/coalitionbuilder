package com.coaltionbuilder.coalitionbuilder_be.mapper;

import com.coaltionbuilder.coalitionbuilder_be.model.Comment;
import com.coaltionbuilder.coalitionbuilder_be.model.CommentDto;
import com.coaltionbuilder.coalitionbuilder_be.model.Post;
import com.coaltionbuilder.coalitionbuilder_be.model.PostDto;
import com.coaltionbuilder.coalitionbuilder_be.repository.UserRepository;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class DTOMapper {

  private final UserRepository userRepository;

  public DTOMapper(UserRepository userRepository) {
    this.userRepository = userRepository;
  }

  public static class MapPost implements Function<PostDto, Post> {

    @Override
    public Post apply(PostDto postDto) {
      Post post = new Post();
      post.setTitle(postDto.getTitle());
      post.setDescription(postDto.getDescription());

      return post;
    }
  }

  public static class MapComment implements Function<CommentDto, Comment> {

    @Override
    public Comment apply(CommentDto commentDto) {
      Comment comment = new Comment();
      comment.setMessage(commentDto.getMessage());

      return comment;
    }
  }

}
