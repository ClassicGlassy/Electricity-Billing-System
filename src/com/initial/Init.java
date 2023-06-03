package com.initial;

import com.database.InitialSetup;
import com.userinterface.ConfigDB_Settings;
import com.userinterface.ConfigDB_Setup;
import com.userinterface.Login;

import javax.swing.*;
import java.io.*;
import java.sql.SQLException;

public class Init{
    public Init(){
        File f = new File("config.cfg");
        if(!f.exists()) {
            new SerializeFile("root", "","3306");
        }
        try {
            new InitialSetup();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage(),"Warning!",JOptionPane.ERROR_MESSAGE);
            JFrame c = new ConfigDB_Setup();
            c.setLocationRelativeTo(null);
            return;
        }


        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                JFrame frame = new Login();
                frame.setLocationRelativeTo(null);
            }
        });

    }

}