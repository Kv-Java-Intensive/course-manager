package com.itacademy.cms.repository;

import com.itacademy.cms.model.User;
import java.util.UUID;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends CrudRepository<User, UUID> {
}
