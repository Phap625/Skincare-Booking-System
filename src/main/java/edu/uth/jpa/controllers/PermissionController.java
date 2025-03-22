package edu.uth.jpa.controllers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import edu.uth.jpa.models.Permission;
import edu.uth.jpa.services.PermissionService;
import java.util.List;
@RestController
@RequestMapping("/permission")
public class PermissionController {
    @Autowired
    private PermissionService permissionService;
    //methods REST
    /*
    * GET : ---> lay du lieu
    * Get {id} --> url/permission/{id}
    * Get url/permissions
    * POST: --> create(them moi)
    * PUT: ---> update toan bo (/{id})
    * PATCH --->update 1 phan cua object (/{id})
    * DELETE --> delete (/{id})
    * khi khai bao controller khong co dong tu trong url
    * Method se quyet dinh hanh vi
    * */

    @GetMapping("/permissions")
    public List<Permission> getAllPermissions() {
        return permissionService.findAll();
    }
    @PostMapping("/permission")
    public Permission addPermission(@RequestBody Permission permission) {
        return permissionService.CreatePermission(permission);
    }
    @PutMapping("/permission/{id}")
    public Permission updatePermission(@RequestBody Permission permission) {
        return permissionService.CreatePermission(permission);
    }
}
    