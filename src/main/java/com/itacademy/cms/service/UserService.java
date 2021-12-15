package com.itacademy.cms.service;

import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.UserDto;
import java.util.List;

public interface UserService {

  List<User> findAll();

  void updateUser(UserDto userDto, String uuid);

  User saveUser(User user);

  User saveUser(UserDto userDto);

  User findByUuid(String uuid);

  void deleteUserByUuid(String uuid);

  void blockUser(String uuid, boolean active);

  User loadUserByName(String name);
}
