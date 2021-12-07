package com.itacademy.cms.mapper;

import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.Course;
import com.itacademy.cms.model.Group;
import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.CategoryDto;
import com.itacademy.cms.model.dto.CertificateDto;
import com.itacademy.cms.model.dto.CourseGetDto;
import com.itacademy.cms.model.dto.CoursePostDto;
import com.itacademy.cms.model.dto.GroupDto;
import com.itacademy.cms.model.dto.ModuleDto;
import com.itacademy.cms.model.dto.TagDto;
import com.itacademy.cms.model.dto.UserDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface MapStructMapper {
  MapStructMapper MAPPER = Mappers.getMapper(MapStructMapper.class);

  Course courseDtoToCourse(CoursePostDto coursePostDto);

  CourseGetDto courseToCourseGetDto(Course course);

  List<CourseGetDto> courseAllToCourseGetDto(List<Course> courseList);

  Certificate certificateDtoToCertificate(CertificateDto certificateDto);

  CertificateDto certificateToCertificateDto(Certificate Certificate);

  List<CertificateDto> certificateToCertificateDtoList(List<Certificate> certificateList);

  User userDtoToUser(UserDto userDto);

  UserDto userToUserDto(User user);

  Category categoryDtoToCategory(CategoryDto categoryDto);

  CategoryDto categoryToCategoryDto(Category category);

  Module moduleDtoToModule(ModuleDto moduleDto);

  ModuleDto moduleToModuleDto(Module module);

  Group groupDto2Group(GroupDto dto);

  GroupDto group2GroupDto(Group group);

  TagDto tagToTagDto(Tag tag);

  Tag tagDtoToTag(TagDto tagDto);
}