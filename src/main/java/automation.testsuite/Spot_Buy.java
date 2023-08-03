package automation.testsuite;

import automation.common.CommonBase;
import automation.constant.CT_Account;
import automation.pageLocator.Login_page;
import automation.pageLocator.Spot_page;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Spot_Buy extends CommonBase {
    WebDriver driver;

    @BeforeTest
    public void openChromeDriver() {
        driver = initChromeDriver(CT_Account.webURL);
    }

//    @Test
//    public void suggest() throws InterruptedException {
//        Login_page Login = new Login_page(driver);
//        Login.LoginFunction("trangnth@timebird.org","Te@12345");
//        Spot_page Buy = new Spot_page(driver );
//        Buy.BuySS("0.050001", "200");
////        WebElement OrderSS = driver.findElement((By) Spot_page.OrderSS);
////        assertTrue(OrderSS.isDisplayed());
//          driver.close();
//    }



    @Test
    public void TotalBuylessthan10$ () throws InterruptedException {
        Login_page Login = new Login_page(driver);
        Login.LoginFunction("trangnth@timebird.org", "Te@12345");
        Spot_page Buy = new Spot_page(driver);
        Buy.OrderNotFill("0.040001", "200");
    }

    @Test
    public void TotalBuy10$ () throws InterruptedException {
        Login_page Login = new Login_page(driver);
        Login.LoginFunction("trangnth@timebird.org", "Te@12345");
        Spot_page Buy = new Spot_page(driver);
        Buy.OrderNotFill("0.040000", "250");
    }
}