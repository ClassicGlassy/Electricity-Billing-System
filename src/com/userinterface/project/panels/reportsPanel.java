package com.userinterface.project.panels;

import com.database.ConnectionProvider;
import com.userinterface.components.Utility;
import com.userinterface.components.labelComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class reportsPanel extends JScrollPane {
//    ADMIN
    public reportsPanel(){

        setBorder(BorderFactory.createTitledBorder("House Bill"));
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        String[] columnNames = {"Bill No", "Reading Date", "Previous Reading", "Present Reading", "Total Unit Consumed", "Amounts Calculated", "Arrears", "Amounts Paid", "Extra"};
        Object[][] rowData = new Object[0][];

        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);
        JTable table = new JTable(model) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        table.setFocusable(false);

        Utility util = new Utility();
        table.setFont(util.convertToFont("font/OpenSans.ttf", 20));
        table.setRowHeight(35);

        try {
            Connection con;
            ConnectionProvider _connectionProvider = new ConnectionProvider();
            con = _connectionProvider.getConnection();
            String query;
            ResultSet rs;

            query = "SELECT *,(pres_reading - prev_reading) as \"consumedU\" from housebill;";
            Statement statement = con.createStatement();

            rs = statement.executeQuery(query);
            int i = 0;
            while(rs.next()) {
                model.insertRow(i, new Object[]{rs.getString("billno"), rs.getString("readingM") + "/" + rs.getString("readingY"),
                        rs.getString("prev_reading"),rs.getString("pres_reading"),rs.getString("consumedU"),rs.getString("amt_calculated"),rs.getString("arrears"),
                        rs.getString("amt_paid"),rs.getString("extra")});
                i++;
            }

            con.close();

        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        getViewport().add(table);

    }
//    TENANT
    public reportsPanel(int userid){
        setLayout(new GridBagLayout());

        labelComponent test = new labelComponent("Hello this is Report Panel", 50);
        add(test);
        setVisible(true);
    }

}
