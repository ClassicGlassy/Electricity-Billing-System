package com.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
    Connection c;
    public Connection connectToDB(){
        try{
            c = DriverManager.getConnection("jdbc:mariadb://localhost:3306/ebs","root","");
            System.out.println("Connection Successful!");
        }
        catch (SQLException e){
            System.out.println("Cannot create a connection to DB");
        }
        return c;
    }

    public void closeDB(){
        try {
            c.close();
        }
        catch (SQLException e){
            System.out.println("Can't Close DB");
        }

    }
}
