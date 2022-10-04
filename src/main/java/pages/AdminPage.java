package pages;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;

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
    By roomCheckboxes = By.cssSelector("[type='checkbox']");
    By roomDetails = By.cssSelector("[class='room-details']");
    By roomEditDetailButton = By.cssSelector("button[class='btn btn-outline-primary float-right']");


    //Check inbox section
    By inboxMessages = By.cssSelector("[id^=message]");//Multiple messages could be present so needs to be a list\array
    By inboxSelectedMessage = By.cssSelector("[data-testid='message']");
    By inboxCloseMessagePopUp = By.cssSelector("[class='btn btn-outline-primary']");
    By inboxSelectedMessageName = By.xpath("//div[contains(@class,'col-10')]");
    By inboxSelectedMessagePhone = By.xpath("//div[contains(@class,'col-2')]");
    By inboxSelectedMessageEmail = By.xpath("//div[contains(@class,'col-12')]");


    WebDriver driver;
    WebDriverWait wait;

    public AdminPage(WebDriver driver) { // create a constructor and pass the driver instance
        this.driver=driver;
        wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }
    //inbox
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

    public String getInboxMessageText() {
        String messageDetails = driver.findElement(inboxSelectedMessage).getText();
        return messageDetails;
    }

    public String getInboxSelectedMessageName() {
        String name = driver.findElement(inboxSelectedMessageName).getText();
        return name;
    }

    public String getInboxSelectedMessagePhone() {
        String phone = driver.findElement(inboxSelectedMessagePhone).getText();
        return phone;
    }

    public String getInboxSelectedMessageEmail() {
        String email = driver.findElement(inboxSelectedMessageEmail).getText();
        return email;
    }

    public void inboxCloseMessagePopUp() {
        driver.findElement(inboxCloseMessagePopUp).click();
    }
    //Rooms
    public void clickRoomsLink() {
        wait.until(ExpectedConditions.elementToBeClickable((By.cssSelector("a[class='nav-link']")))).click();
    }

    public void addRoomNumber(String Number) {
        driver.findElement(roomNumber).sendKeys(Number);
    }

    public WebElement addRoomTypeDropdown() {
        WebElement type = driver.findElement(roomType);
        return type;
    }

    public WebElement addAccessibilityDropdown() {
        WebElement accessible = driver.findElement(roomAccessible);
        return accessible;
    }

    public void addRoomPrice(String Number) {
        driver.findElement(roomPrice).sendKeys(Number);
    }

    public List addRoomListCheckboxes() {
        List<WebElement> a =  driver.findElements(roomCheckboxes);
        return a;
    }

    public void roomSelectByNumber(String number) {
        driver.findElement(By.id("roomName" + number)).click();
    }

    public String roomDetailsText() {
        String roomDescription = driver.findElement(roomDetails).getText();
        return roomDescription;
    }

    public void clickRoomCreateButton() {
        driver.findElement(roomCreateButton).click();
    }
}
