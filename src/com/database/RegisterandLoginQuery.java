package com.database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class RegisterandLoginQuery {
    public void registerUser(Connection con, String name,String email, String pass, String phone, byte acc_type) throws SQLException {
        {
            String insertQuery = "insert into profiles(name,email,pwd,phone,acc_type) values(?,?,?,?,?)";

            if(con != null) {
                try {
//            Prepared Statement
                    PreparedStatement statement = con.prepareStatement(insertQuery);
//            Set the Value
                    statement.setString(1, name);
                    statement.setString(2, email);
                    statement.setString(3, pass);
                    statement.setString(4, phone);
                    statement.setByte(5, acc_type);

//            execute
                    statement.executeUpdate();
                    con.close();
                }
                catch (SQLException e){
                   throw new SQLException("An account with same Email already exist!");
                }
            }
        }


    }

    public ResultSet loginUser(Connection con, String Email, String Password) throws SQLException {

        if(con != null) {
            ResultSet result;
            String query = "select acc_type from profiles where email = ? and pwd = ?";

            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, Email);
            statement.setString(2, Password);

//            System.out.println(query);

            result = statement.executeQuery();
            con.close();
            return result;
        }
        else {
            throw new SQLException();
        }
    }
}
