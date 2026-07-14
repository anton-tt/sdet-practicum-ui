package base;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import pages.HomePage;

import java.net.MalformedURLException;
import java.net.URL;

public class BaseTest {
    protected WebDriver driver;
    protected HomePage homePage;

    @BeforeEach
    void setUp() throws MalformedURLException {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless=new");
        options.addArguments("--no-sandbox");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--window-size=1920,1080");

        boolean remote = Boolean.parseBoolean(
                System.getProperty("remote", "false"));
        if (remote) {
            driver = new RemoteWebDriver(
                    new URL("http://selenoid:4444/wd/hub"),
                    options);
        } else {
            WebDriverManager.chromedriver().setup();
            driver = new ChromeDriver(options);
        }
        homePage = new HomePage(driver);
        homePage.open();
    }

    @AfterEach
    void tearDown() {
        if (driver != null) {
            driver.quit();
        }
    }

}

/*WebDriverManager.chromedriver().setup();
driver = new ChromeDriver(options);
//driver.manage().window().maximize();
homePage = new HomePage(driver);
homePage.open();*/