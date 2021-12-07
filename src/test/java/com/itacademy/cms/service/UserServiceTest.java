package com.itacademy.cms.service;

import com.itacademy.cms.model.User;
import java.util.Optional;
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
    Long id = 1L;
    Assertions.assertThrows(UserNotFoundException.class, () -> userServiceImpl.findById(id));
    Mockito.verify(userRepository).findById(id);
  }

  @Test
  void findByIdTestExpectedUser() {
    Long id = 1L;

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