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
public class PostResponse implements Serializable {

  private Integer id;

  private String title;

  private String description;

  private String author;

  private LocalDateTime creationDate;

  private List<Comment> rootComments;

}
