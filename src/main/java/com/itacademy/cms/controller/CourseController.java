package com.itacademy.cms.controller;

import com.itacademy.cms.model.dto.CourseDto;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.service.CourseService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
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

  @GetMapping
  public List<Course> showAllCourses() {
    return courseService.getAllCourses();
  }

  @GetMapping(value = "/search/{category}")
  public List<Course> showAllCoursesByCategory(@PathVariable("category") String categoryName) {
    return courseService.getAllCoursesByCategory(categoryName);
  }

  @GetMapping("/search/{tag}")
  public List<Course> showAllCourseByTag(@PathVariable("tag") String tagName) {
    return courseService.getAllCoursesByTag(tagName);
  }

  @GetMapping("/{id}")
  public Course showCourseById(@PathVariable("id") Long id) {
    return courseService.getCourseById(id);
  }

  @PostMapping
  public List<Course> addNewCourse(@RequestBody CourseDto courseDto) {
    courseService.addCourse(courseDto);
    return courseService.getAllCourses();
  }

  @PutMapping("/{id}")
  public Course updateCourseById(@RequestBody CourseDto courseDto, @PathVariable("id") Long id) {
    courseService.updateCourse(courseDto, id);
    return courseService.getCourseById(id);
  }

  @DeleteMapping("/{id}")
  public List<Course> deleteCourseById(@PathVariable("id") Long id) {
    courseService.deleteCourseById(id);
    return courseService.getAllCourses();
  }
}
