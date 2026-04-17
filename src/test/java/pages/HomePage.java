package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;
import java.util.List;

public class HomePage {
    private WebDriver driver;
    private WebDriverWait wait;
    private static final String TEST_STORE_URL = "https://automationteststore.com/";
    public static final String NAME_ASC = "pd.name-ASC";
    public static final String NAME_DESC = "pd.name-DESC";
    private final By menCategoryLink = By.xpath("//a[contains(@href,'path=58')]");
    private final By sortDropdown = By.id("sort");
    private final By productNamesLocator = By.cssSelector(".prdocutname");

    public HomePage(WebDriver driver) {
        this.driver = driver;
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(10));
    }

    public void open() {
        driver.get(TEST_STORE_URL);
    }

    public void openMenCategory() {
        wait.until(ExpectedConditions.elementToBeClickable(menCategoryLink)).click();
    }

    public void selectSortOptionByValue(String value) {
        WebElement dropdown = wait.until(
                ExpectedConditions.elementToBeClickable(sortDropdown)
        );
        Select select = new Select(dropdown);
        select.selectByValue(value);
    }

    public void sortByNameAsc() {
        selectSortOptionByValue(NAME_ASC);
    }

    public void sortByNameDesc() {
        selectSortOptionByValue(NAME_DESC);
    }

    private List<WebElement> getProductNameElements() {
        return wait.until(
                ExpectedConditions.presenceOfAllElementsLocatedBy(productNamesLocator)
        );
    }

    public List<String> getProductNames() {
        return getProductNameElements()
                .stream()
                .map(WebElement::getText)
                .map(String::trim)
                .filter(text -> !text.isBlank())
                .toList();
    }

}
