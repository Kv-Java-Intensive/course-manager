package com.itacademy.cms.service;

import com.itacademy.cms.model.Group;
import com.itacademy.cms.model.dto.GroupDto;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
public interface GroupService {

  List<Group> findAll();

  Group findById(Long id);

  void updateGroup(GroupDto groupDto, Long id);

  void saveGroup(Group group);

  void deleteGroup(Long id);

}
