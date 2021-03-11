package com.group.appName.services;

import com.group.appName.models.UserEntity;
import org.springframework.stereotype.Service;

@Service
public interface UserService {
    void addUser(UserEntity userEntity);
    void deleteUser(String name);
    UserEntity editUser(String name);
    UserEntity getOne(String name);
}

