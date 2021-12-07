package com.itacademy.cms.mapper;

import com.itacademy.cms.model.Certificate;
import com.itacademy.cms.model.Tag;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.CertificateDto;
import com.itacademy.cms.model.dto.TagDto;
import com.itacademy.cms.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;


@Mapper(componentModel = "spring")
public interface EntityMapper {

  EntityMapper MAPPER = Mappers.getMapper(EntityMapper.class);

  TagDto tagToTagDto(Tag tag);

  Tag tagDtoToTag(TagDto tagDto);

  Certificate certificateDtoToCertificate(CertificateDto certificateDto);

  CertificateDto certificateToCertificateDto(Certificate certificate);

  User userDtoToUser(UserDto userDto);

  UserDto userToUserDto(User user);
}


