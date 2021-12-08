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
  public List<Module> findAll() throws EntityNotFoundException {
    List<Module> moduleList = (List) moduleRepository.findAll();
    if (moduleList.isEmpty()) {
      throw new EntityNotFoundException("No module found!");
    }
    return moduleList;
  }

  @Override
  public void updateModule(ModuleDto moduleDto, Long id) {
    Optional<Module> moduleOptional = moduleRepository.findById(id);
    moduleOptional.ifPresent(x -> {
      x.setContent(moduleDto.getContent());
      x.setCourse(moduleDto.getCourse());
      x.setLessonNumber(moduleDto.getLessonNumber());
      x.setDescription(moduleDto.getDescription());
      moduleRepository.save(x);
    });
  }


  @Override
  public Module findById(Long id) throws EntityNotFoundException {
    Optional<Module> module = moduleRepository.findById(id);
    if (module.isPresent()) {
      return moduleRepository.getById(id);
    }
    throw new EntityNotFoundException("Module with id " + id + " not found!");
  }

  @Override
  public Module saveModule(ModuleDto moduleDto) {
    moduleRepository.save(moduleMapper.moduleDtoToModule(moduleDto));
    return null;
  }

  @Override
  public void deleteModuleById(Long id) throws EntityNotFoundException {
    if (id == null) {
      throw new ParameterMissingException("Module id is missing");
    } else if (moduleRepository.existsById(id)) {
      moduleRepository.deleteById(id);
    }
    throw new EntityNotFoundException("Module with id " + id + " not found!");
  }
}
