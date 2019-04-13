package com.ps.lab.view;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
import java.awt.event.ActionListener;

import static javax.swing.BoxLayout.Y_AXIS;

public class AdminView extends JFrame {
    private static final String TITLE = "Admin view";
    private static final int WIDTH = 300;
    private static final int HEIGHT = 300;
    private JLabel label = new JLabel("empty user");
    private JButton logout = new JButton("Logout");

    public AdminView() throws HeadlessException {
        super(TITLE);
        setVisible(false);
        initializeView();
        setSize(WIDTH, HEIGHT);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }

    private void initializeView() {
        setLayout(new BoxLayout(getContentPane(), Y_AXIS));
        add(Box.createRigidArea(new Dimension(20, 20)));
        label.setBorder(new EmptyBorder(10, 10, 10, 10));
        add(label);
        add(Box.createRigidArea(new Dimension(20,20)));
        add(logout);
    }

    public void addLogoutActionListener(ActionListener listener) {
        logout.addActionListener(listener);
    }

    public void setLabel(String userName) {
        this.label.setText("logged in as " + userName + " with ADMIN role");
    }
}
