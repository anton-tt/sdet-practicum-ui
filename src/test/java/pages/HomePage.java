package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

import static base.Urls.TEST_STORE_URL;

public class HomePage extends BasePage {
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final By menCategoryLink = By.xpath("//a[contains(@href,'path=58')]");
    private final By productCards = By.cssSelector(".thumbnail");
    private final By sortDropdown = By.id("sort");
    private final By productNamesLocator = By.cssSelector(".prdocutname");
    private final By productPricesLocator = By.cssSelector(".oneprice");
    private final By searchInput = By.id("filter_keyword");
    private final By searchButton = By.cssSelector(".button-in-search");
    private final By cartButton = By.cssSelector("a[href*='checkout/cart']");
    private final By logo = By.cssSelector("a.logo");

    @Step("Открыть главную страницу")
    public void open() {
        driver.get(TEST_STORE_URL);
    }

    @Step("Перейти на главную страницу через логотип")
    public void goHomeByLogo() {
        click(logo);
    }

    @Step("Открыть категорию Men")
    public void openMenCategory() {
        click(menCategoryLink);
    }

    @Step("Выбрать сортировку: {value}")
    public void selectSortOptionByValue(String value) {
        List<WebElement> oldProducts = waitForAllElements(productCards);
        Select select = new Select(waitForClickable(sortDropdown));
        select.selectByValue(value);
        wait.until(ExpectedConditions.stalenessOf(oldProducts.get(0)));
    }

    public List<String> getProductNames() {
        return waitForAllElements(productNamesLocator)
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(text -> !text.isBlank())
                .toList();
    }

    public List<Double> getProductPrices() {
        return waitForAllElements(productPricesLocator)
                .stream()
                .map(e -> e.getText().replace("$", "").trim())
                .filter(text -> !text.isBlank())
                .map(Double::parseDouble)
                .toList();
    }

    @Step("Передать в форму поиска запрос: {text}")
    public void search(String text) {
        clearAndType(searchInput, text);
        click(searchButton);
    }

    @Step("Найти товар {text} и открыть результаты поиска")
    public void searchProduct(String text) {
        search(text);
    }

    @Step("Открыть товар номер {index} на главной странице")
    public void openProductByIndex(int index) {
        List<WebElement> products = waitForAllElements(productNamesLocator);
        products.get(index - 1).click();
    }

    @Step("Открыть корзину через иконку")
    public void openCart() {
        click(cartButton);
    }

}
