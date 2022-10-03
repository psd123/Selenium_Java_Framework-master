package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Methods {

    //Select random dropdown value
    public static void clickRandomListValue(WebElement value) {
        List<WebElement> itemsInDropdown = value.findElements(By.cssSelector("option[value]"));
        //Click random type
        int size = itemsInDropdown.size();
        System.out.println("Number of Items in dropdown is " + size);
        int randomNumber = ThreadLocalRandom.current().nextInt(0,size);
        System.out.println("Random number from Number of items is " + randomNumber);
        System.out.println("Corresponding text in dropdown for random value is " + itemsInDropdown.get(randomNumber).getText());
        itemsInDropdown.get(randomNumber).click();
    }

    public static void waitForElementToContainText (WebDriver element, String text) {
        System.out.println("Element is:- " + element);
        System.out.println("String is:- " + text);
        WebDriverWait wait = new WebDriverWait(element, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + text + "')]")));
    }

}
