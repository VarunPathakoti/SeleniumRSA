package rahulshettyacademy;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class ConfirmationPage extends AbstractComponent  {
	WebDriver driver;
	 
	

	public ConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	
	@FindBy(css = ".hero-primary")
	WebElement orderConfirmation;
	 
	//By msg = By.cssSelector(".hero-primary");
	 
	// String expectedConfirmation = orderConfirmation.getText();

     
     
public String getOrderConfirmation() {
	//waitFOrElementToAppear(msg);
	 String expectedConfirmation = orderConfirmation.getText();

	 System.out.println(expectedConfirmation);
    return expectedConfirmation;
}
	 
}
