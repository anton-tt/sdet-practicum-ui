package tests;

import base.BaseTest;

import enums.SortOption;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

public class FirstTest extends BaseTest {

    @Test
    void sortByNameAscTest() {
        homePage.openMenCategory();
        homePage.selectSortOptionByValue(SortOption.NAME_ASC.getValue());

        List<String> actual = homePage.getProductNames();
        List<String> expected = actual.stream().sorted().toList();

        Assertions.assertEquals(expected, actual, "Сортировка по имени A-Z некорректна");
    }

    @Test
    void sortByNameDescTest() {
        homePage.openMenCategory();
        homePage.selectSortOptionByValue(SortOption.NAME_DESC.getValue());

        List<String> actual = homePage.getProductNames();
        List<String> expected = actual.stream().sorted(Comparator.reverseOrder()).toList();

        Assertions.assertEquals(expected, actual, "Сортировка по имени Z-A некорректна");
    }

    @Test
    void sortByPriceAscTest() {
        homePage.openMenCategory();
        homePage.selectSortOptionByValue(SortOption.PRICE_ASC.getValue());

        List<Double> actual = homePage.getProductPrices();
        List<Double> expected = actual.stream().sorted().toList();

        Assertions.assertEquals(expected, actual, "Сортировка по цене Low>High некорректна");
    }

    @Test
    void sortByPriceDescTest() {
        homePage.openMenCategory();
        homePage.selectSortOptionByValue(SortOption.PRICE_DESC.getValue());

        List<Double> actual = homePage.getProductPrices();
        List<Double> expected = actual.stream().sorted(Comparator.reverseOrder()).toList();

        Assertions.assertEquals(expected, actual, "Сортировка по цене High>Low некорректна");
    }

}
