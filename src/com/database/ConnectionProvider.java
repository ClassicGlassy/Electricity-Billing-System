package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import com.initial.DeserializeFile;

public class ConnectionProvider {

    private final Connection connection;
    public ConnectionProvider() throws SQLException {
        try {
            DeserializeFile DB = new DeserializeFile();
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:"+DB.port + "/ebs", DB.user, DB.password);
        }
        catch (SQLException e){
            throw new SQLException("Database has been disconnected. Reconnect and Try Again!");
        }
    }
    public Connection getConnection() {
        return this.connection;
    }
}
