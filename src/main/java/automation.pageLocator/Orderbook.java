package automation.pageLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.concurrent.TimeUnit;

public class Orderbook{
    public Orderbook(WebDriver driver) {
    this.driver = driver;
}
    private static WebDriver driver;


    public static double getAmountInOrderbookPrice(String price) {
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        WebElement PriceOrderbook = driver.findElement(By.xpath("(//div[@class ='table__box-item'])[1]//div[@class ='item__number signle-orderbook']//p[1]"));
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        int ob_size = PriceOrderbook.findElements(By.tagName("p")).size();

        for (int counter = 0; counter < ob_size; counter++) {
            int index = counter;
            String xpath_price = "(//div[@class ='table__box-item'])[1]//div[@class ='item__number signle-orderbook']//p[1]";
            String actual_price = PriceOrderbook.findElement(By.xpath(xpath_price)).getText();

            if (actual_price.equals(price)) {
                String xpath_amount = "(//div[@class ='table__box-item'])[1]//div[@class ='item__number signle-orderbook']//p[2]";
                String actual_amount = PriceOrderbook.findElement(By.xpath(xpath_amount)).getText();
                double number_amount = Double.parseDouble(actual_amount);
                return number_amount;
            }
        }

        return 0; // Return 0 or a specific default value if the price is not found
    }


    public static void main(String[] args) {
        Orderbook helper = new Orderbook(driver);
        String priceToFind = "100"; // Replace this with the desired price to find
        double amount = helper.getAmountInOrderbookPrice(priceToFind);
        System.out.println("Amount for price " + priceToFind + " is: " + amount);
    }





        public boolean checkPriceExists(String price) {
            WebElement priceElement = driver.findElement(By.xpath("(//div[@class ='table__box-item'])[1]//div[@class ='item__number signle-orderbook']//p[1]"));
            String actualPrice = priceElement.getText();
            return actualPrice.equals(price);
        }

        public double getAmountInOrderbookPrice1(String price) {
            WebElement PriceOrderbook = driver.findElement(By.xpath("(//div[@class ='table__box-item'])[1]//div[@class ='item__number signle-orderbook']//p[1]"));
            int ob_size = PriceOrderbook.findElements(By.tagName("p")).size();

            for (int counter = 0; counter < ob_size; counter++) {
                int index = counter;
                String xpath_price = "(//div[@class ='table__box-item'])[1]//div[@class ='item__number signle-orderbook']//p[1]";
                String actual_price = PriceOrderbook.findElement(By.xpath(xpath_price)).getText();

                if (actual_price.equals(price)) {
                    String xpath_amount = "(//div[@class ='table__box-item'])[1]//div[@class ='item__number signle-orderbook']//p[2]";
                    String actual_amount = PriceOrderbook.findElement(By.xpath(xpath_amount)).getText();
                    double number_amount = Double.parseDouble(actual_amount);
                    return number_amount;
                }
            }

            return 0; // Return 0 or a specific default value if the price is not found
        }

        // Các phương thức và thuộc tính khác
    }



