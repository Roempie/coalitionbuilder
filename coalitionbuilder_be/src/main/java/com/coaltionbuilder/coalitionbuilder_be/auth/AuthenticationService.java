package com.coaltionbuilder.coalitionbuilder_be.auth;

import com.coaltionbuilder.coalitionbuilder_be.config.JwtService;
import com.coaltionbuilder.coalitionbuilder_be.exception.UserAlreadyExistException;
import com.coaltionbuilder.coalitionbuilder_be.model.Role;
import com.coaltionbuilder.coalitionbuilder_be.model.User;
import com.coaltionbuilder.coalitionbuilder_be.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationService {

  private final UserRepository repository;

  private final PasswordEncoder passwordEncoder;

  private final JwtService jwtService;

  private final AuthenticationManager authenticationManager;

  public AuthenticationResponse register(RegisterRequest request) {

    repository.findByEmail(request.getEmail()).ifPresent(user -> {
      throw new UserAlreadyExistException("User with email " + request.getEmail() + " already exists");
    });

    var user = User.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .password(passwordEncoder.encode(request.getPassword()))
            .role(Role.USER)
            .build();
    repository.save(user);
    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
            .userDetails(user)
            .token(jwtToken)
            .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    var user = repository.findByEmail(request.getEmail()).orElseThrow(() -> new UserAlreadyExistException("User with email " + request.getEmail() + " does not exist"));
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    request.getEmail(),
                    request.getPassword()
            )
    );

    var jwtToken = jwtService.generateToken(user);
    return AuthenticationResponse.builder()
            .token(jwtToken)
            .userDetails(user)
            .build();
  }
}
