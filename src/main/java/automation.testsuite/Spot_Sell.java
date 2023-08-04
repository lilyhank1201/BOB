package automation.testsuite;

import automation.common.CommonBase;
import automation.constant.CT_Account;
import automation.pageLocator.Login_page;
import automation.pageLocator.Spot_page_Sell;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class Spot_Sell extends CommonBase {
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
    public void TotalSelllessthan10$ () throws InterruptedException {
        Login_page Login = new Login_page(driver);
        Login.LoginFunction("trangnth@timebird.org", "Te@12345");
        Spot_page_Sell Buy = new Spot_page_Sell(driver);
        Buy.OrderSellNotFill("0.11", "80");
    }

    @Test
    public void TotalSell10$ () throws InterruptedException {
        Login_page Login = new Login_page(driver);
        Login.LoginFunction("trangnth@timebird.org", "Te@12345");
        Spot_page_Sell Buy = new Spot_page_Sell(driver);
        Buy.OrderSellNotFill("0.11", "91");
    }

}