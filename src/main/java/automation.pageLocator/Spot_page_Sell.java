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

public class Spot_page_Sell extends CommonBase {
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
    @FindBy(xpath = "((//div[@class='list__box-form'])[2]//input[@class='ant-input'])[1]")
    public static WebElement price_sell_locator;
    @FindBy(xpath = "((//div[@class='list__box-form'])[2]//input[@class='ant-input'])[2]")
    public static WebElement amount_sell_locator;
    @FindBy(xpath = "//div[@class='form__slider sell']//div[@class='ant-slider-step']/span[1]")
    public static WebElement sell0;
    @FindBy(xpath = "//div[@class='form__slider sell']//div[@class='ant-slider-step']/span[2]")
    public static WebElement sell25;
    @FindBy(xpath = "//div[@class='form__slider sell']//div[@class='ant-slider-step']/span[3]")
    public static WebElement sell50;
    @FindBy(xpath = "//div[@class='form__slider sell']//div[@class='ant-slider-step']/span[4]")
    public static WebElement sell75;
    @FindBy(xpath = "//div[@class='form__slider sell']//div[@class='ant-slider-step']/span[5]")
    public static WebElement sell100;
    @FindBy(xpath = "(//div[@class='list__box-form'])[2]//div[@class='form__text']/p")
    public static WebElement Total_Sell;
    @FindBy(xpath = "//div[@class ='button__btn sell']")
    public static WebElement btnSell;
    @FindBy(xpath = "(//div[@class ='table__box-item flex-end'])[1]//div[@class ='item__number red signle-orderbook']//p[1]")
    public static WebElement Price_on_OrderBookSell;
    @FindBy(xpath = "(//div[@class ='table__box-item flex-end'])[1]//div[@class ='item__number red signle-orderbook']//p[2]")
    public static WebElement Amount_on_OrderBookSell;
    @FindBy(xpath = "(//div[@class ='table__box-item flex-end'])[1]//div[@class ='item__number red signle-orderbook']//p[3]")
    public static WebElement Sum_on_OrderBookSell;
    @FindBy(xpath = "//div[contains(text(),'Create order successfully')]")
    public static WebElement OrderSS;

    @FindBy(xpath = "//a[contains(text(),'Spot')]")
    public static WebElement Menu_spot;
    @FindBy(xpath = "Order total cannot be lower than : 10.00 USDT")
    public static By totallower;
    @FindBy(xpath = "(//div[@class='list__box-available'])[2]")
    public static By availableSell;

    public Spot_page_Sell(WebDriver driver) {
        Spot_page_Sell.driver = driver;
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
//        //Check OrderBookSell trước khi buy
        OrderBookSell before = new OrderBookSell(driver);
        double beforePrice = OrderBookSell.getAmountInOrderbookPrice("0.05000100");
        System.out.println("Before Amount: " + beforePrice);
        price_sell_locator.clear();
        price_sell_locator.sendKeys(price);
        amount_sell_locator.clear();
        amount_sell_locator.sendKeys(amount);
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
        btnSell.click();
//Check orderbook after khi buy
        double after = OrderBookSell.getAmountInOrderbookPrice(price);
        double afterPrice = OrderBookSell.getAmountInOrderbookPrice(price);
        System.out.println("After Amount: " + afterPrice);
        double difference = afterPrice - beforePrice;
        System.out.println("Difference: " + difference);
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    }

    public void OrderSellNotFill(String price, String amount) throws InterruptedException {
        Menu_spot.click();
        Duration timeout = Duration.ofSeconds(5);
        WebDriverWait wait = new WebDriverWait(driver, timeout);
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[contains(text(),'Spot')]"))).click();
        Thread.sleep(1000);
        wait.until(ExpectedConditions.visibilityOf(Cross_coin_TRX)).click();
        driver.manage().timeouts().pageLoadTimeout(3, TimeUnit.SECONDS);
        //Check available Coin counter(USDT) truoc khi BUY
        WebElement FindavailableTRX = driver.findElement(By.xpath("(//div[@class='list__box-available'])[2]"));
        Thread.sleep(3000);
        String availableUSDTText = FindavailableTRX.getText();
        // Loại bỏ các ký tự không phải số khỏi chuỗi
        Thread.sleep(3000);
        availableUSDTText = availableUSDTText.replaceAll("[^0-9.]", "");
        Thread.sleep(3000);
        double BeforeavailableUSDT = Double.parseDouble(availableUSDTText);
        System.out.println("Available ban đầu " + BeforeavailableUSDT);
//Check orderbook trước khi buy
        OrderBookSell before = new OrderBookSell(driver);
        double beforePrice = OrderBookSell.getAmountInOrderbookPrice("0.040000");
        System.out.println("Before Price: " + beforePrice);
        price_sell_locator.clear();
        Thread.sleep(2000);
        price_sell_locator.sendKeys(price);
        Thread.sleep(2000);
        amount_sell_locator.clear();
        Thread.sleep(2000);
        amount_sell_locator.sendKeys(amount);
        Thread.sleep(2000);
//tính total buy
        double inputPriceSell = Double.parseDouble(price);
        int inputAmountSell = Integer.parseInt(amount);
        double expectedTotalSell = inputPriceSell * inputAmountSell;
        System.out.println("Expected Total Sell: " + inputPriceSell * inputAmountSell);
        WebElement totalSellElement = driver.findElement(By.xpath("( //div[@class='list__box sell mw576'])[1]//div[@class='form__text']/p"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        String actualTotalSellText = totalSellElement.getText();
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        actualTotalSellText = actualTotalSellText.replace(" USDT", "");// Loại bỏ các ký tự không phải số khỏi chuỗi
        Thread.sleep(3000);
        System.out.println("Actual Total Sell: " + actualTotalSellText);
//So sánh Actual và expected
        double actualTotalSell = Double.parseDouble(actualTotalSellText);
        double epsilon = 0.0001;// Độ sai số cho phép
        if (Math.abs(expectedTotalSell - actualTotalSell) < epsilon) {
            System.out.println("Total Sell Actual và Total Sell expected trùng khớp.");
            assertTrue(true);
        } else {
            System.out.println("Total Sell Actual và expected không trùng khớp.");
            Assert.fail();
        }
        if (actualTotalSell >= 10.0) {
            //Thực hiện buy
            btnSell.click();
            Thread.sleep(2000);
            //Tính available sau khi BUy = tay
            double NewAvailable = BeforeavailableUSDT - inputAmountSell;
            System.out.println("New Available =" + NewAvailable);
//Check available Coin counter(USDT) sau khi BUY
            Thread.sleep(2000);
            WebElement xpathAvailablesau = driver.findElement(By.xpath("(//div[@class='list__box-available'])[2]"));
            Thread.sleep(2000);
            String GetavailableUSDTsau = xpathAvailablesau.getText();
            Thread.sleep(2000);
            GetavailableUSDTsau = GetavailableUSDTsau.replaceAll("[^0-9.]", "");
            double AfteravailableUSDT = Double.parseDouble(GetavailableUSDTsau);
            Thread.sleep(2000);
            System.out.println("Actual available sau sell " + AfteravailableUSDT);

//So sánh Available
            if (Math.abs(NewAvailable - AfteravailableUSDT) <= epsilon) {
                System.out.println("actual total is match with Expected");
            } else {
                System.out.println("actual total is not match with Expected");
                Assert.fail();
            }


//Check orderbook after khi buy
            double priceToFind = Double.parseDouble(price);
            OrderBookSell orderbook = new OrderBookSell(driver);
            Thread.sleep(2000);
            boolean priceExists = orderbook.checkPriceExists(price);
            Thread.sleep(2000);
            if (priceExists) {
                System.out.println(priceToFind + " có trên order book");
                Thread.sleep(2000);
                WebElement amountElement = driver.findElement(By.xpath("((//div[@class='list__box-form'])[2]//input[@class='ant-input'])[2]"));
                Thread.sleep(2000);
                String currentAmountText = amountElement.getAttribute("value");
                System.out.println("Số lượng hiện tại: " + currentAmountText);
                double currentAmount = Double.parseDouble(currentAmountText);
                double amountToAdd = Double.parseDouble(amount);
                double newAmount = currentAmount + amountToAdd;
                System.out.println("Số lượng mới sau khi cộng thêm amountToAdd: " + newAmount);
                String newAmountText = String.valueOf(newAmount);
                amountElement.clear();
                amountElement.sendKeys(newAmountText);
                System.out.println("Đã cập nhật số lượng mới thành công: " + newAmountText);
                // Kiểm tra xem số lượng đã được cập nhật thành công hay không
                String updatedAmountText = amountElement.getAttribute("value");
                double updatedAmount = Double.parseDouble(updatedAmountText);
                if (Double.compare(newAmount, updatedAmount) == 0) {
                    System.out.println("Số lượng đã được cập nhật thành công");
                } else {
                    System.out.println("Số lượng không khớp với giá trị mới cập nhật");
                }
            } else {
                double amountToEnter = Double.parseDouble(amount);
                enterPriceAndAmount(String.valueOf(priceToFind), String.valueOf(amountToEnter));
                System.out.println("Create order is successfully");
                driver.close();
            }
        } else {
            System.out.println("actualTotalBuyText <  10$ -> Đóng trình duyệt");
        }
    }

//            if (priceExists) {
//                System.out.println(priceToFind + " có trên order book");
//                WebElement amountElement = driver.findElement((By) amount_buy_locator);
//                String currentAmountText = amountElement.getText();
//                System.out.println(currentAmountText);
//                double currentAmount = Double.parseDouble(currentAmountText);
//                double amountToAdd = 200;
//                double newAmount = currentAmount + amountToAdd;
//                System.out.println(newAmount + " Update amounnt on order book is successfully");
//                String newAmountText = String.valueOf(newAmount);
//                amountElement.clear();
//                amountElement.sendKeys(newAmountText);
//                System.out.println("Đã cộng 200 vào cột Amount: " + newAmountText);


    private void enterPriceAndAmount(String priceToFind, String amountToEnter) {
    }


}



 