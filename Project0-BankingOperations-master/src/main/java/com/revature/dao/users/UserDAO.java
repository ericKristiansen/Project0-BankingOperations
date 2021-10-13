package com.revature.dao.users;

import java.util.List;

import com.revature.models.User;

public interface UserDAO {
	
	// define some CRUD operations here
	public List<User> getAllUsers();
	public User getUserByUsername(String userName);
	public List<User> getAllAccountOwnersById(int accountId);
	public void updateUser(User user);
	public void deleteUserById(int id);
	public void addUser(User u);

}
