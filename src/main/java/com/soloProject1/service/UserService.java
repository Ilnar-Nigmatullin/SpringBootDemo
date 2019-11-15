package com.soloProject1.service;

import com.soloProject1.model.User;

import java.util.List;

public interface UserService {
    List<User> readUsers();

    void addUser(User user);

    void editUser(User user);

    void deleteUser(User user);

    User findByUserName(String userName);

    User findUserById(int id);
}
