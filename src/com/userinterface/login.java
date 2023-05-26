package com.userinterface;

import com.userinterface.components.labelComponent;
import com.userinterface.components.passwordComponent;
import com.userinterface.components.textboxComponent;

import javax.swing.*;
import java.awt.*;


public class login extends JFrame {

    JLabel loginImage, usernameL, passwordL;
    JTextField usernameT;
    JPasswordField passwordT;
    JButton loginB, registerB;

    public login(){
//        Set the Title of Login Frame
        super("Login");
        setLayout(new BorderLayout());

//        Initializing Utility Package
        Utility _util = new Utility();


//        Row 1: Picture
        JPanel loginPicPanel = new JPanel(new GridBagLayout());
        loginPicPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx =0;gbc1.gridy = 0;
        loginImage = new JLabel(_util.convertToIcon("sprites/icons/8542610_user_avatar_icon.png",160,160));
        loginPicPanel.add(loginImage,gbc1);
        add(loginPicPanel,BorderLayout.NORTH);

//        Initializing Panel to add Elements named loginPanel
        JPanel loginPanel = new JPanel(new GridBagLayout());
        loginPanel.setBackground(Color.WHITE);

//        GridBag Constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,10,20);

//        Row 2: Username
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridy=0;gbc.gridx=0;
        gbc.gridwidth = 3;
//        usernameL = new JLabel("Username:");
        usernameL = new labelComponent("Username:");
        loginPanel.add(usernameL,gbc);

        gbc.gridy=1;gbc.gridwidth=3;
//        usernameT = new JTextField(20);
        usernameT = new textboxComponent(15);
        loginPanel.add(usernameT,gbc);

//        Row 3: Password
        gbc.gridy=2;gbc.gridwidth=1;
//        passwordL = new JLabel("Password:");
        passwordL = new labelComponent("Pasword:");
        loginPanel.add(passwordL,gbc);

        gbc.gridy=3;gbc.gridwidth=3;
//        gbc.fill=GridBagConstraints.HORIZONTAL;
//        passwordT = new JPasswordField(20);
        passwordT = new passwordComponent(15);
        loginPanel.add(passwordT,gbc);

//        Row 4: Buttons
        gbc.gridwidth=2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill=GridBagConstraints.HORIZONTAL;

        gbc.gridy = 4; gbc.gridx= 0;
        loginB = new JButton("Login");
        loginPanel.add(loginB,gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridwidth=1;
        gbc.gridx = 2;
        registerB = new JButton("Register");
        registerB.addActionListener(e -> register());
        loginPanel.add(registerB,gbc);

//        Adding Login Panel to JFrame
        add(loginPanel,BorderLayout.CENTER);



//        Properties of Login Frame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,600);
        setVisible(true);

    }

    private void register(){
        JFrame frame = new register();
        frame.setLocationRelativeTo(null);
    }


}