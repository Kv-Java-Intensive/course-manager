package com.itacademy.cms.controller;

import com.itacademy.cms.exeption.CourseNotFoundException;
import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.dto.CourseGetDto;
import com.itacademy.cms.model.dto.CoursePostDto;
import com.itacademy.cms.model.dto.SearchCriteriaDto;
import com.itacademy.cms.service.CourseService;
import java.util.List;
import java.util.stream.Collectors;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {

  private final CourseService courseService;
  private final MapStructMapper mapStructMapper;


  @GetMapping("/courses")
  public List<CourseGetDto> showAllCourses() throws CourseNotFoundException {
    return mapStructMapper.courseAllToCourseGetDto(courseService.getAllCourses());
  }

  @PostMapping("/courses/search")
  public List<CourseGetDto> getCoursesBySearch(
      @RequestBody SearchCriteriaDto searchCriteriaDto) {
    return courseService.findCourseBySearch(searchCriteriaDto).stream()
        .map(mapStructMapper::courseToCourseGetDto).collect(Collectors.toList());
  }

  @GetMapping("/courses/{id}")
  public CourseGetDto showCourseById(@PathVariable("id") Long id) throws CourseNotFoundException {
    return mapStructMapper.courseToCourseGetDto(courseService.getCourseById(id));
  }

  @PostMapping
  public void addNewCourse(@AuthenticationPrincipal User user,
                           @RequestBody CoursePostDto coursePostDto) {
    courseService.addCourse(coursePostDto, user);
  }

  @PutMapping("/courses/{id}")
  public void updateCourseById(@RequestBody CoursePostDto coursePostDto,
                               @PathVariable("id") Long id) {
    courseService.updateCourse(coursePostDto, id);
  }

  @DeleteMapping("/courses/{id}")
  public void deleteCourseById(@PathVariable("id") Long id) throws CourseNotFoundException {
    courseService.deleteCourseById(id);
  }
}
