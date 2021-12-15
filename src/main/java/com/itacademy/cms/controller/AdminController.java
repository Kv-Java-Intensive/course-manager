package com.itacademy.cms.controller;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.CourseGetDto;
import com.itacademy.cms.model.dto.UserGetDto;
import com.itacademy.cms.service.CourseService;
import com.itacademy.cms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/admin/")
public class AdminController {

  private final UserService userService;
  private final MapStructMapper mapper;
  private final CourseService courseService;

  @Autowired
  public AdminController(UserService userService, MapStructMapper mapper,
                         CourseService courseService) {
    this.userService = userService;
    this.mapper = mapper;
    this.courseService = courseService;
  }

  @GetMapping(value = "users/{id}")
  public UserGetDto getUserById(@PathVariable(name = "id") Long id) {
    User user = userService.findById(id);
    UserGetDto result = mapper.userToUserGetDto(user);
    return result;
  }

  @GetMapping("/courses/{id}")
  public CourseGetDto showCourseById(@PathVariable("id") Long id) {
    return mapper.courseToCourseGetDto(courseService.getCourseById(id));
  }
}