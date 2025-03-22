package edu.uth.jpa.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import edu.uth.jpa.models.Permission;

import java.util.*;

@Repository
public interface PermissionRepository extends JpaRepository<Permission, Long> {
    @Override
    List<Permission> findAll();

}
