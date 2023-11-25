package com.coaltionbuilder.coalitionbuilder_be.mapper;

import com.coaltionbuilder.coalitionbuilder_be.model.Post;
import com.coaltionbuilder.coalitionbuilder_be.model.PostDto;
import org.springframework.stereotype.Component;

import java.util.function.Function;

@Component
public class PostDTOMapper implements Function<Post, PostDto> {

  @Override
  public PostDto apply(Post post) {
    return new PostDto(
            post.getId(),
            post.getTitle(),
            post.getDescription()
    );
  }
}
