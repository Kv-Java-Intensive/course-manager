package com.itacademy.cms.mapper;

import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.ModuleDto;
import com.itacademy.cms.model.dto.UserDto;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface EntityMapper {

  EntityMapper MAPPER = Mappers.getMapper(EntityMapper.class);

  User userDtoToUser(UserDto userDto);

  UserDto userToUserDto(User user);

  Module moduleDtoToModule(ModuleDto moduleDto);

  ModuleDto moduleToModuleDto(Module module);
}
