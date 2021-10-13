package com.revature.services;


import com.revature.dao.accounts.AccountDAO;
import com.revature.dao.accounts.AccountDAODB;
import com.revature.dao.users.UserDAO;
import com.revature.dao.users.UserDAODB;
import com.revature.services.AccountService;
import com.revature.services.LoginService;
import com.revature.services.UserService;


/**
 * This class exists to coordinate the various services.
 * @author Eric Kristiansen
 *
 */
public class ServiceWrangler {
	
	private static UserDAO uDao = new UserDAODB();
	private static AccountDAO aDao = new AccountDAODB();

	private UserService us;
	private LoginService ls;
	private AccountService as;
	
	
	public ServiceWrangler(String welcomeMessage) {
		this.us = new UserService(welcomeMessage, uDao);
		this.ls = new LoginService(us);
		this.as = new AccountService(aDao, us);
	}
	
	public void viewAllAccountInformation() {
		as.printAllAccounts();
	}

	public void viewAllCustomerInformation() {
		us.printAllCustomers();
	}

	public void approveDenyApplications() {
		as.approveDenyAccounts();
	}
	public void approveDenyCancelApplications()
	{
		as.approveDenyCancelApplications();
	}
	
	public int employeeMenu()
	{
		String menu = "\nEmployee Menu\n\n1 View all account information\n2 View all customer information\n" +
				"3 approve or deny open accounts\n4 exit\nPress the number of your selection and enter: ";
		int choice = -1;
		try
		{
			choice = us.getUserIntResponse(menu);
			if (choice < 1 || choice > 4)
			{
				us.outputToUser("You did not make a valid selection...");
			}
		}catch(Exception ex) {ex.printStackTrace();}
		
		return choice;
	}
	
	public int adminMenu() 
	{
		String menu = "\nAdmin Menu\n\n1 Modify all account information\n2 Modify all customer information\n" +
				"3 approve or deny or cancel accounts\n4 exit\nPress the number of your selection and enter: ";
		int choice = -1;
		try
		{
			choice = us.getUserIntResponse(menu);
			if (choice < 1 || choice > 4)
			{
				us.outputToUser("You did not make a valid selection...");
			}
		}catch(Exception ex) {ex.printStackTrace();}
		
		return choice;
	}
	
	public void modifyAllAccountInformation() { 
		as.modifyAllAccounts();
	}
	
	public void modifyUserAccounts() {
		
		String username = UserService.getLoggedInUser().getUsername();
		
		us.modifyUserAccounts(this);
		
		//reset current user to admin user account
		UserService.setLoggedInUser(us.getUserByUsername(username));
		
	}
	
	public void applyForNewAccount()
	{
		//prompt user to apply for new account
		int choice = us.getUserIntResponse("Would you like to apply for a new account?\n" +
				"Press 1 for yes, or press 2 for no: ");
		if(choice == 1)
		{
			double tmp = -1;
			while(tmp < 0)
			{
				tmp = us.getUserDoubleResponse("Please, enter the amount you would like " +
					"to open the account with: ");
				if (tmp < 0)
				{
					us.outputToUser("You must enter in a value greater than 0. We reccomend a minimum of 5.00");
				}
			}
			
			as.applyForNewAccount(UserService.getLoggedInUser().getId(), tmp);
		}
	}
	
	public boolean isAdmin()
	{
		return UserService.getLoggedInUser().isAdmin();
	}
	
	public void updateUserInformation()
	{
		us.printUserInformation();
		// prompt do you wish to update your information
		int choice = us.getUserIntResponse("Do you wish to update this information?\n"
				+ "Press 1 for yes or 2 for no: ");
		
		//if so, get new information, and update the user.
		if (choice == 1)
		{
			us.updateUserInformation(ls.getUserName(), ls.getPassword(), ls.getFirstName(),
					ls.getLastName(), ls.getEmail());
		}
	}
	
	public boolean isEmployee()
	{
		return UserService.getLoggedInUser().isEmployee();
	}
	
	public boolean isUserAccountApproved()
	{
		return as.isUserAccountApproved();
	}
	
	public boolean transactActiveAccount()
	{
		//select operations on account
		return as.transactActiveAccount();
	}
	
	public boolean determineActiveAccount()
	{
		return as.determineActiveAccount();
	}
	
	public void checkUserAccounts()
	{
		as.checkUserAccounts(UserService.getLoggedInUser().getId());
	}
	
	public void printAllAccounts() {
		as.printAllAccounts();
	}
	
	public boolean executeLogin()
	{
		return ls.executeLoginLogic();
	}
	
	public void approveAccount(int accountId)
	{
		as.approveAccount(accountId);
	}

	public boolean getUserWishesToContinue()
	{
		int choice = us.getUserIntResponse("Would you like to continue or logout?\n"
				+ "Press 1 for continue, or press 2 to logout");
		return (choice == 1);
	}
	
	public void GoodbyeMessage()
	{
		us.outputToUser("\n\nThank you for your patronage. Gooodbye...\n\n");
	}
	
}
