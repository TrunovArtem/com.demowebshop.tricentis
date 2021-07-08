import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.ArrayList;
import java.util.List;

public class GoodsPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By changeDisplayPath = By.xpath("//select[@id='products-pagesize']");
    private By changeSortPath = By.xpath("//select[@id='products-orderby']");
    private By cartButton = By.xpath("//input[@value='Add to cart']");
    private By addToCartBTN = By.xpath("//input[@id='add-to-cart-button-74']");
    private By shoppingCart = By.xpath("//span[@class='cart-label']");

    public GoodsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    public int goodsOnPage (){
        List<WebElement> goodsList = new ArrayList<>();
        goodsList = driver.findElements(By.xpath("//div[@class='item-box']"));
        return goodsList.size();
    }
    public GoodsPage changeDisplay (){
        Select display = new Select(driver.findElement(changeDisplayPath));
        display.selectByIndex(0);
        return this;
    }
    public GoodsPage changeSort (){
        Select display = new Select(driver.findElement(changeSortPath));
        display.selectByIndex(4);
        return this;
    }

    public GoodsPage addToCart() {
        driver.findElement(cartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBTN));
        driver.findElement(addToCartBTN).click();
        return this;
    }
    public Cart openCart(){
        driver.findElement(shoppingCart).click();
        return new Cart(driver,wait);
    }
}