package com.itacademy.cms.controller;

import com.itacademy.cms.model.User;
import com.itacademy.cms.service.UserService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {

  private final UserService userService;

  @PostMapping("/listUser/block")
  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public List<User> block(@RequestParam(name = "id") String uuid) {
    userService.blockUser(uuid);
    List<User> users = userService.findAll();
    return users;
  }
}