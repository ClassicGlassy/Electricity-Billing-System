package com.userinterface.project.panels;

import com.userinterface.components.labelComponent;
import com.userinterface.components.roundedPanel;

import javax.swing.*;
import java.awt.*;

public class dashboardPanel extends JPanel {
    public dashboardPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

            //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,10,40,10);

//        Greet User
        labelComponent username = new labelComponent("Hello Saurab",40);
        gbc.gridx = gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.LINE_START;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.VERTICAL;
        add(username,gbc);

//        3 Panels : Total Bill unit, Bill amount received, Active users
        GridBagConstraints gbcSub = new GridBagConstraints();


//        Total Unit Panel

        roundedPanel total_unit = new roundedPanel(510,200,Color.PINK);
        total_unit.setLayout(new GridBagLayout());
//        Properties for items inside total_unit Panel
        gbcSub.gridx = 0;
        gbcSub.anchor = GridBagConstraints.CENTER;
        gbcSub.weighty = 1;
        labelComponent unitLabel = new labelComponent("Unit Consumed:",40);
        gbcSub.gridy =0;
        total_unit.add(unitLabel,gbcSub);
        labelComponent unitValue = new labelComponent("2000 KW/H",50);
        gbcSub.gridy =1;
        total_unit.add(unitValue,gbcSub);

//        Properties for total_unit Panel
        gbc.gridy = 1; gbc.gridx = 0;
        gbc.gridwidth = 1;
        add(total_unit, gbc);


//        Bill Amount Panel

        roundedPanel bill_amt = new roundedPanel(510,200,Color.PINK);
        bill_amt.setLayout(new GridBagLayout());
//        Properties for items inside bill amount Panel
        gbcSub.gridx = 0;
        gbcSub.anchor = GridBagConstraints.CENTER;
        gbcSub.weighty = 1;
        labelComponent billLabel = new labelComponent("Total Bill:",40);
        gbcSub.gridy =0;
        bill_amt.add(billLabel,gbcSub);
        labelComponent billValue = new labelComponent("Rs. 2000",50);
        gbcSub.gridy =1;
        bill_amt.add(billValue,gbcSub);

//        Properties for bill amount Panel
        gbc.gridy = 1; gbc.gridx = 1;
        gbc.gridwidth = 1;
        add(bill_amt, gbc);



//        Past 5 transaction
        roundedPanel past_transaction = new roundedPanel(1040,300,Color.GREEN);
        gbc.gridy = 2; gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.insets = new Insets(0,10,10,10);
        add(past_transaction,gbc);


//        labelComponent test = new labelComponent("Hello this is Dashboard Panel", 50);
//        add(test);
        setVisible(true);
    }
}