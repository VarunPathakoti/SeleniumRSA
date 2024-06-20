package Resources;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.LandingPage;

public class BaseTest {
    protected WebDriver driver;

    public WebDriver initializeDriver() {
        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        return driver;
    }

    public LandingPage launchApp() {
        if (driver == null) {
            initializeDriver();
        }

        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        LandingPage lp = new LandingPage(driver);
        lp.goTo();
        return lp;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
}
