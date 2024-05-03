package com.coaltionbuilder.coalitionbuilder_be.model;

import java.time.LocalDateTime;
import java.util.List;

public class CommentDto {

  private Integer id;

  private User author;

  private String message;

  private LocalDateTime creationDate;

  private PostDto post;

  private CommentDto parentComment;

  private List<CommentDto> childComments;

  public CommentDto(Integer id, User author, String message, LocalDateTime creationDate, PostDto post, CommentDto parentComment, List<CommentDto> childComments) {
    this.id = id;
    this.author = author;
    this.message = message;
    this.creationDate = creationDate;
    this.post = post;
    this.parentComment = parentComment;
    this.childComments = childComments;
  }

    public CommentDto() {
    }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public User getAuthor() {
    return author;
  }

  public void setAuthor(User author) {
    this.author = author;
  }

  public String getMessage() {
    return message;
  }

  public void setMessage(String message) {
    this.message = message;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public PostDto getPost() {
    return post;
  }

  public void setPost(PostDto post) {
    this.post = post;
  }

  public CommentDto getParentComment() {
    return parentComment;
  }

  public void setParentComment(CommentDto parentComment) {
    this.parentComment = parentComment;
  }

  public List<CommentDto> getChildComments() {
    return childComments;
  }

  public void setChildComments(List<CommentDto> childComments) {
    this.childComments = childComments;
  }
}
