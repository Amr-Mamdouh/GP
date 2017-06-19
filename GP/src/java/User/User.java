package User;

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


public class User {
    int id=0;
    String Type ="";
static int  C_ID=101020;
static int  A_ID=104030;

    public User() {
    }
    
    public User Login(String user_name,String pass)
    {
        User user=new User();
         try{
        Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT 'user_ID' AND 'kind' FROM `User`  "
                            + "  WHERE `userName`= ? AND 'password'= ?");
            
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, pass);
            ResultSet res = preparedStatement.executeQuery();
            user.setId(res.getInt("user_ID"));
            user.setType(res.getString("kind"));
            preparedStatement.close();
            connection.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    public User Company_Login(String user_name,String pass)
    {
        User user=new User();
         try{
        Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT 'user_ID' AND 'kind' FROM `Company`  "
                            + "  WHERE `userName`= ? AND 'pass'= ?");
            
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2, pass);
            ResultSet res = preparedStatement.executeQuery();
            user.setId(res.getInt("user_ID"));
            user.setType(res.getString("kind"));
            preparedStatement.close();
            connection.close();
            return user;
        } catch (SQLException e) {
            e.printStackTrace();
            return null;
        }
        
    }
    public double Get_Bill(int user_id,int order)
    {
         try{
        Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("SELECT 'totalPrice'  FROM `Order`  "
                            + "  WHERE `userID`= ? AND 'order_ID'= ?");
            
            preparedStatement.setInt(1, user_id);
            preparedStatement.setInt(2, order);
            ResultSet res = preparedStatement.executeQuery();
            double price=res.getDouble("totalPrice");
            preparedStatement.close();
            connection.close();
            return price;
        } catch (SQLException e) {
            e.printStackTrace();
            return 0.0;
        }
        
    }
    
    public User SignUp_Company(String user_name,String email,String password,String phone ,String addres)
    {
         try {
                Connection connection = DataSource.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.
                        prepareStatement("INSERT INTO `Company`"
                                + "(`company_ID`, `userName`, `email`,'pass','address','phone','rate','kind') "
                                + "VALUES (?, ?, ?) ");
                preparedStatement.setInt(1,C_ID+1);
                preparedStatement.setString(2, user_name);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, addres);
                preparedStatement.setString(6, phone);
                preparedStatement.setDouble(7, 0.0);
                preparedStatement.setString(8, "Company");

                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
                User u=new User();
                u.setId(C_ID+1);
                C_ID++;
                u.setType("Company");
            return u;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
    public User SignUp_Admin(String user_name,String email,String password,String phone)
    {
    try {
                Connection connection = DataSource.getInstance().getConnection();
                PreparedStatement preparedStatement = connection.
                        prepareStatement("INSERT INTO `user`"
                                + "('user_ID`, `userName`, `email`,'password','address','phone','kind') "
                                + "VALUES (?, ?, ?) ");
                preparedStatement.setInt(1,A_ID+1);
                preparedStatement.setString(2, user_name);
                preparedStatement.setString(3, email);
                preparedStatement.setString(4, password);
                preparedStatement.setString(5, phone);
                preparedStatement.setString(6, "Admin");

                preparedStatement.executeUpdate();
                preparedStatement.close();
                connection.close();
                User u=new User();
                u.setId(A_ID+1);
                A_ID++;
                u.setType("Admin");
      
            return u;

        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public int getId() {
        return id;
    }

    public String getType() {
        return Type;
    }

    public void setId(int id) {
        this.id = id;
    }

    public void setType(String Type) {
        this.Type = Type;
    }
    
   public boolean Update_Company(int c_id,String user_name,String email,String password,String phone ,String addres)
   {
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE `Company` SET "
                            + "'userName`= ? ,"
                            + "`passw`= ? ,`email`= ? ,`address`= ? ,"
                            + "`phone`= ? "
                            + "  WHERE `company_ID`= ?");
            
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, addres);
            preparedStatement.setString(5, phone);
            preparedStatement.setInt(6, c_id);
            int effectedRows = preparedStatement.executeUpdate();
            preparedStatement.close();
            connection.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }


   }
    public boolean Update_Admin(int A_id,String user_name,String email,String password,String phone ,String addres)
   {
        try {
            Connection connection = DataSource.getInstance().getConnection();
            PreparedStatement preparedStatement = connection
                    .prepareStatement("UPDATE `User` SET "
                            + "'userName`= ? ,"
                            + "`passw`= ? ,`email`= ? ,`address`= ? ,"
                            + "`phone`= ? "
                            + "  WHERE `user_ID`= ?");
            
            preparedStatement.setString(1, user_name);
            preparedStatement.setString(2,password);
            preparedStatement.setString(3, email);
            preparedStatement.setString(4, addres);
            preparedStatement.setString(5, phone);
            preparedStatement.setInt(6, A_id);
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
