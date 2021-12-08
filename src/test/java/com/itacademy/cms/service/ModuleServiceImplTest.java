package com.itacademy.cms.service;

import static org.junit.Assert.assertThrows;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import com.itacademy.cms.exeption.EntityNotFoundException;
import com.itacademy.cms.mapper.EntityMapper;
import com.itacademy.cms.model.Module;
import com.itacademy.cms.model.dto.ModuleDto;
import com.itacademy.cms.repository.ModuleRepository;
import com.itacademy.cms.service.impl.ModuleServiceImpl;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class ModuleServiceImplTest {

  private ModuleServiceImpl moduleServiceImpl;

  @Mock
  private ModuleRepository moduleRepository;
  @Mock
  private EntityMapper moduleMapper;

  @Before
  public void mockRepository() {
    MockitoAnnotations.initMocks(this);
    moduleServiceImpl = new ModuleServiceImpl(moduleRepository, moduleMapper);
  }

  @Test
  public void findAllModules() {
    assertThrows(EntityNotFoundException.class, () -> moduleServiceImpl.findAll());
    verify(moduleRepository).findAll();
  }

  @Test
  public void findById_notFound_throwException() {
    Long moduleId = 1L;

    assertThrows(EntityNotFoundException.class, () -> moduleServiceImpl.findById(moduleId));
    verify(moduleRepository, times(1)).findById(moduleId);
  }

  @Test
  public void saveModuleRepository_whenSave_thenGetOk() {

    long moduleId = 1L;
    Module module = new Module();
    module.setId(moduleId);
    moduleRepository.save(module);
  }

  @Test
  public void saveModuleService_whenSave_thenGetOk() {

    long moduleId = 1;
    Module module = new Module();
    module.setId(moduleId);
    ModuleDto m = new ModuleDto();
    moduleServiceImpl.saveModule(m);
  }
}