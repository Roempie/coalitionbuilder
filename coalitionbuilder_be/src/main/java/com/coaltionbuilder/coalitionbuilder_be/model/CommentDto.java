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
public class CommentDto {

  private Integer id;

  private User author;

  private String message;

  private LocalDateTime creationDate;

  private PostDto post;

  private Integer parentCommentId;

  private List<CommentDto> childComments;

}
