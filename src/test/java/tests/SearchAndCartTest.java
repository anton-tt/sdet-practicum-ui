package tests;

import base.BaseTest;
import enums.SortOption;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import models.CartItem;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.ProductPage;
import pages.SearchPage;
import utils.Utils;

import static base.TestData.SEARCH_SHIRT;

//@Epic("UI тесты")
//@Feature("Проверка поисковой выдачи и корзины.")
public class SearchAndCartTest extends BaseTest {

    /*private void searchAndSort(SearchPage searchPage, SortOption option) {
        homePage.search(SEARCH_SHIRT);
        searchPage.sortBy(option.getValue());
    }

    @Test
    void shouldUpdateCheapestItemAndValidateCartTotal() {
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

        CartItem cheapest = cartPage.getCheapestItem();
        cartPage.changeQuantity(
                cheapest.getName(),
                cheapest.getDoubledQuantity()
        );
        cartPage.updateCart();

        double expectedTotal = cartPage.calculateExpectedTotal();
        double actualTotal = cartPage.getTotalPrice();
        Assertions.assertEquals(
                expectedTotal,
                actualTotal,
                0.01,
                "Итоговая сумма корзины рассчитана неверно"
        );
    }*/

}
