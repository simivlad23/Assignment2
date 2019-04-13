package com.ps.lab.service.impl;

import com.ps.lab.model.User;
import com.ps.lab.service.ContextHolder;

public class ContextHolderImpl implements ContextHolder {
    private User currentUser;

    @Override
    public void setCurrentUser(User user) {
        currentUser = user;
    }

    @Override
    public User getCurrentUser() {
        return currentUser;
    }
}
