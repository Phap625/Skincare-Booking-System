package edu.uth.jpa.services;
import edu.uth.jpa.models.User;
import edu.uth.jpa.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
public class UserServices   {
    @Autowired
    private UserRepository userRepository;
    public Optional<User> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
    public boolean existsByUsername(String username) {
        return true;
    }
    public User createUser(User user) {
        return userRepository.save(user);
    }
    public List<User> findAll() {
        return userRepository.findAll();
    }
    //trien khai cac methods save.delete
}
