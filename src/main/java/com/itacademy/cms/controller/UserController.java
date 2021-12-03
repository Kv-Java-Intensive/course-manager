package com.itacademy.cms.controller;

import com.itacademy.cms.exeption.UserNotFoundException;
import com.itacademy.cms.mapper.UserMapper;
import com.itacademy.cms.model.dto.UserDto;
import com.itacademy.cms.service.impl.UserServiceImpl;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

  private final UserServiceImpl userServiceImpl;
  private final UserMapper userMapper;

  @Autowired
  public UserController(UserServiceImpl userServiceImpl, UserMapper userMapper) {
    this.userServiceImpl = userServiceImpl;
    this.userMapper = userMapper;
  }

  @GetMapping("/users")
  public List<UserDto> getAllUser() throws UserNotFoundException {
    return userMapper.userToUserDtoList(userServiceImpl.findAll());
  }

  @GetMapping("/users/{id}")
  public UserDto getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
    return userMapper.userToUserDto(userServiceImpl.findById(id));
  }

  @PostMapping("/users")
  public void saveUser(@RequestBody UserDto userDto) {
    userServiceImpl.saveUser(userDto);
  }

  @PutMapping("/users/{id}")
  public void updateUser(@RequestBody UserDto userDto, @PathVariable Long id)
      throws UserNotFoundException {
    userServiceImpl.updateUser(userDto, id);
  }
}