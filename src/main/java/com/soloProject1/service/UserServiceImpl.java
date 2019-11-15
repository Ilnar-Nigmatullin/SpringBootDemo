package com.soloProject1.service;

import com.soloProject1.dao.UserDAO;
import com.soloProject1.model.User;
import com.soloProject1.dao.UserDAO;
import com.soloProject1.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    private UserDAO userDAO;

    @Autowired
    public UserServiceImpl(UserDAO userDAO) {
        this.userDAO = userDAO;
    }

    public List<User> readUsers() {
        return userDAO.readUsers();
    }

    public void addUser(User user) {
        userDAO.addUser(user);
    }

    public void editUser(User user) {
        userDAO.editUser(user);
    }

    public void deleteUser(User user) {
        userDAO.deleteUser(user);
    }

    @Override
    public User findByUserName(String userName) {
        return userDAO.findByUserName(userName);
    }

    @Override
    public User findUserById(int id) {
        return userDAO.findUserById(id);
    }
}
