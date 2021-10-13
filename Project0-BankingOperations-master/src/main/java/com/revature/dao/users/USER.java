package com.revature.dao.users;

public enum USER {
	// (id, first_name, last_name, user_name, passwprd, email, isEmployee, isAdmin)
	
	ID(1), FIRST_NAME(2), LAST_NAME(3), USER_NAME(4), PASSWORD(5), EMAIL(6), IS_EMPLOYEE(7), IS_ADMIN(8);

    private final int value;

    private USER(int value) {
        this.value = value;
    }

    public int value() { return this.value;}
}
