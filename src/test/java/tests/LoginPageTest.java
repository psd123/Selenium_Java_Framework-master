package tests;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import pages.LoginPage;
import pages.BasePage;
import pages.HomePage;

public class LoginPageTest extends BasePage{
	
	@Test(priority=0)
	public void navigateToLoginPage() {
		HomePage homePage = new HomePage(driver);
		logger.log(LogStatus.INFO, "Clicking 'Admin panel' link");
		homePage.clickLogInLink();
	}
	
	//* Test invalid login attempts using data from DataProvider
	@DataProvider(name="accounts")
	public Object[][] getData(){
		return new Object[][] {
			{"InvalidEmail@gmail.com", "testing123"}, // Invalid email, correct password
		};
	}

	@Test(priority=1, dataProvider="accounts")
	public void verifyInvalidLoginCredentials(String testUsername, String testPassword) {
		LoginPage login = new LoginPage(driver);
		logger.log(LogStatus.INFO, "Click 'Let Me Hack' button if exists");
		login.clickLetMeHack();
		logger.log(LogStatus.INFO, "Entering invalid username");
		login.typeUsername(testUsername);
		logger.log(LogStatus.INFO, "Entering invalid password");
		login.typePassword(testPassword);
		logger.log(LogStatus.INFO, "Clicking Sign in button");
		login.clickSignIn();
		logger.log(LogStatus.PASS, "Log in with invalid credentials failed");
		Assert.assertTrue(login.errorContainerDisplayed());
	}

	@Test(priority=2)
	@Parameters({"username", "password"})
	public void verifyLogin(String username, String password) { 
		LoginPage login = new LoginPage(driver);
		logger.log(LogStatus.INFO, "Click 'Let Me Hack' button if exists");
		login.clickLetMeHack();
		logger.log(LogStatus.INFO, "Entering valid username");
		login.typeUsername(username);
		logger.log(LogStatus.INFO, "Entering valid password");
		login.typePassword(password);
		logger.log(LogStatus.INFO, "Clicking Sign in button");
		login.clickSignIn();
		logger.log(LogStatus.PASS, "Admin page loaded");
		Assert.assertTrue(login.verifyNewPage());
	}

//	@Test(priority=3)
//	@Parameters({"username", "password"})
//	public void verifyContactUsDetails(String username, String password) {
//		LoginPage login = new LoginPage(driver);
//		logger.log(LogStatus.INFO, "Click 'Let Me Hack' button if exists");
//		login.clickLetMeHack();
//		logger.log(LogStatus.INFO, "Entering valid username");
//		login.typeUsername(username);
//		logger.log(LogStatus.INFO, "Entering valid password");
//		login.typePassword(password);
//		logger.log(LogStatus.INFO, "Clicking Sign in button");
//		login.clickSignIn();
//
//	}
}	
