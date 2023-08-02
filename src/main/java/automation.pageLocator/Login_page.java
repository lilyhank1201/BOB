package automation.pageLocator;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class Login_page {
    WebDriver driver;
    //gotoLogin
    @FindBy(xpath = "(//a[contains(text(),'Login')])[1]")
    private WebElement btn_GotoLogin;
    @FindBy(xpath = "//input[@id='username']")
    private WebElement txtUserName;
    @FindBy(xpath = "//input[@id='password']")
    private WebElement txtPass;
    @FindBy(xpath = "//input[@id='kc-login']")
    private WebElement btnLogin;
    @FindBy(xpath = "//input[@id='rememberMe']")
    private WebElement rememberMe;

    public Login_page (WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }

    public void LoginFunction(String email, String password) throws InterruptedException {
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        ((JavascriptExecutor) driver).executeScript("arguments[0].click();", btn_GotoLogin);
//        btn_GotoLogin.click();
        driver.manage().timeouts().implicitlyWait(3, TimeUnit.SECONDS);
        txtUserName.clear();
        txtUserName.sendKeys(email);
        txtPass.clear();
        txtPass.sendKeys(password);
        btnLogin.click();
    }
}