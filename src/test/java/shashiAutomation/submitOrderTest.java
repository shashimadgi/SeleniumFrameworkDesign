package shashiAutomation;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;
import shashiAutomation.AbstractComponents.AbstractComponent;
import shashiAutomation.pageobjects.*;
//import shashiAutomation.pageobjects.ProductCatelogue;
//import shashiAutomation.pageobjects.OrderPage;
import java.time.Duration;
import java.util.List;

public class submitOrderTest  {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();
        String productName = "ZARA COAT 3";
        // Login
        LandingPage lp = new LandingPage(driver);
        lp.goTo();
        ProductCatelogue productCatalogue = lp.loginApp("shashiAutomation123@gmail.com","shashiAutomate123");
        ProductCatelogue productCatelogue = new ProductCatelogue(driver);
        List<WebElement> products=productCatelogue.getProductsList();
        productCatelogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        System.out.println("Confirmation Message: " + confirmMessage);
        // Close the browser
        driver.quit();

    }
}


