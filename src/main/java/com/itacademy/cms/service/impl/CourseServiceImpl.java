package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.EntityNotFoundException;
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
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {
  private final CourseRepository courseDao;
  private final CategoryRepository categoryDao;
  private final TagRepository tagDao;
  private final CertificateRepository certificateDao;
  private final MapStructMapper mapper;

  @Override
  public List<Course> getAllCourses() {
    Optional<List<Course>> courses = Optional.ofNullable(courseDao.findAll());
    if (courses.isEmpty()) {
      throw new EntityNotFoundException("Courses were not found");
    }
    return courses.get();
  }

  @Override
  public List<Course> getAllCoursesByCategory(String categoryName) {
    Optional<List<Course>> courses = Optional.ofNullable(courseDao.findCourseByCategory(
        categoryDao.findByCategoryName(categoryName)));
    if (courses.isEmpty()) {
      throw new EntityNotFoundException("Courses were not found");
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
    courseDao.save(course);
    return courseDao.findAll();
  }

  @Override
  public List<Course> getAllCoursesByTag(String tagName) {
    Optional<List<Course>> courses = Optional.ofNullable(courseDao.findCourseByCourseTags(
        tagDao.findByName(tagName)));
    if (courses.isEmpty()) {
      throw new EntityNotFoundException("Courses were not found");
    }
    return courses.get();
  }

  @Override
  public Course getCourseById(Long id) {
    Optional<Course> course = Optional.ofNullable(courseDao.findCourseById(id));
    if (course.isEmpty()) {
      throw new EntityNotFoundException("This course was not found");
    }
    return course.get();
  }

  @Override

  public void updateCourse(CoursePostDto coursePostDto, Long id) {
    Optional<Course> course = Optional.ofNullable(courseDao.findCourseById(id));

    if (course.isEmpty()) {
      courseDao.save(course.get());
    } else {
      Course courseOld = course.get();
      Course courseNew = mapper.courseDtoToCourse(coursePostDto);
      courseOld.setCourseName(courseNew.getCourseName());
      courseOld.setDescription(courseNew.getDescription());
      courseOld.setPrice(courseNew.getPrice());
      Category category = categoryDao.findByCategoryName(courseNew.getCategory().getCategoryName());
      courseOld.setCategory(category);
      courseOld.setUpdateDate(courseNew.getUpdateDate());
      courseOld.setDuration(courseNew.getDuration());
      courseOld.setLanguage(courseNew.getLanguage());
      courseDao.save(courseOld);
    }
  }

  @Override

  public void deleteCourseById(Long id) {
    Optional<Course> course = Optional.ofNullable(courseDao.findCourseById(id));

    if (course.isEmpty()) {
      throw new EntityNotFoundException("This course was not found");
    } else {
      courseDao.deleteById(id);
    }
  }
}
