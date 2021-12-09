package com.itacademy.cms.service;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.dto.ModuleDto;
import java.util.List;

public interface ModuleService {

  List<Module> findAll() throws EntityNotFoundException;

  Module findById(Long id) throws EntityNotFoundException;

  //void updateModule(ModuleDto moduleDto, Long id) throws EntityNotFoundException;

  void saveModule(ModuleDto moduleDto);

  void deleteModuleById(Long id) throws EntityNotFoundException;
}
