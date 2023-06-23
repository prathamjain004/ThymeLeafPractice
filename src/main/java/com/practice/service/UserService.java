package com.practice.service;

import com.practice.User;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {
    private static List<User> users = new ArrayList<>();

    private static int usersCount = 0;

    static {
        users.add(new User(++usersCount, "Pratham", "prathamjain@gmail.com", 9876543210L));
    }

    public List<User> getAllUsers() {
        return users;
    }

    public User getUserById(int id) {
        User user = null;
        user = users.stream().filter(e->e.getId()==id).findFirst().get();
        return user;
    }

    public User addUser(User user) {
        users.add(user);
        return user;
    }

    public void deleteUser(int id) {
        users.stream().filter(e->e.getId()!=id).collect(Collectors.toList());
    }

    public void deleteAllUsers() {
        users.clear();
    }
}
