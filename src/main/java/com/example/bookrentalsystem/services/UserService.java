package com.example.bookrentalsystem.services;

import com.example.bookrentalsystem.models.User;
import com.example.bookrentalsystem.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User createUser(User user) {
        return this.userRepository.save(user);
    }

    public Optional<User> getUserById(Long userId) {
        return userRepository.findById(userId);
    }

    public Optional<User> getUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

//    public User updateUser(Long userId, User updatedUser) {
//        return updatedUser;
//    }

    public void deleteUser(Long userId){
        userRepository.deleteById(userId);
    }

    public User updateUser(Long userId, User updatedUser) {
        Optional<User> optionalUser = userRepository.findById(userId);
        if(optionalUser.isPresent()) {
            User user = optionalUser.get();
            user.setUsername(updatedUser.getUsername());
            user.setPassword(updatedUser.getPassword());
            user.setEmail(updatedUser.getEmail());
        }
    }
}