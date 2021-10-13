package com.revature.services;

import java.util.List;
import java.util.Scanner;

import com.revature.dao.users.UserDAO;
import com.revature.models.User;
import com.revature.services.ServiceWrangler;

public class UserService {
	
	private UserDAO uDao;
	private static User loggedInUser = new User();
	private Scanner in = new Scanner(System.in);

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
	
	public void printAllCustomers()
	{
		for (User user: getAllUsers())
		{
			if (!user.isEmployee())
			{
				outputToUser(user.toString());
			}
		}
	}
	
	public void modifyUserAccounts(ServiceWrangler sw) { 

		for(User u: getAllUsers())
		{
			try {
				loggedInUser = u;
				sw.updateUserInformation();
				
			}catch(Exception ex) {ex.printStackTrace();}
		}
		
	}
	
	public List<User> getAllUsers()
	{
		return uDao.getAllUsers();
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
	
	public String getUserStringResponse(String message) {
		outputToUser(message);
		return in.nextLine();
	}
	public int getUserIntResponse(String message) {
		outputToUser(message);
		return Integer.parseInt(in.nextLine());
	}
	
	public double getUserDoubleResponse(String message)
	{
		outputToUser(message);
		double tmp = -1;
		try
		{
			tmp = Double.parseDouble(in.nextLine());
		}
		catch(NumberFormatException ex) {
			outputToUser("You have not entered in a valid number.");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return tmp;
	}

	
	public void printUserInformation()
	{
		User u = loggedInUser;
		//display current information
		outputToUser("****User Information****");
		outputToUser("Username: " + u.getUsername());
		outputToUser("First Name: " + u.getFirstName());
		outputToUser("Last Name: " + u.getLastName());
		outputToUser("Email: " + u.getEmail());
		outputToUser("****User Information****");
	}
	
	/*
	 * 			us.updateUserInformation(ls.getUserName(), us.getPassword(), us.getFirstName(),
					us.getLastName(), us.getEmail());
	 */
	public void updateUserInformation(String username, String password, String firstName, 
			String lastName, String email)
	{
		// echo new information
		outputToUser("****New User Information****");
		outputToUser("Username: " + username);
		outputToUser("First Name: " + firstName);
		outputToUser("Last Name: " + lastName);
		outputToUser("Email: " + email);
		outputToUser("****User Information****");

		// get confirmation of update
		int choice = getUserIntResponse("\nDo you wish to change your information to that above?\n" +
				"Press 1 for yes or 2 for no: ");
	
		// update
		if(choice == 1)
		{
			loggedInUser.setUsername(username);
			loggedInUser.setFirstName(firstName);
			loggedInUser.setLastName(lastName);
			loggedInUser.setEmail(email);
			
			uDao.updateUser(loggedInUser);
		}
		
	}
	
	public void addUser(User user)
	{
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
