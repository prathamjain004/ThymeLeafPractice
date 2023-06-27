package com.practice.service;

import com.practice.Exception.DuplicateUserException;
import com.practice.User;
import com.practice.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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
        try {
        user.setId(++usersCount);
        userRepository.save(user);
        return user;
        } catch (DataIntegrityViolationException ex) {
            throw new DuplicateUserException("User with the same email or phone number already exists.");
        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
