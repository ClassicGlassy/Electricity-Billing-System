package com.userinterface.project.popupFrames;

import com.calculation.calculateFinalAmount;
import com.database.ConnectionProvider;
import com.userinterface.components.buttonComponent;
import com.userinterface.components.labelComponent;
import com.userinterface.components.textboxComponent;

import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class usersBillFrame extends JFrame {
    int i = 0;
    String billNo;
    public usersBillFrame(String billno, int meterSize){
        super("Generate User Bill");
        billNo = billno;

        JPanel panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15,20,15,20);
        gbc.fill = GridBagConstraints.BOTH;
        gbc.anchor = GridBagConstraints.LINE_START;

        calculateFinalAmount calculator = new calculateFinalAmount();

        try {
            Connection con;
            ConnectionProvider _connectionProvider = new ConnectionProvider();
            con = _connectionProvider.getConnection();
            String query;
            PreparedStatement statement;
            ResultSet rs;

            String[] meterOptions = new String[meterSize];
            int[] prev_read_permeter = new int[meterSize];

            query = """
                    SELECT DISTINCT(U.meterno), Max(P.pres_reading)
                    FROM users AS U LEFT JOIN permeter_bill AS P
                    ON U.meterno = P.meterno
                    WHERE U.meterno IS NOT NULL AND U.status = 1
                    GROUP BY U.meterno
                    """;

            statement = con.prepareStatement(query);
            rs = statement.executeQuery();


            while (rs.next()) {
//                Setting Meter Options
                meterOptions[i] = rs.getString(1);
                prev_read_permeter[i] = rs.getInt(2);
                i++;
            }
            con.close();

//            Need to implement such that it loops
//          Resetting I to 0

            i = 0;

//            Labels STATIC
            gbc.gridx = 0;
            labelComponent meterL = new labelComponent("Meter No:", 20);
            gbc.gridy = 0;
            panel.add(meterL, gbc);

            labelComponent prevL = new labelComponent("Previous Reading:", 20);
            gbc.gridy = 1;
            panel.add(prevL, gbc);

            labelComponent presL = new labelComponent("Present Reading:", 20);
            gbc.gridy = 2;
            panel.add(presL, gbc);

            labelComponent unitL = new labelComponent("Unit Consumed:", 20);
            gbc.gridy = 3;
            panel.add(unitL, gbc);

            labelComponent amtL = new labelComponent("Amount Calculated:", 20);
            gbc.gridy = 4;
            panel.add(amtL, gbc);


//            Items that need to be updated

            gbc.gridx = 1;
            labelComponent meterV = new labelComponent(meterOptions[i], 20);
            gbc.gridy = 0;
            panel.add(meterV, gbc);

            labelComponent prevV = new labelComponent(String.valueOf(prev_read_permeter[i]), 20);
            gbc.gridy = 1;
            panel.add(prevV, gbc);

            textboxComponent presV = new textboxComponent(10, 20);
            gbc.gridy = 2;
            panel.add(presV, gbc);

            labelComponent unitV = new labelComponent("", 20);
            gbc.gridy = 3;
            panel.add(unitV, gbc);

            labelComponent amtV = new labelComponent("", 20);
            gbc.gridx = 1;
            gbc.gridy = 4;
            panel.add(amtV, gbc);

//                Button
            gbc.gridx = 0;
            buttonComponent calculateBtn = new buttonComponent("Calculate Bill", Color.green, Color.white);
            gbc.gridy = 5;
            gbc.gridwidth = 1;



//            Feature of Calculate Button
            calculateBtn.addActionListener(e -> {
                int pres_reading = Integer.parseInt(presV.getText());
                int prev_reading = prev_read_permeter[i];
                if (prevV.getText().isEmpty()) {
                    prev_reading = Integer.parseInt(JOptionPane.showInputDialog("Please Enter your previous meter reading"));
                }
                int unitconsumed =  pres_reading - prev_reading ;
                float finalAmt = calculator.calculate_bill(unitconsumed);
                amtV.setText("Rs. " + finalAmt);
                unitV.setText(unitconsumed + "KW/Hr");

                panel.validate();
                panel.repaint();
                
                buttonComponent confirmBtn = new buttonComponent("Confirm", Color.green, Color.white);
                gbc.gridx = 1;
                gbc.gridy = 5;
                gbc.gridwidth = 2;

                //            Feature of Confirm Button
                confirmBtn.addActionListener(f -> {
                    updateUserBill(meterOptions[i],prevV.getText(),presV.getText(),finalAmt);
//                        Retrieving Information from the I index.
                    if(i< meterOptions.length -1) {
                        i++;
                        meterV.setText(meterOptions[i]);
                        prevV.setText(String.valueOf(prev_read_permeter[i]));
                        presV.setText("");
                        amtV.setText("");
                        unitV.setText("");
                        panel.remove(confirmBtn);
                        panel.validate();
                        panel.repaint();
                    }
                    else{
                        setVisible(false);
                        dispose();
                    }
                });
                panel.add(confirmBtn,gbc);

            });
            panel.add(calculateBtn, gbc);

/*

//            i = 0;

//            Labels
//                gbc.gridx = 0;
//                labelComponent meterL = new labelComponent("Meter No:", 20);
//                gbc.gridy = 0;
//                panel.add(meterL, gbc);
//
//                labelComponent prevL = new labelComponent("Previous Reading:", 20);
//                gbc.gridy = 1;
//                panel.add(prevL, gbc);
//
//                labelComponent presL = new labelComponent("Present Reading:", 20);
//                gbc.gridy = 2;
//                panel.add(presL, gbc);
//
//                labelComponent unitL = new labelComponent("Unit Consumed:", 20);
//                gbc.gridy = 3;
//                panel.add(unitL, gbc);
//
//                labelComponent amtL = new labelComponent("Amount Calculated:", 20);
//                gbc.gridy = 4;
//                panel.add(amtL, gbc);
//
//
////                Values
//                i = 0;
//                gbc.gridx = 1;
//                labelComponent meterV = new labelComponent(meterOptions[0], 20);
////                System.out.println(meterOptions[i]);
//                gbc.gridy = 0;
//                panel.add(meterV, gbc);
//
//                labelComponent prevV = new labelComponent(String.valueOf(prev_read_permeter[0]), 20);
//                gbc.gridy = 1;
//                panel.add(prevV, gbc);
//
//                textboxComponent presV = new textboxComponent(10, 20);
//                gbc.gridy = 2;
//                panel.add(presV, gbc);
//
//                labelComponent unitV = new labelComponent("", 20);
//                gbc.gridy = 3;
//                panel.add(unitV, gbc);
//
//
//
//                labelComponent amtV = new labelComponent("", 20);
//                gbc.gridx = 1;
//                gbc.gridy = 4;
//                panel.add(amtV, gbc);
//
////                Button
//                gbc.gridx = 0;
//                buttonComponent calculateBtn = new buttonComponent("Calculate Bill", Color.green, Color.white);
//                gbc.gridy = 5;
//                gbc.gridwidth = 1;
//                calculateBtn.addActionListener(e -> {
//                    int pres_reading = Integer.parseInt(presV.getText());
//                    int prev_reading = Integer.parseInt(prevV.getText());
//
//                    if(prevV.getText().isEmpty()){
//                        prev_reading = Integer.parseInt(JOptionPane.showInputDialog("Please Enter your previous meter reading"));
//                    }
//                    int unitconsumed =  pres_reading - prev_reading ;
//                    float finalAmt = calculator.calculate_bill(unitconsumed);
//                    amtV.setText("Rs. " + finalAmt);
//                    unitV.setText(unitconsumed + "KW/Hr");
//                    panel.validate();
//                    panel.repaint();
//
//                    i = 0;
//                    buttonComponent confirmBtn = new buttonComponent("Confirm", Color.green, Color.white);
//                    gbc.gridx = 1;
//                    gbc.gridy = 5;
//                    gbc.gridwidth = 2;
//                    confirmBtn.addActionListener(f -> {
//                        updateUserBill(meterOptions[i],prevV.getText(),presV.getText(),finalAmt);
////                        Retrieving Information from the I index.
//                        if(i< meterOptions.length -1) {
//                            i++;
//                            meterV.setText(meterOptions[i]);
//                            prevV.setText(String.valueOf(prev_read_permeter[i]));
//                            presV.setText("");
//                            amtV.setText("");
//                            unitV.setText("");
//                            panel.remove(confirmBtn);
//                            panel.validate();
//                            panel.repaint();
//                        }
//                        else{
//                            setVisible(false);
//                            dispose();
//                        }
//                    });
//                    panel.add(confirmBtn, gbc);
//                });
//                panel.add(calculateBtn, gbc);
//
//
////            else
//                setVisible(false);
//                dispose();

*/

        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        panel.setVisible(true);
        add(panel);
        pack();
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        setVisible(true);
    }

    private void updateUserBill(String meterno, String prev, String pres, float amount){
        try {
            Connection con;
            ConnectionProvider _connectionProvider = new ConnectionProvider();
            con = _connectionProvider.getConnection();
            String query;
            PreparedStatement statement;

            query = "INSERT INTO permeter_bill (meterno, prev_reading, pres_reading, amt_calculated, billno) VALUES(?,?,?,?,?);";
            statement = con.prepareStatement(query);

            statement.setString(1,meterno);
            statement.setString(2,prev);
            statement.setString(3,pres);
            statement.setFloat(4,amount);
            statement.setString(5,billNo);

            if(statement.executeUpdate() ==1){
                JOptionPane.showMessageDialog(null,"Success");
            }
            else {
                JOptionPane.showMessageDialog(null,"Unsuccessful");
            }
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

    }
}
