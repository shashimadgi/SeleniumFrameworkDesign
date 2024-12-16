package shashiAutomation.tests;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.testng.AssertJUnit;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;
import shashiAutomation.TestComponents.BaseTest;
import shashiAutomation.pageobjects.CartPage;
import shashiAutomation.pageobjects.CheckoutPage;
import shashiAutomation.pageobjects.ConfirmationPage;
import shashiAutomation.pageobjects.LandingPage;
import shashiAutomation.pageobjects.OrderPage;
import shashiAutomation.pageobjects.ProductCatelogue;

public class SubmitOrderTest extends BaseTest{
    String productName = "ZARA COAT 3";

    @Test(dataProvider="getData",groups= {"Purchase"})
    public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException
    {


        ProductCatelogue productCatalogue = landingPage.loginApp(input.get("email"), input.get("password"));
        List<WebElement> products = productCatalogue.getProductsList();
        productCatalogue.addProductToCart(input.get("product"));
        CartPage cartPage = productCatalogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        ConfirmationPage confirmationPage = checkoutPage.submitOrder();
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));


    }

    @Test(dependsOnMethods= {"submitOrder"})
    public void OrderHistoryTest()
    {
        //"ZARA COAT 3";
        ProductCatelogue productCatalogue = landingPage.loginApp("anshika@gmail.com", "Iamking@000");
        OrderPage ordersPage = productCatalogue.goToOrdersPage();
        Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));

    }

//Extent Reports -


    @DataProvider
    public Object[][] getData() throws IOException
    {
        List<HashMap<String,String>> data = getJsonDataToMap(System.getProperty("user.dir")+"//src//test//java//shashiAutomation//data//PurchaseOrder.json");
        return new Object[][]  {{data.get(0)}, {data.get(1) } };

    }

//	 @DataProvider
//	  public Object[][] getData()
//	  {
//	    return new Object[][]  {{"anshika@gmail.com","Iamking@000","ZARA COAT 3"}, {"shetty@gmail.com","Iamking@000","ADIDAS ORIGINAL" } };
//
//	  }
//	HashMap<String,String> map = new HashMap<String,String>();
//	map.put("email", "anshika@gmail.com");
//	map.put("password", "Iamking@000");
//	map.put("product", "ZARA COAT 3");
//
//	HashMap<String,String> map1 = new HashMap<String,String>();
//	map1.put("email", "shetty@gmail.com");
//	map1.put("password", "Iamking@000");
//	map1.put("product", "ADIDAS ORIGINAL");











}
