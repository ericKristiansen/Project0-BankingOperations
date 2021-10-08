package com.revature.services.test;

import static org.junit.Assert.*;
import org.junit.Test;
import com.revature.services.HashingService;

public class HashServiceTest {
	
	@Test
	public void testHashService()
	{
		String test = "test";
		String testing = "testing";
		String testing123 = "testing123";
		
		assertEquals(HashingService.getHash(test), String.valueOf(test.hashCode()));
		assertEquals(HashingService.getHash(testing), String.valueOf(testing.hashCode()));
		assertEquals(HashingService.getHash(testing123), String.valueOf(testing123.hashCode()));
		assertNotEquals(HashingService.getHash(test), String.valueOf(testing.hashCode()));
	}
	
	@Test(expected = NullPointerException.class)
	public void testNull()
	{
		assertEquals(HashingService.getHash(null), null);
	}
	

}
