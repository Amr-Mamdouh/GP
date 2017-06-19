package dao;

import java.sql.Connection;
import java.sql.SQLException;

import org.apache.tomcat.dbcp.dbcp2.BasicDataSource;


public class DataSource {
	private static DataSource dataSource ; 
	private BasicDataSource basicDataSource ; 
	private static final String DB_NAME = "smartmarket";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	private static final String HOST = "localhost";
	private static final String PORT = "3306";
  public DataSource() {
      basicDataSource = new BasicDataSource() ; 
      basicDataSource.setDriverClassName("com.mysql.jdbc.Driver");
      basicDataSource.setUsername(USER);
      basicDataSource.setPassword(PASSWORD);
      basicDataSource.setUrl("jdbc:mysql://"+HOST+":"+PORT+"/"+DB_NAME);
  
      basicDataSource.setMinIdle(20);
      basicDataSource.setMaxIdle(3);
      basicDataSource.setMaxOpenPreparedStatements(20);
  }
  public static DataSource getInstance(){
	   if(dataSource==null){
		   dataSource = new DataSource() ; 
	   }
	   return dataSource ; 
  }
  public Connection getConnection() throws SQLException{
	  return this.basicDataSource.getConnection() ; 
  }
}
