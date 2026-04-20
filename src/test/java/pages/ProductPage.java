package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class ProductPage extends BasePage {
    public ProductPage(WebDriver driver) {
        super(driver);
    }

    private final By quantityInput = By.id("product_quantity");
    private final By addToCartButton = By.cssSelector(".cart");

    @Step("Установить количество товара: {qty}")
    public void setQuantity(int qty) {
        clearAndType(quantityInput, String.valueOf(qty));
    }

    @Step("Добавить товар в корзину")
    public void addToCart() {
        click(addToCartButton);
    }

    @Step("Выбрать все обязательные опции товара")
    public void selectRequiredOptionsIfPresent() {
        List<WebElement> options = waitForAllElements(
                By.cssSelector(".form-group input[type='radio']:not([disabled])")
        );
        for (WebElement option : options) {
            if (option.isDisplayed()) {
                option.click();
                return;
            }
        }
    }

}
