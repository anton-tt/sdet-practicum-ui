package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class HomePage extends BasePage {
    private static final String TEST_STORE_URL = "https://automationteststore.com/";
    public HomePage(WebDriver driver) {
        super(driver);
    }

    private final By menCategoryLink = By.xpath("//a[contains(@href,'path=58')]");
    private final By productCards = By.cssSelector(".thumbnail");
    private final By sortDropdown = By.id("sort");
    private final By productNamesLocator = By.cssSelector(".prdocutname");
    private final By productPricesLocator = By.cssSelector(".oneprice");

    public void open() {
        driver.get(TEST_STORE_URL);
    }

    public void openMenCategory() {
        click(menCategoryLink);
    }

    public void selectSortOptionByValue(String value) {
        List<WebElement> oldProducts = waitForAllElements(productCards);
        Select select = new Select(waitForClickable(sortDropdown));
        select.selectByValue(value);
        wait.until(ExpectedConditions.stalenessOf(oldProducts.get(0)));
    }

    private List<WebElement> getProductNameElements() {
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(productNamesLocator)
        );
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

}
