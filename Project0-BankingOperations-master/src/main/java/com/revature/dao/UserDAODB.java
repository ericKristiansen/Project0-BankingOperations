package com.revature.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.User;
import com.revature.utils.ConnectionUtil;

public class UserDAODB  implements UserDAO{
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();

	//use statements to talk to our database
	@Override
	public List<User> getAllUsers() {

		List<User> userList = new ArrayList<User>();
		
		try {
			//Make the actual connection to the db
			Connection con = conUtil.getConnection();
			
			//create a simple statement
			String sql = "SELECT * FROM USERS;";
			
			//we need to create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			// loop through the result set, and create objects based off the return
			// (id, first_name, last_name, user_name, passwprd, email, isEmployee, isAdmin)
			while(rs.next()) {
				userList.add(populateUser(rs));
			}
		} catch(Exception ex) {ex.printStackTrace();}
		
		return userList;
	}
	
	private User populateUser(ResultSet rs)
	{
		User tempUser = null;
		 try {
			tempUser =  new User(rs.getInt(USER.ID.value()), rs.getString(USER.FIRST_NAME.value()), 
						rs.getString(USER.LAST_NAME.value()), rs.getString(USER.USER_NAME.value()), 
						rs.getString(USER.PASSWORD.value()), rs.getString(USER.EMAIL.value()), 
						rs.getBoolean(USER.IS_EMPLOYEE.value()), rs.getBoolean(USER.IS_ADMIN.value()));
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempUser;
	}

	@Override
	public User getUserByUsername(String username) {
		User tempUser = null;
		try {
			Connection con = conUtil.getConnection();
			
			String sql = "SELECT * FROM users WHERE users.username = '" + username + "';";
			
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);
			
			while(rs.next()) {
				tempUser = populateUser(rs);
			}

		} catch(SQLException e) {
			e.printStackTrace();
		}
		return tempUser;
	}

	@Override
	public void updateUser(User user) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteUserById(int id) {
		Connection con = conUtil.getConnection();
		String sql = "delete from user where user.id = ?";
		PreparedStatement ps;
		try {
			ps = con.prepareStatement(sql);
			ps.setInt(1, id);
			ps.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

	@Override
	public void addUser(User user) 
	{
		//create connection
		Connection con = conUtil.getConnection();
		
		//create statement (id, first_name, last_name, user_name, password, email, isEmployee, isAdmin)
		String sql = "insert into users(first_name, last_name, username, password, email)" +
				" values(?,?,?,?,?);";

		try {
			PreparedStatement ps = con.prepareStatement(sql);
			ps.setString(1, user.getFirstName());
			ps.setString(2, user.getLastName());
			ps.setString(3, user.getUsername());
			ps.setString(4, user.getPassword());
			ps.setString(5, user.getEmail());
			
			ps.execute();
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
	}

}
