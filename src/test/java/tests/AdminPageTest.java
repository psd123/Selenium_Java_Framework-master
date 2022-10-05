package tests;

import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;
import com.relevantcodes.extentreports.LogStatus;
import pages.AdminPage;
import pages.BasePage;
import pages.HomePage;
import pages.LoginPage;
import util.Methods;

import java.time.Duration;
import java.util.List;
import java.util.Random;

public class AdminPageTest extends BasePage{
    //add faker for methods
    static Faker faker = new Faker();

    @Test(priority=0)
    @Parameters({"username", "password"})
    public void verifyContactUsDetails (String username, String password) {
        //Create new contact us entry
        HomePage homePage = new HomePage(driver);
        homePage.clickLetMeHack();
        logger.log(LogStatus.INFO, "Populating the Contact Us section");
        String[] customerDetails = homePage.fillContactUs();
        logger.log(LogStatus.INFO, "Click the Submit button in the Contact Us section");
        homePage.submitContactUs();
        //Extract the customer details entered into the Contact Us section
        String name = customerDetails[0] + " " + customerDetails[1];
        logger.log(LogStatus.INFO, "Entered name was " + name);
        String email = customerDetails[2];
        logger.log(LogStatus.INFO, "Entered email was " + email);
        String phone = customerDetails[3];
        logger.log(LogStatus.INFO, "Entered phone was " + phone);
        String subject = customerDetails[4];
        logger.log(LogStatus.INFO, "Entered subject was " + subject);
        String message = customerDetails[5];
        logger.log(LogStatus.INFO, "Entered message was " + message);
        //Login to admin panel and compare
        logger.log(LogStatus.INFO, "Click the Admin panel link");
        homePage.clickLogInLink();
        //login to admin section
        LoginPage login = new LoginPage(driver);
        login.clickLetMeHack();
        logger.log(LogStatus.INFO, "Entering valid username");
        login.typeUsername(username);
        logger.log(LogStatus.INFO, "Entering valid password");
        login.typePassword(password);
        logger.log(LogStatus.INFO, "Clicking Sign in button");
        login.clickSignIn();
        logger.log(LogStatus.PASS, "Admin page loaded");
        Assert.assertTrue(login.verifyNewPage());
        //Admin page
        AdminPage admin = new AdminPage(driver);
        //click inbox
        logger.log(LogStatus.INFO, "Click the Inbox icon");
        admin.clickInbox();
        //Check the contents of the message with the name entered in the Contact Us section
        Boolean isPresent = driver.findElements(By.xpath("//*[contains(text(),'"+name.trim().replace("'","\\'")+"')]")).size() > 0;
        logger.log(LogStatus.INFO, "isPresent is set to:- " + isPresent);
        if (isPresent==true) {
            //Click the message from the entered name in the Contact Us section
            admin.clickInboxMessageByName(name);
            //wait for message to load
            admin.inboxMessagePopUp();
            Methods.waitForElementToContainText(driver, subject);
            //Get all the text from the message and compare
            //Name
            Methods.waitForElementToContainText(driver, name);
            String from = admin.getInboxSelectedMessageName();
            //remove label from text
            String actualName = Methods.trimStringBefore(": ", from);
            //Compare to entered text
            //Deal with name with an apostrophe by escaping
            Assert.assertEquals(name.trim().replace("'","\\'"), actualName.trim());
            logger.log(LogStatus.PASS, "Name matches:- " + name + " = " + actualName);
            //Phone
            String phoneNo = admin.getInboxSelectedMessagePhone();
            //remove label from text
            String actualPhoneNo = Methods.trimStringBefore(": ", phoneNo);
            //Compare to entered text
            Assert.assertEquals(phone.trim(), actualPhoneNo.trim());
            logger.log(LogStatus.PASS, "Phone matches:- " + phone + " = " + actualPhoneNo);
            //email
            String emailAdd = admin.getInboxSelectedMessageEmail();
            //remove label from text
            String actualEmailAdd = Methods.trimStringBefore(": ", emailAdd);
            //Compare to entered text
            Assert.assertEquals(email.trim(), actualEmailAdd.trim());
            logger.log(LogStatus.PASS, "Email matches:- " + email + " = " + actualEmailAdd);
            //subject
            var messageDetails = admin.getInboxMessageText();
            System.out.println("Displayed details:- " + messageDetails);
            if (messageDetails.contains(subject)) {
                logger.log(LogStatus.PASS, "Message contains " + subject);
                //message
                if (messageDetails.contains(message)) {
                    logger.log(LogStatus.PASS, "Message contains " + messageDetails);
                } else {
                    System.out.println(message + " not found in the email:- "+ messageDetails);
                    boolean messageMatch = false;
                    Assert.assertTrue(messageMatch);
                    logger.log(LogStatus.FAIL, "Message not found:- " + message);
                }
            } else {
                System.out.println(subject + " not found in the email:- " + messageDetails);
                boolean subjectMatch = false;
                Assert.assertTrue(subjectMatch);
                logger.log(LogStatus.FAIL, "Subject not found");
            }
        } else {
            Assert.assertTrue(isPresent);
            logger.log(LogStatus.FAIL, "No messages found");
        }
        //Close the inbox pop up message
        admin.inboxCloseMessagePopUp();
    }

    @Test(priority=1)
    public void addNewRoomAndValidate() {
        AdminPage admin = new AdminPage(driver);
        //select the create room link
        admin.clickRoomsLink();
        //add room number
        int roomNumber = faker.number().numberBetween(102, 999);//faker.number().digits(3);
        String roomNumberString = String.valueOf(roomNumber);
        System.out.println("Room number is set as " + roomNumberString);
        admin.addRoomNumber(String.valueOf(roomNumber));
        logger.log(LogStatus.INFO, "Entered room number is:- " + roomNumberString);
        //room type
        WebElement drpRoom = admin.addRoomTypeDropdown();
        String roomType = Methods.clickRandomListValue(drpRoom);
        logger.log(LogStatus.INFO, "Selected room type is:- " + roomType);
        //select accessible
        WebElement drpAccessible = admin.addAccessibilityDropdown();
        String roomAccessible = Methods.clickRandomListValue(drpAccessible);
        logger.log(LogStatus.INFO, "Selected accessible type is:- " + roomAccessible);
        //add room price, did not like using a decimal value even when set as a string
        //double price = faker.number().randomDouble(2, 50, 300);
        String roomPrice = faker.number().digits(3);
        System.out.println("Room price is set as " + roomPrice);
        //enter the room price but remove any preceding zeros
        admin.addRoomPrice(roomPrice);
        logger.log(LogStatus.INFO, "Entered room price is:- " + roomPrice);
        //select room details from radio list
        List<String> checkboxes = Methods.selectRandomCheckboxes(admin.addRoomListCheckboxes());
        logger.log(LogStatus.INFO, "Entered room options are:- " + checkboxes);
        //click the create button
        admin.clickRoomCreateButton();
        //wait for new rooms to load
        Methods.waitForElementToContainText(driver, roomNumberString);
        //Click the added room number
        admin.roomSelectByNumber(String.valueOf(roomNumber));
        //Get room details
        Methods.waitForElementToContainText(driver, "Description");
        String roomDetails = admin.roomDetailsText();
        System.out.println(roomDetails);
        //Room number - remove any preceding zeros from the value entered
        Assert.assertTrue(Methods.stringContains(roomDetails, roomNumberString));
        logger.log(LogStatus.PASS, "Room number matches " + roomNumberString);
        //Room type
        Assert.assertTrue(Methods.stringContains(roomDetails, roomType));
        logger.log(LogStatus.PASS, "Room type matches " + roomType);
        //Room Accessible
        Assert.assertTrue(Methods.stringContains(roomDetails, roomAccessible));
        logger.log(LogStatus.PASS, "Room accessibility type matches " + roomAccessible);
        //Room price - remove any preceding zeros from the value entered
        Assert.assertTrue(Methods.stringContains(roomDetails, roomPrice.replaceAll("^0+(?=.)", "")));
        logger.log(LogStatus.PASS, "Room price matches " + roomPrice);
        //Room option checkboxes
        String listString = String.join(", ", checkboxes);
        Assert.assertTrue(Methods.stringContains(roomDetails, listString));
        logger.log(LogStatus.PASS, "Room options matches " + checkboxes);
    }
}

