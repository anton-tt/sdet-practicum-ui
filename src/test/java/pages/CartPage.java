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
    private final By productNameLink = By.cssSelector("td.align_left a");
    private final By updateButton = By.id("cart_update");
    private final By totalPriceLocator = By.cssSelector("span.bold.totalamout");
    private final By shippingPriceLocator = By.cssSelector("#totals_table tr:nth-of-type(2) td:nth-of-type(2) span");
    private final By removeButton = By.cssSelector("a[href*='remove=']");

    @Step("Получить из корзины построчно данные товаров")
    public List<WebElement> getCartRows() {
        return waitForAllElements(cartRows)
                .stream()
                .filter(row -> !row.findElements(productNameLink).isEmpty())
                .toList();
    }

    public CartItem mapRowToItem(WebElement row) {
        List<WebElement> cells = row.findElements(By.tagName("td"));

        String name = getProductBaseName(row);
        double unitPrice = parsePrice(cells.get(3).getText());
        int quantity = Integer.parseInt(
                cells.get(4).findElement(By.tagName("input")).getAttribute("value")
        );
        double totalPrice = parsePrice(cells.get(5).getText());

        return new CartItem(name, unitPrice, quantity, totalPrice);
    }

    @Step("Получить товары в виде объектов")
    public List<CartItem> getCartItems() {
        return getCartRows()
                .stream()
                .map(this::mapRowToItem)
                .toList();
    }

    @Step("Получить самый дешёвый товар")
    public CartItem getCheapestItem() {
        return getCartItems()
                .stream()
                .min(Comparator.comparing(CartItem::getUnitPrice))
                .orElseThrow(() -> new RuntimeException("Корзина пуста"));
    }

    @Step("Найти в корзине данные товара по его имени")
    public WebElement findRowByProductName(String productName) {
        return getCartRows()
                .stream()
                .filter(row -> getProductBaseName(row).equals(productName))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Товар не найден в корзине: " + productName));
    }

    @Step("Изменить количество товара")
    public void changeQuantity(String productName, int newQuantity) {
        WebElement row = findRowByProductName(productName);
        WebElement qtyInput = row.findElement(By.cssSelector("td input"));
        qtyInput.clear();
        qtyInput.sendKeys(String.valueOf(newQuantity));
    }

    @Step("Обновить корзину и дождаться перерисовки")
    public void updateCart() {
        driver.findElement(updateButton).click();
        waitForAllElements(cartRows);
    }

    @Step("Получить итоговую стоимость заказа")
    public double getTotalPrice() {
        return waitForAllElements(totalPriceLocator)
                .stream()
                .map(WebElement::getText)
                .filter(text -> text.contains("$"))
                .map(this::parsePrice)
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Итоговая цена не найдена"));
    }

    @Step("Рассчитать ожидаемую стоимость товаров")
    public double calculateSubtotal() {
        return getCartItems()
                .stream()
                .mapToDouble(CartItem::getExpectedTotal)
                .sum();
    }

    @Step("Получить стоимость доставки")
    public double getShippingPrice() {
        return parsePrice(find(shippingPriceLocator).getText());
    }

    @Step("Рассчитать ожидаемую стоимость заказа")
    public double calculateExpectedTotal() {
        return calculateSubtotal() + getShippingPrice();
    }

    private double parsePrice(String text) {
        return Double.parseDouble(text.replace("$", "").trim());
    }

    private String getProductBaseName(WebElement row) {
        return row.findElement(productNameLink).getText().trim();
    }

    public void removeItemByIndex(int index) {
        List<WebElement> rows = getCartRows();
        WebElement row = rows.get(index - 1);
        row.findElement(removeButton).click();
    }

    @Step("Удалить все чётные товары из корзины")
    public void removeEvenItems() {
        List<WebElement> rows = getCartRows();
        for (int i = rows.size(); i >= 1; i--) {
            if (i % 2 == 0) {
                removeItemByIndex(i);
            }
        }
    }

}
