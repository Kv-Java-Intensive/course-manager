package com.itacademy.cms.service;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import com.itacademy.cms.mapper.MapStructMapper;
import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.dto.ModuleDto;
import com.itacademy.cms.repository.ModuleRepository;
import com.itacademy.cms.service.impl.ModuleServiceImpl;
import java.util.Optional;
import javax.transaction.Transactional;
import org.junit.Before;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
@Transactional(Transactional.TxType.REQUIRED)
@ActiveProfiles(profiles = "test")
public class ModuleServiceImplIntegrationTest {

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
    Optional<Module> moduleExisted = moduleRepository.findById(moduleId);
    assertTrue(moduleExisted.isPresent());
    assertEquals(moduleId, moduleExisted.get().getId().longValue());
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


