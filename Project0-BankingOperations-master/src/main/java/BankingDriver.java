
import com.revature.dao.UserDAO;
import com.revature.dao.UserDAODB;
import com.revature.services.LoginService;
import com.revature.services.UserService;

public class BankingDriver {
	
	private static String WELCOME_MESSAGE = "          Welcome to ...\n                     ...\n$$$$" +
									 		"Banking Operations    $$$$\n\n                $.$\n";
	
	private static UserDAO uDao = new UserDAODB();

	public static void main(String[] args) {
		
		UserService us = new UserService(WELCOME_MESSAGE, uDao);
		LoginService ls = new LoginService(us);

		// Get the user logged in. 
		boolean isLoggedIn = ls.executeLoginLogic();
		
		while(isLoggedIn)
		{
			// To begin the application, get the username
			System.out.println("Execute more business logic");
			
			// Get User Account(s)
			// Display Options
			
			
			
			isLoggedIn = false;
		}
		
		return;
	}

}
