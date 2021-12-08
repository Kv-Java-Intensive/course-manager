package com.itacademy.cms.service;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.dto.ModuleDto;
import java.util.List;
import java.util.UUID;

public interface ModuleService {

  List<Module> findAll() throws EntityNotFoundException;

  Module findById(UUID id) throws EntityNotFoundException;

  void updateModule(ModuleDto moduleDto, UUID id) throws EntityNotFoundException;

  void saveModule(ModuleDto moduleDto);

  void deleteModuleById(UUID id) throws EntityNotFoundException;
}
