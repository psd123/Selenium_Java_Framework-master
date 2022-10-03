package pages;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class AdminPage {

    By roomsNav = By.xpath("//*[contains(text(),'Rooms')]");//By.cssSelector("a[href='#/admin/']")
    By reportNav = By.id("reportlink");
    By brandingNav = By.id("brandinglink");
    By inboxNav = By.cssSelector("[class='fa fa-inbox']");
    By frontPageNav = By.id("frontPageLink");
    By logoutNav = By.xpath("//*[contains(text(),'Logout')]");
    //Add room section
    By roomNumber = By.id("roomName");
    By roomType = By.id("type");
    By roomAccessible = By.id("accessible");
    By roomPrice = By.id("roomPrice");
    By roomWifi = By.id("wifiCheckbox");
    By roomTV = By.id("tvCheckbox");
    By roomRadio = By.id("radioCheckbox");
    By roomRefreshment = By.id("refreshmentCheckbox");
    By roomSafe = By.id("safeCheckbox");
    By roomViews = By.id("viewsCheckbox");
    By roomCreateButton = By.id("createRoom");
    //Check inbox section
    By inboxMessages = By.cssSelector("[id^=message]");//Multiple messages could be present so needs to be a list\array
    By inboxSelectedMessage = By.cssSelector("[data-testid='message']");


    WebDriver driver;
    WebDriverWait wait;

    public AdminPage(WebDriver driver) { // create a constructor and pass the driver instance
        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void clickInbox() {
        System.out.println("Wait until the inbox icon has loaded");
        wait.until(ExpectedConditions.elementToBeClickable(driver.findElement(inboxNav)));
        driver.findElement(inboxNav).click();
        //wait for messages to load
        wait.until(ExpectedConditions.elementToBeClickable((By.cssSelector("[id^=message]"))));
    }

    public void clickInboxMessageByName(String name) {
        driver.findElement(By.xpath("//*[contains(text(),'" + name.trim() + "')]")).click();
    }

    public void inboxMessagePopUp() {
        driver.findElement(inboxSelectedMessage);
    }


}
