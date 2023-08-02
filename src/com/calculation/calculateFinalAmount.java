package com.calculation;

import com.database.ConnectionProvider;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class calculateFinalAmount {
    static int[] minU, maxU, minAmt;
    static float[] ratePerU;

//    Constructor to set all table
    public calculateFinalAmount(){
        try{
            ConnectionProvider _connectionProvider = new ConnectionProvider();
            Connection con = _connectionProvider.getConnection();

            ResultSet result, size;
            Statement smt = con.createStatement();

            String query = "SELECT * from nea";
            result = smt.executeQuery(query);
            query = "SELECT count(sn) from nea";
            size = smt.executeQuery(query);

//            Determine the size of array
            size.next();
            minU = new int[size.getInt(1)];
            maxU = new int[size.getInt(1)];
            minAmt = new int[size.getInt(1)];
            ratePerU = new float[size.getInt(1)];

            for(int i = 0; result.next(); i++){
                minU[i] = result.getInt(2);
                maxU[i] = result.getInt(3);
                minAmt[i] = result.getInt(4);
                ratePerU[i] = result.getFloat(5);
            }
            con.close();
        }
        catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public float calculate_bill(int unitConsumed) {
        int index = unit_use_category(unitConsumed);

        int rateable_unit = unitConsumed - (minU[index]) +1;
        return cummulative_bill(index) + rateable_unit * ratePerU[index] + minAmt[index];
    }

    private static int unit_use_category(int unit_use){
        if(unit_use == 0){
            return 0;
        }
        else{
            int i = minU.length - 1;
            while(true){
                if(unit_use < minU[i]){
                    i--;
                }
                else{
                    return i;
                }
            }
        }
    }

    private static float cummulative_bill(int index){
        float sum =0;
        for(int i = 0 ; i< index ; i++) {
            sum = sum + ((maxU[i] - minU[i] + 1) * ratePerU[i]) ;
        }
        return sum;
    }
}