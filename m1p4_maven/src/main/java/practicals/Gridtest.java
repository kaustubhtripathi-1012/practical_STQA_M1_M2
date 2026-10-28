package practicals;

import java.io.File;
import java.net.URL;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
public class Gridtest {
	
	 public static void main(String[] args) throws Exception {

	        String gridURL = "http://localhost:4444";

	        File file = new File("cross.html");

	        String pageURL = file.toURI().toString();

	        // Chrome Test
	        runTest(
	                "Chrome",
	                new ChromeOptions(),
	                gridURL,
	                pageURL
	        );

	        // Firefox Test
	        runTest(
	                "Firefox",
	                new FirefoxOptions(),
	                gridURL,
	                pageURL
	        );

	        // Edge Test
	        runTest(
	                "Edge",
	                new EdgeOptions(),
	                gridURL,
	                pageURL
	        );
	    }

	    public static void runTest(
	            String browserName,
	            Object options,
	            String gridURL,
	            String pageURL) throws Exception {

	        long startTime = System.currentTimeMillis();

	        WebDriver driver;

	        if (options instanceof ChromeOptions) {

	            driver = new RemoteWebDriver(
	                    new URL(gridURL),
	                    (ChromeOptions) options);

	        } else if (options instanceof FirefoxOptions) {

	            driver = new RemoteWebDriver(
	                    new URL(gridURL),
	                    (FirefoxOptions) options);

	        } else {

	            driver = new RemoteWebDriver(
	                    new URL(gridURL),
	                    (EdgeOptions) options);
	        }

	        try {

	            driver.manage().window().maximize();

	            // Open web page
	            driver.get(pageURL);

	            // Get page title
	            String title = driver.getTitle();

	            // Click button
	            driver.findElement(
	                    By.id("testButton")).click();

	            // Get message
	            String message =
	                    driver.findElement(
	                            By.id("message")).getText();

	            long endTime =
	                    System.currentTimeMillis();

	            double executionTime =
	                    (endTime - startTime) / 1000.0;

	            System.out.println();
	            System.out.println("--------------------------------");
	            System.out.println("Browser: " + browserName);
	            System.out.println("Page Title: " + title);
	            System.out.println("Message: " + message);
	            System.out.println(
	                    "Execution Time: "
	                    + executionTime + " seconds");

	            // Validation
	            if (message.equals(
	                    "Button clicked successfully")) {

	                System.out.println(
	                        "Validation: PASS");

	            } else {

	                System.out.println(
	                        "Validation: FAIL");
	            }

	        } finally {

	            driver.quit();
	        }
	    }
	}