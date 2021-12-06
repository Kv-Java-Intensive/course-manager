package com.itacademy.cms.controller;

import com.itacademy.cms.exeption.UserNotFoundException;
import com.itacademy.cms.mapper.EntityMapper;
import com.itacademy.cms.model.dto.UserDto;
import com.itacademy.cms.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final EntityMapper entityMapper;

  @GetMapping("/users")
  public List<UserDto> getAllUser() throws UserNotFoundException {
    return userService.findAll().stream()
        .map(entityMapper::userToUserDto).collect(Collectors.toList());
  }

  @GetMapping("/users/{id}")
  public UserDto getUserById(@PathVariable("id") Long id) throws UserNotFoundException {
    return entityMapper.userToUserDto(userService.findById(id));
  }

  @PostMapping("/users")
  public void saveUser(@RequestBody UserDto userDto) {
    userService.saveUser(userDto);
  }

  @PutMapping("/users/{id}")
  public void updateUser(@RequestBody UserDto userDto, @PathVariable Long id)
      throws UserNotFoundException {
    userService.updateUser(userDto, id);
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable("id") Long id) throws UserNotFoundException {
    userService.deleteUserById(id);
  }
}