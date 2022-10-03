package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.concurrent.ThreadLocalRandom;

public class Methods {

    //Select random dropdown value
    public static void randomListValue(WebElement value) {
        List<WebElement> itemsInDropdown = value.findElements(By.cssSelector("option[value]"));
        //Click random type
        int size = itemsInDropdown.size();
        System.out.println("Number of Items in dropdown is " + size);
        int randomNumber = ThreadLocalRandom.current().nextInt(0,size);
        System.out.println("Random number from Number of items is " + randomNumber);
        System.out.println("Corresponding text in dropdown for random value is " + itemsInDropdown.get(randomNumber).getText());
        itemsInDropdown.get(randomNumber).click();
    }
}
