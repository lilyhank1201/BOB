package automation.pageLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class Spot_page {
    WebDriver driver;
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

    public Spot_page(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }


    public void BuySS(String price, String amount) throws InterruptedException {
        Menu_spot.click();
        driver.findElement(By.xpath("//a[contains(text(),'Spot')]")).click();
        Thread.sleep(1000);
        Cross_coin_TRX.click();
        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
  //Check orderbook trước khi buy
        Orderbook before = new Orderbook();
        double beforeAmount = Orderbook.getAmountInOrderbookPrice("0.0500");
        System.out.println("Before Amount: " + beforeAmount);
        price_buy_locator.clear();
        price_buy_locator.sendKeys(price);
        amount_buy_locator.clear();
        amount_buy_locator.sendKeys(amount);
//tính total buy
        double inputPriceBuy = Double.parseDouble(price);
        int inputAmountBuy =  Integer.parseInt(amount);
        double expectedTotalBuy = inputPriceBuy * inputAmountBuy;
        System.out.println("expected_Total_buy là kết quả của Input_price_buy * Input_amount_buy ");
        WebElement totalBuyElement = driver.findElement(By.xpath("//div[@class='list__box buy']//div[@class='form__text']/p"));
        String actualTotalBuyText = totalBuyElement.getText();
        double actualTotalBuy = Double.parseDouble(actualTotalBuyText);
        boolean isEqual = Double.compare(expectedTotalBuy, actualTotalBuy) == 0;
        if (isEqual) {
            System.out.println("expected_Total_buy là kết quả của Input_price_buy * Input_amount_buy");
        } else {
            System.out.println("expected_Total_buy không là kết quả của Input_price_buy * Input_amount_buy");
        btnBuyTRX.click();
//Check orderbook after khi buy
        double after = Orderbook.getAmountInOrderbookPrice("0.0500");
        double afterAmount = Orderbook.getAmountInOrderbookPrice("0.0500");
        System.out.println("After Amount: " + afterAmount);
        double difference = afterAmount - beforeAmount;
        System.out.println("Difference: " + difference);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);



    }
}}

