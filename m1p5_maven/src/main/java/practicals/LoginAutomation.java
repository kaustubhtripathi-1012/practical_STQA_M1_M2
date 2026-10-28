package practicals;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.*;

public class LoginAutomation {
    // simple assertion helper (no TestNG needed)
    static class Assert {
        static void assertTrue(boolean c, String m) { if (!c) throw new AssertionError(m); }
        static void assertTrue(boolean c) { assertTrue(c, "Assertion failed"); }
    }

    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button[type='submit']")).click();

        // 1. URL validation
        Assert.assertTrue(driver.getCurrentUrl().contains("/secure"), "URL validation failed");
        System.out.println("URL validation PASS : " + driver.getCurrentUrl());
        // 2. Welcome message validation
        String msg = driver.findElement(By.id("flash")).getText();
        Assert.assertTrue(msg.contains("You logged into a secure area!"), "Welcome message mismatch");
        System.out.println("Welcome message PASS: " + msg.trim());
        // 3. Logout visibility check
        WebElement logout = driver.findElement(By.cssSelector("a[href='/logout']"));
        Assert.assertTrue(logout.isDisplayed(), "Logout not visible");
        System.out.println("Logout visible PASS");
        logout.click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
        driver.quit();
    }
}
