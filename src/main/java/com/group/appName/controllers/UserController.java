package com.group.appName.controllers;

import com.group.appName.services.implimentation.UserServiceImpl;
import com.group.appName.models.UserEntity;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotEmpty;
import java.util.List;


@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    UserServiceImpl userServiceImpl;

    @PostMapping(value = "/add")
    public void addUser(@RequestBody UserEntity userEntity){
        userServiceImpl.addUser(userEntity);
    }

    @DeleteMapping(value = "/delete/{userName}")
    public void deleteUser (@PathVariable String userName){
        userServiceImpl.deleteUser(userName);
    }

    @GetMapping(value = "/get/{userName}")
    public void getUserByUserName(@PathVariable String userName){
        userServiceImpl.getOne(userName);
    }

    @GetMapping(value = "/get/all", produces = "application/json")
    public List getUserList () {
        return userServiceImpl.getAllUsers();
    }

    @PutMapping(value = "/update/{userName}")
    public UserEntity updateUser(@PathVariable String userName, @RequestBody UserEntity userEntity){
        return userServiceImpl.editUser(userName);
    }
}

