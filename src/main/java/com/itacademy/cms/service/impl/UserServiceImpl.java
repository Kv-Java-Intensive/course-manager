package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.UserNotFoundException;
import com.itacademy.cms.mapper.UserMapper;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.UserDto;
import com.itacademy.cms.repository.UserRepository;
import com.itacademy.cms.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final UserMapper userMapper;

  @Override
  public List<User> findAll() throws UserNotFoundException {
    List<User> userList = userRepository.findAll();
    if (userList.isEmpty()) {
      throw new UserNotFoundException("No users found!");
    }
    return userList;
  }

  @Override
  public void updateUser(UserDto userDto, Long id) throws UserNotFoundException {
    Optional<User> userOptional = userRepository.findById(id);
    if (userOptional.isPresent()) {
      User userFromDb = userOptional.get();
      userFromDb.setGroups(userDto.getGroups());
      userFromDb.setUserCourse(userDto.getUserCourse());
      userFromDb.setFirstName(userDto.getFirstName());
      userFromDb.setLastName(userDto.getLastName());
      userFromDb.setEmail(userDto.getEmail());
      userFromDb.setPassword(userDto.getPassword());
      userFromDb.setRole(userDto.getRole());
      userFromDb.setAccountCard(userDto.getAccountCard());
      userFromDb.setAbout(userDto.getAbout());
      userRepository.save(userFromDb);
    }
    throw new UserNotFoundException("User with id " + id + "not found!");
  }

  @Override
  public User findById(Long id) throws UserNotFoundException {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      return userRepository.getById(id);
    }
    throw new UserNotFoundException("User with id " + id + "not found!");
  }

  @Override
  public void saveUser(UserDto userDto) {
    userRepository.save(userMapper.userDtoToUser(userDto));
  }
}
