package tests;

import base.BaseTest;
import enums.SortOption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.ProductPage;
import pages.SearchPage;
import utils.Utils;

import java.util.List;

import static base.TestData.SEARCH_SHIRT;

public class SearchTest extends BaseTest {
    @Test
    void searchAndSortTest() {
        homePage.search(SEARCH_SHIRT);
        SearchPage searchPage = new SearchPage(driver);
        searchPage.sortBy(SortOption.NAME_ASC.getValue());

        List<String> actual = searchPage.getProductNames();
        List<String> expected = actual.stream()
                .sorted()
                .toList();
        Assertions.assertEquals(expected, actual, "Сортировка в поиске работает некорректно");
    }

    @Test
    void searchOpenProductTest() {
        homePage.search(SEARCH_SHIRT);
        SearchPage searchPage = new SearchPage(driver);
        searchPage.openProductByIndex(2);
    }

    private void searchAndSort(SearchPage searchPage, SortOption option) {
        homePage.search(SEARCH_SHIRT);
        searchPage.sortBy(option.getValue());
    }

    @Test
    void shouldAddProductsFromSearchToCart() {
        SearchPage searchPage = new SearchPage(driver);
        ProductPage productPage = new ProductPage(driver);

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
    }

}
