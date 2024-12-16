package shashiAutomation.tests;

import org.testng.annotations.Test;
import org.testng.AssertJUnit;
import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import shashiAutomation.TestComponents.BaseTest;
import shashiAutomation.TestComponents.Retry;
import shashiAutomation.pageobjects.CartPage;
import shashiAutomation.pageobjects.CheckoutPage;
import shashiAutomation.pageobjects.ConfirmationPage;
import shashiAutomation.pageobjects.ProductCatelogue;

public class ErrorValidationsTest extends BaseTest {

    @Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
    public void LoginErrorValidation() throws IOException, InterruptedException {


        landingPage.loginApp("anshika@gmail.com", "Iamki000");
        Assert.assertEquals("Incorrect email or password.", landingPage.getErrorMessage());

    }


    @Test
    public void ProductErrorValidation() throws IOException, InterruptedException
    {

        String productName = "ZARA COAT 3";
        ProductCatelogue productCatalogue = landingPage.loginApp("rahulshetty@gmail.com", "Iamking@000");
        List<WebElement> products = productCatalogue.getProductsList();
        productCatalogue.addProductToCart(productName);
        CartPage cartPage = productCatalogue.goToCartPage();
        Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
        Assert.assertFalse(match);



    }




}
