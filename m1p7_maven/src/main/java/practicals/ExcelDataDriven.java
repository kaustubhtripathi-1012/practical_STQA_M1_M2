package practicals;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import java.time.Duration;
import java.util.*;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import java.io.*;

public class ExcelDataDriven {
    public static void main(String[] args) throws Exception {
        String path = "students.xlsx";
        // ---- 1. READ + UPDATE 10 records (marks +5, Status column added) ----
        FileInputStream fis = new FileInputStream(path);
        Workbook wb = new XSSFWorkbook(fis);
        fis.close();
        Sheet sh = wb.getSheet("Students");
        sh.getRow(0).createCell(5).setCellValue("Status");
        for (int i = 1; i <= 10; i++) {
            Row r = sh.getRow(i);
            double marks = r.getCell(4).getNumericCellValue() + 5;   // update
            r.getCell(4).setCellValue(marks);
            r.createCell(5).setCellValue(marks >= 60 ? "Pass" : "Fail");
        }
        // ---- 2. WRITE back ----
        FileOutputStream fos = new FileOutputStream(path);
        wb.write(fos); fos.close();

        // ---- 3. VERIFY on web application (httpbin echo form) ----
        WebDriver driver = new ChromeDriver();
        driver.manage().window().maximize();
        DataFormatter fmt = new DataFormatter();
        int pass = 0;
        for (int i = 1; i <= 10; i++) {
            Row r = sh.getRow(i);
            String name = fmt.formatCellValue(r.getCell(1));
            String email = fmt.formatCellValue(r.getCell(2));
            String phone = fmt.formatCellValue(r.getCell(3));
            driver.get("https://httpbin.org/forms/post");
            driver.findElement(By.name("custname")).sendKeys(name);
            driver.findElement(By.name("custtel")).sendKeys(phone);
            driver.findElement(By.name("custemail")).sendKeys(email);
            driver.findElement(By.cssSelector("form button")).click();
            boolean ok = driver.getPageSource().contains(name) && driver.getPageSource().contains(email);
            System.out.println("Record " + i + " (" + name + ") verification: " + (ok ? "PASS" : "FAIL"));
            if (ok) pass++;
        }
        System.out.println("Verified " + pass + "/10 records");
        wb.close(); driver.quit();
    }
}
