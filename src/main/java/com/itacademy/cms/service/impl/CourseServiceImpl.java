package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.CourseNotFoundException;
import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.UserToCourse;
import com.itacademy.cms.model.dto.CoursePostDto;
import com.itacademy.cms.model.enums.CourseStatus;
import com.itacademy.cms.repository.CategoryRepository;
import com.itacademy.cms.repository.CertificateRepository;
import com.itacademy.cms.repository.CourseRepository;
import com.itacademy.cms.repository.TagRepository;
import com.itacademy.cms.service.CourseService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
  private final CourseRepository courseDAO;
  private final CategoryRepository categoryDAO;
  private final TagRepository tagDAO;
  private final CertificateRepository certificateDAO;
  private final MapStructMapper mapper;

  @Override
  public List<Course> getAllCourses() throws CourseNotFoundException {
    Optional<List<Course>> courses = Optional.ofNullable(courseDAO.findAll());
    if (courses.isEmpty()) {
      throw new CourseNotFoundException("Courses were not found");
    }
    return courses.get();
  }

  @Override
  public List<Course> getAllCoursesByCategory(String categoryName) throws CourseNotFoundException {
    Optional<List<Course>> courses = Optional.ofNullable(courseDAO.findCourseByCategory(
        categoryDAO.findByCategoryName(categoryName)));
    if (courses.isEmpty()) {
      throw new CourseNotFoundException("Courses were not found");
    }
    return courses.get();
  }

  @Override
  public List<Course> addCourse(CoursePostDto coursePostDto, User user) {
    Course course = mapper.courseDtoToCourse(coursePostDto);
    UserToCourse userToCourse = new UserToCourse();
    userToCourse.setCourse(course);
    userToCourse.setUser(user);
    userToCourse.setAuthor(true);
    userToCourse.setCourseStatus(CourseStatus.DEFAULT);
    courseDAO.save(course);
    return courseDAO.findAll();
  }

  @Override
  public List<Course> getAllCoursesByTag(String tagName) throws CourseNotFoundException {
    Optional<List<Course>> courses = Optional.ofNullable(courseDAO.findCourseByCourseTags(
        tagDAO.findByName(tagName)));
    if (courses.isEmpty()) {
      throw new CourseNotFoundException("Courses were not found");
    }
    return courses.get();
  }

  @Override
  public Course getCourseById(UUID id) throws CourseNotFoundException {
    Optional<Course> course = Optional.ofNullable(courseDAO.findCourseById(id));
    if (course.isEmpty()) {
      throw new CourseNotFoundException("This course was not found");
    }
    return course.get();
  }

  @Override
  public void updateCourse(CoursePostDto coursePostDto, UUID id) {
    Optional<Course> course = Optional.ofNullable(courseDAO.findCourseById(id));
    if (course.isEmpty()) {
      courseDAO.save(course.get());
    } else {
      Course courseOld = course.get();
      Course courseNew = mapper.courseDtoToCourse(coursePostDto);
      courseOld.setCourseName(courseNew.getCourseName());
      courseOld.setDescription(courseNew.getDescription());
      courseOld.setPrice(courseNew.getPrice());
      Category category = categoryDAO.findByCategoryName(courseNew.getCategory().getCategoryName());
      courseOld.setCategory(category);
      courseOld.setUpdateDate(courseNew.getUpdateDate());
      courseOld.setDuration(courseNew.getDuration());
      courseOld.setLanguage(courseNew.getLanguage());
      courseDAO.save(courseOld);
    }
  }

  @Override
  public void deleteCourseById(UUID id) throws CourseNotFoundException {
    Optional<Course> course = Optional.ofNullable(courseDAO.findCourseById(id));
    if (course.isEmpty()) {
      throw new CourseNotFoundException("This course was not found");
    } else {
      courseDAO.deleteById(id);
    }
  }
}
