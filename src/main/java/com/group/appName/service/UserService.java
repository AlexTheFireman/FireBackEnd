package com.group.appName.service;

import com.group.appName.model.User;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface UserService {
    void addUser(User user);
    void delete(Integer id);
    User editUser(String name);
    List<User> getAll();
    User getOne(Integer id);
}

