package tests;

import base.BaseTest;

import enums.SortOption;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import io.qameta.allure.Story;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.Comparator;
import java.util.List;

//@Epic("UI тесты")
//@Feature("Сортировка товаров в категории Men")
public class FirstTest extends BaseTest {

   /* @Test
    @Story("Сортировка по алфавиту: A-Z")
    void sortByNameAscTest() {
        homePage.openMenCategory();
        homePage.selectSortOptionByValue(SortOption.NAME_ASC.getValue());

        List<String> actual = homePage.getProductNames();
        List<String> expected = actual.stream().sorted().toList();

        Assertions.assertEquals(expected, actual, "Сортировка по имени A-Z некорректна");
    }

    @Test
    @Story("Сортировка по алфавиту: Z-A")
    void sortByNameDescTest() {
        homePage.openMenCategory();
        homePage.selectSortOptionByValue(SortOption.NAME_DESC.getValue());

        List<String> actual = homePage.getProductNames();
        List<String> expected = actual.stream().sorted(Comparator.reverseOrder()).toList();

        Assertions.assertEquals(expected, actual, "Сортировка по имени Z-A некорректна");
    }

    @Test
    @Story("Сортировка по цене: Low>High")
    void sortByPriceAscTest() {
        homePage.openMenCategory();
        homePage.selectSortOptionByValue(SortOption.PRICE_ASC.getValue());

        List<Double> actual = homePage.getProductPrices();
        List<Double> expected = actual.stream().sorted().toList();

        Assertions.assertEquals(expected, actual, "Сортировка по цене Low>High некорректна");
    }

    @Test
    @Story("Сортировка по цене: High>Low")
    void sortByPriceDescTest() {
        homePage.openMenCategory();
        homePage.selectSortOptionByValue(SortOption.PRICE_DESC.getValue());

        List<Double> actual = homePage.getProductPrices();
        List<Double> expected = actual.stream().sorted(Comparator.reverseOrder()).toList();

        Assertions.assertEquals(expected, actual, "Сортировка по цене High>Low некорректна");
    }*/

}
