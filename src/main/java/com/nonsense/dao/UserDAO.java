package com.nonsense.dao;

import com.nonsense.model.User;

import java.util.List;

public interface UserDAO {

    List<User> getAll();

    User getOne(String email);

    void add(User user);
}
