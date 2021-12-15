package com.itacademy.cms.controller;

import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.UserDto;
import com.itacademy.cms.model.enums.Role;
import com.itacademy.cms.service.UserService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

  private final UserService userService;
  private PasswordEncoder passwordEncoder;

  @Autowired
  public RegistrationController(UserService userService,
                                PasswordEncoder passwordEncoder) {
    this.userService = userService;
    this.passwordEncoder = passwordEncoder;
  }

  @PostMapping("/user/register")
  @PreAuthorize("permitAll()")
  public String createNewUser(@Valid @RequestBody UserDto dto) {

    User user = new User();
    user.setFirstName(dto.getFirstName());
    user.setLastName(dto.getLastName());
    user.setPassword(passwordEncoder.encode(dto.getPassword()));
    user.setActive(true);
    user.setEmail(dto.getEmail());
    user.setRole(Role.valueOf(dto.getRole().name()));

    userService.saveUser(user);
    return user.getUuid();
  }

}
