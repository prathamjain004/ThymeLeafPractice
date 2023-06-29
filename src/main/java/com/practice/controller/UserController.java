package com.practice.controller;

import com.practice.User;
import com.practice.service.UserService;
import jakarta.validation.Valid;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;
@Controller
@RequestMapping("/user")
@Validated
public class UserController {

    private UserService userService;
    private RestTemplate restTemplate;

    public UserController(UserService userService, RestTemplate restTemplate) {
        this.userService = userService;
        this.restTemplate = restTemplate;
    }

    @GetMapping
    public String getAllUsers(Model model) {
         User user = new User();
         model.addAttribute("user", user);
        model.addAttribute("users", userService.getAllUsers());

         return "user";
    }


    @PostMapping
    public String addUser(@Valid @ModelAttribute("user") User user, BindingResult result) {

        if (result.hasErrors()) {
            return "/user";
        }
        if (userService.isEmailExists(user.getEmail())) {
            result.rejectValue("email", "error.email", "Email already exists");
            return "/user";
        }

        if (userService.isPhoneExists(user.getPhone())) {
            result.rejectValue("phone", "error.phone", "Phone number already exists");
            return "/user";
        }
            userService.addUser(user);
        String receiverUrl = "http://localhost:5000/notification";
        restTemplate.postForObject(receiverUrl, user, User.class);
            return "redirect:/user";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") Long id){
        userService.deleteUser(id);
        return "redirect:/user";
    }

}
