package crosswebtesting;


import java.net.URL;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.edge.EdgeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.RemoteWebDriver;

public class chrome {

   public static void main(String[] args) throws Exception {

       String hubURL = "http://localhost:4444";

       // Chrome Test
       WebDriver chrome = new RemoteWebDriver(
               new URL(hubURL),
               new ChromeOptions()
       );

       chrome.get("https://www.google.com");

       System.out.println("Chrome : " + chrome.getTitle());

       chrome.quit();


       // Firefox Test
       WebDriver firefox = new RemoteWebDriver(
               new URL(hubURL),
               new FirefoxOptions()
       );

       firefox.get("https://www.google.com");

       System.out.println("Firefox : " + firefox.getTitle());

       firefox.quit();


       // Edge Test
       WebDriver edge = new RemoteWebDriver(
               new URL(hubURL),
               new EdgeOptions()
       );

       edge.get("https://www.google.com");

       System.out.println("Edge : " + edge.getTitle());

       edge.quit();
   }
}


