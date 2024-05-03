package com.coaltionbuilder.coalitionbuilder_be.model;

import com.coaltionbuilder.coalitionbuilder_be.response.CommentResponse;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;
import java.util.TimeZone;

public class PostDto {

  private Integer id;

  private String title;

  private String description;

  private UserDto author;

  private LocalDateTime creationDate;

  private List<CommentDto> comments;

  public PostDto(Integer id, String title, String description, UserDto author, LocalDateTime creationDate, List<CommentDto> comments) {
    this.id = id;
    this.title = title;
    this.description = description;
    this.author = author;
    this.creationDate = creationDate;
    this.comments = comments;
  }

    public PostDto() {
    }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getDescription() {
    return description;
  }

  public void setDescription(String description) {
    this.description = description;
  }

  public UserDto getAuthor() {
    return author;
  }

  public void setAuthor(UserDto author) {
    this.author = author;
  }

  public LocalDateTime getCreationDate() {
    return creationDate;
  }

  public void setCreationDate(LocalDateTime creationDate) {
    this.creationDate = creationDate;
  }

  public List<CommentDto> getComments() {
    return comments;
  }

  public void setComments(List<CommentDto> comments) {
    this.comments = comments;
  }
}
