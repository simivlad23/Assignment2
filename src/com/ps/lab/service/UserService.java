package com.ps.lab.service;

import com.ps.lab.model.User;

public interface UserService {

    User save(User user);

    void delete(Long id);

    User login(String username, String password);
}
