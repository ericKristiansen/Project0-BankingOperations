package com.revature.services;

import java.util.Scanner;

import com.revature.dao.USER;
import com.revature.exceptions.InvalidCredentialsException;


public class LoginService {

	private UserService us;
	private Scanner in = new Scanner(System.in);
	
	public LoginService(UserService us) {
		this.us = us;
	} 
	
	// Login User called from main... main only cares that when this logic is complete,
	// A user is logged in. Return true if the user is logged in or false if the user wishes to exit.
	public boolean executeLoginLogic()
	{
		// get user name
		loginUser();
		
		// determine if the username is used or not.
		boolean isExistingUser = UserService.getLoggedInUser().isLoggedIn();
		
		if (isExistingUser)
		{
			System.out.println("The user is logged in......");
		}
		else 
		{
			System.out.println("You will register ...");
			registerUser();
		}
			
		return true;
			
	}
	
	public void loginUser()
	{
		boolean done = false;
		while(!done)
		{
			String username = getUserName();
			
			// Check if username exists, and load the user if so, null otherwise
			UserService.setLoggedInUser(us.getUserByUsername(username));
			
			if (UserService.getLoggedInUser() != null)
			{
				us.outputToUser(username + " is Logged in");
				
				//verify password
				boolean isMatch = getPassword().equals(UserService.getLoggedInUser().getPassword());
				
				if (isMatch)
				{
					UserService.getLoggedInUser().setIsLoggedIn(isMatch);
					return;
				}
				else
				{
					try {
					throw new InvalidCredentialsException();
					} catch(InvalidCredentialsException ex) {
						us.outputToUser("Your credentials are invalid...");
						UserService.resetLoggedInUser();
						continue;
					}
				}

			}
			
			// if not ask if entry is correct
			else
			{
				us.outputToUser("we will need more information from " + username);
				us.outputToUser("You have entered: " + username + ". Is that correct?");
				us.outputToUser("Press 1 to for yes, Press 2 for no: ");
				int choice = Integer.parseInt(in.nextLine());
				if(choice == 1) 
				{
					UserService.resetLoggedInUser();
					UserService.getLoggedInUser().setUsername(username);
					done = true; 
				}
				else 
				{ continue; }
			}
			
		}
	}
	
	
	/*
	 * This is to be used when registering new user or validating credentials.
	 * Return the password entered by the user.
	 */
	public String getPassword()
	{
		return getStringFromUser(USER.PASSWORD.name());
	}
	
	public String getFirstName()
	{
		return getStringFromUser(USER.FIRST_NAME.name());
	}
	
	public String getLastName()
	{
		return getStringFromUser(USER.LAST_NAME.name());
	}
	
	public String getEmail()
	{
		return getStringFromUser(USER.EMAIL.name());
	}
	
	public String getUserName()
	{
		return getStringFromUser(USER.USER_NAME.name());
	}
	
	public String getStringFromUser(String str)
	{
			us.outputToUser("Please enter your " + str + " to continue: ");
			return in.nextLine();
	}
	
	/*
	 * 
	 */
	public void registerUser() {		
		UserService.getLoggedInUser().setPassword(getPassword());
		UserService.getLoggedInUser().setFirstName(getFirstName());
		UserService.getLoggedInUser().setLastName(getLastName());
		UserService.getLoggedInUser().setEmail(getEmail());
		
		us.addUser(UserService.getLoggedInUser());
	}
	
	
	
	
	
	
	
}
