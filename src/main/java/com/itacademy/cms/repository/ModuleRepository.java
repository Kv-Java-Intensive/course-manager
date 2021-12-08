package com.itacademy.cms.repository;

import com.itacademy.cms.model.Module;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends CrudRepository<Module, Long> {
  Module getById(Long id);
}
