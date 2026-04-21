package pages;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class SearchPage extends BasePage {
    public SearchPage(WebDriver driver) {
        super(driver);
    }

    private final By productNamesLocator = By.cssSelector(".prdocutname");
    private final By sortDropdown = By.id("sort");

    @Step("Сортировка товаров в поисковой выдаче: {value}")
    public void sortBy(String value) {
        Select select = new Select(waitForClickable(sortDropdown));
        select.selectByValue(value);
    }

    @Step("Открыть товар номер {index} из поисковой выдачи")
    public void openProductByIndex(int index) {
        List<WebElement> products = waitForAllElements(productNamesLocator);
        products.get(index - 1).click();
    }

}
