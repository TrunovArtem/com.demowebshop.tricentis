package UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;

public class CartPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By checkBoxRemove = By.xpath("//input[@name='removefromcart']");
    private By updateCartBtn = By.xpath("//input[@name='updatecart']");
    private By yourShoppingCartisempty = By.xpath("//div[@class='order-summary-content']");
    private By priceOrderTotal = By.xpath("//span[@class='product-price order-total']");
    public CartPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    public String goodsInCart (){
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//a[@class='product-name']")));
        return driver.findElement(By.xpath("//a[@class='product-name']")).getText();
    }
    public CartPage removeFromCart (){
        driver.findElements(checkBoxRemove).get(0).click();
        driver.findElement(updateCartBtn).click();
        return this;
    }
    public boolean cartIsEmpty (){
        return driver.findElement(yourShoppingCartisempty).isDisplayed();
    }
    public String totalOrderPrice (){
        return driver.findElement(priceOrderTotal).getText();
    }

    }