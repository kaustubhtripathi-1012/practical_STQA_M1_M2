package practicals;

import org.openqa.selenium.*;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {
    @FindBy(id = "username") private WebElement username;
    @FindBy(id = "password") private WebElement password;
    @FindBy(css = "button[type='submit']") private WebElement loginBtn;
    @FindBy(id = "flash") private WebElement flash;

    public LoginPage(WebDriver driver) { super(driver); }
    public void open() { driver.get("https://the-internet.herokuapp.com/login"); }
    public SecureAreaPage login(String user, String pass) {
        username.sendKeys(user); password.sendKeys(pass); loginBtn.click();
        return new SecureAreaPage(driver);
    }
    public String getFlashMessage() { return flash.getText(); }
}
