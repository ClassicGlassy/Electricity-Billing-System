package com.userinterface;

import com.database.RegisterAndLoginQuery;

import com.userinterface.components.*;

import javax.swing.*;
import javax.swing.border.LineBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.sql.SQLException;


public class Register extends JFrame{

//    Elements for JPanel
    JLabel title, nameL, emailL,passwordL, phoneL, acc_typeL, meterL;
    JTextField nameT,emailT,phoneT,meterT;
    JPasswordField passwordT;
    JComboBox<String> acc_typeC;
    JButton submit,clear,back;



    public Register(){
//        Set the Title of JFrame to Register.
        super("Register");
        setLayout(new BorderLayout());
//        setBackground(Color.decode("#EDF2FB"));


//        Initializing Panel for Elements using for Registration
        JPanel registerPanel = new JPanel(new GridBagLayout());
//        registerPanel.setBorder(new TitledBorder(new LineBorder(Color.BLUE),"Create Account"
//                ,TitledBorder.LEADING,TitledBorder.TOP,null,Color.red));
        registerPanel.setBackground(Color.decode("#EDF2FB"));

//        GridBagConstraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,10,20);

/* ---------------------------------------------------------------------------------*/

//        Row 1: Title
        gbc.gridx=0;gbc.gridy=0;
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridwidth = 4;
        title = new labelComponent("Register an Account",25);
        registerPanel.add(title,gbc);
        gbc.gridwidth = 1;

        gbc.anchor = GridBagConstraints.LINE_START;


//        Row 2: Create Account
//        Label
        gbc.gridx=0;gbc.gridy=1;
        acc_typeL = new labelComponent("Create Account as:");
        registerPanel.add(acc_typeL,gbc);
//        ComboBox
        gbc.gridx=1;
        gbc.gridwidth=3;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        String[] acc_types = {"Admin","Tenant"};
        acc_typeC = new JComboBox<>(acc_types);
        acc_typeC.setSelectedIndex(1);
        registerPanel.add(acc_typeC,gbc);

        gbc.fill = GridBagConstraints.NONE;

//        Row 3: Name
        //        Label
        gbc.gridx=0;gbc.gridy=2;
        gbc.gridwidth=0;
        nameL = new labelComponent("Name:");
        registerPanel.add(nameL,gbc);
//        TextField
        gbc.gridx=1;
        gbc.gridwidth=3;
        nameT = new textboxComponent(25);

        registerPanel.add(nameT,gbc);

//        Row 4: Email
//        Label
        gbc.gridx=0;gbc.gridy=3;
        gbc.gridwidth=0;
        emailL = new labelComponent("Email:");
        registerPanel.add(emailL,gbc);
//        TextField
        gbc.gridx=1;
        gbc.gridwidth=3;
        emailT = new textboxComponent(25);
        registerPanel.add(emailT,gbc);

//        Row 5: Password
//        Label
        gbc.gridx=0;gbc.gridy=4;
        gbc.gridwidth=0;
        passwordL = new labelComponent("Password:");
        registerPanel.add(passwordL,gbc);
//        TextField
        gbc.gridx=1;
        gbc.gridwidth=3;
        passwordT = new passwordComponent(25);
        registerPanel.add(passwordT,gbc);

//        Row 6: Phone
//        Label
        gbc.gridx=0;gbc.gridy=5;
        gbc.gridwidth=0;
        phoneL = new labelComponent("Phone:");
        registerPanel.add(phoneL,gbc);
//        TextField
        gbc.gridx=1;
        gbc.gridwidth=3;
        phoneT = new textboxComponent(25);
        registerPanel.add(phoneT,gbc);

//        Row 7: Submit and Clear Buttons
//        Buttons

        gbc.anchor = GridBagConstraints.LINE_START;

        gbc.gridx=1; gbc.gridy=6;
        submit = new buttonComponent("Submit","sprites/icons/register_Ico.png",15,Color.green,Color.BLACK);
        submit.addActionListener(e -> submitQuery());

        registerPanel.add(submit,gbc);

        gbc.gridx=3;
        gbc.anchor = GridBagConstraints.LINE_END;
        clear = new buttonComponent("Clear","sprites/icons/clear_Ico.png",15,Color.decode("#CCDBFD"),Color.black);
        clear.addActionListener(e -> clear());

        registerPanel.add(clear,gbc);

        gbc.anchor = GridBagConstraints.NORTHWEST;
        gbc.gridy = 0;gbc.gridx = 0;
        gbc.gridwidth = 3;
        back = new buttonComponent("Back",Color.decode("#E2EAFC"),Color.BLACK);
        back.addActionListener(e -> login());
        registerPanel.add(back,gbc);

/* ---------------------------------------------------------------------------------*/

//        Adding Panel to JFrame
        add(registerPanel,BorderLayout.CENTER);

//        Properties of Register Frame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700,400);
        setVisible(true);
    }

//    Functions

    private void submitQuery(){
        int option = JOptionPane.showConfirmDialog(null,"Do you want to register?");
        if(option == 0) {
            try {
                RegisterAndLoginQuery _queryProvider = new RegisterAndLoginQuery();
                _queryProvider.registerUser(nameT.getText(), emailT.getText(),
                        String.valueOf(passwordT.getPassword()), phoneT.getText(), (byte) acc_typeC.getSelectedIndex());
                JOptionPane.showMessageDialog(null,"Account created successfully!");
                login();
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        }
    }

    private void login(){
        setVisible(false);
        JFrame frame = new Login();
        frame.setLocationRelativeTo(null);
    }

    private void clear(){
        nameT.setText("");
        emailT.setText("");
        phoneT.setText("");
        passwordT.setText("");
        acc_typeC.setSelectedIndex(1);
        nameT.requestFocus();
    }


}
