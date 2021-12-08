package com.itacademy.cms.service;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.dto.ModuleDto;
import com.itacademy.cms.repository.ModuleRepository;
import javax.transaction.Transactional;
import org.junit.jupiter.api.Assertions;
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
    Module savedModule = getSavedModule();

    Module moduleById = moduleService.findById(savedModule.getId());

    Assertions.assertEquals(savedModule.getContent(), moduleById.getContent());
    Assertions.assertEquals(savedModule.getDescription(), moduleById.getDescription());
    Assertions.assertEquals(savedModule.getLessonNumber(), moduleById.getLessonNumber());
  }

  @Test
  void moduleUpdateTest() throws EntityNotFoundException {
    ModuleDto moduleDtoToUpdate = new ModuleDto();

    moduleDtoToUpdate.setContent("updated");
    moduleDtoToUpdate.setDescription("updated");
    moduleDtoToUpdate.setLessonNumber(2);

    Module initModule = getSavedModule();

    moduleService.updateModule(moduleDtoToUpdate, initModule.getId());

    Module updatedModule = moduleService.findById(initModule.getId());

    Assertions.assertEquals(moduleDtoToUpdate.getContent(), updatedModule.getContent());
    Assertions.assertEquals(moduleDtoToUpdate.getDescription(), updatedModule.getDescription());
    Assertions.assertEquals(moduleDtoToUpdate.getLessonNumber(), updatedModule.getLessonNumber());
  }

  @Test
  void moduleDeleteTest() throws EntityNotFoundException {
    Module initModule = getSavedModule();

    Assertions.assertNotNull(moduleService.findById(initModule.getId()));

    moduleService.deleteModuleById(initModule.getId());

    Assertions.assertThrows(EntityNotFoundException.class,
        () -> moduleService.findById(initModule.getId()));
  }

  private Module getSavedModule() {
    ModuleDto moduleDto = new ModuleDto();

    moduleDto.setContent("some content");
    moduleDto.setDescription("some description");
    moduleDto.setLessonNumber(2);

    return moduleService.saveModule(moduleDto);
  }
}
