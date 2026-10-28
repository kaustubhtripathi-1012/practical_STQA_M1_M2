package practicals;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.*;
import java.io.File;
import java.text.SimpleDateFormat;
import org.apache.commons.io.FileUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class ScreenshotLogging {
    static Logger log = LogManager.getLogger(ScreenshotLogging.class);

    static void capture(WebDriver driver, String name) throws Exception {
        File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        String ts = new SimpleDateFormat("yyyyMMdd_HHmmss").format(new Date());
        File dest = new File("screenshots/" + name + "_" + ts + ".png");
        FileUtils.copyFile(src, dest);
        log.error("Screenshot saved: " + dest.getPath());
    }

    public static void main(String[] args) throws Exception {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(3));
        try {
            log.info("Test started: login with invalid user");
            driver.get("https://the-internet.herokuapp.com/login");
            driver.findElement(By.id("username")).sendKeys("wrong");
            driver.findElement(By.id("password")).sendKeys("wrong");
            driver.findElement(By.cssSelector("button.radius")).click();
            log.info("Flash: " + driver.findElement(By.id("flash")).getText().trim());
            // Intentional failure to demonstrate screenshot on failure
            driver.findElement(By.id("nonExistingElement")).click();
        } catch (Exception e) {
            log.error("Test FAILED: " + e.getMessage().split("\n")[0]);
            capture(driver, "login_failure");
        } finally {
            log.info("Test finished");
            driver.quit();
        }
    }
}
