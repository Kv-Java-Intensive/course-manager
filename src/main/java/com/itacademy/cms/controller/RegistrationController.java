package com.itacademy.cms.controller;

import com.itacademy.cms.model.dto.UserDto;
import com.itacademy.cms.model.enums.Role;
import com.itacademy.cms.service.UserService;
import javax.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class RegistrationController {
  private final UserService userService;

  @GetMapping("/users/register")
  public String registrationPage(Model model) {
    model.addAttribute("user", new UserDto());
    return "...";
  }

  @PostMapping("/users/register")
  public String collectData(@Valid @ModelAttribute("user") UserDto user,
                            BindingResult bindingResult) {
    if (bindingResult.hasErrors()) {
      return "...";
    }

    user.setFirstName("nick");
    user.setLastName("kose");
    user.setEmail("nick@gmail.com");
    user.setPassword("qwerty");
    user.setActive(true);

    user.setRole(Role.USER);
    userService.saveUser(user);
    return "/users/{id}/profile";
  }

}
