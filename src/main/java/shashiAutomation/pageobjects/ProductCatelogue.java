package shashiAutomation.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import shashiAutomation.AbstractComponents.AbstractComponent;

import java.util.List;


public class ProductCatelogue extends AbstractComponent {

    WebDriver  driver;

    public  ProductCatelogue(WebDriver driver){
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver,this);

    }
//    List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));

    @FindBy(css = ".col-lg-4")
    List<WebElement> products;

    @FindBy(css = ".ng-animating")
    WebElement spinner;


    By productsby = By.cssSelector(".col-lg-4");
    By addToCart = By.cssSelector(".card-body button:last-of-type");
    By toastMessage = By.cssSelector("#toast-container");

    public List<WebElement> getProductsList(){
        visibilityOfWebElement(productsby);
        return products;
    }

    public WebElement getProductByName(String productName){

        List<WebElement> products = driver.findElements(By.cssSelector(".col-lg-4"));
        WebElement prod = getProductsList().stream()
                .filter(product -> product.findElement(By.cssSelector("b")).getText().equals(productName))
                .findFirst()
                .orElse(null);
        return prod;

    }

    public void addProductToCart(String productName){
           WebElement prod =  getProductByName(productName);
        if (prod != null) {

            prod.findElement(addToCart).click();
            System.out.println("SUCCESS--------");
        } else {
            System.out.println("Product not found.");
        }
        visibilityOfWebElement(toastMessage);
        waitInvisibilityOfWebElement(spinner);
    }

}


