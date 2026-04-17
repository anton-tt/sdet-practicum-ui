package tests;

import base.BaseTest;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import java.util.Comparator;
import java.util.List;

public class FirstTest extends BaseTest {
    public static final String NAME_ASC = "pd.name-ASC";
    public static final String NAME_DESC = "pd.name-DESC";
    public static final String PRICE_ASC = "p.price-ASC";
    public static final String PRICE_DESC = "p.price-DESC";

    @Test
    void sortByNameAscTest() {
        HomePage homePage = new HomePage(driver);
        homePage.open();

        homePage.openMenCategory();
        homePage.selectSortOptionByValue(NAME_ASC);

        List<String> actual = homePage.getProductNames();
        List<String> expected = actual.stream()
                .sorted()
                .toList();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sortByNameDescTest() {
        HomePage homePage = new HomePage(driver);
        homePage.open();

        homePage.openMenCategory();
        homePage.selectSortOptionByValue(NAME_DESC);

        List<String> actual = homePage.getProductNames();
        List<String> expected = actual.stream()
                .sorted(Comparator.reverseOrder())
                .toList();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sortByPriceAscTest() {
        HomePage homePage = new HomePage(driver);
        homePage.open();

        homePage.openMenCategory();
        homePage.selectSortOptionByValue(PRICE_ASC);

        List<Double> actual = homePage.getProductPrices();
        List<Double> expected = actual.stream()
                .sorted()
                .toList();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sortByPriceDescTest() {
        HomePage homePage = new HomePage(driver);
        homePage.open();

        homePage.openMenCategory();
        homePage.selectSortOptionByValue(PRICE_DESC);

        List<Double> actual = homePage.getProductPrices();
        List<Double> expected = actual.stream()
                .sorted(Comparator.reverseOrder())
                .toList();

        Assertions.assertEquals(expected, actual);
    }

}
