package com.itacademy.cms.service;

import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.dto.ModuleDto;
import java.util.List;

public interface ModuleService {

  List<Module> findAll();

//  Module findById(Long id);

  void updateModule(ModuleDto moduleDto, String uuid);

  Module saveModule(ModuleDto moduleDto);

//  void deleteModuleById(Long id);

  Module findByUuid(String uuid);

  void deleteModuleByUuid(String uuid);
}
