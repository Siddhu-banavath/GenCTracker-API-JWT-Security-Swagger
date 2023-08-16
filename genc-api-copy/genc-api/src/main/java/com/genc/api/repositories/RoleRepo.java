package com.genc.api.repositories;


import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.genc.api.entities.Role;

@Transactional
public interface RoleRepo extends JpaRepository<Role, Integer>{



	


}
