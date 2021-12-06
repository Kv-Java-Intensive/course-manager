package com.itacademy.cms.service;

import com.itacademy.cms.exeption.UserNotFoundException;
import com.itacademy.cms.model.User;
import com.itacademy.cms.model.dto.UserDto;
import com.itacademy.cms.model.enums.Role;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Transactional(Transactional.TxType.REQUIRED)
@ActiveProfiles(profiles = "test")
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class UserServiceIntegrationTest {

  @Autowired
  UserService userService;

  @Test
  void userSaveAndFindByIdTest() {
    User savedUser = getSavedUser();

    User userById = userService.findById(savedUser.getId());

    Assertions.assertEquals(savedUser.getFirstName(), userById.getFirstName());
    Assertions.assertEquals(savedUser.getLastName(), userById.getLastName());
    Assertions.assertEquals(savedUser.getEmail(), userById.getEmail());
    Assertions.assertEquals(savedUser.getPassword(), userById.getPassword());
    Assertions.assertEquals(savedUser.getRole(), userById.getRole());
    Assertions.assertEquals(savedUser.getAccountCard(), userById.getAccountCard());
    Assertions.assertEquals(savedUser.getAbout(), userById.getAbout());
  }

  @Test
  void userUpdateTest() {
    UserDto userDtoToUpdate = new UserDto();

    userDtoToUpdate.setFirstName("updated");
    userDtoToUpdate.setLastName("updated");
    userDtoToUpdate.setEmail("updated@gmail.com");
    userDtoToUpdate.setPassword("updated");
    userDtoToUpdate.setRole(Role.USER);
    userDtoToUpdate.setAccountCard(1111111);
    userDtoToUpdate.setAbout("updated");

    User initUser = getSavedUser();

    userService.updateUser(userDtoToUpdate, initUser.getId());

    User updatedUser = userService.findById(initUser.getId());

    Assertions.assertEquals(userDtoToUpdate.getFirstName(), updatedUser.getFirstName());
    Assertions.assertEquals(userDtoToUpdate.getLastName(), updatedUser.getLastName());
    Assertions.assertEquals(userDtoToUpdate.getEmail(), updatedUser.getEmail());
    Assertions.assertEquals(userDtoToUpdate.getPassword(), updatedUser.getPassword());
    Assertions.assertEquals(userDtoToUpdate.getRole(), updatedUser.getRole());
    Assertions.assertEquals(userDtoToUpdate.getAccountCard(), updatedUser.getAccountCard());
    Assertions.assertEquals(userDtoToUpdate.getAbout(), updatedUser.getAbout());
  }

  @Test
  void userDeleteTest() {
    User initUser = getSavedUser();

    Assertions.assertNotNull(userService.findById(initUser.getId()));

    userService.deleteUserById(initUser.getId());

    Assertions.assertThrows(UserNotFoundException.class,
        () -> userService.findById(initUser.getId()));
  }

  private User getSavedUser() {
    UserDto userDto = new UserDto();

    userDto.setFirstName("testUser");
    userDto.setLastName("userTest");
    userDto.setEmail("test@gmail.com");
    userDto.setPassword("testTest");
    userDto.setRole(Role.USER);
    userDto.setAccountCard(1234567);
    userDto.setAbout("something");

    return userService.saveUser(userDto);
  }
}
