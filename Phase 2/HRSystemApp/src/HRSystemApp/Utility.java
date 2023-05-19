package HRSystemApp;

import java.sql.*;

public class Utility 
{
	public String dburl="jdbc:oracle:thin:@***REMOVED***:1521/***REMOVED***";
	public String user="test";
	public String pass="test";
	Statement stmt;
	Connection conn;
	ResultSet rs;
	
	public Utility() throws SQLException 
	{
		conn=DriverManager.getConnection(dburl, user, pass);
		stmt=conn.createStatement();
	}
	
	public void terminate() throws SQLException
	{
		stmt.close();
		conn.close();
	}
	
}