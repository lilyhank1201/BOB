package automation.testsuite;

import automation.common.CommonBase;
import automation.constant.CT_Account;
import automation.pageLocator.Login_page;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class LoginRun extends CommonBase {
    WebDriver driver;

    @BeforeTest
    public void openChromeDriver() {
        driver = initChromeDriver(CT_Account.webURL);
    }

    @Test
    public void LoginSuccessfully() throws InterruptedException {
        Login_page Login = new Login_page(driver);
        Login.LoginFunction("trangnth@timebird.org", "Te@12345");
//        driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
       // WebElement TitleHome = driver.findElement(Dashboard.Home);
 //        assertTrue(TitleHome.isDisplayed());
        driver.close();
    }
}
