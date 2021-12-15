package com.itacademy.cms.controller;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.dto.UserDto;
import com.itacademy.cms.service.UserService;
import java.util.List;
import java.util.stream.Collectors;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class UserController {

  private final UserService userService;
  private final MapStructMapper entityMapper;

  @GetMapping("/users")
  @PreAuthorize("hasAuthority('ADMIN')")
  public List<UserDto> getAllUser() {
    return userService.findAll().stream()
        .map(entityMapper::userToUserDto).collect(Collectors.toList());
  }

  @GetMapping("/users/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public UserDto getUserByUuid(@PathVariable("id") String uuid) {
    return entityMapper.userToUserDto(userService.findByUuid(uuid));
  }

  @PutMapping("/users/{id}")
  @PreAuthorize("isAuthenticated()")
  public void updateUser(@RequestBody UserDto userDto, @PathVariable("id") String uuid) {
    userService.updateUser(userDto, uuid);
  }

  @Transactional
  @DeleteMapping("/users/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public void deleteUser(@PathVariable("id") String uuid) {
    userService.deleteUserByUuid(uuid);
  }
}