package com.itacademy.cms.mapper;

import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.UserDto;
import java.util.List;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface UserMapper {

  UserMapper MAPPER = Mappers.getMapper(UserMapper.class);

  User userDtoToUser(UserDto userDto);

  UserDto userToUserDto(User user);

  List<UserDto> userToUserDtoList(List<User> userList);
}
