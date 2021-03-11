package com.group.appName.services.implimentation;

import com.group.appName.repositories.UserRepository;
import com.group.appName.models.UserEntity;
import com.group.appName.services.UserService;
import org.h2.engine.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Transactional
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserRepository userRepository;

    public void addUser (UserEntity user)
    {
        userRepository.save(user);
    }

    @Override
    public void deleteUser(String username) {
        userRepository.deleteByUsername(username);
    }

    @Override
     public UserEntity editUser(String name) {
        UserEntity userEntity = userRepository.getUserEntityByUsernameIsLike(name);

        return userEntity;
    }

    public List<String> getAllUsers() {
        return userRepository.getAllUserNames();
    }

    @Override
    public UserEntity getOne(String name)
    {
        return userRepository.getUserEntityByUsernameIsLike(name);
    }

}


