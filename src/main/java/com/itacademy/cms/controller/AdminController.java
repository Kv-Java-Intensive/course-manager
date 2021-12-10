package com.itacademy.cms.controller;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.dto.UserDto;
import com.itacademy.cms.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequiredArgsConstructor
public class AdminController {

  @Autowired
  private final UserService userService;
  @Autowired
  private final MapStructMapper entityMapper;


  @GetMapping("/listUser")
  public List<UserDto> getAllUsers() {
    return userService.findAll().stream()
        .map(entityMapper::userToUserDto).collect(Collectors.toList());
  }
}
