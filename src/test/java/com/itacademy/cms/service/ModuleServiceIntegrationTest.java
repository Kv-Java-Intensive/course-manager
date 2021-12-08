package com.itacademy.cms.service;

import static org.junit.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.dto.ModuleDto;
import com.itacademy.cms.repository.ModuleRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@SpringBootTest
@Transactional(Transactional.TxType.REQUIRED)
@ActiveProfiles(profiles = "test")
public class ModuleServiceIntegrationTest {

  @Autowired
  ModuleService moduleService;

  @Autowired
  ModuleRepository moduleRepository;

  @BeforeEach
  void cleanTable() {
    moduleRepository.deleteAll();
  }

  @Test
  void moduleSaveAndFindByIdTest() throws EntityNotFoundException {
    Module savedModule = getModule();

    Module module = moduleService.findById(savedModule.getId());

    assertEquals(savedModule.getContent(), module.getContent());
    assertEquals(savedModule.getDescription(), module.getDescription());
    assertEquals(savedModule.getLessonNumber(), module.getLessonNumber());
    assertEquals(savedModule.getCourse(), module.getCourse());
  }

  @Test
  void moduleUpdateTest() throws EntityNotFoundException {
    ModuleDto moduleDtoToUpdate = new ModuleDto();

    moduleDtoToUpdate.setDescription("Some updated description");
    moduleDtoToUpdate.setContent("Some updated content");
    moduleDtoToUpdate.setLessonNumber(3);

    Module module = getModule();

    moduleService.updateModule(moduleDtoToUpdate, module.getId());

    Module updatedModule = moduleService.findById(module.getId());

    assertEquals(moduleDtoToUpdate.getContent(), updatedModule.getContent());
    assertEquals(moduleDtoToUpdate.getDescription(), updatedModule.getDescription());
    assertEquals(moduleDtoToUpdate.getLessonNumber(), updatedModule.getLessonNumber());
  }

  private Module getModule() {
    ModuleDto moduleDto = new ModuleDto();

    moduleDto.setContent("Some content");
    moduleDto.setDescription("Some description");
    moduleDto.setLessonNumber(2);

    return moduleService.saveModule(moduleDto);
  }
}