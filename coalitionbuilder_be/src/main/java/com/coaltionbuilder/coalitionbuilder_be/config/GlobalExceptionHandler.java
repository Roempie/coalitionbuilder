package com.coaltionbuilder.coalitionbuilder_be.config;

import com.coaltionbuilder.coalitionbuilder_be.exception.CommentNotFoundException;
import com.coaltionbuilder.coalitionbuilder_be.exception.UserAlreadyExistException;
import com.coaltionbuilder.coalitionbuilder_be.exception.UserNotFoundException;
import com.coaltionbuilder.coalitionbuilder_be.response.ErrorResponse;
import com.coaltionbuilder.coalitionbuilder_be.exception.PostNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

  @ExceptionHandler({PostNotFoundException.class, CommentNotFoundException.class, UserNotFoundException.class})
  public ResponseEntity<ErrorResponse> handleEntityNotFound(Exception ex) {

    return ResponseEntity.status(HttpStatus.NOT_FOUND).body(
            ErrorResponse.builder()
                    .message(ex.getMessage())
                    .status(HttpStatus.NOT_FOUND)
                    .timestamp(LocalDateTime.now())
                    .build()
    );
  }

  @ExceptionHandler(UserAlreadyExistException.class)
  public ResponseEntity<ErrorResponse> handeUserAlreadyExistsException(Exception ex) {

    return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY).body(
            ErrorResponse.builder()
                    .message(ex.getMessage())
                    .status(HttpStatus.UNPROCESSABLE_ENTITY)
                    .timestamp(LocalDateTime.now())
                    .build()
    );
  }
}
