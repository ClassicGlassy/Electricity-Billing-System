package com.database;


import java.sql.*;

public class RegisterAndLoginQuery {
    Connection con;

    public RegisterAndLoginQuery() throws SQLException {
        ConnectionProvider _connectionProvider = new ConnectionProvider();
        con = _connectionProvider.getConnection();
    }

    public void registerUser(String name,String email, String pass, String phone, byte acc_type) throws SQLException {
        {
            String insertQuery = "insert into profiles(name,email,pwd,phone,acc_type) values(?,?,?,?,?)";

            try {
//            Prepared Statement
                    PreparedStatement statement = con.prepareStatement(insertQuery);
//            Set the Value
                    statement.setString(1, name);
                    statement.setString(2, email);
                    statement.setString(3, pass);
                    statement.setString(4, phone);
                    statement.setByte(5, acc_type);

//                  execute
                    statement.executeUpdate();

                }
                catch (SQLException e){
                   throw new SQLException("An account with same Email already exist!");
                }
                finally {
                    con.close();
            }
        }
    }


    public ResultSet loginUser(String Email, String Password)throws SQLException{
        try {
            ResultSet result;
            String query = "select acc_type from profiles where email = ? and pwd = ?";

            PreparedStatement statement = con.prepareStatement(query);
            statement.setString(1, Email);
            statement.setString(2, Password);

            result = statement.executeQuery();
            return result;
        }
        catch (SQLException e){
            e.printStackTrace();
            throw new SQLException("Invalid Inputs");
        }
        finally {
            con.close();
        }

    }
}
