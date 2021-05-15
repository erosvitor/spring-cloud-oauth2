package com.ctseducare.oauth2.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ctseducare.oauth2.entities.Permission;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Integer> {

}
