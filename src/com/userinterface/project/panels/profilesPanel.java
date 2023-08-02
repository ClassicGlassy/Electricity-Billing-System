package com.userinterface.project.panels;

import com.database.ConnectionProvider;
import com.userinterface.components.labelComponent;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class profilesPanel extends JPanel {
    public profilesPanel(int userid){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,10,40,10);

//        Username
        labelComponent usernameLabel = new labelComponent("Username: ",50);
        gbc.gridx = gbc.gridy = 0;
        add(usernameLabel,gbc);



//        Meter Details
        labelComponent meterLabel = new labelComponent("Meter No: ",50);
        gbc.gridx =0; gbc.gridy = 1;
        add(meterLabel,gbc);



//        Phone Details
        labelComponent phoneLabel = new labelComponent("Phone Number:", 45);
        gbc.gridx =0; gbc.gridy = 2;
        add(phoneLabel,gbc);




        try {
            Connection con;
            ConnectionProvider _connectionProvider = new ConnectionProvider();
            con = _connectionProvider.getConnection();
            String query;
            PreparedStatement statement;
            ResultSet rs;

            query = "SELECT name, phone, meterno, status FROM users WHERE id = ?;";
            statement = con.prepareStatement(query);
            statement.setInt(1,userid);
            rs = statement.executeQuery();

            if(rs.next()){
                labelComponent usernameFillup = new labelComponent(rs.getString("name"),50);
                gbc.gridx = 1; gbc.gridy = 0;
                add(usernameFillup,gbc);

                labelComponent meterFillup = new labelComponent(rs.getString("meterno"),50);
                gbc.gridx =  gbc.gridy = 1;
                add(meterFillup,gbc);

                labelComponent phoneFillup = new labelComponent(rs.getString("phone"), 50);
                gbc.gridx =1; gbc.gridy = 2;
                add(phoneFillup,gbc);
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        setVisible(true);
    }
}
