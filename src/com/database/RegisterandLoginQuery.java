package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class RegisterandLoginQuery {
    public void registerUser(){

    }

    public byte loginUser(Connection con, String Email, String Password){
        try{
            String query = "select acc_type from profiles where email = ? and pwd = ?";

            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1,Email);
            statement.setString(2,Password);

            System.out.println(query);

            ResultSet result = statement.executeQuery();
            if(result.next()) {
//                System.out.println(result.getByte("acc_type"));
                return result.getByte("acc_type");
            }
        }
        catch (Exception e){
            e.printStackTrace();
        }
        return 3;
    }
}
