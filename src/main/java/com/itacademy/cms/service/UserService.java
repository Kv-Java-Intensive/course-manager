package com.itacademy.cms.service;

import com.itacademy.cms.exeption.UserNotFoundException;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.UserDto;
import java.util.List;

public interface UserService {

  List<User> findAll() throws UserNotFoundException;

  User findById(Long id) throws UserNotFoundException;

  void updateUser(UserDto userDto, Long id) throws UserNotFoundException;

  User saveUser(UserDto userDto);

  void deleteUserById(Long id) throws UserNotFoundException;
}
