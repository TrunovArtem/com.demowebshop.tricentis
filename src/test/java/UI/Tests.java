package UI;

import org.junit.Assert;
import org.junit.Test;

public class Tests extends BasePage {
    @Test
    public void addMostExpensiveItemToCart() {
    mainPage.getMainPage();
    GoodsPage goodsPage = mainPage.getCategoryDesktops();
    goodsPage.changeDisplay();
    goodsPage.changeSort();
    int goodsOnPage = goodsPage.goodsOnPage();
    Assert.assertEquals(4,goodsOnPage);
    goodsPage.addToCart();
    CartPage cartPage = goodsPage.openCart();
    String productNameInCart = cartPage.goodsInCart();
    Assert.assertEquals("Build your own expensive computer",productNameInCart);
    }
    @Test
    public  void removeItemFromCart (){
    GoodsPage goodsPage = mainPage.getGoodsPage();
    goodsPage.selectProcessorFast();
    goodsPage.selectRAM8Gb();
    goodsPage.selectAllSoftware();
    goodsPage.addToCart();
    Assert.assertEquals(true,goodsPage.checkTheShoppingCartHasPlus1Item());
    String itemName = goodsPage.getItemName();
    String itemPrice = goodsPage.getItemPrice();
    CartPage cartPage = goodsPage.openCart();
    Assert.assertEquals(itemName, cartPage.goodsInCart());
    Assert.assertEquals(itemPrice, cartPage.totalOrderPrice());
    cartPage.removeFromCart();
    Assert.assertEquals(true, cartPage.cartIsEmpty());
    }
}
