package practicals;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.*;
import org.testng.Assert;
import org.testng.annotations.*;

public class TestNGDemo {
    WebDriver driver;

    @BeforeClass(alwaysRun = true)
    public void setUp() { driver = new ChromeDriver(); driver.manage().window().maximize(); }

    @AfterClass(alwaysRun = true)
    public void tearDown() { driver.quit(); }

    @Test(priority = 1, groups = {"smoke", "regression"})
    public void verifyTitle() {
        driver.get("https://the-internet.herokuapp.com/");
        Assert.assertEquals(driver.getTitle(), "The Internet");
    }
    @Test(priority = 2, groups = {"smoke"})
    public void verifyLogin() {
        driver.get("https://the-internet.herokuapp.com/login");
        driver.findElement(By.id("username")).sendKeys("tomsmith");
        driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
        driver.findElement(By.cssSelector("button.radius")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/secure"));
    }
    @Test(priority = 3, groups = {"regression"}, dependsOnMethods = "verifyLogin")
    public void verifyLogout() {
        driver.findElement(By.cssSelector("a[href='/logout']")).click();
        Assert.assertTrue(driver.getCurrentUrl().contains("/login"));
    }
    @Test(priority = 4, groups = {"regression"}, enabled = true)
    public void failingTest() {   // intentional fail to show in report
        driver.get("https://the-internet.herokuapp.com/checkboxes");
        Assert.assertEquals(driver.findElements(By.cssSelector("input[type='checkbox']")).size(), 5);
    }
    @Test(priority = 5, enabled = false)
    public void skippedTest() { }
}
