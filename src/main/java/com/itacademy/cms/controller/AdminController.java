package com.itacademy.cms.controller;

import com.itacademy.cms.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class AdminController {

  private final UserService userService;

  @PostMapping("/listUser/block")
  //  @PreAuthorize("hasRole('ROLE_ADMIN')")
  public void block(@RequestParam(name = "id") String uuid,
                    @RequestParam(name = "active") boolean active
  ) {
    userService.blockUser(active, uuid);
  }
}