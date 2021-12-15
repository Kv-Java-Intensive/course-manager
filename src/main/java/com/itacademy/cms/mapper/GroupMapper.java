package com.itacademy.cms.mapper;

import com.itacademy.cms.model.Group;
import com.itacademy.cms.model.dto.GroupDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface GroupMapper {

  GroupMapper INSTANCE = Mappers.getMapper(GroupMapper.class);

  Group groupDto2Group(GroupDto dto);

  GroupDto group2GroupDto(Group group);

}
