package com.itacademy.cms.services.impl;

import com.itacademy.cms.model.Group;
import com.itacademy.cms.model.User;
import com.itacademy.cms.repository.GroupRepository;
import com.itacademy.cms.repository.UserRepository;
import com.itacademy.cms.services.GroupService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;

public class GroupServiceImpl implements GroupService {

  private GroupRepository groupRepository;

  @Autowired
  public GroupServiceImpl(GroupRepository groupRepository) {
    this.groupRepository = groupRepository;
  }

  @Override
  public List<Group> getAllGroups() {
    return groupRepository.findAll();
  }

  @Override
  public void addNewGroup(Group group) {
    groupRepository.save(group);
  }

  @Override
  public List<Group> getAllUserGroups(User user) {
    return groupRepository.findAllById()
  }

  @Override
  public Group findGroupById(long id) {
    return null;
  }

  @Override
  public void deleteGroup(Group group) {

  }
}
