package shashiAutomation.tests;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import shashiAutomation.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;

public class StandAloneTest  {
    public static void main(String[] args) throws InterruptedException {
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        driver.manage().window().maximize();

        driver.get("https://rahulshettyacademy.com/client/");

        String productName = "ZARA COAT 3";

        // Login
        driver.findElement(By.id("userEmail")).sendKeys("shashiAutomation123@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("shashiAutomate123");
        driver.findElement(By.id("login")).click();
        LandingPage lp = new LandingPage(driver);



        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(40));
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".col-lg-4")));

        // Add product to cart
        List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
        WebElement prod = products.stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"))
                .findFirst()
                .orElse(null);

        if (prod != null) {
            prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
            System.out.println("SUCCESS--------");
        } else {
            System.out.println("Product not found.");
        }

        // Wait for toast to disappear
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));

        // Ensure spinner is gone
        wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ngx-spinner-overlay")));

        // Attempt to click the cart button
//        try {
        WebElement cartButton = driver.findElement(By.cssSelector("[routerlink*='cart']"));
        wait.until(ExpectedConditions.elementToBeClickable(cartButton));

        // Click using JavaScript Executor
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", cartButton);
        System.out.println("clicked");

        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        Boolean match = cartProducts.stream().anyMatch(cartProduct -> cartProduct.getText().equalsIgnoreCase(productName));
        Assert.assertTrue(match);
        driver.findElement(By.cssSelector("[class*='totalRow'] button")).click();
        Thread.sleep(10);

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("[placeholder='Select Country']")));
        System.out.println("GOT IT");

        Actions action = new Actions(driver);


        action.sendKeys(driver.findElement(By.cssSelector("[placeholder='Select Country']")),"india").build().perform();
        System.out.println("Action clicked and entered india");

        wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
        driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
        driver.findElement(By.cssSelector(".btnn")).click();
        Thread.sleep(10);

       String message= driver.findElement(By.cssSelector(".hero-primary")).getText();
       System.out.println(message);
       Assert.assertTrue(message.equalsIgnoreCase("Thankyou for the order."));





//            // Validate navigation
//            Thread.sleep(5000); // Temporary hard wait for debugging
//            System.out.println("Current URL: " + driver.getCurrentUrl());
//
//            // Verify cart page by checking an element on the target page
//        } catch (Exception e) {
//            System.out.println("Failed to navigate to cart: " + e.getMessage());
//        }
//
//        // Close the browser
//        driver.quit();
    }
}
