package practicals;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.*;

public class ObjectCounter {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://www.saucedemo.com/");   // change to any URL
        int links   = driver.findElements(By.tagName("a")).size();
        int images  = driver.findElements(By.tagName("img")).size();
        int buttons = driver.findElements(By.tagName("button")).size();
        int inputs  = driver.findElements(By.tagName("input")).size();
        System.out.println("Links   : " + links);
        System.out.println("Images  : " + images);
        System.out.println("Buttons : " + buttons);
        System.out.println("Inputs  : " + inputs);
        System.out.println("Total objects: " + (links + images + buttons + inputs));
        // Input element breakup
        Map<String,Integer> types = new TreeMap<>();
        for (WebElement e : driver.findElements(By.tagName("input"))) types.merge(String.valueOf(e.getAttribute("type")), 1, Integer::sum);
        System.out.println("Input types: " + types);
        driver.quit();
    }
}
