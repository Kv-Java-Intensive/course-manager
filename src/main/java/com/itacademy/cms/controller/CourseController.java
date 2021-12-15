package com.itacademy.cms.controller;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.dto.CourseGetDto;
import com.itacademy.cms.model.dto.CoursePostDto;
import com.itacademy.cms.service.CourseService;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courses")
@RequiredArgsConstructor
public class CourseController {

  private final CourseService courseService;
  private final MapStructMapper mapStructMapper;

  @GetMapping
  @PreAuthorize("permitAll()")
  public List<CourseGetDto> showAllCourses() {
    return mapStructMapper
        .courseAllToCourseGetDto(
            courseService.getAllCourses());
  }

  @GetMapping("/search/{category}")
  @PreAuthorize("permitAll()")
  public List<CourseGetDto> showAllCoursesByCategory(@PathVariable("category")
                                                         String categoryName) {
    return mapStructMapper.courseAllToCourseGetDto(
        courseService.getAllCoursesByCategory(categoryName));
  }

  @GetMapping("/search/{tag}")
  @PreAuthorize("permitAll()")
  public List<CourseGetDto> showAllCourseByTag(@PathVariable("tag")
                                                   String tagName) {
    return mapStructMapper.courseAllToCourseGetDto(courseService.getAllCoursesByTag(tagName));
  }

  @GetMapping("/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public CourseGetDto showCourseByUuid(@PathVariable("id") String uuid) {
    return mapStructMapper.courseToCourseGetDto(courseService.getCourseByUuid(uuid));
  }

  @PostMapping
  @PreAuthorize("permitAll()")
  public void addNewCourse(@RequestBody CoursePostDto coursePostDto) {
    courseService.saveCourse(coursePostDto);
  }

  @PutMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
  public void updateCourseById(@RequestBody CoursePostDto coursePostDto,
                               @PathVariable("id") Long id) {
    courseService.updateCourse(coursePostDto, id);
  }

  @Transactional
  @DeleteMapping("/{id}")
  @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
  public void deleteCourseById(@PathVariable("id") String uuid) {
    courseService.deleteCourseByUuid(uuid);
  }
}
