package com.example.springapp.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import com.example.springapp.entities.User;
import com.example.springapp.repositories.UserRepository;

import java.util.*;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Page<User> getAllUsers(int page, int size, String sortBy, String direction) {
        Sort sort = direction.equalsIgnoreCase("desc") ? Sort.by(sortBy).descending() : Sort.by(sortBy).ascending();
        Pageable pageable = PageRequest.of(page, size, sort);
        return userRepository.findAll(pageable);
    }

    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(User user) {
        return userRepository.save(user);
    }

    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id).map(user -> {
            if (userDetails.getName() != null) user.setName(userDetails.getName());
            if (userDetails.getUsername() != null) user.setUsername(userDetails.getUsername());
            if (userDetails.getEmail() != null) user.setEmail(userDetails.getEmail());
            if (userDetails.getPassword() != null) user.setPassword(userDetails.getPassword());
            if (userDetails.getPhoneNumber() != null) user.setPhoneNumber(userDetails.getPhoneNumber());
            if (userDetails.getRole() != null) user.setRole(userDetails.getRole());
            return userRepository.save(user);
        }).orElseThrow(() -> new RuntimeException("User not found"));
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public User getByUsername(String username){
        return userRepository.findByUsername(username).orElse(null);
    }
}
