package com.group.appName.service.implimentation;

import com.group.appName.dao.UserDao;
import com.group.appName.model.User;
import com.group.appName.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserDao userDao;

    public void addUser (User user) {
        userDao.saveAndFlush(user);
    }

    @Override
    public void delete(Integer id) {
        userDao.deleteById(id);
    }

    @Override
    public User editUser(String name) {
        return null;
    }

    @Override
    public List<User> getAll() {
        return userDao.findAll();
    }

    @Override
    public User getOne(Integer id) {
        return userDao.getOne(id);
    }

}


