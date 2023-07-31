package automation.pageLocator;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Dashboard {
    WebDriver driver;
    @FindBy(xpath = "//*[@id=\"app\"]/div/div[1]/div[2]/div[1]/a/img")
    public static WebElement Home;
    @FindBy(xpath = "//a[contains(text(),'Markets')]")
    public static WebElement Menu_Market;
    @FindBy(xpath = "//a[contains(text(),'Spot')]")
    public static WebElement Menu_spot;
    @FindBy(xpath = "//a[contains(text(),'Margin')]")
    public static WebElement Menu_Margin;
    public Dashboard(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);

    }

}
