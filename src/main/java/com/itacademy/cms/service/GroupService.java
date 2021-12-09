package com.itacademy.cms.service;

import com.itacademy.cms.exeption.NoSuchGroupException;

  Group findById(Long id);

  void updateGroup(GroupDto groupDto, Long id);

  void saveGroup(Group group);

  void deleteGroup(Long id);

}
