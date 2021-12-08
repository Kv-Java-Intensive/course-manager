package com.itacademy.cms.service;

import com.itacademy.cms.model.Group;
import com.itacademy.cms.model.dto.GroupDto;
import java.util.List;
import java.util.UUID;

public interface GroupService {

  List<Group> findAll();

  Group findById(UUID id);

  void updateGroup(GroupDto groupDto, UUID id);

  void saveGroup(Group group);

  void deleteGroup(UUID id);

}
