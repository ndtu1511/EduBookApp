package com.example.edubookapp.controller.androidapi;

import com.example.edubookapp.model.User;
import com.example.edubookapp.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserApi {
    @Autowired
    private UserService userService;

    @PreAuthorize("hasRole('MEMBER')")
    @GetMapping("/api/users")
    public Iterable<User> allUser(){
        return userService.findAll();
    }
    @GetMapping("/api/user/{id}")
    public User show(@PathVariable("id") Integer id){
        return userService.findOne(id).get();
    }
}
