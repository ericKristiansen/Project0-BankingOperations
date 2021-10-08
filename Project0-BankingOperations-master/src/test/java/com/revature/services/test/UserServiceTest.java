package com.revature.services.test;

import com.revature.services.UserService;

import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import com.revature.dao.UserDAODB;

public class UserServiceTest {
	
	// we want to be able to mock the UserService and UserDAO, so we don't actually hit the db
	// inject mocks because we are going to inject the mocked uDao functionality into user service
	@InjectMocks
	public UserService uServe;
	
	//mock because we are going to mock the functionality of the user dao so we don't hit the database when testing
	@Mock
	public UserDAODB uDao;
	
	@Before
	public void initMocks() {
		MockitoAnnotations.initMocks(this);
		
	}
	
	@Test
	public void testValidLogin() {
		
	}

}
