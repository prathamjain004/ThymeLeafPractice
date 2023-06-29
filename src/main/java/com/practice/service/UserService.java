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
//        try {
        user.setId(++usersCount);
        userRepository.save(user);
        return user;
//        } catch (DataIntegrityViolationException ex) {
//            throw new DuplicateUserException("User with the same email or phone number already exists.");
//        }
    }

    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }

}
