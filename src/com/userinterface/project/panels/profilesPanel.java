package com.userinterface.project.panels;

import com.userinterface.components.labelComponent;

import javax.swing.*;
import java.awt.*;

public class profilesPanel extends JPanel {
    public profilesPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

        //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,10,40,10);

//        Username
        labelComponent usernameLabel = new labelComponent("Username: ",50);
        gbc.gridx = gbc.gridy = 0;
        add(usernameLabel,gbc);

        labelComponent usernameFillup = new labelComponent("Saurab",50);
        gbc.gridx = 1; gbc.gridy = 0;
        add(usernameFillup,gbc);

//        Meter Details
        labelComponent meterLabel = new labelComponent("Meter No: ",50);
        gbc.gridx =0; gbc.gridy = 1;
        add(meterLabel,gbc);

        labelComponent meterFillup = new labelComponent("1200 ",50);
        gbc.gridx =  gbc.gridy = 1;
        add(meterFillup,gbc);

//        Registered Date
        labelComponent dateLabel = new labelComponent("Registered Date:", 45);
        gbc.gridx =0; gbc.gridy = 2;
        add(dateLabel,gbc);

        labelComponent dateFillup = new labelComponent("2023-08-09", 50);
        gbc.gridx =1; gbc.gridy = 2;
        add(dateFillup,gbc);

        setVisible(true);
    }
}
