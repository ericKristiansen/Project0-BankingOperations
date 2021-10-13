package com.revature.dao.accounts;

import java.util.List;

import com.revature.models.Account;
import com.revature.models.AccountDisplay;

public interface AccountDAO {
	
	// define some CRUD operations here
	public List<AccountDisplay> getAllAccounts();
	public List<AccountDisplay> getUserAccountList(int accountId);
	public void updateAccount(Account account);
	public void deleteAccountById(int accountId);
	public void applyNewAccount(int userId, double balance);
	public void addUserToAccount(int accountId, int userId);
	public void setAccountApproval(int accountId, boolean approved);
	public void updateAccountBalance(int accountId, double bal);
	
}
