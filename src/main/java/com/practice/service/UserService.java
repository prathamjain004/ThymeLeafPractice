package com.practice.service;

import com.practice.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static List<User> users = new ArrayList<>();

    public List<User> getAllUsers() {
        return users;
    }

    private static int todosCount = 0;

//    public User getUserById(int id) {
//        User user = null;
//        user = users.stream().filter(e->e.getId()==id).findFirst().get();
//        return user;
//    }

    public User addUser(String name, String email, Long phone) {
        User user = new User(++todosCount, name, email, phone);
        users.add(user);
        return user;
    }

    public void deleteUser(int id) {
        Predicate<? super User> predicate = user -> user.getId() == id;
        users.removeIf(predicate);
    }

//    public void deleteAllUsers() {
//        users.clear();
//    }
}
