package com.itacademy.cms.controller;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.dto.CourseGetDto;
import com.itacademy.cms.model.dto.CoursePostDto;
import com.itacademy.cms.service.CourseService;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/course")
@RequiredArgsConstructor
public class CourseController {

  private final CourseService courseService;
  private final MapStructMapper mapStructMapper;


  @GetMapping
  public List<CourseGetDto> showAllCourses() {
    return mapStructMapper.courseAllToCourseGetDto(courseService.getAllCourses());
  }

  @GetMapping(value = "/search/{category}")
  public List<CourseGetDto> showAllCoursesByCategory(@PathVariable("category")
                                                         String categoryName) {
    return mapStructMapper.courseAllToCourseGetDto(
        courseService.getAllCoursesByCategory(categoryName));
  }

  @GetMapping("/search/{tag}")
  public List<CourseGetDto> showAllCourseByTag(@PathVariable("tag")
                                                   String tagName) {
    return mapStructMapper.courseAllToCourseGetDto(courseService.getAllCoursesByTag(tagName));
  }

  @GetMapping("/{id}")
  public CourseGetDto showCourseById(@PathVariable("id") Long id) {
    return mapStructMapper.courseToCourseGetDto(courseService.getCourseById(id));
  }

//  @PostMapping
//  public void addNewCourse(@AuthenticationPrincipal User user,
//                           @RequestBody CoursePostDto coursePostDto) {
//    courseService.addCourse(coursePostDto, user);
//  }

  @PutMapping("/{id}")
  public void updateCourseById(@RequestBody CoursePostDto coursePostDto,
                               @PathVariable("id") Long id) {
    courseService.updateCourse(coursePostDto, id);
  }

  @DeleteMapping("/{id}")
  public void deleteCourseById(@PathVariable("id") Long id) {
    courseService.deleteCourseById(id);
  }
}
