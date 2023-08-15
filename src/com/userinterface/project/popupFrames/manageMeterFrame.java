package com.userinterface.project.popupFrames;

import com.database.ConnectionProvider;
import com.userinterface.components.buttonComponent;
import com.userinterface.components.labelComponent;
import com.userinterface.components.textboxComponent;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class manageMeterFrame extends JFrame {
    public manageMeterFrame() {

        super("Manage Meters");
//        parent = parentPanel;

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 20, 15, 20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;

        gbc.gridx = 0;
        labelComponent meterL = new labelComponent("Meter No:",20);
        gbc.gridy = 0;
        panel.add(meterL,gbc);

        labelComponent roomL = new labelComponent("Room No:",20);
        gbc.gridy = 1;
        panel.add(roomL,gbc);


        gbc.gridx = 1;
        textboxComponent meterT = new textboxComponent(3,20);
        gbc.gridy = 0;
        panel.add(meterT,gbc);

        textboxComponent roomT = new textboxComponent(3,20);
        gbc.gridy = 1;
        panel.add(roomT,gbc);


        buttonComponent saveBtn = new buttonComponent("Save", Color.green, Color.white);
        gbc.gridy = 2;
        gbc.gridx = 0;
        gbc.gridwidth = 2;

        saveBtn.addActionListener(btn-> {
            try {
                Connection con;
                ConnectionProvider _connectionProvider = new ConnectionProvider();
                con = _connectionProvider.getConnection();
                String query;
                PreparedStatement statement;

                query = """
                    INSERT INTO meterdetails
                    VALUES (?, ?);
                    """;

                statement = con.prepareStatement(query);
                statement.setString(1,meterT.getText());
                statement.setString(2,roomT.getText());

                statement.execute();

                con.close();

                setVisible(false);
                dispose();

            } catch (SQLException e) {
                JOptionPane.showMessageDialog(null,e.getMessage());
            }
        });
        panel.add(saveBtn,gbc);


        panel.setVisible(true);
        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }

}
