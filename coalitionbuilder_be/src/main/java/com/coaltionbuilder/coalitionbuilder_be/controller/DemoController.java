package com.coaltionbuilder.coalitionbuilder_be.controller;

import com.coaltionbuilder.coalitionbuilder_be.exception.UserNotFoundException;
import com.coaltionbuilder.coalitionbuilder_be.model.User;
import com.coaltionbuilder.coalitionbuilder_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/demo-controller")
@RequiredArgsConstructor
public class DemoController {

  private final UserRepository userRepository;

  @GetMapping
  public ResponseEntity<String> sayHello(@RequestParam String email) {
    User user = this.userRepository.findByEmail(email).orElseThrow(() -> new UserNotFoundException("User with email " + email + " does not exist"));

    return ResponseEntity.ok(user.toString());
  }
}
