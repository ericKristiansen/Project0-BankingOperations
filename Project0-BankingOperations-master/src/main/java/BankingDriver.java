
import com.revature.services.ServiceWrangler;

public class BankingDriver {

	private static String WELCOME_MESSAGE = "          Welcome to ...\n                     ...\n"
			+ "$$$$    Banking Operations    $$$$\n\n                $.$\n";

	public static void main(String[] args) {

		ServiceWrangler sw = new ServiceWrangler(WELCOME_MESSAGE);

		// Get the user logged in.
		boolean isLoggedIn = sw.executeLogin();

		// This loop will continue to execute until the user logs out.
		while (isLoggedIn) {
			// Update user information
			sw.updateUserInformation();

			// if admin
			if (sw.isAdmin()) {

				boolean adminMenuNotDone = true;
				while (adminMenuNotDone) {
					int choice = sw.adminMenu();

					switch (choice) {
					case (1): 
						sw.modifyAllAccountInformation();
						break;
					case (2):
						sw.modifyUserAccounts();
						break;
					case (3):
						sw.approveDenyCancelApplications();
						break;
					case (4): // Exit the application
						adminMenuNotDone = false;
						isLoggedIn = false;
						break;
					default:
						break;
					}
				}
			} else if (sw.isEmployee()) {
				boolean empMenuNotDone = true;
				while (empMenuNotDone) {
					int choice = sw.employeeMenu();

					switch (choice) {
					case (1): 
						sw.viewAllAccountInformation();
						break;
					case (2):
						sw.viewAllCustomerInformation();
						break;
					case (3):
						sw.approveDenyApplications();
						break;
					case (4): // Exit the application
						empMenuNotDone = false;
						isLoggedIn = false;
						break;
					default:
						break;
					}
				}
			} else // The user is a general customer
					// check to see if any user accounts have been approved
			{
				sw.checkUserAccounts();

				// apply for new account ************************
				sw.applyForNewAccount();

				// Move on to transactions
				// if user has an account that is approved, they may continue
				if (sw.isUserAccountApproved()) {
					boolean notDone = sw.determineActiveAccount();

					while (notDone) {
						notDone = sw.transactActiveAccount();
					}
				}
			}

			isLoggedIn = sw.getUserWishesToContinue();
			if (!isLoggedIn) {
				sw.GoodbyeMessage();
			}
		}

		return;
	}

}
