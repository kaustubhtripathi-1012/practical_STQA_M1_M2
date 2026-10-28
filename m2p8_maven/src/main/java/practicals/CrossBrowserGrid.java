package practicals;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.*;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import java.net.URL;

public class CrossBrowserGrid {
    static String run(Capabilities caps) {
        long start = System.currentTimeMillis();
        WebDriver driver = null;
        try {
            driver = new RemoteWebDriver(new URL("http://localhost:4444"), caps);
            driver.get("https://the-internet.herokuapp.com/login");
            driver.findElement(By.id("username")).sendKeys("tomsmith");
            driver.findElement(By.id("password")).sendKeys("SuperSecretPassword!");
            driver.findElement(By.cssSelector("button.radius")).click();
            boolean ok = driver.getCurrentUrl().contains("/secure");
            return (ok ? "PASS" : "FAIL") + " in " + (System.currentTimeMillis() - start) + " ms";
        } catch (Exception e) {
            return "ERROR (" + e.getClass().getSimpleName() + ") - compatibility/config issue";
        } finally { if (driver != null) driver.quit(); }
    }
    public static void main(String[] args) {
        Map<String, Capabilities> m = new LinkedHashMap<>();
        m.put("Chrome", new ChromeOptions());
        m.put("Firefox", new FirefoxOptions());
        m.put("Edge", new EdgeOptions());
        System.out.println("Browser   | Result");
        for (Map.Entry<String, Capabilities> e : m.entrySet())
            System.out.println(e.getKey() + "  | " + run(e.getValue()));
    }
}
