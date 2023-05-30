package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {

    public Connection connectToDB() throws SQLException{
        Connection c = null;
        try{
            c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ebs","root","");
//            System.out.println("Connection Successful!");
            return c;
        }
        catch (SQLException e){
            throw new SQLException("Cannot connect to DataBase.");
        }
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
