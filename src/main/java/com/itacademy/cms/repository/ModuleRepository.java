package com.itacademy.cms.repository;

import com.itacademy.cms.model.Module;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ModuleRepository extends CrudRepository<Module, UUID> {
  Module getById(UUID id);
}
