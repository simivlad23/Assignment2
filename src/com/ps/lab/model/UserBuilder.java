package com.ps.lab.model;

public class UserBuilder  {
    private Long id;
    private String username = "u";
    private String password = "p";
    private boolean admin;

    public UserBuilder setId(Long id) {
        this.id = id;
        return this;
    }

    public UserBuilder setUsername(String username) {
        this.username = username;
        return this;
    }

    public UserBuilder setPassword(String password) {
        this.password = password;
        return this;
    }

    public UserBuilder setAdmin(boolean admin) {
        this.admin = admin;
        return this;
    }

    public User build() {
        User user = new User();
        user.setId(this.id);
        user.setUsername(this.username);
        user.setPassword(this.password);
        user.setAdmin(this.admin);

        return user;
    }
}
