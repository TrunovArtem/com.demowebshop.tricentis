package UI;

import org.junit.Assert;
import org.junit.Test;

public class Tests extends BasePage {

    @Test
    public void addMostExpensiveItemToCart() {
        mainPage.getMainPage();
        ItemPage itemPage = mainPage.getCategoryDesktops();
        itemPage.changeDisplay();
        itemPage.changeSort();
        int itemsOnPage = itemPage.itemsOnPage();
        Assert.assertEquals(4,itemsOnPage);
        itemPage.addToCart();
        CartPage cartPage = itemPage.openCart();
        String productNameInCart = cartPage.goodsInCart();
        Assert.assertEquals("Build your own expensive computer",productNameInCart);
    }

    @Test
    public  void removeItemFromCart (){
        ItemPage itemPage = mainPage.getItemPage();
        itemPage.selectProcessorFast();
        itemPage.selectRAM8Gb();
        itemPage.selectAllSoftware();
        itemPage.addToCart();
        Assert.assertTrue(itemPage.checkTheShoppingCartHasPlus1Item());
        String itemName = itemPage.getItemName();
        String itemPrice = itemPage.getItemPrice();
        CartPage cartPage = itemPage.openCart();
        Assert.assertEquals(itemName, cartPage.goodsInCart());
        Assert.assertEquals(itemPrice, cartPage.totalOrderPrice());
        cartPage.removeFromCart();
        Assert.assertTrue(cartPage.cartIsEmpty());
    }

}
