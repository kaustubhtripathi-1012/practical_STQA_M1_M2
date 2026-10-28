package practicals;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.*;
import org.openqa.selenium.support.ui.Select;

public class ComboBoxVerification {
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/dropdown");
        Select dropdown = new Select(driver.findElement(By.id("dropdown")));
        List<WebElement> options = dropdown.getOptions();
        System.out.println("Total items: " + options.size());
        for (WebElement o : options) System.out.println(" - " + o.getText());
        String def = dropdown.getFirstSelectedOption().getText();
        System.out.println("Default selected: " + def + " -> " + (def.equals("Please select an option") ? "PASS" : "FAIL"));
        // Selection testing
        for (int i = 1; i < options.size(); i++) {
            dropdown.selectByIndex(i);
            String sel = dropdown.getFirstSelectedOption().getText();
            System.out.println("Selected '" + sel + "' -> " + (sel.equals(options.get(i).getText()) ? "PASS" : "FAIL"));
        }
        System.out.println("Is multiple select? " + dropdown.isMultiple());
        driver.quit();
    }
}
