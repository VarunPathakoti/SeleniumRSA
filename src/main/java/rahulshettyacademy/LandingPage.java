package rahulshettyacademy;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import abstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent{
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id="userEmail")
	WebElement userName;
	
	@FindBy(id="userPassword")
	WebElement userPassword;
	
	@FindBy(id="login")
	WebElement loginBtn;
	
	
	public void goTo() {
		 driver.get("https://rahulshettyacademy.com/client");
	        driver.manage().window().maximize();
	}
	public ProductCatalogue loginApp(String email, String pwd) {
		userName.sendKeys(email);
		userPassword.sendKeys(pwd);
		loginBtn.click();
		 ProductCatalogue pc = new ProductCatalogue(driver);
		 return pc;
	}
}
