import org.junit.Assert;
import org.junit.Test;

public class Tests extends BasePage {
    @Test
    public void FirstTest(){
    mainPage.getMainPage();
    Assert.assertEquals(4,mainPage.getCategoryDesktops().changeDisplay().changeSort().goodsOnPage());
    Assert.assertEquals("Build your own expensive computer",mainPage.getCategoryDesktops().changeDisplay().changeSort().addToCart().openCart().goodsInCart());
    }

}
