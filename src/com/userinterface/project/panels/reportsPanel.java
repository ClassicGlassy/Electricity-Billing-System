package com.userinterface.project.panels;

import com.database.ConnectionProvider;
import com.userinterface.components.Utility;
import com.userinterface.components.labelComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class reportsPanel extends JScrollPane {
//    ADMIN
    public reportsPanel(){
        setBorder(BorderFactory.createTitledBorder("House Bill"));
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        String[] columnNames = {"Bill No", "Reading Date", "Previous Reading", "Present Reading", "Total Unit Consumed", "Amounts Calculated"};
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
                        rs.getString("prev_reading"),rs.getString("pres_reading"),rs.getString("consumedU"),rs.getString("amt_calculated")});
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
        setBorder(BorderFactory.createTitledBorder("House Bill"));
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);
        setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);

        String[] columnNames = {"Reading Date", "Previous Reading", "Present Reading", "Total Unit Consumed", "Amounts Calculated"};
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

            query = "SELECT H.readingM, H.readingY, P.prev_reading, P.pres_reading, P.amt_calculated\n" +
                    "FROM permeter_bill as P LEFT JOIN housebill AS H\n" +
                    "ON P.billno = H.billno\n" +
                    "WHERE P.meterno = (SELECT meterno FROM users WHERE id = ?)";
            PreparedStatement statement = con.prepareStatement(query);
            statement.setInt(1,userid);

            rs = statement.executeQuery();
            int i = 0;
            while(rs.next()) {
                String unit = String.valueOf(rs.getInt("pres_reading") - rs.getInt("prev_reading"));
                model.insertRow(i, new Object[]{rs.getString("readingY") + "/" + rs.getString("readingM"),
                        rs.getString("prev_reading"),rs.getString("pres_reading"),
                        unit,rs.getString("amt_calculated")});
                i++;
            }
            con.close();
        }
        catch (SQLException e){
            JOptionPane.showMessageDialog(null,e.getMessage());
        }
        getViewport().add(table);
    }
}
