package UI;

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
    private  By typeProcessorFast = By.xpath("//input[@id='product_attribute_74_5_26_82']");
    private By ram8gb = By.xpath("//input[@id='product_attribute_74_6_27_85']");
    private By imageViewer = By.xpath("//input[@id='product_attribute_74_8_29_88']");
    private By officeSuite = By.xpath("//input[@id='product_attribute_74_8_29_89']");
    private By otherOfficeSuite = By.xpath("//input[@id='product_attribute_74_8_29_90']");
    private By itemName = By.xpath("//h1[@itemprop='name']");
    private By itemPrice = By.xpath("//span[@itemprop='price']");
    private By cartQty = By.xpath("//span[@class='cart-qty']");
    public GoodsPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    public int goodsOnPage (){
        List<WebElement> goodsList = new ArrayList<>();
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[@class='item-box']")));
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
    public GoodsPage selectProcessorFast (){
        driver.findElement(typeProcessorFast).click();
        return this;
    }
    public GoodsPage selectRAM8Gb (){
        driver.findElement(ram8gb).click();
        return this;
    }
    public GoodsPage selectAllSoftware (){
        driver.findElement(imageViewer).click();
        driver.findElement(officeSuite).click();
        driver.findElement(otherOfficeSuite).click();
        return this;
    }

    public GoodsPage addToCart() {
        wait.until(ExpectedConditions.visibilityOfElementLocated(cartButton));
        driver.findElement(cartButton).click();
        wait.until(ExpectedConditions.visibilityOfElementLocated(addToCartBTN));
        driver.findElement(addToCartBTN).click();
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//span[@class='close']")));
        driver.findElement(By.xpath("//span[@class='close']")).click();
                return this;
    }
    public CartPage openCart(){
        driver.findElement(shoppingCart).click();
        return new CartPage(driver,wait);
    }
    public boolean  checkTheShoppingCartHasPlus1Item (){
        Integer qty = Integer.parseInt(driver.findElement(cartQty).getText().toString().replace("(","" ).replace(")",""));
        if (qty>0){
            return true;
        }
        return false;
    }
    public String getItemName (){
        return driver.findElement(itemName).getText();
    }
    public String getItemPrice (){
        return driver.findElement(itemPrice).getText();
    }
}