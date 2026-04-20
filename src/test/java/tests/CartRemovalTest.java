package tests;

import base.BaseTest;
import io.qameta.allure.Epic;
import io.qameta.allure.Feature;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import pages.CartPage;
import pages.ProductPage;
import utils.Utils;

import java.util.List;

@Epic("UI тесты")
@Feature("Проверка корзины.")
public class CartRemovalTest extends BaseTest {

    @Test
    void shouldRemoveEvenProductsFromCartAndValidateTotal() {
        ProductPage productPage = new ProductPage(driver);
        CartPage cartPage = new CartPage(driver);

        List<Integer> randomProducts = Utils.getRandomUniqueNumbers(1, 16, 5);
        for (Integer index : randomProducts) {
            homePage.open();
            homePage.openProductByIndex(index);
            int quantity = Utils.getRandomQuantity(1, 5);
            productPage.setQuantity(quantity);
            productPage.addToCart();
        }
        cartPage.removeEvenItems();

        double expectedTotal = cartPage.calculateExpectedTotal();
        double actualTotal = cartPage.getTotalPrice();
        Assertions.assertEquals(
                expectedTotal,
                actualTotal,
                0.01,
                "Итоговая сумма корзины рассчитана неверно"
        );
    }

}
