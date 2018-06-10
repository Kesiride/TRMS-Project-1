package com.revature.daoimpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.beans.ConnFactory;
import com.revature.beans.Employee;
import com.revature.dao.EmployeeDAO;

public class EmployeeDAOImpl implements EmployeeDAO{

	@Override
	public List<Employee> getAllEmployees() throws SQLException {
		// Retrieves the ConnFactory instance to create a database connection and creates a list to store employees
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection();
		List<Employee> employees = new ArrayList<>();
		
		// Prepares the SQL resources
		String sql = "SELECT * FROM EMPLOYEE";
		Statement stmt = conn.createStatement();
		ResultSet rs = stmt.executeQuery(sql);
		
		// Retrieves all of the employees in the database and puts them in the employees list
		while(rs.next()) {
			Employee emp = new Employee();
			
			
			
			employees.add(emp);
		}
		
		// Closes the database connection and returns employees
		conn.close();
		return employees;
	}

	@Override
	public Employee getEmployee(int id) throws SQLException {
		// Retrieves the ConnFactory instance to create a database connection and creates an empty employee to store information
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection();
		Employee emp = new Employee();
				
		// Prepares the SQL resources
		String sql = "SELECT * FROM EMPLOYEE WHERE empId = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
		
		//Puts the retrieved employee information into emp
		while(rs.next()) {
			
		}
		
		// Closes the database connection and returns emp
		conn.close();
		return emp;
	}

	@Override
	public Employee employeeLogin(String username, String password) throws SQLException {
		// Retrieves the ConnFactory instance to create a database connection and creates an empty employee to store information
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection();
		Employee emp = new Employee();
						
		// Prepares the SQL resources
		String sql = "SELECT * FROM EMPLOYEE WHERE username = ? AND password = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setString(1, username);
		stmt.setString(2, password);
		ResultSet rs = stmt.executeQuery();
				
		//Puts the retrieved employee information into emp
		while(rs.next()) {
			
		}
				
		// Closes the database connection and returns emp
		conn.close();
		return emp;
	}

	@Override
	public List<Employee> getSubordinates(int id) throws SQLException {
		// Retrieves the ConnFactory instance to create a database connection and creates a list to store employees
		ConnFactory cf = ConnFactory.getInstance();
		Connection conn = cf.getConnection();
		List<Employee> employees = new ArrayList<>();
								
		// Prepares the SQL resources
		String sql = "SELECT * FROM Subordinates WHERE SupId = ?";
		PreparedStatement stmt = conn.prepareStatement(sql);
		stmt.setInt(1, id);
		ResultSet rs = stmt.executeQuery();
						
		//Puts the retrieved employee information into employees
		while(rs.next()) {
			Employee emp = new Employee();
			
			
			
			
			employees.add(emp);
		}
						
		// Closes the database connection and returns employees
		conn.close();
		return employees;
	}
}