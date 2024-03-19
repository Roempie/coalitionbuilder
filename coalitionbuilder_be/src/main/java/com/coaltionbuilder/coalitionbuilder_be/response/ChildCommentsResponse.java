package com.coaltionbuilder.coalitionbuilder_be.response;

import com.coaltionbuilder.coalitionbuilder_be.model.Comment;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ChildCommentsResponse {

  private Comment comment;

  private List<Comment> childComments;

}
