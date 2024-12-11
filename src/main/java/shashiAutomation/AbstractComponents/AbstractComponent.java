package shashiAutomation.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import shashiAutomation.pageobjects.CartPage;
//import shashiAutomation.pageobjects.OrderPage;



import java.time.Duration;

public class AbstractComponent {
    WebDriver driver;

    public AbstractComponent(WebDriver driver) {
        this.driver = driver;
    }


    @FindBy(css = "[routerlink*='cart']")
    WebElement cartHeader;

    @FindBy(css = "[routerlink*='myorders']")
    WebElement orderHeader;


    public void visibilityOfWebElement(By findby) {


        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(findby));

    }

    public void waitForWebElementToAppear(WebElement findBy) {

        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.visibilityOf(findBy));

    }

    public CartPage goToCartPage() throws InterruptedException {
        Thread.sleep(2000);
        cartHeader.click();
        CartPage cartPage = new CartPage(driver);
        return cartPage;
    }

//    public OrderPage goToOrdersPage()
//    {
//        orderHeader.click();
//        OrderPage orderPage = new OrderPage(driver);
//        return orderPage;
//    }

    public void waitInvisibilityOfWebElement(WebElement ele){
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.invisibilityOf(ele));

    }


}
