package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    private final Connection connection;
    public ConnectionProvider() throws SQLException {
        try {
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ebs", "root", "");
        }
        catch (SQLException e){
            throw new SQLException("Cannot connect to DataBase.");
        }
    }
    public Connection getConnection() {
        return connection;
    }

//    REMOVE this Function if not used in Future
    public void closeDB(Connection c){
        try {
            c.close();
        }
        catch (SQLException e){
            System.out.println("Can't Close DB");
        }

    }
}
