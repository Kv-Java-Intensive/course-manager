package com.itacademy.cms.services;

import com.itacademy.cms.model.Group;
import com.itacademy.cms.model.User;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface GroupService {

  List<Group> getAllGroups();

  void addNewGroup(Group group);

  List<Group> getAllUserGroups(User user);

  Group findGroupById(long id);

  void deleteGroup(Group group);

}
