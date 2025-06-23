package org.example.dictionary.controller;

import org.example.dictionary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService){
        this.userService = userService;
    }

    @GetMapping("/allUsers")
    public String getUsers(Model model){
        model.addAttribute("userList", userService.getAll());

        return "userGet.html";
    }
}
