package com.itacademy.cms.service;

import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.dto.ModuleDto;
import java.util.List;
import java.util.UUID;

public interface ModuleService {

  List<Module> findAll();

  Module findById(UUID id);

  void updateModule(ModuleDto moduleDto, UUID id);

  void saveModule(ModuleDto moduleDto);

  void deleteModuleById(UUID id);
}
