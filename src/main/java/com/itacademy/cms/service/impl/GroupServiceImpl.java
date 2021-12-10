package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.exeption.NoSuchGroupException;
import com.itacademy.cms.model.Group;
import com.itacademy.cms.model.dto.GroupDto;
import com.itacademy.cms.repository.GroupRepository;
import com.itacademy.cms.service.GroupService;
import java.util.List;
import java.util.Optional;

public class GroupServiceImpl implements GroupService {

  private GroupRepository groupRepository;

  public List<Group> getGroupsByUserId(Long id) {
    return groupRepository.getAllByUser(id);
  }

  @Override
  public List<Group> findAll() {
    List<Group> groups = (List<Group>) groupRepository.findAll();
    if (groups.isEmpty()) {
      throw new EntityNotFoundException("No groups found!");
    }
    return groups;
  }

  @Override
  public Group findById(Long id) throws NoSuchGroupException {

    Group group = groupRepository.getById(id);
    if (group != null) {
      return group;
    } else {
      throw new NoSuchGroupException("Group with id = " + id + " does not exist!");

    }
  }

  @Override
  public void updateGroup(GroupDto groupDto, Long id) throws NoSuchGroupException {

    Optional<Group> opt = groupRepository.findById(id);
    opt.ifPresent(group -> {
      group.setName(groupDto.getName());
      group.setCourse(groupDto.getCourse());
      group.setCapacity(group.getCapacity());
      group.setStartDate(groupDto.getStartDate());
      groupRepository.save(group);
    });
  }


  @Override
  public void saveGroup(Group group) {
    groupRepository.save(group);
  }

  @Override
  public void deleteGroup(Long id) throws NoSuchGroupException {

    Group group = groupRepository.getById(id);
    groupRepository.delete(group);
  }
}
