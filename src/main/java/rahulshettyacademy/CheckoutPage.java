package rahulshettyacademy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent  {
	WebDriver driver;
	 
	

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(xpath = "//*[@placeholder=\"Select Country\"]")
	WebElement countryDrpdown;
	@FindBy(css = ".action__submit ")
	WebElement placeOrder;
	 
	 
	 @FindBy(css = ".list-group")
	 List<WebElement> countryList;
	 
	 By list = By.cssSelector(".list-group");
public ConfirmationPage placeOrder(String country) throws InterruptedException {
	countryDrpdown.sendKeys(country);
	waitFOrElementToAppear(list);
   
    for(WebElement countryValue : countryList) {
    	System.out.println(countryValue.getText());
    	if(countryValue.getText().equalsIgnoreCase("Indonesia"))
    		System.out.println(countryValue);
    	
    	countryValue.click();	
    
    }
    
    placeOrder.click();
    ConfirmationPage confirmPage = new ConfirmationPage(driver);
    return confirmPage;
    
}
	 
}
