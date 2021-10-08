package com.revature.services;

import java.util.List;
import com.revature.dao.UserDAO;
import com.revature.models.User;

public class UserService {
	
	private UserDAO uDao;
	private static User loggedInUser = new User();
	//private List<User> listUsers = new ArrayList<User>();

	public UserService(String m, UserDAO uDao)
	{
		outputToUser(m);
		this.uDao = uDao;
	}
	
	public void printAllUsers()
	{
		for (User user: getAllUsers())
		{
			outputToUser(user.toString());
		}
	}
	
	public List<User> getAllUsers()
	{
		return uDao.getAllUsers();
		// return listUsers;
	}
	
	public User signUp(String first, String last, String email, String userName, String passHash) {
		
		try {
			User u = new User(first, last, email, userName, passHash);
			uDao.addUser(u);
		} catch(Exception ex) {ex.printStackTrace();}

		
		return new User();
	}
	
	public User signIn() {
		try {
			
			
		} catch(Exception ex) {ex.printStackTrace();}
		
		return null;
	}
	

	
	
	public void outputToUser(String m) { System.out.println(m); }
	


	
	public void getNewUserData()
	{
		// get user info
		  // need first name, last name, username, password, verifypassword
		// u.setUserName(getUserDataPoint(PROMPT + "username :"));
		
		// password is not equal to verifypassword
		  // output message to user mandating reentry of data
		  // prompt for more user data
	}
	
	
	public void addUser(User user)
	{
		// Add the user to the database
		uDao.addUser(user);
	}

	public static User getLoggedInUser() {
		return loggedInUser;
	}

	public static void setLoggedInUser(User loggedInUser) {
		UserService.loggedInUser = loggedInUser;
	}

	public User getUserByUsername(String username) {

		return uDao.getUserByUsername(username);
	}
	
	public static void resetLoggedInUser() 
	{ 
		UserService.loggedInUser = new User();
	}

}
