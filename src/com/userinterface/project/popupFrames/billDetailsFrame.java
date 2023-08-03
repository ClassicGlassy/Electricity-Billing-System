package com.userinterface.project.popupFrames;

import com.database.ConnectionProvider;
import com.userinterface.components.Utility;
import com.userinterface.components.labelComponent;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class billDetailsFrame extends JFrame {

    public billDetailsFrame(String billno){
        super("Details Bill");

        JPanel bill_titlePanel = new JPanel(new BorderLayout());
        JScrollPane tablePanel = new JScrollPane();

        labelComponent billnoL = new labelComponent("Bill NO:   " + billno,20);
        bill_titlePanel.add(billnoL);

//        Table

        String[] columnNames = {"Meter No", "Unit Consumed", "Amount"};
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
            PreparedStatement statement;
            ResultSet rs;

            query = "SELECT P.meterno, (P.pres_reading - P.prev_reading) AS unit, P.amt_calculated\n" +
                    "FROM housebill as H left JOIN permeter_bill AS P\n" +
                    "ON H.billno = P.billno\n" +
                    "WHERE H.billno = ?";
            statement = con.prepareStatement(query);
            statement.setString(1,billno);
            rs = statement.executeQuery();


            int i = 0;
            while(rs.next()){
                model.insertRow(i, new Object[]{rs.getString("meterno"), rs.getString("unit"), rs.getString("amt_calculated")});
                i++;
            }

            con.close();

        }
        catch (SQLException e) {
            JOptionPane.showMessageDialog(null,e.getMessage());
        }

        add(bill_titlePanel,BorderLayout.NORTH);

        tablePanel.getViewport().add(table);
        add(tablePanel,BorderLayout.CENTER);
        pack();
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);
    }
}
