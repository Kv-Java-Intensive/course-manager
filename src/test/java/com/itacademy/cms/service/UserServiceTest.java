package com.itacademy.cms.service;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.model.User;
import com.itacademy.cms.repository.UserRepository;
import com.itacademy.cms.service.impl.UserServiceImpl;
import java.util.Optional;
import java.util.UUID;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class UserServiceTest {

  @Mock
  UserRepository userRepository;

  @InjectMocks
  UserServiceImpl userService;

  @Test
  void findAllTest() {
    Assertions.assertThrows(EntityNotFoundException.class, () -> userService.findAll());
    Mockito.verify(userRepository).findAll();
  }

  @Test
  void findByIdTestExpectedException() {
    String uuid = UUID.randomUUID().toString();
    Assertions.assertThrows(EntityNotFoundException.class, () -> userService.findByUuid(uuid));
    Mockito.verify(userRepository).findByUuid(uuid);
  }

  @Test
  void findByIdTestExpectedUser() {
    String uuid = UUID.randomUUID().toString();

    User user = new User();
    user.setUuid(uuid);
    user.setFirstName("test");

    Optional<User> optionalUser = Optional.of(user);
    Mockito.when(userRepository.findByUuid(uuid)).thenReturn(optionalUser);
    User savedUser = userService.findByUuid(uuid);

    Assertions.assertEquals(user.getId(), savedUser.getId());
    Assertions.assertEquals(user.getFirstName(), savedUser.getFirstName());
  }
}