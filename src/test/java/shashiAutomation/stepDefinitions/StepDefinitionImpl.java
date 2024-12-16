package shashiAutomation.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import shashiAutomation.TestComponents.BaseTest;
import shashiAutomation.pageobjects.CartPage;
import shashiAutomation.pageobjects.CheckoutPage;
import shashiAutomation.pageobjects.ConfirmationPage;
import shashiAutomation.pageobjects.LandingPage;
import shashiAutomation.pageobjects.ProductCatelogue;

public class StepDefinitionImpl extends BaseTest{

    public LandingPage landingPage;
    public ProductCatelogue productCatelogue;
    public ConfirmationPage confirmationPage;
    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException
    {
        landingPage = launchApplication();
        //code
    }


    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password)
    {
        productCatelogue = landingPage.loginApp(username,password);
    }


    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException
    {
        List<WebElement> products = productCatelogue.getProductsList();
        productCatelogue.addProductToCart(productName);
    }

    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName) throws InterruptedException {
        CartPage cartPage = productCatelogue.goToCartPage();

        Boolean match = cartPage.VerifyProductDisplay(productName);
        Assert.assertTrue(match);
        CheckoutPage checkoutPage = cartPage.goToCheckout();
        checkoutPage.selectCountry("india");
        confirmationPage = checkoutPage.submitOrder();
    }


    @Then("{string} message is displayed on ConfirmationPage")
    public void message_displayed_confirmationPage(String string)
    {
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();
    }

    @Then("^\"([^\"]*)\" message is displayed$")
    public void something_message_is_displayed(String strArg1) throws Throwable {

        Assert.assertEquals(strArg1, landingPage.getErrorMessage());
        driver.close();
    }










}
