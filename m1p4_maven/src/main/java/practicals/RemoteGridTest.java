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

public class RemoteGridTest {
    public static void main(String[] args) throws Exception {
        URL hub = new URL("http://localhost:4444");
        List<Capabilities> browsers = Arrays.asList(new ChromeOptions(), new FirefoxOptions(), new EdgeOptions());
        for (Capabilities caps : browsers) {
            long start = System.currentTimeMillis();
            WebDriver driver = new RemoteWebDriver(hub, caps);
            try {
                driver.get("https://the-internet.herokuapp.com/");
                String title = driver.getTitle();
                System.out.println(caps.getBrowserName() + " -> title: " + title
                        + " | " + (title.equals("The Internet") ? "PASS" : "FAIL")
                        + " | time " + (System.currentTimeMillis() - start) + " ms");
            } finally { driver.quit(); }
        }
    }
}
