package com.itacademy.cms.controller;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.dto.ModuleDto;
import com.itacademy.cms.service.ModuleService;
import java.util.ArrayList;
import java.util.List;
import javax.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ModuleController {

  private final ModuleService moduleService;
  private final MapStructMapper moduleMapper;

  @GetMapping("/modules")
  @PreAuthorize("permitAll()")
  public List<ModuleDto> getAllModules() {
    List<Module> modules = moduleService.findAll();
    List<ModuleDto> moduleDtos = new ArrayList<>();
    for (Module module : modules) {
      moduleDtos.add(moduleMapper.moduleToModuleDto(module));
    }
    return moduleDtos;
  }

  @GetMapping("/modules/{id}")
  @PreAuthorize("hasAuthority('ADMIN')")
  public ModuleDto getModuleByUuid(@PathVariable("id") String uuid) {
    return moduleMapper.moduleToModuleDto(moduleService.findByUuid(uuid));
  }

  @PostMapping("/modules")
  @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
  public void saveModule(@RequestBody ModuleDto moduleDto) {
    moduleService.saveModule(moduleDto);
  }

  @PutMapping("/modules/{id}")
  @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
  public void updateModule(@RequestBody ModuleDto moduleDto, @PathVariable("id") String uuid) {
    moduleService.updateModule(moduleDto, uuid);
  }

  @Transactional
  @DeleteMapping("/modules/{id}")
  @PreAuthorize("hasAnyAuthority('ADMIN', 'AUTHOR')")
  public void deleteModule(@PathVariable("id") String uuid) {
    moduleService.deleteModuleByUuid(uuid);
  }

}
