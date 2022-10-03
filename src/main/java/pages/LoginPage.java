package pages;

import com.relevantcodes.extentreports.LogStatus;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class LoginPage {

	By usernameField = By.id("username");
	By passwordField = By.id("password");
	By signInButton = By.id("doLogin");
	By letMeHack = By.cssSelector("button[class='btn btn-primary'");
	By errorContainer = By.cssSelector("[style='border: 1px solid red;']");
	
	WebDriver driver;
	WebDriverWait wait;
	
	public LoginPage(WebDriver driver) { // create a constructor and pass the driver instance
		this.driver=driver; 
		wait = new WebDriverWait(driver, Duration.ofSeconds(10));
	}

	public void clickLetMeHack() {
		Boolean isPresent = driver.findElements(letMeHack).size() > 0;
		if (isPresent) {
			driver.findElement(letMeHack).click();
		}
	}

	public void typeUsername(String username) { //pass a parameter so we don't hardcode values in the object class.
		driver.findElement(usernameField).clear();
		driver.findElement(usernameField).sendKeys(username);
	}
	
	public void typePassword(String password) {
		driver.findElement(passwordField).clear();
		driver.findElement(passwordField).sendKeys(password);
	}
	
	public void clickSignIn() {
		driver.findElement(signInButton).click();
	}
	
	public boolean errorContainerDisplayed() {
		return wait.until(ExpectedConditions.visibilityOfElementLocated(errorContainer)).isDisplayed();
	}
	
	public boolean verifyNewPage() {
		wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector("[class='nav-link']")));
		//urlToBe("https://automationintesting.online/#/admin"));
		return driver.findElement(By.cssSelector("[class='navbar navbar-expand-md navbar-dark bg-dark']")).getText().contains("Logout");
	}

}
