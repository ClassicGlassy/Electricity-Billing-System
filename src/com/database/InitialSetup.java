package com.database;

import com.initial.DeserializeFile;

import java.sql.*;


public class InitialSetup {
    public InitialSetup() throws SQLException{
        try{
            DeserializeFile DB = new DeserializeFile();

//            Creating connection to Server to retrieve
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:"+DB.port, DB.user, DB.password);

            String dbName = "ebs";
            ResultSet rs = connection.getMetaData().getCatalogs();
            while(rs.next()){
                String catalogs = rs.getString(1);

                if(dbName.equals(catalogs)){
                    connection.close();
                    return;
                }
            }

//              TODO Check for this line in next patch.
            String Query = "CREATE DATABASE ebs;";
            Statement stmt = connection.createStatement();
            stmt.executeQuery(Query);
            connection.close();

//              Connection to Database EBS.
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:"+DB.port+"/ebs", DB.user, DB.password);

//            Creating Tables

//            Profile Tables
            Query = "CREATE TABLE profiles(id int PRIMARY KEY AUTO_INCREMENT, name varchar(50),email varchar(100) UNIQUE,pwd varchar(128),phone varchar(10),acc_type BIT);";
            stmt.executeQuery(Query);


            connection.close();

        }
        catch (SQLException e){
            throw new SQLException("Cannot establish a connection to the Database.");
        }

    }
}
