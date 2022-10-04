package util;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.ThreadLocalRandom;

public class Methods {

    //Select random dropdown value
    public static String clickRandomListValue(WebElement value) {
        List<WebElement> itemsInDropdown = value.findElements(By.cssSelector("option[value]"));
        //Click random type
        int size = itemsInDropdown.size();
        System.out.println("Number of Items in dropdown is " + size);
        int randomNumber = ThreadLocalRandom.current().nextInt(0,size);
        System.out.println("Random number from Number of items is " + randomNumber);
        System.out.println("Corresponding text in dropdown for random value is " + itemsInDropdown.get(randomNumber).getText());
        itemsInDropdown.get(randomNumber).click();
        String listValue = itemsInDropdown.get(randomNumber).getText();
        return listValue;
    }

    public static void waitForElementToContainText (WebDriver element, String text) {
        System.out.println("Element is:- " + element);
        System.out.println("String is:- " + text);
        WebDriverWait wait = new WebDriverWait(element, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[contains(text(), '" + text + "')]")));
    }

    public static String trimStringBefore(String before, String text) {
        String trimString = text.substring(text.indexOf(before) + 1);
        return trimString;
    }

    public static List<String> selectRandomCheckboxes(List element) {
        List<WebElement> a = element;
        Random rand = new Random(); //instance of random class
        int int_random = rand.nextInt(a.size());
        if (int_random == 0) {
            int_random = int_random+1;
        }
        System.out.println("Random value for detail selection loop is " + int_random);
        //create arrayList to return selected values
        List<String> value = new ArrayList<>();
        for(int i = 0; i < a.size(); i += int_random) {
            a.get(i).click();
            String selectedValue = a.get(i).getAttribute("value");
            //add value to arrayList
            value.add(selectedValue);
            System.out.println("Selected option " + a.get(i).getAttribute("value"));
        }
        System.out.println("ArrayList:-  " + value);
        return value;
    }

    public static boolean stringContains(String fullText, String findText) {
        boolean contains = fullText.contains(findText);
        return contains;
    }

}
