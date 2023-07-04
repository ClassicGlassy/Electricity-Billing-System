package com.userinterface;

import com.database.RegisterAndLoginQuery;

import com.userinterface.components.*;

import javax.swing.*;
import java.awt.*;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Login extends JFrame {

    JLabel loginImage, emailL, passwordL;
    JTextField emailT;
    JPasswordField passwordT;
    JButton loginB, registerB;

    public Login(){
//        Set the Title of Login Frame
        super("Login");
        setLayout(new BorderLayout());

//        Row 1: Picture
        JPanel loginPicPanel = new JPanel(new GridBagLayout());
        loginPicPanel.setBackground(Color.WHITE);
        GridBagConstraints gbc1 = new GridBagConstraints();
        gbc1.gridx =0;gbc1.gridy = 0;
        loginImage = new imageComponent("sprites/icons/user_avatar_icon.png",160,160);
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
        emailL = new labelComponent("Email:");
        loginPanel.add(emailL,gbc);

        gbc.gridy=1;gbc.gridwidth=3;
//        usernameT = new JTextField(20);
        emailT = new textboxComponent(20);
        loginPanel.add(emailT,gbc);

//        Row 3: Password
        gbc.gridy=2;gbc.gridwidth=1;
//        passwordL = new JLabel("Password:");
        passwordL = new labelComponent("Password:");
        loginPanel.add(passwordL,gbc);

        gbc.gridy=3;gbc.gridwidth=3;
        passwordT = new passwordComponent(20);
        loginPanel.add(passwordT,gbc);

//        Row 4: Buttons
        gbc.gridwidth=2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill=GridBagConstraints.HORIZONTAL;

        gbc.gridy = 4; gbc.gridx= 0;
        loginB = new buttonComponent("Login","sprites/icons/login_Ico.png",15,Color.GREEN,Color.BLACK);
        loginB.addActionListener(e -> login());
        loginPanel.add(loginB,gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridwidth=1;
        gbc.gridx = 2;
        registerB = new buttonComponent("Register","sprites/icons/register_Ico.png",15,Color.PINK,Color.BLACK);
        registerB.addActionListener(e -> register());
        loginPanel.add(registerB,gbc);


//        TODO Remove
//        Settings Button
        /*
        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridwidth=1;
        gbc.gridx = 2;gbc.gridy=0;
        registerB = new buttonComponent("","sprites/icons/settings.png",15,Color.PINK,Color.BLACK);
        registerB.addActionListener(e -> settings());
        loginPanel.add(registerB,gbc);

         */

//        Adding Login Panel to JFrame
        add(loginPanel,BorderLayout.CENTER);


//        Properties of Login Frame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,600);
        setVisible(true);

    }

    private void login(){
        try {
            RegisterAndLoginQuery Login = new RegisterAndLoginQuery();
            try (ResultSet rs = Login.loginUser(emailT.getText(), String.valueOf(passwordT.getPassword()))) {
                if (rs.next()) {
                    setVisible(false);
                    if (rs.getByte("acc_type") == 0) new Project("Admin");
                    else new Project("Tenant");
                } else {
                    JOptionPane.showMessageDialog(null, "Invalid Login Credentials");
                    clear();
                }
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

    private void register(){
        setVisible(false);
        JFrame frame = new Register();
        frame.setLocationRelativeTo(null);
    }
    private void clear(){
        emailT.setText("");
        passwordT.setText("");
        emailT.requestFocus();
    }

//    TODO Remove from Future
//    private void settings(){
//        setVisible(false);
//        JFrame frame = new ConfigDB_Settings();
//        frame.setLocationRelativeTo(null);
//    }


}