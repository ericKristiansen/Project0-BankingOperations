package com.revature.models;


public class Account {

	private int accountId;
	private String accountName;
	private double balance;
	private boolean isApproved;

	public Account() {
		super();
	}

	public Account(int accountId, int userId, double balance, boolean isApproved) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.isApproved = isApproved;
	}

	public int getAccountId() {
		return accountId;
	}

	public void setAccountId(int accountId) {
		this.accountId = accountId;
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

	public String getAccountName() {
		return accountName;
	}

	public void setAccountName(String accountName) {
		this.accountName = accountName;
	}

	@Override
	public String toString() {
		return "Account [accountId=" + accountId + ", accountName=" + accountName + ", balance=" + balance
				+ ", isApproved=" + isApproved + "]";
	}


}
