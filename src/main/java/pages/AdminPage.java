package pages;

import org.openqa.selenium.By;

public class AdminPage {

    By roomsNav = By.xpath("//*[contains(text(),'Rooms')]");//By.cssSelector("a[href='#/admin/']")
    By reportNav = By.id("reportlink");
    By brandingNav = By.id("brandinglink");
    By inboxNav = By.cssSelector("i[class='fa fa-inbox'");
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




}
