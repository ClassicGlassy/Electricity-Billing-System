package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryProvider {

    public void insertInto(Connection con, String name, String pass, String code){
        try{
            String insertQuery = "insert into profile(name,password,code) values(?,?,?)";
//            Prepared Statement
            PreparedStatement statement = con.prepareStatement(insertQuery);
//            Set the Value
            statement.setString(1,name);
            statement.setString(2,pass);
            statement.setString(3,code);

//            execute
            int res = statement.executeUpdate();
            System.out.println(res);
            System.out.println("Inserted Successfully");


        }
        catch (SQLException e){
            System.out.println("Can't Process the Insert Query to Profile Table.");
        }



    }


}
