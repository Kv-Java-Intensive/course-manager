package com.itacademy.cms.controller;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.dto.ModuleDto;
import com.itacademy.cms.service.ModuleService;
import java.util.List;
import lombok.RequiredArgsConstructor;
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
  public List<Module> getAllModules() {
    return moduleService.findAll();
  }

  @GetMapping("/modules/{id}")
  public ModuleDto getModuleById(@PathVariable("id") Long id) {
    return moduleMapper.moduleToModuleDto(moduleService.findById(id));
  }

  @PostMapping("/modules")
  public void saveModule(@RequestBody ModuleDto moduleDto) {
    moduleService.saveModule(moduleDto);
  }

  @PutMapping("/modules/{id}")
  public void updateModule(@RequestBody ModuleDto moduleDto, @PathVariable Long id) {
    moduleService.updateModule(moduleDto, id);
  }

  @DeleteMapping("/modules/{id}")
  public void deleteModule(@PathVariable("id") Long id) {
    moduleService.deleteModuleById(id);
  }

}
