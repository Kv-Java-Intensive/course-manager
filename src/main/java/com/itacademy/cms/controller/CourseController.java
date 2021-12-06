package com.itacademy.cms.controller;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.CourseDto;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.dto.CourseGetDto;
import com.itacademy.cms.model.dto.CoursePostDto;
import com.itacademy.cms.service.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/course")
public class CourseController {

  @Autowired
  private CourseService courseService;

  //private Category category;
  @Autowired
  private MapStructMapper mapper;

  @GetMapping
  public ResponseEntity<List<CourseGetDto>> showAllCourses() {
    return new ResponseEntity<List<CourseGetDto>>(mapper
        .courseAllToCourseGetDto(courseService.getAllCourses()), HttpStatus.OK);
  }

  @GetMapping(value = "/search/{category}")
  public ResponseEntity<List<CourseGetDto>> showAllCoursesByCategory(@PathVariable("category")
                                                                           String categoryName) {
    return new ResponseEntity<List<CourseGetDto>>(mapper
        .courseAllToCourseGetDto(courseService.getAllCoursesByCategory(categoryName)),
        HttpStatus.OK);
  }

  @GetMapping("/search/{tag}")
  public ResponseEntity<List<CourseGetDto>> showAllCourseByTag(@PathVariable("tag")
                                                                     String tagName) {
    return new ResponseEntity<List<CourseGetDto>>(mapper
        .courseAllToCourseGetDto(courseService.getAllCoursesByTag(tagName)), HttpStatus.OK);
  }

  @GetMapping("/{id}")
  public ResponseEntity<CourseGetDto> showCourseById(@PathVariable("id") Long id) {
    return new ResponseEntity<>(mapper.courseToCourseGetDto(courseService.getCourseById(id)),
        HttpStatus.OK);
  }

  @PostMapping
  public ResponseEntity<Void> addNewCourse(@AuthenticationPrincipal User user,
                                           @RequestBody CoursePostDto coursePostDto) {
    courseService.addCourse(coursePostDto);
    return new ResponseEntity<>(HttpStatus.CREATED);
  }

  @PutMapping("/{id}")
  public ResponseEntity<Course> updateCourseById(@RequestBody CoursePostDto coursePostDto,
                                 @PathVariable("id") Long id) {
    courseService.updateCourse(coursePostDto, id);
    return new ResponseEntity<>(mapper.courseToCourseGetDto(courseService.getCourseById(id)),
        HttpStatus.OK);
  }

  @DeleteMapping("/{id}")
  public ResponseEntity<Void> deleteCourseById(@PathVariable("id") Long id) {
    courseService.deleteCourseById(id);
    return new ResponseEntity<>(HttpStatus.OK);
  }
}
