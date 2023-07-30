package com.initial;

import com.database.InitialSetup;
import com.userinterface.setuplogin.ConfigDataBaseConnection;
import com.userinterface.setuplogin.Login;

import javax.swing.*;
import java.io.*;
import java.sql.SQLException;

public class Init{
    public Init(){
//        Check if File config.cfg exist or not. If not creates
        File f = new File("config.cfg");
        if(!f.exists()) {
            new SerializeFile("root", "", "3306");
        }
        try {
            new InitialSetup();
        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Warning!",JOptionPane.ERROR_MESSAGE);
            JFrame c = new ConfigDataBaseConnection();
            c.setLocationRelativeTo(null);
            return;
        }

//        Invokes Login frame.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Login();
                frame.setLocationRelativeTo(null);
            }
        });

    }

}