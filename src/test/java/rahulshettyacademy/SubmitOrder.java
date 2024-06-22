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
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import Resources.BaseTest;
import Resources.Retry;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrder extends BaseTest {


    @Test(dataProvider = "getData",retryAnalyzer = Retry.class)
    public void submitOrder(String email, String pwd) throws InterruptedException {
        String productName = "ADIDAS ORIGINAL";
        String country = "ind";
        String selectCountry = "Indonesia";

        LandingPage lp = launchApp(); // Ensure WebDriver is initialized before launching the app
        ProductCatalogue pc = lp.loginApp(email, pwd);
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
    
    @DataProvider
    public Object[][] getData() {
    	return new Object[][] {{"postuser.test@gmail.com","Postman@123"},{"postuser1.test@gmail.com","Postman@123"}};
    }

   

}
