package practicals;

import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {
    @Test(priority = 1)
    public void validLogin() {
        LoginPage lp = new LoginPage(driver); lp.open();
        SecureAreaPage sp = lp.login("tomsmith", "SuperSecretPassword!");
        Assert.assertTrue(sp.getMessage().contains("You logged into a secure area!"));
        Assert.assertTrue(sp.isLogoutVisible());
    }
    @Test(priority = 2)
    public void invalidLogin() {
        LoginPage lp = new LoginPage(driver); lp.open();
        lp.login("wrong", "wrong");
        Assert.assertTrue(lp.getFlashMessage().contains("Your username is invalid!"));
    }
    @Test(priority = 3)
    public void logoutTest() {
        LoginPage lp = new LoginPage(driver); lp.open();
        LoginPage back = lp.login("tomsmith", "SuperSecretPassword!").logout();
        Assert.assertTrue(back.getFlashMessage().contains("You logged out"));
    }
}
