package com.ps.lab.controller;

import com.ps.lab.model.User;
import com.ps.lab.service.UserService;
import com.ps.lab.view.LoginView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class LoginController {

    private final UserService userService;

    public LoginController(LoginView loginView,
                           UserService userService) {

        this.userService = userService;

        //culegem date din model (service, etc)
            //mapari de obiecte

        //populam UI-ul
    }

    //cate o clasa privata care implementeaza
    //ActionListener pentru fiecare buton
    private class LoginActionListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
//            String username = ui.getUsername();
//            String password = ui.getPassword();
//
//            User currentUser = userService.login(username, password);
//            if(currentUser != null) {
//                //successful login
//                contextHolder.setCurrentUser(currentUser);
//                getOuter().setVisible(false);
//                if(currentUser.isAdmin()) {
//                    System.out.println("open admin panel");
//                } else {
//                    Main.openUserView();
//                }
//            } else {
//                cleatInputs();
//            }
        }
    }

    //metode private ajutatoare

}
