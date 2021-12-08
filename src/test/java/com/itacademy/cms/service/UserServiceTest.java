package com.itacademy.cms.service;

import com.itacademy.cms.exeption.UserNotFoundException;
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
  UserServiceImpl userServiceImpl;

  @Test
  void findAllTest() {
    Assertions.assertThrows(UserNotFoundException.class, () -> userServiceImpl.findAll());
    Mockito.verify(userRepository).findAll();
  }

  @Test
  void findByIdTestExpectedException() {
    UUID id = UUID.randomUUID();
    Assertions.assertThrows(UserNotFoundException.class, () -> userServiceImpl.findById(id));
    Mockito.verify(userRepository).findById(id);
  }

  @Test
  void findByIdTestExpectedUser() {
    UUID id = UUID.randomUUID();

    User user = new User();
    user.setId(id);
    user.setFirstName("test");

    Optional<User> optionalUser = Optional.of(user);
    Mockito.when(userRepository.findById(id)).thenReturn(optionalUser);
    User savedUser = userServiceImpl.findById(id);

    Assertions.assertEquals(user.getId(), savedUser.getId());
    Assertions.assertEquals(user.getFirstName(), savedUser.getFirstName());
  }
}