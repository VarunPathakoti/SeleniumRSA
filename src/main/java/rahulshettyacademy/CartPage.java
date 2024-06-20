package rahulshettyacademy;



import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;
//import static org.junit.Assert.assertTrue;

public class CartPage extends AbstractComponent  {
	WebDriver driver;
	 
	//String productName = "ADIDAS ORIGINAL";

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css = ".cartSection h3")
	List<WebElement> cartProducts;
	 
	 
	 @FindBy(css = ".totalRow button")
		WebElement checkoutBtn;
	 
	 By checkOut = By.cssSelector(".totalRow button");
	 
	public Boolean validatingOrderNo(String productName) {
		 Boolean match = false; // Declare match outside the loop
		    
		    for(WebElement cartProduct : cartProducts) {
		        if (cartProduct.getText().equalsIgnoreCase(productName)) {
		            match = true;
		            break; // Exit the loop early if match is found
		        }
		        System.out.println(cartProduct.getText());	          
		    }
		    
		    return match;
	}
	
	public CheckoutPage checkOut() {
		waitFOrElementToAppear(checkOut);
		checkoutBtn.click();
		CheckoutPage checkOut = new CheckoutPage(driver);
		return checkOut;
	}
}
