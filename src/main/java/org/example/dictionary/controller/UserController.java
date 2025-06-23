package org.example.dictionary.controller;

import org.example.dictionary.model.User;
import org.example.dictionary.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.swing.text.html.parser.Entity;

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

    @PostMapping("/addUser")
    public ResponseEntity<String> addUser(@RequestBody User user){
        userService.addUser(user);

        return ResponseEntity.ok("User added");
    }
}
