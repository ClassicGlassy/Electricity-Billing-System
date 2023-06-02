package com.database;

import com.initial.DeserializeFile;

import java.sql.*;
import java.util.Objects;

public class InitialSetup {
    public InitialSetup() throws SQLException{
        try{
            DeserializeFile DB = new DeserializeFile();
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:"+DB.port, DB.user, DB.password);

            String dbName = "ebs";
            ResultSet rs = connection.getMetaData().getCatalogs();
            while(rs.next()){
                String catalogs = rs.getString(1);

                if(dbName.equals(catalogs)){
                    return;
                }
            }
            String createDBQuery = "create database ebs;";
            Statement stmt = connection.createStatement();
            stmt.executeQuery(createDBQuery);
            connection.close();

            connection = DriverManager.getConnection("jdbc:mariadb://localhost:"+DB.port+"/ebs", DB.user, DB.password);
            String createTableQuery = "create table profiles(name varchar(50),email varchar(100),pwd varchar(128),phone varchar(10),acc_type BIT);";
            Statement stmt1 = connection.createStatement();
            stmt1.executeQuery(createTableQuery);
            connection.close();

        }
        catch (SQLException e){
            e.printStackTrace();
        }

    }
}
