package com.coaltionbuilder.coalitionbuilder_be.response;

import com.coaltionbuilder.coalitionbuilder_be.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentResponse implements Serializable {

  private Integer id;

  private String message;

  private LocalDateTime creationDate;

  private String author;

  private Integer numChildComments;

}
