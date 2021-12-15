package com.itacademy.cms.service;


import com.itacademy.cms.exeption.CourseNotFoundException;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.CoursePostDto;
import com.itacademy.cms.model.dto.SearchCriteriaDto;
import java.util.List;

public interface CourseService {

  List<Course> getAllCourses() throws CourseNotFoundException;

  List<Course> findCourseBySearch(SearchCriteriaDto searchCriteriaDto);

  List<Course> addCourse(CoursePostDto coursePostDto, User user);

  Course getCourseById(Long id) throws CourseNotFoundException;

  void updateCourse(CoursePostDto coursePostDto, Long id);

  void deleteCourseById(Long id) throws CourseNotFoundException;
}
