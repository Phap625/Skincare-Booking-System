package edu.uth.jpa.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import edu.uth.jpa.models.User;
import edu.uth.jpa.services.UserServices;
import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private UserServices userServices;
    @PostMapping
    public User createUser(@RequestBody User user) {
        return userServices.createUser(user);
    }
    @GetMapping()
    public List<User> Users() {
        return userServices.findAll();
    }
}
