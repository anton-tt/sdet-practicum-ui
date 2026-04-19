package pages;

import io.qameta.allure.Step;
import models.CartItem;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.Comparator;
import java.util.List;

public class CartPage extends BasePage {
    public CartPage(WebDriver driver) {
        super(driver);
    }

    private final By cartRows = By.cssSelector("#cart table tbody tr");

    @Step("Получить товары из корзины в виде строк")
    public List<WebElement> getCartRows() {
        return driver.findElements(cartRows)
                .stream()
                //.filter(row -> row.findElements(By.tagName("td")).size() == 7)
                .filter(row -> !row.findElements(By.cssSelector("td.align_left a")).isEmpty())
                .toList();
    }

    public CartItem mapRowToItem(WebElement row) {
        List<WebElement> cells = row.findElements(By.tagName("td"));

        String name = cells.get(1).getText().trim();
        double unitPrice = Double.parseDouble(
                cells.get(3).getText().replace("$", "").trim()
        );
        int quantity = Integer.parseInt(
                cells.get(4).findElement(By.tagName("input")).getAttribute("value")
        );
        double totalPrice = Double.parseDouble(
                cells.get(5).getText().replace("$", "").trim()
        );

        return new CartItem(name, unitPrice, quantity, totalPrice);
    }

    @Step("Получить товары из корзины")
    public List<CartItem> getCartItems() {
        return getCartRows()
                .stream()
                .map(this::mapRowToItem)
                .toList();
    }

    @Step("Найти самый дешевый товар в корзине")
    public CartItem getCheapestItem() {
        return getCartItems()
                .stream()
                .min(Comparator.comparing(CartItem::getUnitPrice))
                .orElseThrow(() -> new RuntimeException("Корзина пуста"));
    }

}
