package com.userinterface.project.panels;

import com.database.ConnectionProvider;
import com.userinterface.components.Utility;
import com.userinterface.project.popupFrames.editProfileFrame;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class mnguserPanel extends JScrollPane {
    public mnguserPanel(){
        setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_ALWAYS);

        String[] columnNames = {"Name", "Email", "Meter No", "Status"};
        Object[][] rowData = new Object[0][];

        DefaultTableModel model = new DefaultTableModel(rowData, columnNames);


        JTable table = new JTable(model) {
            public boolean editCellAt(int row, int column, java.util.EventObject e) {
                return false;
            }
        };
        table.setFocusable(false);
        table.addMouseListener(new MouseAdapter() {
            public void mouseClicked(MouseEvent me) {
                if (me.getClickCount() == 2) {     // to detect doble click events
                    JTable target = (JTable)me.getSource();
                    int row = target.getSelectedRow(); // select a row
                    editProfileFrame frame = new editProfileFrame((String) table.getValueAt(row,1));
                    frame.setLocationRelativeTo(null);
                }
            }
        });

        Utility util = new Utility();
        table.setFont(util.convertToFont("font/OpenSans.ttf", 20));
        table.setRowHeight(35);

        try {
            Connection con;
            ConnectionProvider _connectionProvider = new ConnectionProvider();
            con = _connectionProvider.getConnection();
            String query;
            ResultSet rs;

            query = "SELECT U.name, L.email, U.meterno, U.status FROM users AS U LEFT JOIN login AS L ON U.id = L.userid WHERE L.acc_type = 1;";
            Statement statement = con.createStatement();

            rs = statement.executeQuery(query);
            int i = 0;
            while(rs.next()) {
                String status = "Inactive";
                if(rs.getInt("status") == 1)
                    status = "Active";
                model.insertRow(i, new Object[]{rs.getString("name"), rs.getString("email"), rs.getString("meterno"), status});
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
