package com.itacademy.cms.controller;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.dto.CourseGetDto;
import com.itacademy.cms.model.dto.CoursePostDto;
import com.itacademy.cms.model.dto.SearchCriteriaDto;
import com.itacademy.cms.service.CourseService;
import java.util.List;
import java.util.stream.Collectors;
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
@RequestMapping("/api")
@RequiredArgsConstructor
public class CourseController {

  private final CourseService courseService;
  private final MapStructMapper mapStructMapper;

  @GetMapping("/courses")
  @PreAuthorize("permitAll()")
  public List<CourseGetDto> showAllCourses() {
    return mapStructMapper
        .courseAllToCourseGetDto(
            courseService.getAllCourses());
  }

  @PostMapping("/courses/search")
  public List<CourseGetDto> getCoursesBySearch(
      @RequestBody SearchCriteriaDto searchCriteriaDto) {
    return courseService.findCourseBySearch(searchCriteriaDto).stream()
        .map(mapStructMapper::courseToCourseGetDto).collect(Collectors.toList());
  }

  @GetMapping("/courses/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public CourseGetDto showCourseByUuid(@PathVariable("id") String uuid) {
    return mapStructMapper.courseToCourseGetDto(courseService.getCourseByUuid(uuid));
  }

  @PostMapping("/courses")
  @PreAuthorize("permitAll()")
  public void addNewCourse(@RequestBody CoursePostDto coursePostDto) {
    courseService.saveCourse(coursePostDto);
  }

  @PutMapping("/courses/{id}")
  @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
  public void updateCourseById(@RequestBody CoursePostDto coursePostDto,
                               @PathVariable("id") Long id) {
    courseService.updateCourse(coursePostDto, id);
  }

  @Transactional
  @DeleteMapping("/courses/{id}")
  @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
  public void deleteCourseByUuid(@PathVariable("id") String uuid) {
    courseService.deleteCourseByUuid(uuid);
  }
}
