package com.besant.app.repo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.besant.app.pojo.Profile;
import com.besant.app.pojo.User;


public class JdbcConnection {
	
	public User getUserValueFromUserId(String userId) {
		User user= null;
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");
		
		Connection con= DriverManager.getConnection("jdbc:mySql://localhost:3306/banking","root","Pass@123");
		PreparedStatement ps= con.prepareStatement("select * from login_servlet where userid=?");
		ps.setString(1, userId);
		ResultSet rs= ps.executeQuery();
		while(rs.next()) {
			user= new User();
			user.setUserId(rs.getString(1));
			user.setPassword(rs.getString(2));			
		}	
		
		}catch(Exception e) {
			
			
		}
		return user;
		
	}

	public boolean signup(Profile profile) {
		try {
		Class.forName("com.mysql.cj.jdbc.Driver");		
		Connection con= DriverManager.getConnection("jdbc:mySql://localhost:3306/banking","root","Pass@123");
		PreparedStatement ps= con.prepareStatement("insert into login_servlet values (?,?,?,?,?)");
		ps.setString(1, profile.getUserId());
		ps.setString(2, profile.getPassword());
		ps.setString(3, profile.getEmail());
		ps.setString(4, profile.getPhone());
		ps.setString(5, profile.getName());
		int result= ps.executeUpdate();
		return result==1;
		
		
		}catch(Exception e) {
			e.printStackTrace();
			
		}
		return false;
	}

}
