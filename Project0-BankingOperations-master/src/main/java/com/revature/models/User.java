package com.revature.models;

import java.util.List;

public class User {
	
	/*
	 * -- Create User
	   -- (id, first_name, last_name, user_name, passwprd, email, isEmployee, isAdmin)
	 */
	private int id;
	private String firstName;
	private String lastName;
	private String username;
	private String password;
	private String email;
	private boolean isEmployee;
	private boolean isAdmin;
	private boolean isLoggedIn;
	private List<Account> listAccounts;

	public User() {
	}

	// Create a new user. At this point, the user doesn't have a database id yet.
	public User(String firstName, String lastName, String username, String password, String email) {
		this.id = -1;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.isEmployee = false;
		this.isAdmin = false;
		this.isLoggedIn = false;
	}

	public User(int id, String firstName, String lastName, String username, String password, String email,
			boolean isEmployee, boolean isAdmin) {
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.username = username;
		this.password = password;
		this.isEmployee = isEmployee;
		this.isAdmin = isAdmin;
		this.isLoggedIn = false;
	}

	/*******************************************************
	 * General getters and setters
	 *******************************************************/

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String userName) {
		this.username = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public boolean isEmployee() {
		return isEmployee;
	}

	public void setIsEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setIsAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}

	public List<Account> getListAccounts() {
		return listAccounts;
	}

	public void setListAccounts(List<Account> listAccounts) {
		this.listAccounts = listAccounts;
	}
	
	public void setIsLoggedIn(boolean isLoggedIn)
	{
		this.isLoggedIn = isLoggedIn;
	}
	
	public boolean isLoggedIn()
	{ return this.isLoggedIn; }

	@Override
	public String toString() {
		return "User [id=" + id + ", firstName=" + firstName + ", lastName=" + lastName + ", username=" + username
				+ ", password=" + password + ", email=" + email + ", isEmployee=" + isEmployee + ", isAdmin=" + isAdmin
				+ ", listAccounts=" + listAccounts + "]";
	}

}
