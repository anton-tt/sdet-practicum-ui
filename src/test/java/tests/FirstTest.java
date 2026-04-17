package tests;

import base.BaseTest;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.HomePage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FirstTest extends BaseTest {

    @Test
    void sortByNameAscTest() {
        HomePage homePage = new HomePage(driver);
        homePage.open();

        homePage.openMenCategory();
        homePage.sortByNameAsc();
        List<String> actual = homePage.getProductNames();

        List<String> expected = new ArrayList<>(actual);
        Collections.sort(expected);

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void sortByNameDescTest() {
        HomePage homePage = new HomePage(driver);
        homePage.open();

        homePage.openMenCategory();
        homePage.sortByNameDesc();
        List<String> actual = homePage.getProductNames();

        List<String> expected = new ArrayList<>(actual);
        expected.sort(Collections.reverseOrder());

        Assertions.assertEquals(expected, actual);
    }

}
