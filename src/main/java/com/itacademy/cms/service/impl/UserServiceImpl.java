package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.exeption.ParameterMissingException;
import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.SearchCriteriaDto;
import com.itacademy.cms.model.dto.UserDto;
import com.itacademy.cms.repository.UserRepository;
import com.itacademy.cms.repository.specification.UserSpecificationsBuilder;
import com.itacademy.cms.service.UserService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

  private static final Logger logger = LoggerFactory.getLogger(UserServiceImpl.class);

  private final UserRepository userRepository;
  private final MapStructMapper userMapper;

  @Override
  public List<User> findAll() {
    logger.info("GET ALL USERS");
    List<User> userList = (List) userRepository.findAll();
    if (userList.isEmpty()) {
      logger.error("USERS LIST IS EMPTY");
      throw new EntityNotFoundException("No users found!");
    }
    return userList;
  }

  @Override
  public List<User> findUserBySearch(SearchCriteriaDto searchCriteriaDto) {
    UserSpecificationsBuilder builder = new UserSpecificationsBuilder();
    for (int i = 0; i < searchCriteriaDto.getCriteriaList().size(); i++) {
      builder.with(searchCriteriaDto.getCriteriaList().get(i).getKey(),
          searchCriteriaDto.getCriteriaList().get(i).getOperation(),
          searchCriteriaDto.getCriteriaList().get(i).getValue());
    }
    Specification<User> spec = builder.build();
    return userRepository.findAll(spec);
  }

  @Override
  public void updateUser(UserDto userDto, String uuid) {
    logger.info("UPDATE USER WITH ID = {}", uuid);
    Optional<User> userOptional = userRepository.findByUuid(uuid);
    userOptional.ifPresent(user -> {
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
    logger.info("GET USER WITH ID = {}", uuid);
    Optional<User> user = userRepository.findByUuid(uuid);
    if (user.isPresent()) {
      return user.get();
    } else {
      logger.error("USER WITH ID = {} DOES NOT EXIST", uuid);
      throw new EntityNotFoundException("User with uuid " + uuid + " not found!");
    }
  }

  @Override
  public User saveUser(UserDto userDto) {
    logger.info("CREATE NEW USER");
    try {
      return userRepository.save(userMapper.userDtoToUser(userDto));
    } catch (Exception ex) {
      logger.error("ERROR WHILE SAVING NEW USER");
      return null;
    }
  }

  @Override
  public void deleteUserByUuid(String uuid) {
    if (uuid == null) {
      logger.error("UUID IS EMPTY");
      throw new ParameterMissingException("User uuid is missing");
    } else if (userRepository.existsByUuid(uuid)) {
      logger.info("REMOVE USER WITH ID = {}", uuid);
      userRepository.deleteByUuid(uuid);
      return;
    }
    logger.error("USER WITH ID = {} DOES NOT EXIST", uuid);
    throw new EntityNotFoundException("User with uuid " + uuid + " not found!");
  }

  @Override
  public void blockUser(String uuid, boolean active) {
    userRepository.blockUser(uuid, active);
  }
}
