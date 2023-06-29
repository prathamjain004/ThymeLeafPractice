package com.practice.controller;

import com.practice.User;
import com.practice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/user")
@Validated
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        super();
        this.userService = userService;
    }

    @GetMapping
    public String getAllUsers(Model model) {
         User user = new User();
         model.addAttribute("user", user);
        List<User> userList = userService.getAllUsers();
        List<User> uniqueUsers = userList.stream().distinct()
                .collect(Collectors.toList());
        model.addAttribute("users", uniqueUsers);
         return "user";
    }


    @PostMapping
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result, Model model) {
//        if (result.hasErrors()) {
//            FieldError fieldError = result.getFieldErrors().get(0);
//            String errorMessage = fieldError.getDefaultMessage();
//            model.addAttribute("errorMessage", errorMessage);
//            return "user";
//        }
//        try {
            userService.addUser(user);
            return "redirect:/user";
//        } catch (DuplicateUserException ex) {
//            model.addAttribute("phone", ex.getMessage());
//            return "/user";
//        }
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/user";
    }

}
