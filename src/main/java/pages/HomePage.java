package pages;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import com.github.javafaker.Faker;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import util.CustomerClass;

public class HomePage {
	//add faker for methods
	static Faker faker = new Faker();

	By letMeHack = By.cssSelector("button[class='btn btn-primary'");
	By farmHouseLogo = By.cssSelector("[class='hotel-logoUrl']");
	By signInLink = By.xpath("//a[contains(text(), 'Admin panel')]");
	//Book room section
	By bookRoomButton = By.cssSelector("[class='btn btn-outline-primary float-right openBooking']");
	By bookRoomFirstName = By.cssSelector("[name='firstname']");
	By bookRoomLastName = By.cssSelector("[name='lastname']");
	By bookRoomEmail = By.cssSelector("[name='email']");
	By bookRoomPhone = By.cssSelector("[name='phone']");
	By bookRoomBookButton = By.cssSelector("[class='btn btn-outline-primary float-right book-room']");
	By bookRoomCancelButton = By.cssSelector("[class='btn btn-outline-danger float-right book-room']");
	By bookRoomError = By.cssSelector("[class='alert alert-danger']");
	By bookRoomSuccessPopUp = By.cssSelector("[class='ReactModal__Content ReactModal__Content--after-open confirmation-modal']");
	By bookRoomSuccessCloseButton = By.cssSelector("[class='btn btn-outline-primary']");
	//Contact Us section
	By contactUsSection = By.cssSelector("[class='row contact']");
	By contactUsName = By.id("name");
	By contactUsEmail = By.id("email");
	By contactUsPhone = By.id("phone");
	By contactUsSubject = By.id("subject");
	By contactUsMessage = By.id("description");
	By contactUsSubmit = By.id("submitContact");

	WebDriver driver;
	WebDriverWait wait;

	public HomePage(WebDriver driver) {
		this.driver=driver;
		wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	}

	public void clickLetMeHack() {
		Boolean isPresent = driver.findElements(letMeHack).size() > 0;
		if (isPresent) {
			driver.findElement(letMeHack).click();
		}
	}

	public String getTitle() {
		return driver.getTitle();
	}

	public boolean getLogo() {
		 return driver.findElement(farmHouseLogo).isDisplayed();
	}

	public void clickLogInLink() {
		 driver.findElement(signInLink).click();
	}

	public void clickBookRoomLink() {
		driver.findElement(bookRoomButton).click();
	}

	public void fillBookRoomDetails() {
		//Select today's date plus tomorrow with a left mouse click drag
		WebElement startDate = driver.findElement(By.cssSelector("[class='rbc-date-cell rbc-now rbc-current']"));
		//Drag and release to tomorrow
		Actions action = new Actions(driver);
		action.clickAndHold(startDate).pause(Duration.ofSeconds(2))
				.moveByOffset(0,10)
				.moveByOffset(50, 0)
				.release().build().perform();
		//Create and enter Customer details
		String[] customerDetails = CustomerClass.Customer();
		String firstName = customerDetails[0];
		String lastName = customerDetails[1];
		String email = customerDetails[2];
		String phone = customerDetails[3];

		driver.findElement(bookRoomFirstName).sendKeys(firstName);
		driver.findElement(bookRoomLastName).sendKeys(lastName);
		driver.findElement(bookRoomEmail).sendKeys(email);
		driver.findElement(bookRoomPhone).sendKeys(phone);
	}

	public void clickBookRoomBookButton() {
		driver.findElement(bookRoomBookButton).click();
	}

	public boolean verifyBookRoomSuccess() {
		wait.until(ExpectedConditions.elementToBeClickable(bookRoomSuccessCloseButton));
		return driver.findElement(bookRoomSuccessPopUp).getText().contains("Booking Successful!");
	}

	public void closeBookRoomSuccessPopUp() {
		driver.findElement(bookRoomSuccessCloseButton).click();
	}

	public void fillContactUs() {
		//Create customer details
		String[] customerDetails = CustomerClass.Customer();
		String name = customerDetails[0] + " " + customerDetails[1];
		String email = customerDetails[2];
		String phone = customerDetails[3];
		String subject = faker.lorem().sentence(2);
		String message = faker.lorem().paragraph(4);

		driver.findElement(contactUsName).sendKeys(name);
		driver.findElement(contactUsEmail).sendKeys(email);
		driver.findElement(contactUsPhone).sendKeys(phone);
		driver.findElement(contactUsSubject).sendKeys(subject);
		driver.findElement(contactUsMessage).sendKeys(message);
	}

	public void submitContactUs() {
		driver.findElement(contactUsSubmit).click();
	}

	public boolean verifyContactUsSubmitted() {
		By byXpath = By.xpath("//*[contains(text(),'Thanks for getting in touch')]");
		wait.until(ExpectedConditions.presenceOfElementLocated(byXpath));
		return driver.findElement(contactUsSection).getText().contains("Thanks for getting in touch");
	}
}
