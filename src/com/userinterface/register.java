package com.userinterface;

import javax.swing.*;
import java.awt.*;

public class register extends JFrame {

//    Elements for JPanel
    JLabel title, nameL, emailL,passwordL, phoneL, acc_typeL, meterL;
    JTextField nameT,emailT,phoneT,meterT;
    JPasswordField passwordT;
    JButton submit,back;

    public register(){
//        Set the Title of JFrame to Register.
        super("Register");
        setLayout(new BorderLayout());

//        Initializing Panel for Elements using for Registration
        JPanel registerPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

//        Row 1: Title
        gbc.gridx=0;gbc.gridy=0;
        gbc.gridwidth = 2;
        title = new JLabel("Register an Account");
        registerPanel.add(title,gbc);
        gbc.gridwidth = 1;

//        Row 2: Create Account
//        Label
        gbc.gridx=0;gbc.gridy=1;
        acc_typeL = new JLabel("Create Account:");
        registerPanel.add(acc_typeL,gbc);


//        Adding Panel to JFrame
        add(registerPanel,BorderLayout.CENTER);

//        Properties of Register Frame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,400);
        setVisible(true);


    }

}
