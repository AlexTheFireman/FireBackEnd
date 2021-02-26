package com.group.appName.controller;

import com.group.appName.service.implimentation.UserServiceImpl;
import com.group.appName.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.bind.annotation.*;

@SpringBootApplication
@RestController
@EnableTransactionManagement
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping(value = "/api/user/add")
    public void addUser(@RequestBody User user){
        userServiceImpl.addUser(user);
    }

    @DeleteMapping(value = "/api/user/delete/{id}")
    public void deleteUserById (@PathVariable int id){
        userServiceImpl.delete(id);
    }

    @GetMapping(value = "/api/user/{id}")
    public void getUserById(@PathVariable int id){
        System.out.println(userServiceImpl.getOne(id));
    }

}

