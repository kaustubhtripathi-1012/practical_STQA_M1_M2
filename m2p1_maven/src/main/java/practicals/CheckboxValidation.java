package practicals;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.*;

public class CheckboxValidation {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");
        List<WebElement> boxes = driver.findElements(By.cssSelector("input[type='checkbox']"));
        int checked = 0, unchecked = 0;
        for (WebElement b : boxes) { if (b.isSelected()) checked++; else unchecked++; }
        System.out.println("Total=" + boxes.size() + "  Checked=" + checked + "  Unchecked=" + unchecked);
        // Dynamic selection: tick every unchecked box and validate
        for (WebElement b : boxes) if (!b.isSelected()) b.click();
        int nowChecked = 0;
        for (WebElement b : driver.findElements(By.cssSelector("input[type='checkbox']"))) if (b.isSelected()) nowChecked++;
        System.out.println("After selecting all -> checked = " + nowChecked + " : " + (nowChecked == boxes.size() ? "PASS" : "FAIL"));
        // Untick all
        for (WebElement b : boxes) if (b.isSelected()) b.click();
        driver.quit();
    }
}
