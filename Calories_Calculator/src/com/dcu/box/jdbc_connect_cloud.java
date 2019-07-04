package com.dcu.box;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Properties;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class jdbc_connect_cloud {
	
	Connection con = null;
    PreparedStatement pst = null;
    ResultSet rs = null;
    HikariConfig config = null;
    HikariDataSource pool = null;
	
	public  ResultSet connectToCloudJDBC(String query) {
		Properties properties = new Properties();
		ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
		InputStream input = classLoader.getResourceAsStream("com/dcu/box/instance.properties");
		try {
			properties.load(input);
		} catch (IOException e) {
			System.out.println("Exception in loading properties");
		}
		String INSTANCE_CONNECTION_NAME = properties.getProperty("INSTANCE_CONNECTION_NAME");
		String user = properties.getProperty("user");
		String password = properties.getProperty("password");
		String database = properties.getProperty("database");
		
		this.config = new HikariConfig();
		// Configure which instance and what database user to connect with.
		config.setJdbcUrl(String.format("jdbc:mysql:///%s", database));
		config.setUsername(user); // e.g. "root", "postgres"
		config.setPassword(password); // e.g. "my-password"
		
		// For Java users, the Cloud SQL JDBC Socket Factory can provide authenticated connections.
		// See https://github.com/GoogleCloudPlatform/cloud-sql-jdbc-socket-factory for details.
		config.addDataSourceProperty("socketFactory", "com.google.cloud.sql.mysql.SocketFactory");
		config.addDataSourceProperty("cloudSqlInstance", INSTANCE_CONNECTION_NAME);
		config.addDataSourceProperty("useSSL", "false");
		
	
		this.pool = new HikariDataSource(config);
		
        try {
        	
        	 this.con = pool.getConnection();
             this.pst = con.prepareStatement(query);
             this.rs = pst.executeQuery();
        	
        }
        catch(Exception e)
        {
        	System.out.println("Exception while fetching data");
        }
		return rs;
        
	}
	
	public void closeConnection()
	{
		try {
            
            if (rs != null) {
                rs.close();
            }
            
            if (pst != null) {
                pst.close();
            }
            
            if (con != null) {
                con.close();
            }
            
            pool.close();

        } catch (SQLException ex) {

            System.out.println("Exception in close connection");
        }
	}
}
