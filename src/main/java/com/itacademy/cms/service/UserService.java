package com.itacademy.cms.service;

import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.SearchCriteriaDto;
import com.itacademy.cms.model.dto.UserDto;
import java.util.List;

public interface UserService {

  List<User> findAll();

  void updateUser(UserDto userDto, String uuid);

  List<User> findUserBySearch(SearchCriteriaDto searchCriteriaDto);

  User saveUser(UserDto userDto);

  User findByUuid(String uuid);

  void deleteUserByUuid(String uuid);

  void blockUser(boolean active, String uuid);
}
