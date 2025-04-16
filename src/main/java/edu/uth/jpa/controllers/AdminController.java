package edu.uth.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import edu.uth.jpa.models.User;
import edu.uth.jpa.services.UserServices;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private UserServices userService;

    @GetMapping("/list-user")
    public String adminPage() {
        return "master/admin/index";
    }

    // Tạo mới user
    @PostMapping
    public ResponseEntity<String> createUser(@RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok("✅ Đăng ký user thành công!");
    }

    // Lấy danh sách tất cả user
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.findAll();
        return ResponseEntity.ok(users);
    }

    // Cập nhật thông tin user
    @PutMapping("/{id}")
    public ResponseEntity<?> updateUser(@PathVariable("id") Long id, @RequestBody User user) {
        try {
            user.setId(id); // đảm bảo đúng id
            User updatedUser = userService.updateUser(user);
            return ResponseEntity.ok(updatedUser);
        } catch (RuntimeException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Không tìm thấy user để cập nhật!");
        }
    }

    // Xoá user
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable("id") Long id) {
        boolean deleted = userService.deleteUser(id);
        if (deleted) {
            return ResponseEntity.ok("✅ Xoá user thành công!");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("❌ Không tìm thấy user để xoá!");
        }
    }
}

