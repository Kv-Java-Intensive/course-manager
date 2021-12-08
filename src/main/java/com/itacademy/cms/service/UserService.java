package com.itacademy.cms.service;

import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.UserDto;
import java.util.List;
import java.util.UUID;

public interface UserService {

  List<User> findAll();

  User findById(UUID id);

  void updateUser(UserDto userDto, UUID id);

  User saveUser(UserDto userDto);

  void deleteUserById(UUID id);
}