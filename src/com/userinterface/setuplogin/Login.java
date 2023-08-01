package com.userinterface.setuplogin;

import com.database.ConnectionProvider;

import com.userinterface.components.*;
import com.userinterface.project.applicationFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Login extends JFrame {
    JLabel emailL, passwordL;
    JTextField emailT;
    JPasswordField passwordT;
    JButton loginB, registerB;

    public Login(){
//        Set the Title of Login Frame
        super("Login");
        setLayout(new BorderLayout());

//        Color.decode("#EDF2FB");

        imageComponent testLabel = new imageComponent("sprites/users/user_avatar_icon.png",200 ,200);
        add(testLabel,BorderLayout.NORTH);

//        Initializing Panel to add Elements named loginPanel
        JPanel loginPanel = new JPanel(new GridBagLayout());

//        GridBag Constraints
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(0,0,10,20);

//        Row 2: Email
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridy=0;gbc.gridx=0;
        gbc.gridwidth = 3;
        emailL = new labelComponent("Email:",25);
        loginPanel.add(emailL,gbc);

        gbc.gridy=1;gbc.gridwidth=3;
        emailT = new textboxComponent(20);
        loginPanel.add(emailT,gbc);

//        Row 3: Password
        gbc.gridy=2;gbc.gridwidth=1;
        passwordL = new labelComponent("Password:",25);
        loginPanel.add(passwordL,gbc);

        gbc.gridy=3;gbc.gridwidth=3;
        passwordT = new passwordComponent(20);
        loginPanel.add(passwordT,gbc);

//        Row 4: Buttons
        gbc.gridwidth=2;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.fill=GridBagConstraints.HORIZONTAL;

        gbc.gridy = 4; gbc.gridx= 0;
        loginB = new buttonComponent("Login","sprites/icons/login_Ico.png",20,Color.GREEN,Color.BLACK);
        loginB.addActionListener(e -> login(emailT.getText(), String.valueOf(passwordT.getPassword())));
        loginPanel.add(loginB,gbc);

        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.fill=GridBagConstraints.NONE;
        gbc.gridwidth=1;
        gbc.gridx = 2;
        registerB = new buttonComponent("Register","sprites/iconfinder/buttons/8542459_user_plus_icon.png",20,Color.decode("#E2EAFC"),Color.BLACK);
        registerB.addActionListener(e -> register());
        loginPanel.add(registerB,gbc);
        
//        Adding Login Panel to JFrame
        add(loginPanel,BorderLayout.CENTER);


//        Properties of Login Frame.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500,600);
        setVisible(true);

    }

    private void login(String Email, String Password){
        try {
            Connection con;
            ConnectionProvider _connectionProvider = new ConnectionProvider();
            con = _connectionProvider.getConnection();

            String query = "SELECT acc_type, userid FROM login where email = ? and password = ?";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, Email);
            statement.setString(2, Password);

            try (ResultSet rs = statement.executeQuery()) {
                if (rs.next()) {
                    setVisible(false);
                    new applicationFrame(rs.getByte("acc_type"), rs.getInt("userid"));
                }
                else {
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
}