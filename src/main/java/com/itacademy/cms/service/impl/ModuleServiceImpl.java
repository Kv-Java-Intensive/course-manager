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
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ModuleServiceImpl implements ModuleService {

  private final ModuleRepository moduleRepository;
  private final MapStructMapper moduleMapper;

  @Override
  public List<Module> findAll() {
    List<Module> moduleList = (List) moduleRepository.findAll();
    if (moduleList.isEmpty()) {
      throw new EntityNotFoundException("No module found!");
    }
    return moduleList;
  }

  @Override
  public void updateModule(ModuleDto moduleDto, String uuid) {
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
    Optional<Module> module = moduleRepository.findByUuid(uuid);
    return module.orElseThrow(
        () -> new EntityNotFoundException("Module with uuid " + uuid + " not found!"));
  }

  @Override
  public Module saveModule(ModuleDto moduleDto) {
    return moduleRepository.save(moduleMapper.moduleDtoToModule(moduleDto));

  }

  @Override
  public void deleteModuleByUuid(String uuid) {
    if (uuid == null) {
      throw new ParameterMissingException("Module uuid is missing");
    } else if (moduleRepository.existsByUuid(uuid)) {
      moduleRepository.deleteByUuid(uuid);
      return;
    }
    throw new EntityNotFoundException("Module with uuid " + uuid + " not found!");
  }
}
