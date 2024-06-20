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

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandaloneTest {

    public static void main(String[] args) throws InterruptedException {
        // Setup WebDriver
        WebDriverManager.chromedriver().setup();
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        String productName = "ADIDAS ORIGINAL";
        String country = "ind";
        LandingPage lp = new LandingPage(driver);
        // Implicit wait
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        
        // Navigate to the webpage
        driver.get("https://rahulshettyacademy.com/client");
        driver.manage().window().maximize();
        
        // Perform login
        driver.findElement(By.id("userEmail")).sendKeys("postuser.test@gmail.com");
        driver.findElement(By.id("userPassword")).sendKeys("Postman@123");
        driver.findElement(By.id("login")).click();
        
        // Wait for products to load (if needed)
        // Add more precise waiting logic here if necessary
        
        // Get list of products
        wait.until(ExpectedConditions.visibilityOfElementLocated(By.className("card-body")));
        List<WebElement> products = driver.findElements(By.className("card-body"));
        
        // Print product information
	        for (WebElement product : products) {
	            System.out.println("-------");
	            System.out.println(product.getText());
	            if(product.getText().contains(productName)) {
	            	product.findElement(By.xpath("(//button[contains(text(),'Add To Cart')])[2]")).click();
	            	Thread.sleep(2000);
	            	break;
	            }
	        }
	        
	       // WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	      //  wait.until(ExpectedConditions.invisibilityOfElementLocated(By.cssSelector(".ng-animating")));
	        wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("toast-container")));
	      	driver.findElement(By.xpath("//*[contains(text(),' Cart')]")).click();
        
        
        
        List<WebElement> cartProducts = driver.findElements(By.cssSelector(".cartSection h3"));
        for(WebElement cartProduct : cartProducts) {
        	Boolean match = cartProduct.getText().equalsIgnoreCase(productName);
        	System.out.println(cartProduct.getText());
        	assertTrue(match);	
        
        }
        
        driver.findElement(By.cssSelector(".totalRow button")).click();
        driver.findElement(By.xpath("//*[@placeholder=\"Select Country\"]")).sendKeys(country);
        List<WebElement> countryList = driver.findElements(By.cssSelector(".form-group section button"));
        for(WebElement countryValue : countryList) {
        	if(countryValue.getText().equalsIgnoreCase("Indonesia"))
        	countryValue.click();	
        
        }
        
        driver.findElement(By.cssSelector(".action__submit ")).click();
        String expectedConfirmation = driver.findElement(By.cssSelector(".hero-primary")).getText();
        System.out.println(expectedConfirmation);
       assertTrue(expectedConfirmation.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        // Close the browser
        Thread.sleep(3000);
        driver.quit();
    }
}
