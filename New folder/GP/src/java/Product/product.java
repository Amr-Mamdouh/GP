package Product;

import dao.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
/**
 *
 * @author amrfo_000
 */


public class product {
    public boolean Delete_Product(int id )
    {
         try {
            Connection conection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = conection.prepareStatement(
                    "DELETE FROM `product` WHERE product_ID = ? ");
            preparedStatement.setInt(1, id);
            int effectedRows = preparedStatement.executeUpdate();
            conection.close();
            preparedStatement.close();
            return  effectedRows > 0;
        } catch (Exception e) {
            return false;
        }
    }
    public boolean Rate_order(int company_id,double rate) 
    {
        try{
        Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE `Company` SET "
                            + "`rate`= ? "+
                            "  WHERE `company_ID`= ?");
            
            preparedStatement.setDouble(1, rate);
            preparedStatement.setInt(2, company_id);
            int effectedRows = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return effectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
        

    }
    public double Get_Rate(int company_id)
    {
         try{
        Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT 'rate' FROM `Company`  "
                            + "  WHERE `company_ID`= ?");
            
            preparedStatement.setInt(1, company_id);
             ResultSet res = preparedStatement.executeQuery();
             double rate =res.getDouble("rate");
            preparedStatement.close();
            connection.close();
            return rate;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
    }
    
    public boolean Set_Order_Statue(int order_id,String statue)
    {
try{
        Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE `ORDER` SET "
                            + "`Statue`= ? "+
                            "  WHERE `order_ID`= ?");
            
            preparedStatement.setString(1, statue);
            preparedStatement.setInt(2, order_id);
            int effectedRows = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return effectedRows > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }        
    }
   
    public String Get_Order_Statue(int order_id)
    {
         try{
        Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT 'statue' FROM `Order`  "
                            + "  WHERE `order_ID`= ?");
            
            preparedStatement.setInt(1, order_id);
            ResultSet res = preparedStatement.executeQuery();
            String statue=res.getString("statue");
            preparedStatement.close();
            connection.close();
            return statue;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
    }
    public boolean Recive_Order(int order_ID)
    {
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE `order` SET "
                            + "`delivered`= ?  WHERE `order_ID`= ?");
            
            preparedStatement.setInt(1, 1);
            preparedStatement.setInt(10,order_ID);
            int effectedRows = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
}
