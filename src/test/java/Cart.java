import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class Cart {
    private WebDriver driver;
    private WebDriverWait wait;
    public Cart(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    public String goodsInCart (){
        return driver.findElement(By.xpath("//a[@class='product-name']")).getText();
    }

    }