package com.manager.tab.tabmanager.controller;

import com.manager.tab.tabmanager.entity.User;
import com.manager.tab.tabmanager.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;

import java.util.List;

@RestController
public class UserController {
    @Autowired private UserService userService;

    @PostMapping("/users")
    public User saveUser(
            @Valid @RequestBody User user){
        return userService.saveUser(user);
    }

    @GetMapping("/users")
    public List<User> fetchUserList(){
        return userService.fetchUserList();
    }

    @PutMapping("/users/{id}")
    public User
    updateUser(@RequestBody User user, @PathVariable("id") Long userId){
        return userService.updateUser(user, userId);
    }

    @DeleteMapping("/users/{id}")
    public String deleteUserById(@PathVariable("id") Long userId){
        userService.deleteUserById(userId);
        return "Deleted Successfully";
    }

    @GetMapping("/hello")
    public String hello(@RequestParam(value = "name", defaultValue = "World") String name) {
        return String.format("Hello %s!", name);
    }
}
