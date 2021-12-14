package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.exeption.ParameterMissingException;
import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Group;
import com.itacademy.cms.model.dto.GroupDto;
import com.itacademy.cms.repository.GroupRepository;
import com.itacademy.cms.service.GroupService;
import java.util.List;
import java.util.Optional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class GroupServiceImpl implements GroupService {

  private static final Logger logger = LoggerFactory.getLogger(GroupServiceImpl.class);

  private GroupRepository groupRepository;
  private MapStructMapper groupMapper;

  public List<Group> getGroupsByUserId(Long id) {
    return groupRepository.getAllByUser(id);
  }

  @Override
  public List<Group> findAll() {
    logger.info("GET ALL CERTIFICATES");
    List<Group> groups = (List) groupRepository.findAll();
    return groups;
  }

  @Override
  public Group findById(Long id) {
    logger.info("GET GROUP WITH ID = {}", id);
    Group group = groupRepository.getById(id);
    if (group != null) {
      return group;
    } else {
      logger.error("GROUP WITH ID = {} DOES NOT EXIST", id);
      throw new EntityNotFoundException("Group with id = " + id + " does not exist!");
    }
  }

  @Override
  public void updateGroup(GroupDto groupDto, Long id) {
    logger.info("UPDATE GROUP WITH ID = {}", id);
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
    logger.info("CREATE NEW GROUP");
    groupRepository.save(group);
  }

  @Override
  public void deleteGroup(Long id) {
    if (id == null) {
      logger.error("UUID IS EMPTY");
      throw new ParameterMissingException("Group uuid is missing");
    } else {
      logger.info("REMOVE CERTIFICATE WITH ID = {}", id);
      Group group = groupRepository.getById(id);
      groupRepository.delete(group);
    }
  }
}
