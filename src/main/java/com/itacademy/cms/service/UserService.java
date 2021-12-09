package com.itacademy.cms.service;

import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.UserDto;
import java.util.List;

public interface UserService {

  List<User> findAll();

  User findById(Long id);

  //void updateUser(UserDto userDto, Long id);

  User saveUser(UserDto userDto);

  void deleteUserById(Long id);
}