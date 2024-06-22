package Resources;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.LandingPage;

public class BaseTest {
    protected WebDriver driver;
public LandingPage lp;
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
        lp = new LandingPage(driver);
        lp.goTo();
        return lp;
    }

    public void quitDriver() {
        if (driver != null) {
            driver.quit();
            driver = null;
        }
    }
    
    public String getScreenshot(String testCaseName) throws IOException {	
    	TakesScreenshot ts = (TakesScreenshot)driver;
    	File src = ts.getScreenshotAs(OutputType.FILE);
    	File tgt = new File(System.getProperty("user.dir")+"//reports//"+testCaseName+".png");
    	FileUtils.copyFile(src, tgt);
    	return System.getProperty("user.dir")+"//reports//"+testCaseName+".png";
    	
    }
    
	@BeforeMethod
    public void setUp() {
        initializeDriver(); // Initialize WebDriver before each test method
    }
	
	 @AfterMethod
	    public void tearDown() {
	        quitDriver(); // Quit WebDriver after each test method
	    }
}
