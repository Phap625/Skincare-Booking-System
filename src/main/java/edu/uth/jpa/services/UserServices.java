package edu.uth.jpa.services;
import java.util.Collections;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import edu.uth.jpa.dtos.RegisterDTO;
import edu.uth.jpa.models.User;
import edu.uth.jpa.repositories.UserRepository;

import java.util.*;

@Service
public class UserServices implements UserDetailsService  {
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return new org.springframework.security.core.userdetails.User(
                user.getUsername(), user.getPassword(), Collections.singleton(new SimpleGrantedAuthority(user.getRole()))
        );
    }
    public String registerUser(RegisterDTO registerDTO) {

        if (userRepository.existsByUsername(registerDTO.getUsername())) {
            return "User already exists!";
        }

        User user = new User();
        user.setUsername(registerDTO.getUsername());
        user.setPassword(passwordEncoder.encode(registerDTO.getPassword()));
        user.setRole(registerDTO.getRole());
        user.setEmail(registerDTO.getEmail());

        userRepository.save(user);
        return "User registered successfully!";
    }

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
