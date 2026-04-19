package tests;

import base.BaseTest;
import enums.SortOption;
import models.CartItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebElement;
import pages.CartPage;
import pages.ProductPage;
import pages.SearchPage;
import utils.Utils;

import java.util.List;

import static base.TestData.SEARCH_SHIRT;

public class SearchTest extends BaseTest {

    private void searchAndSort(SearchPage searchPage, SortOption option) {
        homePage.search(SEARCH_SHIRT);
        searchPage.sortBy(option.getValue());
    }

    @Test
    void shouldAddProductsFromSearchToCart() {
        SearchPage searchPage = new SearchPage(driver);
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        searchAndSort(searchPage, SortOption.NAME_ASC);
        searchPage.openProductByIndex(2);

        int qty2 = Utils.getRandomQuantity(1, 5);
        productPage.setQuantity(qty2);
        productPage.addToCart();

        searchAndSort(searchPage, SortOption.NAME_ASC);
        searchPage.openProductByIndex(3);

        int qty3 = Utils.getRandomQuantity(1, 5);
        productPage.setQuantity(qty3);
        productPage.addToCart();

        List<WebElement> cartRows = cartPage.getCartRows();
        Assertions.assertEquals(2, cartRows.size(), "Должно быть 2 строки");

        List<CartItem> cartItems = cartPage.getCartItems();
        Assertions.assertEquals(2, cartItems.size(), "Должно быть 2 объекта");
        CartItem item = cartPage.getCheapestItem();
        System.out.println(item.getUnitPrice());
    }

}
