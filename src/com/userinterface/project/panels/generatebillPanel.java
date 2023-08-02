package com.userinterface.project.panels;

import com.database.ConnectionProvider;
import com.userinterface.components.buttonComponent;
import com.userinterface.components.labelComponent;
import com.userinterface.components.textboxComponent;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class generatebillPanel extends JPanel {
    public generatebillPanel(){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,20,15,20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;

//        Set Bill for House

//        Legends
        gbc.gridx = 0;

        labelComponent billNoL = new labelComponent("Bill No:",20);
        gbc.gridy = 0;
        add(billNoL,gbc);

        labelComponent readingDateL = new labelComponent("Reading Date:",20);
        gbc.gridy = 1;
        add(readingDateL,gbc);

        labelComponent presentReadingL = new labelComponent("Present Reading:",20);
        gbc.gridy = 2;
        add(presentReadingL,gbc);

//        Inputs
        gbc.gridx = 1;
        textboxComponent billNoT = new textboxComponent(15,20);
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        add(billNoT,gbc);

        String[] month_names = {"Baishakh","Jestha","Ashar","Shrawan","Bhadra","Ashwin","Kartik","Mangsir","Poush","Magh","Falgun","Chaitra"};
        JComboBox month_select = new JComboBox(month_names);
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        add(month_select,gbc);

        String[] year;
        year = returnArray(2078,2085);
        JComboBox year_select = new JComboBox(year);
        gbc.gridy = 1;
        gbc.gridx = 2;
        gbc.gridwidth = 1;
        add(year_select,gbc);

        textboxComponent presentReadingT = new textboxComponent(10,20);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        add(presentReadingT,gbc);

        buttonComponent calculateBtn = new buttonComponent("Calculate Bill",Color.WHITE, Color.green);
        gbc.anchor = GridBagConstraints.CENTER;
        gbc.gridx = 0;
        gbc.gridy = 4;
        calculateBtn.addActionListener(e -> {
            calculateBill(Integer.parseInt(billNoT.getText()), (int)month_select.getSelectedItem(), (int) year_select.getSelectedItem(), Integer.parseInt(presentReadingT.getText()));

        });
        add(calculateBtn,gbc);

    }

    private void calculateBill(int billno, int month, int year, int read){
        try{
            Connection con;
            ConnectionProvider _connectionProvider = new ConnectionProvider();
            con = _connectionProvider.getConnection();
            String query;
            PreparedStatement statement;
            ResultSet rs;


        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }
    String[] returnArray(int start,int end){
        int size = start - end;
        size = Math.abs(size) + 1;

        String[] returnValues = new String[size];
        int i=0;

        while(start<=end)
        {
            returnValues[i] = Integer.toString(start);
            start++;
            i++;
        }


        return returnValues;

    }

}
