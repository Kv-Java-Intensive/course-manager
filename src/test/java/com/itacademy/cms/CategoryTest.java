package com.itacademy.cms;


import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.tuple;
import static org.junit.jupiter.api.Assertions.assertEquals;

import com.itacademy.cms.mapper.CategoryMapper;
import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.Group;
import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.UserToCourse;
import com.itacademy.cms.model.dto.CategoryDto;
import com.itacademy.cms.model.enums.Language;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.mapstruct.factory.Mappers;

public class CategoryTest {


  //  private static final String DATE_FORMAT = "dd-MM-yyyy";
//  private static final String DATE_FORMAT_HOUR = "HH:mm:ss";
  CategoryMapper categoryMapper = Mappers.getMapper(CategoryMapper.class);

  @Test
  public void shouldMapCategoryToDto() {
    UserToCourse userCourses = new UserToCourse();
    Tag courseTags = new Tag();
    Group groups = new Group();
    Module modules = new Module();
    Certificate certificate = new Certificate();
    Course course = new Course("Java",
        "Java Core",
        20D,
        null,
        "2021-12-03",
        "12:00:00",
        Language.ENGLISH,
        Collections.singletonList(userCourses),
        Collections.singletonList(courseTags),
        Collections.singletonList(groups),
        Collections.singletonList(modules),
        certificate
    );
    Category category = new Category(Collections.singletonList(course),
        "Programming");
    course.setCategory(category);

    CategoryDto categoryDto = categoryMapper.categoryToDto(category);

    List<CourseDto> courses = categoryDto.getCourses();
//    assertThat(courses).hasSize(1);
    assertThat(courses.get(0).getCourseName().isEqualTo("Java"));
    assertThat(courses.get(0).getDescription().isEqualTo("Java Core"));
    assertThat(courses.get(0).getPrice().isEqualTo(20D));
    assertThat(courses.get(0).getCategory.isEqualTo(category));
    assertThat(courses.get(0).getUpdateDate.isEqualTo("2021-12-03"));
    assertThat(courses.get(0).getDuration.isEqualTo("12:00:00"));
    assertThat(courses.get(0).getLanguage.isEqualTo(Language.ENGLISH);
    assertThat(courses.get(0).getCertificate.isEqualTo(certificate));

    assertEquals(categoryDto.getCategory(), category.getCategory());
  }

  @Test
  public void shouldMapDtoToCategory() {
    UserToCourseDto userCoursesDto = new UserToCourseDto();
    TagDto courseTagsDto = new TagDto();
    GroupDto groupsDto = new GroupDto();
    ModuleDto modulesDto = new ModuleDto();
    CertificateDto certificateDto = new CertificateDto();
    CourseDto courseDto = new CourseDto("Java",
        "Java Core",
        20D,
        null,
        "2021-12-03",
        "12:00:00",
        Language.ENGLISH,
        null,
        null,
        null,
        null,
        certificateDto
    );
    CategoryDto categoryDto = new CategoryDto();
    categoryDto.setCategory("Programming");
    categoryDto.courses = new ArrayList<>(Collections.singleton(courseDto));
    courseDto.setCategory(categoryDto);
    courseDto.userCourses = new ArrayList<>(Collections.singleton(userCoursesDto));
    courseDto.courseTags = new ArrayList<>(Collections.singleton(courseTagsDto));
    courseDto.groups = new ArrayList<>(Collections.singleton(groupsDto));
    courseDto.modules = new ArrayList<>(Collections.singleton(modulesDto));


    Category category = categoryMapper.dtoToCategory(categoryDto);


    assertThat(category.getCategory()).isEqualTo("Programming");
    assertThat(category.getCourses())
        .extracting("courseName", "description", "price", "category", "updateDate", "duration"
            "language", "certificate")
        .containsExactly(tuple("Java Core",
            20D,
            categoryDto,
            "2021-12-03",
            "12:00:00",
            Language.ENGLISH,
            certificateDto));

    assertEquals(categoryDto.getCategory(), category.getCategory());

  }
}
