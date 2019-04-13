package com.ps.lab.view;

import com.ps.lab.Main;
import com.ps.lab.model.User;
import com.ps.lab.service.ContextHolder;
import com.ps.lab.service.UserService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginView  extends JFrame {
    private static final String TITLE = "Login";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 150;
    private JLabel userNameLabel = new JLabel("Username");
    private JLabel passwordLabel = new JLabel("Password");
    private JTextField userNameTextField = new JTextField();
    private JPasswordField passwordTextField = new JPasswordField();
    private JButton loginButton = new JButton("Login");
    private JButton registerButton = new JButton("Register");

    private final UserService userService;
    private final ContextHolder contextHolder;

    public LoginView(UserService userService, ContextHolder contextHolder) throws HeadlessException {
        super(TITLE);
        setVisible(false);
        initializeView();
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

        this.userService = userService;
        this.contextHolder = contextHolder;
    }

    private void initializeView() {
        setLayout(null);
        userNameLabel.setBounds(10, 10, 80, 25);
        passwordLabel.setBounds(10, 40, 80, 25);
        userNameTextField.setBounds(100, 10, 160, 25);
        passwordTextField.setBounds(100, 40, 160, 25);
        loginButton.setBounds(10, 80, 80, 25);
        registerButton.setBounds(180, 80, 80, 25);

        add(userNameLabel);
        add(passwordLabel);
        add(userNameTextField);
        add(passwordTextField);
        add(loginButton);
        add(registerButton);

        loginButton.addActionListener(new LoginActionListener());
        registerButton.addActionListener(new RegisterActionListener());
    }

    //use these 2 methods when adding ActionListeners
    //that are implemented in another class
    public void addLoginActionListener(ActionListener listener) {
        loginButton.addActionListener(listener);
    }

    public void addRegisterActionListener(ActionListener listener) {
        registerButton.addActionListener(listener);
    }


    //use these public methods when working with a controller
    public String getUserName() {
        return this.userNameTextField.getText();
    }

    public String getPassword() {
        return this.passwordTextField.getText();
    }

    public void cleatInputs() {
        this.userNameTextField.setText("");
        this.passwordTextField.setText("");
    }

    public LoginView getOuter() {
        return LoginView.this;
    }

    private class LoginActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String username = userNameTextField.getText();
            String password = passwordTextField.getText();

            User currentUser = userService.login(username, password);
            if(currentUser != null) {
                //successful login
                contextHolder.setCurrentUser(currentUser);
                getOuter().setVisible(false);
                if(currentUser.isAdmin()) {
                    System.out.println("open admin panel");
                } else {
                    Main.openUserView();
                }
            } else {
                cleatInputs();
            }
        }
    }

    private class RegisterActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {

        }
    }
}