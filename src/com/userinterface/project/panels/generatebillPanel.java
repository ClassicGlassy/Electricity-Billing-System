package com.userinterface.project.panels;

import com.calculation.calculateFinalAmount;
import com.database.ConnectionProvider;
import com.userinterface.components.buttonComponent;
import com.userinterface.components.labelComponent;
import com.userinterface.components.textboxComponent;
import com.userinterface.project.popupFrames.manageMeterFrame;
import com.userinterface.project.popupFrames.usersBillFrame;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class generatebillPanel extends JPanel {
    GridBagConstraints gbc = new GridBagConstraints();
    public generatebillPanel(){
        setLayout(new GridBagLayout());

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


            calculateBill(billNoT.getText(), month_select.getSelectedIndex(), year_select.getSelectedIndex(), Integer.parseInt(presentReadingT.getText()));
            remove(billNoL);
            remove(readingDateL);
            remove(presentReadingL);
            remove(billNoT);
            remove(month_select);
            remove(year_select);
            remove(presentReadingT);
            remove(calculateBtn);
//            remove(mngMeter);
            validate();
            repaint();
        });
        add(calculateBtn,gbc);

    }

    private void calculateBill(String billno, int month, int year, int pres_read){
        try{
            Connection con;
            ConnectionProvider _connectionProvider = new ConnectionProvider();
            con = _connectionProvider.getConnection();
            String query;
            PreparedStatement statement;
            ResultSet rs;

//            Checking if previous meter reading occured
            int prev_reading;

            query = "SELECT pres_reading FROM housebill ORDER BY pres_reading DESC LIMIT 1";
            statement = con.prepareStatement(query);
            rs = statement.executeQuery();

            if(rs.next()){
                prev_reading = rs.getInt("pres_reading");
            }
            else{
                prev_reading = Integer.parseInt(JOptionPane.showInputDialog("Please Enter your previous meter reading"));
            }

            int total_unit_consumed = pres_read - prev_reading;
            calculateFinalAmount bill = new calculateFinalAmount();
            float amt_calcuated = bill.calculate_bill(total_unit_consumed);

//            Bill Preview

//            Labels
            gbc.gridx = 0;
            gbc.gridwidth = 2;
//            gbc.insets = new Insets(0,0,10,10);
            labelComponent billMYL = new labelComponent("BILL MTH & YEAR:", 20);
            gbc.gridy=0;
            add(billMYL,gbc);

            labelComponent billNoL = new labelComponent("BILL NO:", 20);
            gbc.gridy=1;
            add(billNoL,gbc);

//            gbc.insets = new Insets(30,10,10,0);
            labelComponent presReadL = new labelComponent("PRESENT READING:",20);
            gbc.gridy=2;
            add(presReadL,gbc);
            labelComponent prevReadL = new labelComponent("PREVIOUS READING:",20);
//            gbc.insets = new Insets(0,10,10,0);
            gbc.gridy=3;
            add(prevReadL,gbc);
            labelComponent unitL = new labelComponent("UNIT:",20);
            gbc.gridy=4;
            add(unitL,gbc);

            labelComponent currentAmtL = new labelComponent("CURRENT AMOUNT:",20);
//            gbc.insets = new Insets(30,10,10,0);
            gbc.gridy=5;
            add(currentAmtL,gbc);

//            Values

            gbc.gridx = 2;
            gbc.gridwidth = 2;
//            gbc.insets = new Insets(0,10,10,0);
            labelComponent billMYV = new labelComponent((month+1) + "-" + (2078 + year), 20);
            gbc.gridy=0;
            add(billMYV,gbc);

            labelComponent billNoV = new labelComponent(billno, 20);
            gbc.gridy=1;
            add(billNoV,gbc);

//            gbc.insets = new Insets(30,10,10,0);
            labelComponent presReadV = new labelComponent(String.valueOf(pres_read),20);
            gbc.gridy=2;
            add(presReadV,gbc);
            labelComponent prevReadV = new labelComponent(String.valueOf(prev_reading),20);
//            gbc.insets = new Insets(0,10,10,0);
            gbc.gridy=3;
            add(prevReadV,gbc);
            labelComponent unitV = new labelComponent(String.valueOf(total_unit_consumed),20);
            gbc.gridy=4;
            add(unitV,gbc);

            labelComponent currentAmtV = new labelComponent(String.valueOf(amt_calcuated),20);
//            gbc.insets = new Insets(30,10,10,0);
            gbc.gridy=5;
            add(currentAmtV,gbc);

            buttonComponent confirmBtn = new buttonComponent("Confirm",Color.green,Color.white);
            gbc.gridy=6;
            gbc.gridx = 0;
            gbc.gridwidth = 3;
            confirmBtn.addActionListener(e -> {
                confirmBill(billno,month+1 , year+2078, prev_reading, pres_read, amt_calcuated);
            });
            add(confirmBtn,gbc);

            validate();
            repaint();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
    }

//    Bill Preview
    private void confirmBill(String billno, int month, int year,int prev_read, int pres_read, float amt_calculated){
        String query = "INSERT INTO housebill (billno, readingM, readingY, prev_reading, pres_reading, amt_calculated) VALUES (?,?,?,?,?,?);";
        try {
            Connection con;
            ConnectionProvider _connectionProvider = new ConnectionProvider();
            con = _connectionProvider.getConnection();
            PreparedStatement statement = con.prepareStatement(query);

            statement.setString(1,billno);
            statement.setInt(2,month);
            statement.setInt(3,year);
            statement.setInt(4,prev_read);
            statement.setInt(5,pres_read);
            statement.setFloat(6,amt_calculated);

            if(statement.executeUpdate() == 1){
                JOptionPane.showMessageDialog(null,"Data inserted Successfully!");

//                Checking if Tenants are present or not
                query = "SELECT COUNT(DISTINCT(meterno)) as count FROM users WHERE meterno IS NOT NULL AND status = 1;";
                statement = con.prepareStatement(query);
                ResultSet rs = statement.executeQuery();
                rs.next();

                int tenantSize = rs.getInt(1);
                System.out.println(" Tenant size " +tenantSize);
                if(tenantSize >0){
                    usersBillFrame userbillFrame = new usersBillFrame(billno, tenantSize);
                    userbillFrame.setLocationRelativeTo(null);
                }
            }
            else{
                JOptionPane.showMessageDialog(null,"Data cannot be inserted!", "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage(),"WARNING",JOptionPane.WARNING_MESSAGE);
        }
    }

//    Functions
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
