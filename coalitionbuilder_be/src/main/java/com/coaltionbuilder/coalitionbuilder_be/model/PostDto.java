package com.coaltionbuilder.coalitionbuilder_be.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;
import java.util.List;

@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDto {

  private Integer id;

  private String title;

  private String description;

  private String author;

  private LocalDateTime creationDate;

  private List<CommentDto> comments;

}
