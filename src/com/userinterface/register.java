package com.userinterface;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class register extends JFrame{

//    Elements for JPanel
    JLabel title, nameL, emailL,passwordL, phoneL, acc_typeL, meterL;
    JTextField nameT,emailT,phoneT,meterT;
    JPasswordField passwordT;
    JComboBox<String> acc_typeC;
    JButton submit,clear,back;

    public register(){
//        Set the Title of JFrame to Register.
        super("Register");
        setLayout(new BorderLayout());

//        Initializing Panel for Elements using for Registration
        JPanel registerPanel = new JPanel(new GridBagLayout());
        registerPanel.setBorder(new TitledBorder(new LineBorder(Color.BLUE),"Create Account"
                ,TitledBorder.LEADING,TitledBorder.TOP,null,Color.red));
        registerPanel.setBackground(Color.WHITE);

//        GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,10,20);
        

/* ---------------------------------------------------------------------------------*/

//        Row 1: Title
        gbc.gridx=0;gbc.gridy=0;
        gbc.gridwidth = 2;
        title = new JLabel("Register an Account");
        registerPanel.add(title,gbc);
        gbc.gridwidth = 1;

        gbc.anchor = GridBagConstraints.LINE_START;

//        Row 2: Create Account
//        Label
        gbc.gridx=0;gbc.gridy=1;
        acc_typeL = new JLabel("Create Account as:");
        registerPanel.add(acc_typeL,gbc);
//        TextField
        gbc.gridx=1;
        String[] acc_types = {"Admin","Tenant"};
        acc_typeC = new JComboBox<>(acc_types);
        registerPanel.add(acc_typeC,gbc);

//        Row 3: Name
        //        Label
        gbc.gridx=0;gbc.gridy=2;
        nameL = new JLabel("Name:");
        registerPanel.add(nameL,gbc);
//        TextField
        gbc.gridx=1;
        nameT = new JTextField(25);
        registerPanel.add(nameT,gbc);

//        Row 4: Email
//        Label
        gbc.gridx=0;gbc.gridy=3;
        emailL = new JLabel("Email:");
        registerPanel.add(emailL,gbc);
//        TextField
        gbc.gridx=1;
        emailT = new JTextField(25);
        registerPanel.add(emailT,gbc);

//        Row 5: Password
//        Label
        gbc.gridx=0;gbc.gridy=4;
        passwordL = new JLabel("Password:");
        registerPanel.add(passwordL,gbc);
//        TextField
        gbc.gridx=1;
        passwordT = new JPasswordField(25);
        registerPanel.add(passwordT,gbc);

//        Row 6: Phone
//        Label
        gbc.gridx=0;gbc.gridy=5;
        phoneL = new JLabel("Phone:");
        registerPanel.add(phoneL,gbc);
//        TextField
        gbc.gridx=1;
        phoneT = new JTextField(25);
        registerPanel.add(phoneT,gbc);

//        Row 7: Submit and Clear Buttons
//        Buttons

        gbc.anchor = GridBagConstraints.CENTER;

        gbc.gridx=0; gbc.gridy=6;
        submit = new JButton("Submit");

        submit.addActionListener(e -> submitQuery());

        submit.setBackground(Color.black);
        submit.setForeground(Color.white);
        registerPanel.add(submit,gbc);


        gbc.gridx=1; gbc.gridy=6;
        clear = new JButton("Clear");
        clear.addActionListener(e -> System.out.println("Clear Button Clicked!"));
        registerPanel.add(clear,gbc);
        clear.setBackground(Color.black);
        clear.setForeground(Color.white);


        /* ---------------------------------------------------------------------------------*/

//        Adding Panel to JFrame
        add(registerPanel,BorderLayout.CENTER);

//        Properties of Register Frame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,400);
        setVisible(true);
    }

    private void submitQuery(){
        System.out.println(nameT.getText());
        System.out.println(emailT.getText());
        System.out.println(passwordT.getPassword());
    }


}
