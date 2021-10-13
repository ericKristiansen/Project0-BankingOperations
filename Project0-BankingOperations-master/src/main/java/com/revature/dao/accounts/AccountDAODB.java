package com.revature.dao.accounts;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.revature.models.Account;
import com.revature.models.AccountDisplay;
import com.revature.utils.ConnectionUtil;

public class AccountDAODB implements AccountDAO{
	
	ConnectionUtil conUtil = ConnectionUtil.getConnectionUtil();

	@Override
	public List<AccountDisplay> getAllAccounts() {
		
		List<AccountDisplay> accountList = new ArrayList<AccountDisplay>();

		try {
			Connection con = conUtil.getConnection();

			String sql = "SELECT * from GET_ACCOUNTS();";

			// we need to create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			// loop through the result set, and create objects based off the return
			// (id, first_name, last_name, user_name, passwprd, email, isEmployee, isAdmin)
			while (rs.next()) {
				accountList.add(populateAccountDisplay(rs));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return accountList;
	}
	
	//String fullName, String username, int userId, int accountId, String accountName,
	// double balance, boolean isApproved
	private AccountDisplay populateAccountDisplay(ResultSet rs)
	{
		AccountDisplay tempAccountDisplay = null;
		 try {
			 tempAccountDisplay =  new AccountDisplay(rs.getString(1), rs.getString(2), 
						rs.getInt(3), rs.getInt(4), rs.getString(5), rs.getDouble(6), 
						rs.getBoolean(7));
			 
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tempAccountDisplay;
	}
	
	@Override
	public List<AccountDisplay> getUserAccountList(int userId) {

		List<AccountDisplay> accountList = new ArrayList<AccountDisplay>();

		try {
			Connection con = conUtil.getConnection();

			String sql = "SELECT * from GET_ACCOUNTS_BY_USER_ID(" + userId + ");";

			// we need to create a statement with the sql string
			Statement s = con.createStatement();
			ResultSet rs = s.executeQuery(sql);

			// loop through the result set, and create objects based off the return
			// (id, first_name, last_name, user_name, passwprd, email, isEmployee, isAdmin)
			while (rs.next()) {
				accountList.add(populateAccountDisplay(rs));
			}

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return accountList;
	}

	@Override
	public void updateAccount(Account account) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteAccountById(int accountId) {
		try {
			Connection con = conUtil.getConnection();

			String sql = "CALL DELETE_ACCOUNT(" + accountId + ");";

			// we need to create a statement with the sql string
			Statement s = con.createStatement();
			s.execute(sql);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public void applyNewAccount(int userId, double balance) {

		try {
			Connection con = conUtil.getConnection();

			String sql = "SELECT APPLY_FOR_ACCOUNT(" + userId + "," + balance + ");";

			// we need to create a statement with the sql string
			Statement s = con.createStatement();
			s.execute(sql);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}

	@Override
	public void addUserToAccount(int accountId, int userId) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void setAccountApproval(int accountId, boolean approved) {
		try {
			Connection con = conUtil.getConnection();

			String sql = "CALL SET_ACCOUNT_APPROVED(" + accountId + ", " + approved + ");";

			// we need to create a statement with the sql string
			Statement s = con.createStatement();
			s.execute(sql);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	
	// update_account_balance(a_id int, bal numeric)
	@Override
	public void updateAccountBalance(int accountId, double bal) {
		try {
			Connection con = conUtil.getConnection();

			String sql = "CALL UPDATE_ACCOUNT_BALANCE(" + accountId + ", " + bal + ");";

			// we need to create a statement with the sql string
			Statement s = con.createStatement();
			s.execute(sql);

		} catch (Exception ex) {
			ex.printStackTrace();
		}
		
	}
	
	

}
