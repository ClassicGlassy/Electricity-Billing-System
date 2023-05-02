package com.userinterface;

import javax.swing.*;
import java.awt.*;

public class login extends JFrame {
    public login() {
        super("Login");     //Sets the title of the JFrame
        setLayout(null);

/*
        TODO
        Import "OpenSans" font into the project.
        Import "User" image into the project.
*/



//        JPanel bgPanel Properties
        JPanel bgPanel = new JPanel();
        bgPanel.setBackground(Color.lightGray);
        bgPanel.setLayout(null);
        bgPanel.setBounds(0,0,360,480);


//        JImage User properties
        ImageIcon userIcon = new ImageIcon(ClassLoader.getSystemResource(
                "sprites/icons/8542610_user_avatar_icon.png"));
        Image temp = userIcon.getImage().getScaledInstance(112,128,Image.SCALE_DEFAULT);
        ImageIcon userIconN = new ImageIcon(temp);

        JLabel userIco = new JLabel(userIconN);
        userIco.setBounds(124,15, 112,128);
        bgPanel.add(userIco);


//        JLabel Username Properties
        JLabel usernameL = new JLabel("USERNAME");
        usernameL.setBounds(40,175,180,25);
        bgPanel.add(usernameL);

//        JTextField Username Properties
        JTextField usernameT = new JTextField();
        usernameT.setBounds(40,205,270,25);
        bgPanel.add(usernameT);

//        JLabel Password Properties
        JLabel passwordL = new JLabel("PASSWORD");
        passwordL.setBounds(40,260,182,25);
        bgPanel.add(passwordL);

//        JPasswordField Password Properties
        JPasswordField passwordT = new JPasswordField();
        passwordT.setBounds(40,290,270,25);
        bgPanel.add(passwordT);

//        JButton Login Properties
        JButton loginBtn = new JButton("LOGIN");
        loginBtn.setBounds(110,360,140,50);
        bgPanel.add(loginBtn);




        add(bgPanel);           //Adds Background Panel to JFrame

//        Parameters of JFrame
        setSize(new Dimension(360,480));
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setVisible(true);
    }

/*
    REMOVE main function
    MAIN Function only to INVOKE login constructor
*/

    public static void main(String[] args) {
        login login = new login();
    }

}
