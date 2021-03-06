package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.exeption.ParameterMissingException;
import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.dto.CoursePostDto;
import com.itacademy.cms.model.dto.SearchCriteriaDto;
import com.itacademy.cms.repository.CategoryRepository;
import com.itacademy.cms.repository.CourseRepository;
import com.itacademy.cms.repository.specification.CourseSpecificationsBuilder;
import com.itacademy.cms.service.CourseService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CourseServiceImpl implements CourseService {

  private static final Logger logger = LoggerFactory.getLogger(CourseServiceImpl.class);

  private final CourseRepository courseDao;
  private final CategoryRepository categoryDao;
  private final MapStructMapper mapper;

  @Override
  public List<Course> getAllCourses() {
    logger.info("GET ALL COURSES");
    Optional<List<Course>> courses = Optional.ofNullable(courseDao.findAll());
    if (courses.isEmpty()) {
      logger.error("COURSES LIST IS EMPTY");
      throw new EntityNotFoundException("Courses were not found");
    }
    return courses.get();
  }

  @Override
  public List<Course> findCourseBySearch(SearchCriteriaDto searchCriteriaDto) {
    CourseSpecificationsBuilder builder = new CourseSpecificationsBuilder();
    for (int i = 0; i < searchCriteriaDto.getCriteriaList().size(); i++) {
      builder.with(searchCriteriaDto.getCriteriaList().get(i).getKey(),
          searchCriteriaDto.getCriteriaList().get(i).getOperation(),
          searchCriteriaDto.getCriteriaList().get(i).getValue());
    }
    Specification<Course> spec = builder.build();
    return courseDao.findAll(spec);
  }

  @Override
  public Course saveCourse(CoursePostDto coursePostDtoDto) {
    logger.info("CREATE NEW COURSE");
    return courseDao.save(mapper.courseDtoToCourse(coursePostDtoDto));
  }

  @Override
  public Course getCourseByUuid(String uuid) {
    logger.info("GET COURSE WITH ID = {}", uuid);
    Optional<Course> course = courseDao.findCourseByUuid(uuid);
    return course.orElseThrow(
        () -> new EntityNotFoundException("Course with uuid " + uuid + " not found!"));
  }

  @Override
  public void updateCourse(CoursePostDto coursePostDto, Long id) {
    logger.info("UPDATE COURSE WITH ID = {}", id);
    Optional<Course> course = Optional.ofNullable(courseDao.findCourseById(id));
    Course courseNew = mapper.courseDtoToCourse(coursePostDto);
    if (course.isEmpty()) {
      logger.info("COURSE WITH ID = {} DOES NOT EXIST - CREATE NEW", id);
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
      logger.error("UUID IS EMPTY");
      throw new ParameterMissingException("Course uuid is missing");
    } else if (courseDao.existsByUuid(uuid)) {
      logger.info("REMOVE COURSE WITH ID = {}", uuid);
      courseDao.deleteByUuid(uuid);
      return;
    }
    logger.error("COURSE WITH ID = {} DOES NOT EXIST", uuid);
    throw new EntityNotFoundException("Category with uuid " + uuid + " not found!");
  }
}
