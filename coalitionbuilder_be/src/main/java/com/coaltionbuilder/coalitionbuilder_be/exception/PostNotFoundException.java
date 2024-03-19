package com.coaltionbuilder.coalitionbuilder_be.exception;

public class PostNotFoundException extends RuntimeException{
  public PostNotFoundException(String message) {
    super(message);
  }
}
