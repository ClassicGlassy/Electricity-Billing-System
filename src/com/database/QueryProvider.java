package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class QueryProvider {

    public void insertInto(Connection con, String name,String email, String pass, String phone, byte acc_type){
        try{
            String insertQuery = "insert into profiles(name,email,pwd,phone,acc_type) values(?,?,?,?,?)";
//            Prepared Statement
            PreparedStatement statement = con.prepareStatement(insertQuery);
//            Set the Value
            statement.setString(1,name);
            statement.setString(2,email);
            statement.setString(3,pass);
            statement.setString(4,phone);
            statement.setByte(5,acc_type);

//            execute
            statement.executeUpdate();
            System.out.println("Inserted Successfully");
            con.close();


        }
        catch (SQLException e){
            System.out.println("Can't Process the Insert Query to Profiles Table.");
        }



    }


}
