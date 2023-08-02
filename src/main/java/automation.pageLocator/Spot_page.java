package automation.pageLocator;

import automation.common.CommonBase;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.testng.Assert.assertTrue;

public class Spot_page extends CommonBase {
    static WebDriver driver;
    //cross
    @FindBy(xpath = "/tr[@class='ant-table-row ant-table-row-level-0'])[1]")
    public static WebElement Cross_coin_BTC;
    @FindBy(xpath = "(//tr[@class='ant-table-row ant-table-row-level-0'])[2]")
    public static WebElement Cross_coin_ETH;
    @FindBy(xpath = "(//tr[@class='ant-table-row ant-table-row-level-0'])[3]")
    public static WebElement Cross_coin_TRX;
    @FindBy(xpath = "(//tr[@class='ant-table-row ant-table-row-level-0'])[4]")
    public static WebElement Cross_coin_BNB;
    //counter BUY
    @FindBy(xpath = "(//div[@class='list__box-available'])[1]")
    public static WebElement available_counter_buy_old;
    @FindBy(xpath = "(//div[@class='list__box-filter']//div[contains(text(),'Limit')])[1]")
    public static WebElement Buy_Limit_label;
    @FindBy(xpath = "(//div[@class='list__box-filter']//div[contains(text(),'Market')])[1]")
    public static WebElement Buy_Market_label;
    @FindBy(xpath = "(//div[@class='list__box-filter']//div[contains(text(),'Trigger Order')])[1]")
    public static WebElement Buy_Trigger_Order_label;
    @FindBy(xpath = "(//div[@class='list__box buy']//div//input[@class='ant-input'])[1]")
    public static WebElement price_buy_locator;
    @FindBy(xpath = "(//div[@class='list__box buy']//div//input[@class='ant-input'])[2]")
    public static WebElement amount_buy_locator;
    @FindBy(xpath = "//div[@class='form__slider buy']//div[@class='ant-slider-step']/span[1]")
    public static WebElement buy0;
    @FindBy(xpath = "//div[@class='form__slider buy']//div[@class='ant-slider-step']/span[2]")
    public static WebElement buy25;
    @FindBy(xpath = "//div[@class='form__slider buy']//div[@class='ant-slider-step']/span[3]")
    public static WebElement buy50;
    @FindBy(xpath = "//div[@class='form__slider buy']//div[@class='ant-slider-step']/span[4]")
    public static WebElement buy75;
    @FindBy(xpath = "//div[@class='form__slider buy']//div[@class='ant-slider-step']/span[5]")
    public static WebElement buy100;
    @FindBy(xpath = "//div[@class='list__box buy']//div[@class='form__text']/p")
    public static WebElement Total_buy;
    @FindBy(xpath = "//div[contains(text(),'Buy BTC')]")
    public static WebElement btnBuyBTC;
    @FindBy(xpath = "//div[contains(text(),'Buy ETH')]")
    public static WebElement btnBuyETH;
    @FindBy(xpath = "//div[contains(text(),'Buy BNB')]")
    public static WebElement btnBuyBNB;
    @FindBy(xpath = "//div[contains(text(),'Buy TRX')]")
    public static WebElement btnBuyTRX;
    @FindBy(xpath = "(//div[@class ='table__box-item'])[1]")
    public static WebElement Price_on_orderbook;
    @FindBy(xpath = "(//div[@class ='table__box-item'])[1]//div[@class ='item__number signle-orderbook']//p[2]")
    public static WebElement Amount_on_orderbook;
    @FindBy(xpath = "(//div[@class ='table__box-item'])[1]//div[@class ='item__number']//p[3]")
    public static WebElement Sum_on_orderbook;
    @FindBy(xpath = "//div[contains(text(),'Create order successfully')]")
    public static WebElement OrderSS;

    @FindBy(xpath = "//a[contains(text(),'Spot')]")
    public static WebElement Menu_spot;
    @FindBy(xpath = "Order total cannot be lower than : 10.00 USDT")
    public static By totallower;
    @FindBy(xpath = "(//div[@class='list__box-available'])[1]")
    public static By availableUSDT;

    public Spot_page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void suggest(String price, String amount) throws InterruptedException {
        Menu_spot.click();
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Spot')]"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(Cross_coin_TRX)).click();
        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
//        //Check orderbook trước khi buy
        Orderbook before = new Orderbook(driver);
        double beforePrice = before.getAmountInOrderbookPrice("0.05000100");
        System.out.println("Before Amount: " + beforePrice);
        price_buy_locator.clear();
        price_buy_locator.sendKeys(price);
        amount_buy_locator.clear();
        amount_buy_locator.sendKeys(amount);
//tính total buy
        double inputPriceBuy = Double.parseDouble(price);
        int inputAmountBuy = Integer.parseInt(amount);
        double expectedTotalBuy = inputPriceBuy * inputAmountBuy;
        System.out.println(inputPriceBuy * inputAmountBuy);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
        WebElement totalBuyElement = driver.findElement(By.xpath("//div[@class='list__box buy']//div[@class='form__text']/p"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String actualTotalBuyText = totalBuyElement.getText();
// Loại bỏ các ký tự không phải số khỏi chuỗi
        actualTotalBuyText = actualTotalBuyText.replace(" USDT", "");
        System.out.println(actualTotalBuyText);
        //So sánh Actual và expected
        double actualTotalBuy = Double.parseDouble(actualTotalBuyText);
        double epsilon = 0.0001;
        if (Math.abs(expectedTotalBuy - actualTotalBuy) < epsilon) {
            System.out.println("Hai giá trị trùng khớp tương đối.");
            assertTrue(true);
        } else {
            System.out.println("Hai giá trị không trùng khớp tương đối.");
            Assert.fail();
        }
        //Thực hiện buy
        btnBuyTRX.click();
//Check orderbook after khi buy
        double after = Orderbook.getAmountInOrderbookPrice(price);
        double afterPrice = Orderbook.getAmountInOrderbookPrice(price);
        System.out.println("After Amount: " + afterPrice);
        double difference = afterPrice - beforePrice;
        System.out.println("Difference: " + difference);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    public void OrderNotFill(String price, String amount) throws InterruptedException {
        Menu_spot.click();
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Spot')]"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(Cross_coin_TRX)).click();
        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
 //Check availabel Coin counter(USDT) truoc khi BUY
        WebElement  availableUSDT = driver.findElement(By.xpath("(//div[@class='list__box-available'])[1]"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String   availableUSDTText = availableUSDT.getText();
        // Loại bỏ các ký tự không phải số khỏi chuỗi
        availableUSDTText = availableUSDTText.replaceAll("[^0-9.]", "");
        double BeforeavailableUSDT = Double.parseDouble(availableUSDTText);
        System.out.println("Available ban đầu " + BeforeavailableUSDT );
//Check orderbook trước khi buy
        Orderbook before = new Orderbook(driver);
        double beforePrice = before.getAmountInOrderbookPrice("0.04000100");
        System.out.println("Before Amount: " + beforePrice);
        price_buy_locator.clear();
        price_buy_locator.sendKeys(price);
        amount_buy_locator.clear();
        amount_buy_locator.sendKeys(amount);
//tính total buy
        double inputPriceBuy = Double.parseDouble(price);
        int inputAmountBuy = Integer.parseInt(amount);
        double expectedTotalBuy = inputPriceBuy * inputAmountBuy;
        System.out.println(inputPriceBuy * inputAmountBuy);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        Thread.sleep(3000);
        WebElement totalBuyElement = driver.findElement(By.xpath("//div[@class='list__box buy']//div[@class='form__text']/p"));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        String actualTotalBuyText = totalBuyElement.getText();
// Loại bỏ các ký tự không phải số khỏi chuỗi
        actualTotalBuyText = actualTotalBuyText.replace(" USDT", "");
        System.out.println(actualTotalBuyText);
        //So sánh Actual và expected
        double actualTotalBuy = Double.parseDouble(actualTotalBuyText);
        double epsilon = 0.0001;// Độ sai số cho phép
        if (Math.abs(expectedTotalBuy - actualTotalBuy) < epsilon) {
            System.out.println("Hai giá trị trùng khớp tương đối.");
            assertTrue(true);
        } else {
            System.out.println("Hai giá trị không trùng khớp tương đối.");
            Assert.fail();
        }
        if (actualTotalBuy >= 10.0) {
            //Thực hiện buy
            btnBuyTRX.click();
            System.out.println("actualTotalBuyText >= 10$");
            Thread.sleep(2000);
//Tính available sau khi BUy = tay
            double NewAvailable = BeforeavailableUSDT - expectedTotalBuy;
//Check available Coin counter(USDT) sau khi BUY
            driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
            String availableUSDTsau = availableUSDT.getText();
            availableUSDTsau = availableUSDTsau.replaceAll("[^0-9.]", "");
            double AfteravailableUSDT = Double.parseDouble(availableUSDTsau);
            System.out.println("Available sau khi Buy " + BeforeavailableUSDT );
//So sánh Available
            Thread.sleep(2000);
            if (Math.abs(NewAvailable - AfteravailableUSDT) <= epsilon) {
                System.out.println("actual total is match with Expected");
            } else {
                System.out.println("actual total is not match with Expected");
            }

//Check orderbook after khi buy
            String priceToFind = String.valueOf(price);
            Orderbook orderbook = new Orderbook(driver);
            boolean priceExists = orderbook.checkPriceExists(priceToFind);
            if (priceExists) {
                System.out.println(priceToFind + " có trên order book");
                WebElement amountElement = driver.findElement((By) amount_buy_locator);
                String currentAmountText = amountElement.getText();
                System.out.println(currentAmountText);
                double currentAmount = Double.parseDouble(currentAmountText);
                double amountToAdd = 200;
                double newAmount = currentAmount + amountToAdd;
                System.out.println(newAmount + " Update amounnt on order book is successfully");
                String newAmountText = String.valueOf(newAmount);
                amountElement.clear();
                amountElement.sendKeys(newAmountText);
                System.out.println("Đã cộng 200 vào cột Amount: " + newAmountText);
            } else {
                String amountToEnter = "200";
                enterPriceAndAmount(priceToFind, amountToEnter);
                System.out.println("Create order is successfully");
                driver.close();
            }
        } else {
            driver.close();
            System.out.println("actualTotalBuyText <  10$ -> Đóng trình duyệt");
        }
    }


    private void enterPriceAndAmount(String priceToFind, String amountToEnter) {
    }


}



 