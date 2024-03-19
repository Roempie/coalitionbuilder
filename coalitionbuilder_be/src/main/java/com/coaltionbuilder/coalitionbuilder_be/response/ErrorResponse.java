package com.coaltionbuilder.coalitionbuilder_be.response;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;

@Data
@Builder
public class ErrorResponse {

  private String message;

  private HttpStatus status;

  private LocalDateTime timestamp;

}
