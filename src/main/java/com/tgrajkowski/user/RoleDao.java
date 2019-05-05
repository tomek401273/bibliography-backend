package com.tgrajkowski.user;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RoleDao extends CrudRepository<Role, Long> {
    Role findByName(String name);
    List<Role> findAll();
}
