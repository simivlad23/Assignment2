package com.ps.lab.service;

import com.ps.lab.model.User;

public interface ContextHolder {
    void setCurrentUser(User user);

    User getCurrentUser();

//  orice tine de starea aplicatiei
}
