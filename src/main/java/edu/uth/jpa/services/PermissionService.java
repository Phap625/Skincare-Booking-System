package edu.uth.jpa.services;

import edu.uth.jpa.repositories.PermissionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import edu.uth.jpa.models.Permission;
import java.util.*;

@Service
public class PermissionService {
    @Autowired
    private PermissionRepository permissionRepository;
    public List<Permission> findAll() {
        return permissionRepository.findAll();
    }
    public Permission CreatePermission(Permission permission) {
        return permissionRepository.save(permission);
    }
    public Permission UpdatePermission(Permission permission) {
        return permissionRepository.save(permission);
    }
}
