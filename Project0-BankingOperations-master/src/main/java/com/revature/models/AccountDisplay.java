package com.revature.models;

public class AccountDisplay {

	// f_name, u_name, uid, aid, aname, c_balance, approved

	private String fullName;
	private String username;
	private int userId;
	private int accountId;
	private String accountName;
	private double balance;
	private boolean isApproved;

	public AccountDisplay() {
		super();
	}

	public AccountDisplay(String fullName, String username, int userId, int accountId, String accountName,
			double balance, boolean isApproved) {
		super();
		this.fullName = fullName;
		this.username = username;
		this.userId = userId;
		this.accountId = accountId;
		this.accountName = accountName;
		this.balance = balance;
		this.isApproved = isApproved;
	}

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
	}

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public boolean isApproved() {
		return isApproved;
	}

	public void setApproved(boolean isApproved) {
		this.isApproved = isApproved;
	}

	@Override
	public String toString() {
		return "AccountDisplay [fullName=" + fullName + ", username=" + username + ", userId=" + userId + ", accountId="
				+ accountId + ", accountName=" + accountName + ", balance=" + balance + ", isApproved=" + isApproved
				+ "]";
	}

	
	
}
