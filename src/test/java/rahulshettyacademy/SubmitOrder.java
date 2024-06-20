package rahulshettyacademy;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;


import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import Resources.BaseTest;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrder extends BaseTest {
	@BeforeMethod
    public void setUp() {
        initializeDriver(); // Initialize WebDriver before each test method
    }

    @Test
    public void submitOrder() throws InterruptedException {
        String productName = "ADIDAS ORIGINAL";
        String country = "ind";
        String selectCountry = "Indonesia";

        LandingPage lp = launchApp(); // Ensure WebDriver is initialized before launching the app
        ProductCatalogue pc = lp.loginApp("postuser.test@gmail.com", "Postman@123");
        List<WebElement> products = pc.getProducts();
        pc.getProductandAdd(productName);
        CartPage cp = pc.ClickOnCart();
        Boolean match = cp.validatingOrderNo(productName);
        CheckoutPage checkOut = cp.checkOut();
        ConfirmationPage confirmPage = checkOut.placeOrder(country);
        Thread.sleep(10000);
        String expectedConfirmation = confirmPage.getOrderConfirmation();
        //assertTrue(match);
       // assertTrue(expectedConfirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));

        
    }

    @AfterMethod
    public void tearDown() {
        quitDriver(); // Quit WebDriver after each test method
    }

}