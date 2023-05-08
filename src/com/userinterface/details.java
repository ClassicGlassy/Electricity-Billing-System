package com.userinterface;

import javax.swing.*;
import java.awt.*;

public class details extends JPanel {
    public details(){
        Dimension size = getPreferredSize();
        size.width = 250;
        setPreferredSize(size);

        setBorder(BorderFactory.createTitledBorder("Personal Details"));

        JLabel nameLabel = new JLabel("Name: ");
        JLabel occupationLabel = new JLabel("Occupation: ");

        JTextField nameField = new JTextField(10);
        JTextField occupationField = new JTextField(10);

        JButton addBtn = new JButton("Add");

        setLayout(new GridBagLayout());

        GridBagConstraints gc = new GridBagConstraints();

//        First column ////

        gc.anchor = GridBagConstraints.LINE_END;
        gc.weightx=0.5;
        gc.weighty=0.5;

        gc.gridx = 0;
        gc.gridy = 0;

        add(nameLabel,gc);

        gc.gridx = 0;
        gc.gridy = 1;
        add(occupationLabel,gc);

//        Second Column
        gc.anchor = GridBagConstraints.LINE_START;
        gc.gridx = 1;
        gc.gridy = 0;

        add(nameField,gc);

        gc.gridy = 1;
        add(occupationField,gc);


    }
}
