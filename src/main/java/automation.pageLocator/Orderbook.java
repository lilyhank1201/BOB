package automation.pageLocator;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Orderbook{
    private static WebDriver driver;


    public static int getAmountInOrderbookPrice(String price) {
        WebElement element = driver.findElement(By.xpath("(//div[@class ='table__box-item'])[1]//div[@class ='item__number signle-orderbook']//p[1]"));
        int ob_size = element.findElements(By.tagName("p")).size();

        for (int counter = 0; counter < ob_size; counter++) {
            int index = counter;
            String xpath_price = "(//div[@class ='table__box-item'])[1]//div[@class ='item__number signle-orderbook']//p[1]";
            String actual_price = element.findElement(By.xpath(xpath_price)).getText();

            if (actual_price.equals(price)) {
                String xpath_amount = "(//div[@class ='table__box-item'])[1]//div[@class ='item__number signle-orderbook']//p[2]";
                String actual_amount = element.findElement(By.xpath(xpath_amount)).getText();
                int number_amount = Integer.parseInt(actual_amount);
                return number_amount;
            }
        }

        return 0; // Return 0 or a specific default value if the price is not found
    }

    public void closeDriver() {
        driver.quit();
    }

    public static void main(String[] args) {
        Orderbook helper = new Orderbook();
        String priceToFind = "100"; // Replace this with the desired price to find
        int amount = helper.getAmountInOrderbookPrice(priceToFind);
        System.out.println("Amount for price " + priceToFind + " is: " + amount);
        helper.closeDriver();
    }
}
