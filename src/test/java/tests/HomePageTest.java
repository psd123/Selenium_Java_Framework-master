package tests;

import org.openqa.selenium.By;
import org.testng.Assert;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import pages.BasePage;
import pages.HomePage;

public class HomePageTest extends BasePage{

	@Test(priority=0)
	public void verifyTitle() {
		HomePage homePage = new HomePage(driver);
		logger.log(LogStatus.INFO, "Click 'Let Me Hack' button if exists");
		homePage.clickLetMeHack();
		logger.log(LogStatus.INFO, "Checking title matches string");
		Assert.assertEquals(homePage.getTitle(), "Restful-booker-platform demo");
		logger.log(LogStatus.PASS, "Title matches with title from DOM");
	}

	@Test(priority=1)
	public void verifyLogo() {
		HomePage homePage = new HomePage(driver);
		logger.log(LogStatus.INFO, "Click 'Let Me Hack' button if exists");
		homePage.clickLetMeHack();
		logger.log(LogStatus.INFO, "Checking if logo is present");
		Assert.assertTrue(homePage.getLogo());
		logger.log(LogStatus.PASS, "Logo is present");
	}

	@Test(priority=2)
	public void bookRoom() {
		HomePage homePage = new HomePage(driver);
		logger.log(LogStatus.INFO, "Click 'Let Me Hack' button if exists");
		homePage.clickLetMeHack();
		logger.log(LogStatus.INFO, "Click the 'Book Room' button");
		homePage.clickBookRoomLink();
		logger.log(LogStatus.INFO, "Populate customer details");
		homePage.fillBookRoomDetails();
		logger.log(LogStatus.INFO, "Submit room booking");
		homePage.clickBookRoomBookButton();
		Assert.assertTrue(homePage.verifyBookRoomSuccess());
		logger.log(LogStatus.PASS, "Booking successful");
		homePage.closeBookRoomSuccessPopUp();
	}

	@Test(priority=3)
	public void contactUs() {
		HomePage homePage = new HomePage(driver);
		logger.log(LogStatus.INFO, "Click 'Let Me Hack' button if exists");
		homePage.clickLetMeHack();
		logger.log(LogStatus.INFO, "Populate contact us form");
		homePage.fillContactUs();
		logger.log(LogStatus.INFO, "Click 'Submit' button");
		homePage.submitContactUs();
		Assert.assertTrue(homePage.verifyContactUsSubmitted());
		logger.log(LogStatus.PASS, "Contact us submitted successfully");
	}

}
