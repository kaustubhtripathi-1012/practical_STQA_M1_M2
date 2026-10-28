package practicals;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.*;

public class JenkinsSeleniumTest {
    WebDriver driver;
    @BeforeClass public void setUp() {
        ChromeOptions o = new ChromeOptions();
        o.addArguments("--headless=new", "--no-sandbox", "--disable-dev-shm-usage"); // headless for CI
        driver = new ChromeDriver(o);
    }
    @Test public void titleTest() {
        driver.get("https://the-internet.herokuapp.com/");
        Assert.assertEquals(driver.getTitle(), "The Internet");
    }
    @Test public void loginPageTest() {
        driver.get("https://the-internet.herokuapp.com/login");
        Assert.assertTrue(driver.getPageSource().contains("Login Page"));
    }
    @AfterClass public void tearDown() { driver.quit(); }
}
