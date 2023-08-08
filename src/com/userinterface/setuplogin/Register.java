package com.userinterface.setuplogin;

import com.database.ConnectionProvider;

import com.userinterface.components.*;

import javax.swing.*;
import java.awt.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Register extends JFrame{

//    Elements for JPanel
    JLabel title, nameL, emailL,passwordL, phoneL, acc_typeL;
    JTextField nameT,emailT,phoneT;
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
            String emailAddress = emailT.getText();
            String regexPattern = "^(?=.{1,64}@)[A-Za-z0-9_-]+(\\.[A-Za-z0-9_-]+)*@"
                    + "[^-][A-Za-z0-9-]+(\\.[A-Za-z0-9-]+)*(\\.[A-Za-z]{2,})$";

            Pattern pattern = Pattern.compile(regexPattern);
            Matcher matcher = pattern.matcher(emailAddress);

            if (matcher.matches() && !emailAddress.isEmpty()){
                try {
                    ConnectionProvider _connectionProvider = new ConnectionProvider();
                    Connection con = _connectionProvider.getConnection();
                    String query;
                    PreparedStatement statement;
                    ResultSet rs;

//                Checking if Email address exist or not
                    query = "SELECT EXISTS(SELECT * from login WHERE email = ?);";
                    statement = con.prepareStatement(query);
                    statement.setString(1, emailAddress);
                    rs = statement.executeQuery();
                    if (rs.next()) {
                        if(rs.getInt(1) == 0) {
//                        Email doesn't exist so we can register.

//                          First Query to users table
                            query = "insert into users(name,phone) values(?,?)";
                            statement = con.prepareStatement(query);
//                          Set the Value for users
                            statement.setString(1, nameT.getText());
                            statement.setString(2, phoneT.getText());
                            statement.executeUpdate();


//                          Find the ID of user
                            query = "Select id from users where name = ? AND phone = ?";
                            statement = con.prepareStatement(query);
                            statement.setString(1, nameT.getText());
                            statement.setString(2, phoneT.getText());
                            rs = statement.executeQuery();
                            int userid = 0;
                            if (rs.next()) {
                                userid = rs.getInt(1);
                            }

//                            HASH PASSWORD
                            String password = String.valueOf(passwordT.getPassword());
                            // get an instance of the SHA-256 message digest algorithm
                            MessageDigest md = MessageDigest.getInstance("SHA-256");
                            // compute the hash of the input string
                            byte[] hash = md.digest(password.getBytes());

                            // convert the hash to a hexadecimal string
                            StringBuilder hashPassword = new StringBuilder();
                            for (byte b : hash) {
                                hashPassword.append(String.format("%02x", b));
                            }


//                            Second Query to login table
                            query = "INSERT INTO login(email, password, userid, acc_type) VALUES(?,?,?,?)";
                            statement = con.prepareStatement(query);
                            statement.setString(1,emailAddress);
                            statement.setString(2, String.valueOf(hashPassword));
                            statement.setInt(3,userid);
                            statement.setInt(4, acc_typeC.getSelectedIndex());

                            statement.executeUpdate();

                            JOptionPane.showMessageDialog(null,"Account created successfully!");
                            login();


                        }
                        else{
                            JOptionPane.showMessageDialog(null,"Account with same Email Address already exist");
                            clear();
                        }
                    }



                } catch (SQLException e) {
                    JOptionPane.showMessageDialog(null,e.getMessage());
                } catch (NoSuchAlgorithmException e) {
                    throw new RuntimeException(e);
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Invalid Email Address");
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
