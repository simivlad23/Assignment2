package com.ps.lab.service.impl;

import com.ps.lab.model.User;
import com.ps.lab.repository.UserRepository;
import com.ps.lab.service.UserService;

public class UserServiceImpl implements UserService {

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User save(User user) {

        if (user.getId() != null) {
            return userRepository.update(user);
        } else {
            return userRepository.create(user);
        }
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public User login(String username, String password) {
        User user = userRepository.findByUsername(username);

        if (user != null) {
            if (user.getPassword().equals(password)) {
                return user;
            } else {
                System.out.println("bad password");
            }
        } else {
            System.out.println("no user with username: " + username);
    }

        return null;
    }
}
