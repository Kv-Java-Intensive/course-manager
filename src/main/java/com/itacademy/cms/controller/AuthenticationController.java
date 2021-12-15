package com.itacademy.cms.controller;

import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.AuthenticationRequestDto;
import com.itacademy.cms.model.enums.Role;
import com.itacademy.cms.security.jwt.JwtTokenProvider;
import com.itacademy.cms.service.UserService;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.springframework.security.core.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/login")
public class AuthenticationController {

  private final AuthenticationManager authenticationManager;

  private final JwtTokenProvider jwtTokenProvider;

  private final UserService userService;

  @Autowired
  public AuthenticationController(AuthenticationManager authenticationManager,
                                  JwtTokenProvider jwtTokenProvider, UserService userService) {
    this.authenticationManager = authenticationManager;
    this.jwtTokenProvider = jwtTokenProvider;
    this.userService = userService;
  }

  @PostMapping()
  public ResponseEntity login(@RequestBody AuthenticationRequestDto requestDto) {
    try {
      String username = requestDto.getUsername();
      authenticationManager.authenticate(
          new UsernamePasswordAuthenticationToken(username, requestDto.getPassword()));
      User user = userService.findUserByEmail(username);
      if (user == null) {
        throw new UsernameNotFoundException("User with username: " + username + " not found");
      }
      List<Role> roles = new ArrayList<>();
      roles.add(user.getRole());
      String token = jwtTokenProvider.createToken(username, roles);
      Map<Object, Object> response = new HashMap<>();
      response.put("username", username);
      response.put("token", token);
      return ResponseEntity.ok(response);
    } catch (AuthenticationException e) {
      throw new BadCredentialsException("Invalid username or password");
    }
  }
}
