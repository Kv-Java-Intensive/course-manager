package com.itacademy.cms.repository;

import com.itacademy.cms.model.Module;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleDAO extends CrudRepository<Module, Long> {
  Module getById(Long id);
}
