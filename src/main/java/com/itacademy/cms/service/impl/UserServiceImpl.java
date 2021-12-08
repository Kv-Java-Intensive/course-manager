package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.exeption.ParameterMissingException;
import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.UserDto;
import com.itacademy.cms.repository.UserRepository;
import com.itacademy.cms.service.UserService;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private final UserRepository userRepository;
  private final MapStructMapper userMapper;

  @Override
  public List<User> findAll() {
    List<User> userList = (List) userRepository.findAll();
    if (userList.isEmpty()) {
      throw new EntityNotFoundException("No users found!");
    }
    return userList;
  }

  @Override
  public void updateUser(UserDto userDto, UUID id) {
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
  public User findById(UUID id) {
    Optional<User> user = userRepository.findById(id);
    return user.orElseThrow(
        () -> new EntityNotFoundException("User with id " + id + " not found!"));
  }

  @Override
  public User saveUser(UserDto userDto) {
    return userRepository.save(userMapper.userDtoToUser(userDto));
  }

  @Override
  public void deleteUserById(UUID id) {
    if (id == null) {
      throw new ParameterMissingException("User id is missing");
    } else if (userRepository.existsById(id)) {
      userRepository.deleteById(id);
      return;
    }
    throw new EntityNotFoundException("User with id " + id + " not found!");
  }
}