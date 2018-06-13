package com.revature.util;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;


public class ConnFactory {
	// Holds the single instance of ConnFactory allowed
	private static ConnFactory cf = new ConnFactory();
		
	String dbURL = "";
	String dbUsername = "";
	String dbPassword = "";
	DataSource ds;
	// A private constructor that makes the ConnFactory class a singleton
	private ConnFactory() {
		super();
	}
	
	public ConnFactory(String dbUrl, String username, String password) {
		this.dbURL = dbUrl;
		this.dbUsername = username;
		this.dbPassword = password;
		
		ds = setupDataSource();
	}
	
	public DataSource setupDataSource() {
        BasicDataSource ds = new BasicDataSource();
        ds.setUsername(this.dbUsername);
        ds.setPassword(this.dbPassword);
        ds.setUrl(this.dbURL);
        ds.setDriverClassName("com.mysql.jdbc.Driver");
        return ds;
    }
		
	// Retrieves the instance of ConnFactory
	public static synchronized ConnFactory getInstance() {
		// Creates a new instance if one doesn't already exist
		if(cf == null)
			cf = new ConnFactory();
		// Returns the instance of Connection factory
		return cf;
	}
		
	// Creates and returns a Connection to the database
	public Connection getConnection() {
		// Creates a Connection object
		Connection conn = null;
		
		// Attempts to establish a connection with the database
		try {
			// Loads the database properties folder
			Properties prop = new Properties();
			FileReader file = new FileReader("database.properties");
			prop.load(file);
			
			//Retrieves the necessary database driver
			Class.forName(prop.getProperty("driver"));
			
			// Establishes the connection using the properties file
			conn = DriverManager.getConnection(prop.getProperty("url"), 
											   prop.getProperty("usr"), 
											   prop.getProperty("password"));
		// Executes if a connection couldn't be established with the database
	   	}catch(SQLException | IOException | ClassNotFoundException e) {
	   		System.out.println("Couldn't connect to database!");
	   		e.printStackTrace();
	   	}
		
		// Returns the created Connection object
		return conn;
	}
}
