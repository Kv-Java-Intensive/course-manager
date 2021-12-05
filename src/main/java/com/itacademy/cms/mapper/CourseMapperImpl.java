package com.itacademy.cms.mapper;

import static com.itacademy.cms.model.enums.CourseStatus.DEFAULT;

import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.UserToCourse;
import com.itacademy.cms.model.dto.CourseDto;
import com.itacademy.cms.model.enums.CourseStatus;
import com.itacademy.cms.model.enums.Language;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseMapperImpl implements CourseMapper {

  public CourseMapperImpl() {
  }

  @Override
  public Course courseDtoToCourse(CourseDto courseDto) {
    if (courseDto == null) {
      return null;
    } else {
      Course course = new Course();
      course.setLanguage(Language.getLanguageByString(courseDto.getLanguage()));
      CategoryMapperImpl categoryMapper = new CategoryMapperImpl();
      course.setCategory(categoryMapper.categoryDtoToCategory(courseDto.getCategoryDto()));
      course.setCertificate(null);
      course.setModules(null);
      course.setGroups(null);
      CertificateMapperImpl certificateMapper = new CertificateMapperImpl();
      course.setCertificate(certificateMapper
          .certificteDtoToCategory(courseDto.getCertificateDto()));
      TagMapperImpl tagMapper = new TagMapperImpl();
      course.setCourseTags(tagMapper.tagDtoToTag(courseDto.getTagDto()));
      UserToCourse userToCourse = new UserToCourse();
      userToCourse.setCourse(course);
      UserMapperImpl userMapper = new UserMapperImpl();
      User user = userMapper.userDtoToUser(courseDto.getUserDto());
      userToCourse.setUser(user);
      userToCourse.setCourseStatus(CourseStatus.DEFAULT);
      userToCourse.setAuthor(true);
      List<UserToCourse> userToCourseList = new ArrayList<UserToCourse>();
      userToCourseList.add(userToCourse);
      course.setUserCourses(userToCourseList);
      return course;
    }
  }

  @Override
  public CourseDto CourseDtoResponse(Course course) {
    return null;
  }
}
