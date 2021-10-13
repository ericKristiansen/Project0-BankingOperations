package com.revature.services;

import java.util.ArrayList;
import java.util.List;

import com.revature.dao.accounts.AccountDAO;
import com.revature.models.AccountDisplay;

public class AccountService {

	private AccountDAO aDao;
	private UserService us;

	private static AccountDisplay activeAccount = null;
	private static boolean isAccountApproved = false;
	private static List<AccountDisplay> userAccounts;
	private static List<AccountDisplay> allAccounts;
	private static List<AccountDisplay> userApprovedAccounts = new ArrayList<AccountDisplay>();

	public AccountService(AccountDAO aDao, UserService us) {
		this.aDao = aDao;
		this.us = us;
	}

	/**
	 * Iterate through the user's accounts, and verify if they are approved or not.
	 * If the user has no approved accounts, they cannot yet make transactions.
	 * userApprovedAccounts is used to hold those accounts ready for transactions.
	 * The user will be alerted of account status.
	 * 
	 * @param userId
	 */
	public void checkUserAccounts(int userId) {

		getUserAccounts(userId);

		for (AccountDisplay ad : userAccounts) {
			us.outputToUser("************************");
			us.outputToUser("Account Number: " + ad.getAccountId() + "\nAccount Name: " + ad.getAccountName());
			us.outputToUser("Account Balance: " + ad.getBalance());
			if (ad.isApproved()) {
				us.outputToUser("Account Status: Approved");
				userApprovedAccounts.add(ad);
				isAccountApproved = true;
			} else {
				us.outputToUser("Account Status: Waiting on Approval\n");
			}
		}

		if (userApprovedAccounts.size() == 1) {
			activeAccount = userApprovedAccounts.get(0);
		}
	}
	
	public void approveDenyCancelApplications()
	{
		this.getAllAccounts();
		for(AccountDisplay ad: allAccounts)
		{
			//display account information
			us.outputToUser("\n**************\n" + ad.toString() + "\n\n");
			
			// step through accounts if not approved prompt for approval
			if (!ad.isApproved())
			{
				int choice = us.getUserIntResponse("Do you approve?\n"
						+ "Press 1 and enter to approve or press 2 for the next account.\n"
						+ "If you wish to delete this application or account, press 5: ");
				if(choice == 1)
				{
					aDao.setAccountApproval(ad.getAccountId(), true);
				}
				else if (choice == 5)
				{
					aDao.deleteAccountById(ad.getAccountId());
				}
			}
			else
			{
				int choice = us.getUserIntResponse("Do you want to deny (disapprove)this account?\n"
						+ "Press 1 and enter to disapprove or press 2 for the next account: ");
				if(choice == 1)
				{
					aDao.setAccountApproval(ad.getAccountId(), false);
				}
			}
		}
	}
	
	public void approveDenyAccounts()
	{
		this.getAllAccounts();
		for(AccountDisplay ad: allAccounts)
		{
			//display account information
			us.outputToUser("\n**************\n" + ad.toString() + "\n\n");
			
			// step through accounts if not approved prompt for approval
			if (!ad.isApproved())
			{
				int choice = us.getUserIntResponse("Do you approve?\n"
						+ "Press 1 and enter to approve or press 2 for the next account: ");
				if(choice == 1)
				{
					aDao.setAccountApproval(ad.getAccountId(), true);
				}
			}
			else
			{
				int choice = us.getUserIntResponse("Do you want to deny (disapprove)this account?\n"
						+ "Press 1 and enter to disapprove or press 2 for the next account: ");
				if(choice == 1)
				{
					aDao.setAccountApproval(ad.getAccountId(), false);
				}
			}
		}
	}
	
	public void modifyAllAccounts() { //*********************
		printAllAccounts();
		for (AccountDisplay ad: allAccounts)
		{
			activeAccount = ad;
			transactActiveAccount();
		}
	}
	
	public void applyForNewAccount(int userId, double amount)
	{
		// get amount to open account with
		aDao.applyNewAccount(userId, amount);
	}

	public void printAllAccounts() {
		getAllAccounts();

		for (AccountDisplay ad : allAccounts) {
			if (ad != null) {
				us.outputToUser(ad.toString());
			}
		}
	}

	private void getAllAccounts() {
		AccountService.allAccounts = aDao.getAllAccounts();
	}

	private void getUserAccounts(int userId) {
		AccountService.userAccounts = this.aDao.getUserAccountList(userId);
	}

	public boolean isUserAccountApproved() {
		return AccountService.isAccountApproved;
	}

	public boolean transactActiveAccount() {
		String menu = "Please, Select an option below:\n1 Withdrawal \n2 Deposit \n3 transfer \n4 end transactions"
				+ "Make your selection and press enter: ";
		boolean notDone = true;
		while (notDone) {
			// loop transactions until
			// withdrawal, deposit, or transfer
			int choice = us.getUserIntResponse(menu);

			switch (choice) {
			case (1):
				us.outputToUser("You have chosen to make a withdrawal.");
				makeWithdrawal();
				checkUserAccounts(UserService.getLoggedInUser().getId());
				break;
			case (2):
				us.outputToUser("You have chosen to make a deposit.");
				makeDeposit();
				checkUserAccounts(UserService.getLoggedInUser().getId());
				break;
			case (3):
				us.outputToUser("You have chosen to make a transfer.");
				makeTransfer();
				checkUserAccounts(UserService.getLoggedInUser().getId());
				break;
			case (4):
				us.outputToUser("Ending Transactions...");
				notDone = false;
				return notDone;
			default:
				us.outputToUser("That choice is not in the menu. Please, " + "make another selection.");
				break;
			}
		}

		return notDone;
	}

	public boolean determineActiveAccount() {
		int accountId = us.getUserIntResponse("Please enter the account number to transact: ");
		boolean found = false;

		for (AccountDisplay ad : userApprovedAccounts) {
			if (ad.getAccountId() == accountId) {
				activeAccount = ad;
				found = true;
			}
		}

		if (found == false) {
			us.outputToUser("You have not entered a valid account number.");
		}
		return found;
	}

	public void approveAccount(int accountId) {
		aDao.setAccountApproval(accountId, true);
	}

	private void makeWithdrawal() {
		double tmp = us.getUserDoubleResponse("How much would you like to withdraw?\nEnter the value here: ");
		double bal = activeAccount.getBalance();
		if (tmp > 0) {
			// verify if the account has enough money for the withdrawal
			if (bal >= tmp) {
				// make withdrawal
				activeAccount.setBalance(bal - tmp);
				// update balance
				aDao.updateAccountBalance(activeAccount.getAccountId(), activeAccount.getBalance());
			} else {
				us.outputToUser("You do not have enough money. Your balance: " + bal);
			}
		} else {
			us.outputToUser("You must enter a valid number greater than $0.00 to withdraw.");
		}
	}

	private void makeDeposit() {
		double tmp = us.getUserDoubleResponse("How much would you like to deposit?\nEnter the value here: ");
		double bal = activeAccount.getBalance();

		if (tmp > 0) {
			activeAccount.setBalance(bal + tmp);

			aDao.updateAccountBalance(activeAccount.getAccountId(), activeAccount.getBalance());
		} else {
			us.outputToUser("You must enter a valid number greater than $0.00 to deposit.");
		}
	}

	private void makeTransfer() {
		AccountDisplay destinationAccount = null;

		// need more than one active account
		if (userApprovedAccounts.size() > 1) {
			boolean destinationAccountFound = false;
			// get account to transfer to (from account is active account)
			// only one other
			if (userApprovedAccounts.size() < 3) {
				for (AccountDisplay ad : userApprovedAccounts) {
					if (ad.getAccountId() != activeAccount.getAccountId()) {
						destinationAccount = ad;
						destinationAccountFound = true;
						break;
					}
				}
			} else {
				// print out those account numbers, have user select one
				for (AccountDisplay ad : userApprovedAccounts) {
					if (ad.getAccountId() != activeAccount.getAccountId()) {
						us.outputToUser("***********************");
						us.outputToUser("Account Number: " + ad.getAccountId());
						us.outputToUser("Account Name: " + ad.getAccountName());
						us.outputToUser("Account Balance: " + ad.getBalance());
						us.outputToUser("***********************");
					}
				}
				int choice = us.getUserIntResponse("Please select an account to transfer to.\n"
						+ "Enter the account number here and press enter: ");
				
				// validate if the choice is a valid number ... the account number in list
				for (AccountDisplay ad : userApprovedAccounts) {
					if (choice == ad.getAccountId()) {
						destinationAccount = ad;
						destinationAccountFound = true;
						break;
					}
				}
			}
			if (destinationAccountFound) {
				// get amount to transfer
				double tmp = us.getUserDoubleResponse("Please, enter in the amount to transfer: ");
				double bal = activeAccount.getBalance();
				if (tmp > 0) {
					// verify if the account has enough money for the withdrawal
					if (bal >= tmp) {
						// make transfer
						activeAccount.setBalance(bal - tmp);
						destinationAccount.setBalance(destinationAccount.getBalance() + tmp);
						// update balance
						aDao.updateAccountBalance(activeAccount.getAccountId(), activeAccount.getBalance());
						aDao.updateAccountBalance(destinationAccount.getAccountId(), destinationAccount.getBalance());
					} else {
						us.outputToUser("You do not have enough money. Your balance: " + bal);
					}
				} else {
					us.outputToUser("You must enter a valid number greater than $0.00 to transfer.");
				}
			} else {
				us.outputToUser(
						"The destination account number you've entered does not exist, or is not approved yet.");
			}
		} else {
			us.outputToUser("You must have more than one approved account to be able to transfer money.");
		}

	}

}
