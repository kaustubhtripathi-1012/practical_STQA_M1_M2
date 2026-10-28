package practicals;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.*;
import org.openqa.selenium.support.ui.*;

public class WebElementInteraction {
    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        // Implicit wait (applies to every findElement)
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.get("https://rahulshettyacademy.com/AutomationPractice/");

        // Radio button
        driver.findElement(By.xpath("//input[@value='radio2']")).click();
        // Checkbox
        WebElement cb = driver.findElement(By.id("checkBoxOption1"));
        if (!cb.isSelected()) cb.click();
        // Dropdown
        new Select(driver.findElement(By.id("dropdown-class-example"))).selectByVisibleText("Option2");
        // Textbox + Alert
        driver.findElement(By.id("name")).sendKeys("Selenium");
        driver.findElement(By.id("alertbtn")).click();
        Alert alert = driver.switchTo().alert();
        System.out.println("Alert text: " + alert.getText());
        alert.accept();
        // Frame
        driver.switchTo().frame("courses-iframe");
        System.out.println("Inside frame, header found: " + driver.findElements(By.tagName("h1")).size());
        driver.switchTo().defaultContent();

        // Explicit wait (separate demo page)
        driver.get("https://the-internet.herokuapp.com/dynamic_loading/1");
        driver.findElement(By.cssSelector("#start button")).click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(15));
        WebElement result = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("finish")));
        System.out.println("Explicit wait result: " + result.getText());
        driver.quit();
    }
}
