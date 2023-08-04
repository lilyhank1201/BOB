package automation.pageLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class OrderBookSell {
    public OrderBookSell(WebDriver driver) {
    this.driver = driver;
}

    private static WebDriver driver;
    @FindBy(xpath = "//a[contains(text(),'Spot')]")
    public static WebElement Menu_spot;

    @FindBy(xpath = "(//tr[@class='ant-table-row ant-table-row-level-0'])[3]")
    public static WebElement Cross_coin_TRX;


    public static double getAmountInOrderbookPrice(String price) throws InterruptedException {
        Thread.sleep(2000);
        WebElement PriceOrderbook = driver.findElement(By.xpath("((//div[@class ='table__box-item flex-end'])[1]//div[@class ='item__number red signle-orderbook'])[2]"));
        System.out.println("pri " + PriceOrderbook);
        System.out.println("pri " + PriceOrderbook.getText());
        int i = 1;
        while (true) {

            try {
                driver.findElement(By.xpath("((//div[@class ='table__box-item flex-end'])[1]//div[@class ='item__number red signle-orderbook'])["+i+"]"));
            } catch (Exception e) {
                break;
            }

            if (i > 5) {
                System.out.println("i > 5 roi  be oi");
                break;
            }

            WebElement priceOrderBook = driver.findElement(By.xpath("((//div[@class ='table__box-item flex-end'])[1]//div[@class ='item__number red signle-orderbook'])["+i+"]//p[1]"));
            WebElement amountOrderBook = driver.findElement(By.xpath("((//div[@class ='table__box-item flex-end'])[1]//div[@class ='item__number red signle-orderbook'])["+i+"]//p[2]"));
            WebElement sumOrderBook = driver.findElement(By.xpath("((//div[@class ='table__box-item flex-end'])[1]//div[@class ='item__number red signle-orderbook'])["+i+"]//p[3]"));

            System.out.println("priceOrderBook "+ priceOrderBook.getText());
            System.out.println("amountOrderBook "+ amountOrderBook.getText());
            System.out.println("sumOrderBook "+ sumOrderBook.getText());

            String priceOb = priceOrderBook.getText();
            String amountOb = priceOrderBook.getText();
            String sumOb = priceOrderBook.getText();
            System.out.println("price truyen vao " + price);
            System.out.println("price truyen vao " + Double.valueOf(price) + " " + Double.valueOf(priceOb));

            if (Double.valueOf(price).equals(Double.valueOf(priceOb))) {
                System.out.println("trung nhau roi do hihhihih " + price + " and price ob " + priceOb);
            }

            i++;

        }
//        System.out.println("check price ob " + PriceOrderbook.getText());
//        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
//        int ob_size = PriceOrderbook.findElements(By.tagName("p")).size();
//
//        System.out.println("check size " + ob_size);
//
//        for (int counter = 0; counter < ob_size; counter++) {
//            int index = counter;
//            String xpath_price = "(//div[@class ='table__box-item flex-end'])[1]//div[@class ='item__number red signle-orderbook']//p[1]";
//            String actual_price = PriceOrderbook.findElement(By.xpath(xpath_price)).getText();
//
//            if (actual_price.equals(price)) {
//                String xpath_amount = "(//div[@class ='table__box-item flex-end'])[1]//div[@class ='item__number red signle-orderbook']//p[2]";
//                String actual_amount = PriceOrderbook.findElement(By.xpath(xpath_amount)).getText();
//                double number_amount = Double.parseDouble(actual_amount);
//                return number_amount;
//            }
//        }

        return 0; // Return 0 or a specific default value if the price is not found
    }


    public static void main(String[] args) throws InterruptedException {
        OrderBookSell helper = new OrderBookSell(driver);
        String priceToFind = "100"; // Replace this with the desired price to find
        double amount = helper.getAmountInOrderbookPrice(priceToFind);
        System.out.println("Amount for price " + priceToFind + " is: " + amount);
    }





        public static boolean checkPriceExists(String price) throws InterruptedException {
            Thread.sleep(3000);
            WebElement priceElement = driver.findElement(By.xpath("(//div[@class ='table__box-item flex-end'])[1]//div[@class ='item__number red signle-orderbook']//p[1]"));
            String actualPrice = priceElement.getText();
            System.out.println("check price 1 " + actualPrice);
            return actualPrice.equals(price);
         }

        public double getAmountInOrderbookPrice1(String price, double additionalAmount) {
            WebElement PriceOrderbook = driver.findElement(By.xpath("(//div[@class ='table__box-item flex-end'])[1]//div[@class ='item__number red signle-orderbook']//p[1]"));
            int ob_size = PriceOrderbook.findElements(By.tagName("p")).size();

            for (int counter = 0; counter < ob_size; counter++) {
                int index = counter;
                String xpath_price = "(//div[@class ='table__box-item flex-end'])[1]//div[@class ='item__number red signle-orderbook']//p["+index +"]";
                String actual_price = PriceOrderbook.findElement(By.xpath(xpath_price)).getText();

                if (actual_price.equals(price)) {
                    String xpath_amount = "(//div[@class ='table__box-item flex-end'])[1]//div[@class ='item__number red signle-orderbook']//p[2]";
                    String actual_amount = PriceOrderbook.findElement(By.xpath(xpath_amount)).getText();
                    double number_amount = Double.parseDouble(actual_amount);
                    return number_amount + additionalAmount;

                }
            }

            return 0.0; // Return 0 or a specific default value if the price is not found
        }

    public double getAmountInOrderbookPrice1(String s) {
        return 0;
    }
}



