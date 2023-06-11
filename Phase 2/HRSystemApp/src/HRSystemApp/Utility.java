package HRSystemApp;

import java.sql.*;
import java.util.ArrayList;

public class Utility 
{
	private String dburl="jdbc:oracle:thin:@hostname:port/service_name";
	private String user;
	private String pass;
	private Statement stmt;
	private PreparedStatement pstmt;
	private Connection conn;
	private ResultSet rs;
	
	public Utility(String user,String pass) throws SQLException 
	{
		this.user=user;
		this.pass=pass;
		conn=DriverManager.getConnection(dburl, user, pass);
		stmt=conn.createStatement();
	}
	
	public void terminate() throws SQLException
	{
		stmt.close();
		conn.close();
	}
	
	public boolean login(String username,String password) throws SQLException {
		String sql="select username,pass from login where username=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, username.toUpperCase());
		rs=pstmt.executeQuery();
		if(rs.next())
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	
	public ArrayList<String> getPositions() throws SQLException{
		ArrayList<String> positions= new ArrayList<String>();
		String sql="select pos_name from positions";
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			positions.add(rs.getString(1));	
		}
		return positions;
	}
	
	public ArrayList<Integer> getDno () throws SQLException{
		ArrayList<Integer> dnumbers= new ArrayList<Integer>();
		String sql="select dnumber from department";
		ResultSet rs=stmt.executeQuery(sql);
		while(rs.next())
		{
			dnumbers.add(rs.getInt(1));	
		}
		return dnumbers;
	}
	
	public void addEmp(String ID,String name,String gender,String bdate,String sal,String comm,String phone,String pos,String dno) throws SQLException{
		String sql="insert into Employee values(?,?,?,TO_DATE(?,'DD-MON-YYYY'),sysdate,?,?,?,?,?)";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, ID);
		pstmt.setString(2, name);
		pstmt.setString(3, gender);
		pstmt.setString(4, bdate);
		pstmt.setString(5, sal);
		pstmt.setString(6, comm);
		pstmt.setString(7, phone);
		pstmt.setString(8, pos);
		pstmt.setString(9, dno);
		pstmt.executeUpdate();
		pstmt.close();
	}
	
	public void removeEmp(String ID) throws SQLException {
		String sql="delete from employee where emp_id=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, ID);
		int row=pstmt.executeUpdate();
		pstmt.close();
		
	}
	
	public void updateSal (String ID,String sal) throws SQLException {
		String sql="update employee set salary=? where emp_id=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, sal);
		pstmt.setString(2, ID);
		int row=pstmt.executeUpdate();
		pstmt.close();
		
	}
	
	public ResultSet loadEmp() throws SQLException {
		String sql="select emp_id as ID ,ename as Name,Gender as Gender,pos as Position,deptno as Department"
				+ " from employee";
		rs=stmt.executeQuery(sql);
		return rs;
	}
	
	public ResultSet loadEmpSal() throws SQLException {
		String sql="select employee.emp_id as ID ,ename as Name,Salary as Salary,pos as Position,deptno as Department,Grade"
				+ " from employee join emp_grade on (employee.emp_id=emp_grade.emp_id)";
		rs=stmt.executeQuery(sql);
		return rs;
	}
	
	
	public ResultSet clearAllowance() throws SQLException {
		String sql="select Aname as Allowances from position_allowance where 1=2";
		rs=stmt.executeQuery(sql);
		return rs;
	}
	
	public ResultSet getAllowance(String pos) throws SQLException {
		String sql="select Aname as Allowances from position_allowance where Pos_name=?";
		pstmt=conn.prepareStatement(sql);
		pstmt.setString(1, pos);
		rs=pstmt.executeQuery();
		return rs;
	}
	
	public ResultSet loadEmpReport() throws SQLException {
		String sql="select Employee_Name,Department_Name,Hiredate from Employee_History";
		rs=stmt.executeQuery(sql);
		return rs;
	}
	
	public ResultSet loadVehicleReport() throws SQLException {
		String sql="select Employee_Name,Vehicle_Model,Next_Maintenance_Date from Vehicle_History";
		rs=stmt.executeQuery(sql);
		return rs;
	}
	
	public ResultSet loadProjectReport() throws SQLException {
		String sql="select Project_Name,Project_Description,Department_Name from Project_History";
		rs=stmt.executeQuery(sql);
		return rs;
	}
	
	public ResultSet loadOvertimeReport() throws SQLException {
		String sql="select Employee_Name,Overtime_Pay  from Overtime_History";
		rs=stmt.executeQuery(sql);
		return rs;
	}
}