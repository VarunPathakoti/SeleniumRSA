package rahulshettyacademy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver;
	//String productName = "ADIDAS ORIGINAL";

	public ProductCatalogue(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "card-body")
	List<WebElement> products;

	By productsBy = By.className("card-body");
	By toastMsg = By.id("toast-container");
	public List<WebElement> getProducts() {
		waitFOrElementToAppear(productsBy);
		return products;
		}
		
	public void getProductandAdd(String productName) throws InterruptedException {
		 for (WebElement product : products) {
	            System.out.println("-------");
	            System.out.println(product.getText());
	            if(product.getText().contains(productName)) {
	            	product.findElement(By.xpath("(//button[contains(text(),'Add To Cart')])[2]")).click();
	            	Thread.sleep(2000);
	            	break;
	            }
	        }
		 waitFOrElementToAppear(toastMsg);
		 
	}

	

}
