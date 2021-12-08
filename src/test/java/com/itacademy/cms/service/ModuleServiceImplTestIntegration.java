package com.itacademy.cms.service;

import static org.junit.Assert.assertEquals;


import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.dto.ModuleDto;
import com.itacademy.cms.repository.ModuleRepository;
import com.itacademy.cms.service.impl.ModuleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ModuleServiceImplTestIntegration {

  private ModuleService moduleService;

  @Autowired
  private ModuleRepository moduleRepository;
  @Autowired
  private MapStructMapper moduleMapper;

  @Before
  public void mockRepository() {
    MockitoAnnotations.initMocks(this);
    moduleService = new ModuleServiceImpl(moduleRepository, moduleMapper);
  }

  @BeforeEach
  void cleanTable() {
    moduleRepository.deleteAll();
  }

  @Test
  public void saveModuleRepository_whenSave_thenGetOk() {

    long moduleId = 1L;
    Module module = new Module();
    module.setId(moduleId);
    moduleRepository.save(module);
    Module moduleExisted = moduleRepository.getById(moduleId);
    assertEquals(moduleId, moduleExisted.getId().longValue());
  }

  @Test
  public void saveModuleService_whenSave_thenGetOk() {

    long moduleId = 1L;
    Module module = new Module();
    module.setId(moduleId);
    ModuleDto m = new ModuleDto();
    m.setContent("hello");
    moduleService.saveModule(m);
  }
}


