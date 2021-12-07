package com.itacademy.cms.mapper;

import com.itacademy.cms.model.Category;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.CategoryDto;
import com.itacademy.cms.model.Certificate;

import com.itacademy.cms.model.dto.CertificateDto;
import com.itacademy.cms.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntityMapper {

  EntityMapper MAPPER =
      Mappers.getMapper(EntityMapper.class);

  Certificate certificateDtoToCertificate(CertificateDto certificateDto);

  CertificateDto certificateToCertificateDto(Certificate Certificate);

  User userDtoToUser(UserDto userDto);

  UserDto userToUserDto(User user);


  Category categoryDtoToCategory(CategoryDto categoryDto);

  CategoryDto categoryToCategoryDto(Category category);
}