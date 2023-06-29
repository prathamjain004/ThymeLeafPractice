package com.practice.service;

import com.practice.User;
import com.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
       return userRepository.findAll();
    }

    private static int usersCount = 0;

    public User addUser(User user) {
        if (isEmailExists(user.getEmail())) {
            throw new IllegalArgumentException("Email already exists");
        }

        if (isPhoneExists(user.getPhone())) {
            throw new IllegalArgumentException("Phone number already exists");
        }
        user.setId(++usersCount);
        userRepository.save(user);
        return user;
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

    public boolean isEmailExists(String email) {
        return userRepository.existsByEmail(email);
    }

    public boolean isPhoneExists(Long phone) {
        return userRepository.existsByPhone(phone);
    }

}
