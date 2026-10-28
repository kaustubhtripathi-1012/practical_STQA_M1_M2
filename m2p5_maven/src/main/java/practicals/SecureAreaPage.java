package practicals;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class SecureAreaPage extends BasePage {
    @FindBy(css = "a[href='/logout']") private WebElement logout;
    @FindBy(id = "flash") private WebElement flash;
    public SecureAreaPage(WebDriver driver) { super(driver); }
    public boolean isLogoutVisible() { return logout.isDisplayed(); }
    public String getMessage() { return flash.getText(); }
    public LoginPage logout() { logout.click(); return new LoginPage(driver); }
}
