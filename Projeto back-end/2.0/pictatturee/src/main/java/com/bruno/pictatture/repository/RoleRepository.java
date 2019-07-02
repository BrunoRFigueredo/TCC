package com.bruno.pictatture.repository;

import com.bruno.pictatture.models.Role;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface RoleRepository extends CrudRepository<Role, Long> {

    List<Role> findBy();

    Optional<Role> findById(Long id);

    Role findRoleByNameIs(String name);
}
