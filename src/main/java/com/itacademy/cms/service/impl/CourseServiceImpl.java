package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.exeption.ParameterMissingException;
import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.dto.CoursePostDto;
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
  public Course saveCourse(CoursePostDto coursePostDtoDto) {
    return courseDao.save(mapper.courseDtoToCourse(coursePostDtoDto));
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
  public Course getCourseByUuid(String uuid) {
    Optional<Course> course = courseDao.findCourseByUuid(uuid);
    return course.orElseThrow(
        () -> new EntityNotFoundException("Course with uuid " + uuid + " not found!"));
  }

  @Override

  public void updateCourse(CoursePostDto coursePostDto, Long id) {
    Optional<Course> course = Optional.ofNullable(courseDao.findCourseById(id));
    Course courseNew = mapper.courseDtoToCourse(coursePostDto);
    if (course.isEmpty()) {
      courseDao.save(courseNew);
    } else {
      Course courseOld = course.get();
      courseOld.setCourseName(courseNew.getCourseName());
      courseOld.setDescription(courseNew.getDescription());
      courseOld.setPrice(courseNew.getPrice());
      String categoryName =
          courseNew.getCategory() == null ? null : courseNew.getCategory().getCategoryName();
      Category category =
          categoryName == null ? null : categoryDao.findByCategoryName(categoryName);
      courseOld.setCategory(category);
      courseOld.setUpdateDate(courseNew.getUpdateDate());
      courseOld.setDuration(courseNew.getDuration());
      courseOld.setLanguage(courseNew.getLanguage());
      courseDao.save(courseOld);
    }
  }

  @Override

  public void deleteCourseByUuid(String uuid) {
    if (uuid == null) {
      throw new ParameterMissingException("Course uuid is missing");
    } else if (courseDao.existsByUuid(uuid)) {
      courseDao.deleteByUuid(uuid);
      return;
    }
    throw new EntityNotFoundException("Category with uuid " + uuid + " not found!");
  }
}
