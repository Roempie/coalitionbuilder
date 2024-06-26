package com.coaltionbuilder.coalitionbuilder_be.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {

  private String message;

  private Integer parentCommentId;

  private Integer postId;
}
