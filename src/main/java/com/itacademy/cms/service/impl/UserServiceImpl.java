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
  public void updateUser(UserDto userDto, String uuid) {
    Optional<User> userOptional = userRepository.findByUuid(uuid);
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
  public User findByUuid(String uuid) {
    Optional<User> user = userRepository.findByUuid(uuid);
    return user.orElseThrow(
        () -> new EntityNotFoundException("User with uuid " + uuid + " not found!"));
  }

  @Override
  public User saveUser(UserDto userDto) {
    return userRepository.save(userMapper.userDtoToUser(userDto));
  }

  @Override
  public void deleteUserByUuid(String uuid) {
    if (uuid == null) {
      throw new ParameterMissingException("User uuid is missing");
    } else if (userRepository.existsByUuid(uuid)) {
      userRepository.deleteByUuid(uuid);
      return;
    }
    throw new EntityNotFoundException("User with uuid " + uuid + " not found!");
  }
}