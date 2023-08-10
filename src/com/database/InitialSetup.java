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
            stmt.execute(Query);
            connection.close();

//              Connection to Database EBS.
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:"+DB.port+"/ebs", DB.user, DB.password);
            stmt = connection.createStatement();

//            Creating Tables

//          MASTER Tables

//            House bill Table
            Query = """
                    CREATE TABLE housebill (
                    billno bigint(20) UNSIGNED NOT NULL PRIMARY KEY,
                    `readingM` tinyint(4) DEFAULT NULL,
                    `readingY` smallint(6) DEFAULT NULL,
                    `prev_reading` int(11) NOT NULL UNIQUE,
                    `pres_reading` int(11) NOT NULL UNIQUE,
                    `amt_calculated` float UNSIGNED DEFAULT NULL);""";
            stmt.execute(Query);

//            Meter Details
            Query = """
                    CREATE TABLE `meterdetails` (
                      `meterno` int(11) NOT NULL PRIMARY KEY,
                      `roomno` varchar(3) DEFAULT NULL);""";
            stmt.execute(Query);

//            NEA Bill Structure
            Query = """
                    CREATE TABLE `nea` (
                      `sn` int(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
                      `minU` int(11) DEFAULT NULL,
                      `maxU` int(11) DEFAULT NULL,
                      `minAmt` int(11) DEFAULT NULL,
                      `rateperUnit` decimal(4,1) DEFAULT NULL);""";
            stmt.execute(Query);

//            User Details Table
            Query = """
                    CREATE TABLE `users` (
                      `id` int(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
                      `name` varchar(100) NOT NULL,
                      `phone` varchar(10) DEFAULT NULL,
                      `meterno` int(11) DEFAULT NULL,
                      `status` bit(1) DEFAULT b'0',
                       FOREIGN KEY(meterno) REFERENCES meterdetails(meterno) );""";
            stmt.execute(Query);

//            Login Details
            Query = """
                    CREATE TABLE `login` (
                      `sn` int(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
                      `email` varchar(120) NOT NULL UNIQUE,
                      `password` varchar(256) NOT NULL,
                      `userid` int(11) DEFAULT NULL,
                      `acc_type` tinyint(1) NOT NULL,
                       FOREIGN KEY(userid) REFERENCES users(id) );""";
            stmt.execute(Query);

            Query = """
                    CREATE TABLE `permeter_bill` (
                      `sn` int(11) AUTO_INCREMENT NOT NULL PRIMARY KEY,
                      `meterno` int(11) DEFAULT NULL,
                      `prev_reading` int(11) NOT NULL,
                      `pres_reading` int(11) NOT NULL,
                      `amt_calculated` float DEFAULT NULL,
                      `billno` bigint(20) UNSIGNED DEFAULT NULL,
                       FOREIGN KEY(meterno) REFERENCES meterdetails(meterno),
                       FOREIGN KEY(billno) REFERENCES housebill(billno) );""";
            stmt.execute(Query);

//            Inserting value to NEA Table (2078 Poush)

            Query = """
                    INSERT INTO nea (minU, maxU, minAmt, rateperUnit)
                    VALUES
                    (1,20,30,3),
                    (21,30,50,6.5),
                    (31,50,50,8),
                    (51,100,75,9.5),
                    (101,250,100,9.5),
                    (251,1000,150,11);""";
            stmt.execute(Query);

            connection.close();

        }
        catch (SQLException e){
            throw new SQLException("Cannot establish a connection to the Database.");
        }

    }
}
