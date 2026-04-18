package tests;

import base.BaseTest;
import enums.SortOption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.SearchPage;

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

}
