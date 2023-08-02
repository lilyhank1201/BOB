package automation.testsuite;

import automation.common.CommonBase;
import automation.constant.CT_Account;
import automation.pageLocator.Login_page;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

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
        driver.manage().timeouts().pageLoadTimeout(100, TimeUnit.SECONDS);
//        WebElement TitleHome = driver.findElement((By) Dashboard.Home);
//         assertTrue(TitleHome.isDisplayed());
        driver.close();
    }
}

//    public void BuySS(String price, String amount) throws InterruptedException {
//        Menu_spot.click();
//        Duration timeout = Duration.ofSeconds(5);
//        WebDriverWait wait = new WebDriverWait(driver, timeout);
//        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Spot')]"))).click();
//        Thread.sleep(1000);
//        wait.until(ExpectedConditions.visibilityOf(Cross_coin_TRX)).click();
//        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
////        //Check orderbook trước khi buy
//        Orderbook before = new Orderbook(driver);
//        double beforePrice = before.getAmountInOrderbookPrice("0.05000100");
//        System.out.println("Before Amount: " + beforePrice);
//        price_buy_locator.clear();
//        price_buy_locator.sendKeys(price);
//        amount_buy_locator.clear();
//        amount_buy_locator.sendKeys(amount);
////tính total buy
//        double inputPriceBuy = Double.parseDouble(price);
//        int inputAmountBuy = Integer.parseInt(amount);
//        double expectedTotalBuy = inputPriceBuy * inputAmountBuy;
//        System.out.println(inputPriceBuy * inputAmountBuy);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        Thread.sleep(3000);
//        WebElement totalBuyElement = driver.findElement(By.xpath("//div[@class='list__box buy']//div[@class='form__text']/p"));
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//        String actualTotalBuyText = totalBuyElement.getText();
//// Loại bỏ các ký tự không phải số khỏi chuỗi
//        actualTotalBuyText = actualTotalBuyText.replace(" USDT", "");
//        System.out.println(actualTotalBuyText);
//        //So sánh Actual và expected
//        double actualTotalBuy = Double.parseDouble(actualTotalBuyText);
//        double epsilon = 0.0001;
//        if (Math.abs(expectedTotalBuy - actualTotalBuy) < epsilon) {
//            System.out.println("Hai giá trị trùng khớp tương đối.");
//            Assert.assertTrue(true);
//        } else {
//            System.out.println("Hai giá trị không trùng khớp tương đối.");
//            Assert.fail();
//        }
//        //Thực hiện buy
//        btnBuyTRX.click();
////Check orderbook after khi buy
//        double after = Orderbook.getAmountInOrderbookPrice(price);
//        double afterPrice = Orderbook.getAmountInOrderbookPrice(price);
//        System.out.println("After Amount: " + afterPrice);
//        double difference = afterPrice - beforePrice;
//        System.out.println("Difference: " + difference);
//        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
//
//    }
