package com.itacademy.cms.service;

import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.dto.ModuleDto;
import java.util.List;

public interface ModuleService {

  List<Module> findAll();

  Module findById(Long id);

  void saveModule(ModuleDto moduleDto);

  void deleteModuleById(Long id);

  void updateModule(ModuleDto moduleDto, Long id);
}
