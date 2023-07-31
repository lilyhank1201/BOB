package automation.testsuite;

import automation.common.CommonBase;
import automation.constant.CT_Account;
import automation.pageLocator.Login_page;
import automation.pageLocator.Spot_page;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import static org.testng.Assert.assertTrue;

public class Spot_Buy extends CommonBase {
    WebDriver driver;
    @BeforeTest
    public void openChromeDriver() {
        driver = initChromeDriver(CT_Account.webURL);
    }

    @Test
    public void BuySS() throws InterruptedException {
        Login_page Login = new Login_page(driver);
        Login.LoginFunction("trangnth@timebird.org", "Te@12345");
        Spot_page Buy = new Spot_page(driver );
        Buy.BuySS("0.0500", "200");
        WebElement OrderSS = driver.findElement((By) Spot_page.OrderSS);
        assertTrue(OrderSS.isDisplayed());
        driver.close();
    }
}