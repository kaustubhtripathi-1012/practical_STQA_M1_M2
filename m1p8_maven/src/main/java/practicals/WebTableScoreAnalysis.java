package practicals;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.*;
import org.openqa.selenium.support.ui.*;
import java.io.File;

public class WebTableScoreAnalysis {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get(new File("marks.html").getAbsoluteFile().toURI().toString());
        new WebDriverWait(driver, Duration.ofSeconds(10))
            .until(ExpectedConditions.numberOfElementsToBeMoreThan(By.cssSelector("#marksTable tbody tr"), 0));

        List<WebElement> rows = driver.findElements(By.cssSelector("#marksTable tbody tr"));
        int total = rows.size(), above60 = 0;
        for (WebElement row : rows) {
            List<WebElement> cols = row.findElements(By.tagName("td"));
            boolean flag = false;
            for (int i = 1; i < cols.size(); i++)
                if (Integer.parseInt(cols.get(i).getText().trim()) > 60) { flag = true; break; }
            if (flag) { above60++; System.out.println(cols.get(0).getText() + " scored above 60 in at least one subject"); }
        }
        System.out.println("Total students            : " + total);
        System.out.println("Students scoring > 60     : " + above60);
        System.out.printf("Percentage                : %.2f%%%n", above60 * 100.0 / total);
        driver.quit();
    }
}
