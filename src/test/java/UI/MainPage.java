package UI;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

public class MainPage {
    private WebDriver driver;
    private WebDriverWait wait;
    private By categoryComputersPath = By.xpath("//div[@class='block block-category-navigation']/div/ul/li/a[@href='/computers']");
    private By categoryDesktopsPath = By.xpath("//a[@title='Show products in category Desktops']");
    String BaseUrl = "http://demowebshop.tricentis.com/";
    public MainPage(WebDriver driver, WebDriverWait wait) {
        this.driver = driver;
        this.wait = wait;
    }
    public void getMainPage() {
        driver.get(BaseUrl);
    }
    public ItemPage getItemPage() {
        driver.get("http://demowebshop.tricentis.com/build-your-own-expensive-computer-2");
        return new ItemPage(driver, wait);
    }
    public ItemPage getCategoryDesktops (){
        driver.findElement(categoryComputersPath).click();
        driver.findElement(categoryDesktopsPath).click();
        return  new ItemPage(driver,wait);
    }

}
