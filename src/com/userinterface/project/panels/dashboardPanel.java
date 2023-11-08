package com.userinterface.project.panels;

import com.database.ConnectionProvider;
import com.userinterface.components.labelComponent;
import com.userinterface.components.roundedPanel;

import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class dashboardPanel extends JPanel {
    public dashboardPanel(int userid, int acc_type){
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();

            //natural height, maximum width
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(0,10,40,10);

        try {
            Connection con;
            ConnectionProvider _connectionProvider = new ConnectionProvider();
            con = _connectionProvider.getConnection();
            String query;
            PreparedStatement statement;
            ResultSet rs;

            query = "SELECT name FROM users WHERE id = ?";
            statement = con.prepareStatement(query);
            statement.setInt(1,userid);
            rs = statement.executeQuery();
            rs.next();


            //        Greet User
            labelComponent username = new labelComponent("Welcome " + rs.getString("name"), 40);
            gbc.gridx = gbc.gridy = 0;
            gbc.anchor = GridBagConstraints.LINE_START;
            gbc.gridwidth = 2;
            gbc.fill = GridBagConstraints.VERTICAL;
            add(username, gbc);

//        2 Panels : Total Bill unit, Bill amount received
            GridBagConstraints gbcSub = new GridBagConstraints();
            String total_unitV = "", newYear ="", total_amountV ="";

            if(acc_type == 0){
                query = "SELECT readingY, SUM(amt_calculated)AS amount, SUM(pres_reading-prev_reading)AS unit\n" +
                        "FROM housebill\n" +
                        "GROUP BY readingY\n" +
                        "ORDER BY readingY DESC\n" +
                        "LIMIT 1";
                statement = con.prepareStatement(query);
                rs = statement.executeQuery();
                if(rs.next()){
                    newYear = rs.getString("readingY");
                    total_amountV = rs.getString("amount");
                    total_unitV = rs.getString("unit");
                }
            }

            else{
                query = "SELECT H.readingY, SUM(P.amt_calculated) AS amount, SUM(H.pres_reading - H.prev_reading) AS unit\n" +
                        "FROM permeter_bill AS P LEFT JOIN housebill AS H\n" +
                        "ON P.billno = H.billno\n" +
                        "WHERE meterno = (SELECT meterno FROM users WHERE id = ?)\n" +
                        "ORDER BY H.readingY DESC\n" +
                        "LIMIT 1";
                statement = con.prepareStatement(query);
                statement.setInt(1,userid);
                rs = statement.executeQuery();
                if(rs.next()){
                    total_amountV = rs.getInt("amount") > 0?  rs.getString("amount"): "0";
                    newYear = rs.getInt("readingY") > 0 ? rs.getString("readingY"): "YYYY";
                    total_unitV = rs.getInt("unit") > 0 ? rs.getString("unit"): "0";
                }
            }
            con.close();

            roundedPanel total_unit = new roundedPanel(510, 200, Color.PINK);
            total_unit.setLayout(new GridBagLayout());
//        Properties for items inside total_unit Panel
            gbcSub.gridx = 0;
            gbcSub.anchor = GridBagConstraints.CENTER;
            gbcSub.weighty = 1;
            labelComponent unitLabel = new labelComponent("Unit Consumed:", 40);
            gbcSub.gridy = 0;
            total_unit.add(unitLabel, gbcSub);
            labelComponent unitValue = new labelComponent(total_unitV + "KW/Hr", 50);
            gbcSub.gridy = 1;
            total_unit.add(unitValue, gbcSub);
            labelComponent unitYear = new labelComponent("of "+ newYear, 20);
            gbcSub.gridy = 2;
            total_unit.add(unitYear, gbcSub);

//        Properties for total_unit Panel
            gbc.gridy = 1;
            gbc.gridx = 0;
            gbc.gridwidth = 1;
            add(total_unit, gbc);


//        Bill Amount Panel

            roundedPanel bill_amt = new roundedPanel(510, 200, Color.PINK);
            bill_amt.setLayout(new GridBagLayout());
//        Properties for items inside bill amount Panel
            gbcSub.gridx = 0;
            gbcSub.anchor = GridBagConstraints.CENTER;
            gbcSub.weighty = 1;
            labelComponent billLabel = new labelComponent("Total Bill:", 40);
            gbcSub.gridy = 0;
            bill_amt.add(billLabel, gbcSub);
            labelComponent billValue = new labelComponent("Rs. " + total_amountV, 50);
            gbcSub.gridy = 1;
            bill_amt.add(billValue, gbcSub);

//        Properties for bill amount Panel
            gbc.gridy = 1;
            gbc.gridx = 1;
            gbc.gridwidth = 1;
            add(bill_amt, gbc);

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }




//        labelComponent test = new labelComponent("Hello this is Dashboard Panel", 50);
//        add(test);
        setVisible(true);
    }
}
