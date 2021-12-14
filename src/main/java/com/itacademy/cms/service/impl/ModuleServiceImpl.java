package com.itacademy.cms.service.impl;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.exeption.ParameterMissingException;
import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.dto.ModuleDto;
import com.itacademy.cms.repository.ModuleRepository;
import com.itacademy.cms.service.ModuleService;
import java.util.List;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

  private static final Logger logger = LoggerFactory.getLogger(ModuleServiceImpl.class);

  private final ModuleRepository moduleRepository;
  private final MapStructMapper moduleMapper;

  @Override
  public List<Module> findAll() {
    logger.info("GET ALL MODULES");
    List<Module> moduleList = (List) moduleRepository.findAll();
    if (moduleList.isEmpty()) {
      logger.error("MODULES LIST IS EMPTY");
      throw new EntityNotFoundException("No module found!");
    }
    return moduleList;
  }

  @Override
  public void updateModule(ModuleDto moduleDto, String uuid) {
    logger.info("UPDATE MODULE WITH ID = {}", uuid);
    Optional<Module> moduleOptional = moduleRepository.findByUuid(uuid);
    moduleOptional.ifPresent(x -> {
      x.setContent(moduleDto.getContent());
      x.setLessonNumber(moduleDto.getLessonNumber());
      x.setDescription(moduleDto.getDescription());
      moduleRepository.save(x);
    });
  }


  @Override
  public Module findByUuid(String uuid) {
    logger.info("GET MODULE WITH ID = {}", uuid);
    Optional<Module> module = moduleRepository.findByUuid(uuid);
    if (module.isPresent()) {
      return module.get();
    } else {
      logger.error("MODULE WITH ID = {} DOES NOT EXIST", uuid);
      throw new EntityNotFoundException("Module with uuid " + uuid + " not found!");
    }
  }

  @Override
  public Module saveModule(ModuleDto moduleDto) {
    logger.info("CREATE NEW MODULE");
    return moduleRepository.save(moduleMapper.moduleDtoToModule(moduleDto));
  }

  @Override
  public void deleteModuleByUuid(String uuid) {
    if (uuid == null) {
      logger.error("UUID IS EMPTY");
      throw new ParameterMissingException("Module uuid is missing");
    } else if (moduleRepository.existsByUuid(uuid)) {
      logger.info("REMOVE MODULE WITH ID = {}", uuid);
      moduleRepository.deleteByUuid(uuid);
      return;
    }
    logger.error("MODULE WITH ID = {} DOES NOT EXIST", uuid);
    throw new EntityNotFoundException("Module with uuid " + uuid + " not found!");
  }
}
