package practicals;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.*;
import java.util.stream.Collectors;

public class TableSortingValidation {
    static List<String> column(WebDriver d, int col) {
        return d.findElements(By.cssSelector("#table1 tbody tr td:nth-child(" + col + ")"))
                .stream().map(WebElement::getText).collect(Collectors.toList());
    }
    public static void main(String[] args) {
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        driver.get("https://the-internet.herokuapp.com/tables");

        // print table data
        for (WebElement r : driver.findElements(By.cssSelector("#table1 tbody tr")))
            System.out.println(r.getText());

        // Sorting validation on "Last Name" (column 1)
        driver.findElement(By.cssSelector("#table1 thead th:nth-child(1)")).click();
        List<String> actual = column(driver, 1);
        List<String> expected = new ArrayList<>(actual);
        Collections.sort(expected);
        System.out.println("Sorted ascending? " + (actual.equals(expected) ? "PASS" : "FAIL") + " " + actual);

        // Filter validation: rows whose Due amount > $50
        int count = 0;
        for (String due : column(driver, 4)) if (Double.parseDouble(due.replace("$", "")) > 50) count++;
        System.out.println("Rows with Due > $50 : " + count);
        driver.quit();
    }
}
