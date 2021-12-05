package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.ParameterMissingException;
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
  public void updateUser(UserDto userDto, Long id) {
    Optional<User> userOptional = userRepository.findById(id);
    userOptional.ifPresent(user -> {
      user.setGroups(userDto.getGroups());
      user.setUserCourse(userDto.getUserCourse());
      user.setFirstName(userDto.getFirstName());
      user.setLastName(userDto.getLastName());
      user.setEmail(userDto.getEmail());
      user.setPassword(userDto.getPassword());
      user.setRole(userDto.getRole());
      user.setAccountCard(userDto.getAccountCard());
      user.setAbout(userDto.getAbout());
      userRepository.save(user);
    });
  }


  @Override
  public User findById(Long id) throws UserNotFoundException {
    Optional<User> user = userRepository.findById(id);
    if (user.isPresent()) {
      return userRepository.getById(id);
    }
    throw new UserNotFoundException("User with id " + id + " not found!");
  }

  @Override
  public void saveUser(UserDto userDto) {
    userRepository.save(userMapper.userDtoToUser(userDto));
  }

  @Override
  public void deleteUserById(Long id) throws UserNotFoundException {
    if (id == null) {
      throw new ParameterMissingException("User id is missing");
    } else if (userRepository.existsById(id)) {
      userRepository.deleteById(id);
    }
    throw new UserNotFoundException("User with id " + id + " not found!");
  }
}
