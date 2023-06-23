package com.practice.controller;

import com.practice.User;
import com.practice.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping
    public ModelAndView getAllUsers() {
         List<User> list = this.userService.getAllUsers();
         return new ModelAndView("user","user", list);
    }

    @GetMapping("/{id}")
    public String getUser(@PathVariable("id") int id) {
        userService.getUserById(id);
        return "user";
    }

    @PostMapping
    public String addUser(@ModelAttribute User user) {
        this.userService.addUser(user);
        return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") int id){
        this.userService.deleteUser(id);
        return "redirect:/user";
    }

    @DeleteMapping
    public String deleteAllUsers() {
        this.userService.deleteAllUsers();
        return "redirect:/user";
    }

}
