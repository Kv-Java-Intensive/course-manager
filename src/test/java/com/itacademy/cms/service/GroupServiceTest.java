package com.itacademy.cms.service;

import com.itacademy.cms.exeption.NoSuchGroupException;
import com.itacademy.cms.model.Group;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

@ExtendWith(MockitoExtension.class)
public class GroupServiceTest {

  @Autowired
  private GroupService groupService;

//  @Test
//  void findAllTest() {
//
//  }

  //  @Test
  public void createAndFindTest() {
    Group group = new Group();
    group.setId(1L);
    group.setCapacity(25);
    group.setStartDate(LocalDateTime.now());
    group.setName("new_group");
    groupService.saveGroup(group);

    Group check = groupService.findById(1L);
    Assertions.assertEquals(group.getId(), check.getId());
    Assertions.assertEquals(group.getCapacity(), check.getCapacity());
    Assertions.assertEquals(group.getStartDate(), check.getStartDate());
    Assertions.assertEquals(group.getName(), check.getName());
  }

  //  @Test
  public void deleteTest() {
    Group group = new Group();
    group.setId(1L);
    group.setCapacity(25);
    group.setStartDate(LocalDateTime.now());
    group.setName("new_group");
    groupService.saveGroup(group);

    groupService.deleteGroup(group.getId());
    Assertions.assertThrows(NoSuchGroupException.class, () -> groupService.findById(group.getId()));
  }
}
