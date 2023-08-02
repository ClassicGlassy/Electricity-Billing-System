package com.userinterface.project.popupFrames;

import com.database.ConnectionProvider;
import com.userinterface.components.buttonComponent;
import com.userinterface.components.labelComponent;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class editProfileFrame extends JFrame {
    public editProfileFrame (String email){
        super("Manage User");


        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,20,15,20);
        gbc.fill = GridBagConstraints.BOTH;

        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridx = 0;
        labelComponent nameL = new labelComponent("Name:",20);
        gbc.gridy = 0;
        panel.add(nameL,gbc);

        labelComponent emailL = new labelComponent("Email:",20);
        gbc.gridy = 1;
        panel.add(emailL,gbc);

        labelComponent meterL = new labelComponent("Meter No:",20);
        gbc.gridy = 2;
        panel.add(meterL,gbc);

        labelComponent statusL = new labelComponent("Status:",20);
        gbc.gridy = 3;
        panel.add(statusL,gbc);


        gbc.anchor = GridBagConstraints.LINE_END;
        gbc.gridx = 1;
        try {
            Connection con;
            ConnectionProvider _connectionProvider = new ConnectionProvider();
            con = _connectionProvider.getConnection();
            String query;
            PreparedStatement statement;
            ResultSet rs;

            query = "SELECT meterno, count(meterno) FROM meterdetails;";
            statement = con.prepareStatement(query);
            rs = statement.executeQuery();
            rs.next();
            String[] meterOptions = new String[rs.getInt(2)];

            query = "SELECT meterno FROM meterdetails;";
            statement = con.prepareStatement(query);
            rs = statement.executeQuery();
            int i =0;
            while(rs.next()){
                meterOptions[i]= rs.getString("meterno");
                i++;
            }


            query = "SELECT name, meterno, status FROM users where id = (SELECT userid FROM login where email = ?);";
            statement = con.prepareStatement(query);
            statement.setString(1, email);
            rs = statement.executeQuery();

            String[] statusList = {"Inactive", "Active"};

            if(rs.next()){
                labelComponent nameV = new labelComponent(rs.getString("name"),20);
                gbc.gridy = 0;
                panel.add(nameV,gbc);

                labelComponent emailV = new labelComponent(email,20);
                gbc.gridy = 1;
                panel.add(emailV,gbc);

                String currentM = rs.getString("meterno");

                JComboBox meterV = new JComboBox(meterOptions);
                meterV.setSelectedItem(currentM);
                gbc.gridy = 2;
                panel.add(meterV,gbc);

                JComboBox statusV = new JComboBox(statusList);
                statusV.setSelectedIndex(rs.getInt("status"));
                gbc.gridy = 3;
                panel.add(statusV,gbc);

                buttonComponent saveBtn = new buttonComponent("Save",Color.WHITE, Color.green);
                gbc.anchor = GridBagConstraints.CENTER;
                gbc.gridx = 0;
                gbc.gridy = 4;
                saveBtn.addActionListener(e -> {
                    saveUser((String) meterV.getSelectedItem(), statusV.getSelectedIndex(), email);
                });
                panel.add(saveBtn,gbc);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        setVisible(true);
    }

    private void saveUser(String meterno, int status, String email){
        String query = "UPDATE `users` SET `meterno` = ?, `status` = ? WHERE id = (SELECT userid FROM login where email = ?) ;";
        try {
            Connection con;
            ConnectionProvider _connectionProvider = new ConnectionProvider();
            con = _connectionProvider.getConnection();
            PreparedStatement statement;

            statement = con.prepareStatement(query);
            statement.setString(1,meterno);
            statement.setInt(2, status);
            statement.setString(3, email);

            if(statement.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null,"User Information Updated Successfully");
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }
}
