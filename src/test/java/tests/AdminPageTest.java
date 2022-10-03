package tests;

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

public class AdminPageTest extends BasePage{

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
        Boolean isPresent = driver.findElements(By.xpath("//*[contains(text(),'"+name.trim()+"')]")).size() > 0;
        logger.log(LogStatus.INFO, "isPresent is set to:- " + isPresent);
        if (isPresent==true) {
            driver.findElement(By.xpath("//*[contains(text(),'" + name.trim() + "')]")).click();
            //wait for message to load
            driver.findElement(By.cssSelector("[data-testid='message']"));
            driver.findElement(By.xpath("//div[contains(@class,'col-10')]"));
            //admin.inboxMessagePopUp();
            Methods.waitForElementToContainText(driver, subject);
            //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(2));
            //wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + subject + "')]")));
            //new WebDriverWait(driver, Duration.ofSeconds(2)).until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='btn btn-outline-primary']")));
            //driver.findElement(By.cssSelector("[class='btn btn-outline-primary']"));
            //Get all the text from the message and compare
            //Name
            String from = driver.findElement(By.xpath("//div[contains(@class,'col-10')]")).getText();
            //remove label from text
            String actualName = from.substring(from.indexOf(": ") + 1);
            //Compare to entered text
            Assert.assertEquals(name.trim(), actualName.trim());
            logger.log(LogStatus.PASS, "Name matches:- " + name + " = " + actualName);
            //Phone
            String phoneNo = driver.findElement(By.xpath("//div[contains(@class,'col-2')]")).getText();
            //remove label from text
            String actualPhoneNo = phoneNo.substring(phoneNo.indexOf(": ") + 1);
            //Compare to entered text
            Assert.assertEquals(phone.trim(), actualPhoneNo.trim());
            logger.log(LogStatus.PASS, "Phone matches:- " + phone + " = " + actualPhoneNo);
            //email
            String emailAdd = driver.findElement(By.xpath("//div[contains(@class,'col-12')]")).getText();
            //remove label from text
            String actualEmailAdd = emailAdd.substring(emailAdd.indexOf(": ") + 1);
            //Compare to entered text
            Assert.assertEquals(email.trim(), actualEmailAdd.trim());
            logger.log(LogStatus.PASS, "Email matches:- " + email + " = " + actualEmailAdd);
            //subject
            var messageDetails = driver.findElement(By.cssSelector("[data-testid='message']")).getText();
            System.out.println("Displayed details:- " + messageDetails);
            if (messageDetails.contains(subject)) {
                logger.log(LogStatus.PASS, "Message contains " + subject);
                //message
                if (messageDetails.contains(message)) {
                    driver.findElement(By.cssSelector("[class='btn btn-outline-primary']")).click();
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
            Assert.assertFalse(isPresent);
            logger.log(LogStatus.FAIL, "No messages found");
        }

    }
}

