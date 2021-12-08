package com.itacademy.cms.controller;


import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.dto.UserDto;
import com.itacademy.cms.service.UserService;
import java.util.List;
import java.util.UUID;
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

  private final MapStructMapper entityMapper;


  @GetMapping("/users")
  public List<UserDto> getAllUser() {
    return userService.findAll().stream()
        .map(entityMapper::userToUserDto).collect(Collectors.toList());
  }

  @GetMapping("/users/{id}")
  public UserDto getUserById(@PathVariable("id") UUID id) {
    return entityMapper.userToUserDto(userService.findById(id));
  }

  @PostMapping("/users")
  public void saveUser(@RequestBody UserDto userDto) {
    userService.saveUser(userDto);
  }

  @PutMapping("/users/{id}")
  public void updateUser(@RequestBody UserDto userDto, @PathVariable UUID id) {
    userService.updateUser(userDto, id);
  }

  @DeleteMapping("/users/{id}")
  public void deleteUser(@PathVariable("id") UUID id) {
    userService.deleteUserById(id);
  }
}