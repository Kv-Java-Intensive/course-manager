package com.itacademy.cms.controller;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.UserDto;
import com.itacademy.cms.service.UserService;
import java.security.InvalidParameterException;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
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

  @GetMapping("/findUserByName")
  public User findByName(@RequestParam("name") String name, Model model) {
    if (name == null) {
      throw new InvalidParameterException();
    }
    User user = userService.findByFirstName(name);
    model.addAttribute("user", user);
    return user;
  }

  @GetMapping("/findUserByLastName")
  public User findByLastName(@RequestParam("lastName") String lastName, Model model) {
    if (lastName == null) {
      throw new InvalidParameterException();
    }
    User user = userService.findByLastName(lastName);
    model.addAttribute("user", user);
    return user;
  }

  @GetMapping("/findUserByEmail")
  public User findByEmail(@RequestParam("email") String email, Model model) {
    if (email == null) {
      throw new InvalidParameterException();
    }
    User user = userService.findByEmail(email);
    model.addAttribute("user", user);
    return user;
  }

  @PostMapping("/listUser/block")
  public List<User> block(@RequestParam(name = "id") Long id,
                          @RequestParam(name = "active") boolean active,
                          Model model) {

    userService.changeActive(id, active);

    List<User> users = userService.findAll();
    model.addAttribute("people", users);
    return users;
  }
}