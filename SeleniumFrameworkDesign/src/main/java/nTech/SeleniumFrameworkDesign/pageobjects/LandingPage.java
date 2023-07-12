package nTech.SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nTech.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	WebDriver driver;
	
	public LandingPage(WebDriver driver) {
		super(driver);
//		Initialization
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	WebElement userEmail=driver.findElement(By.id("userEmail"));
	
	//PageFactory
	@FindBy(id="userEmail")
	WebElement userEmailWE;
	
	@FindBy(id="userPassword")
	WebElement passwordWE;
	
	@FindBy(id="login")
	WebElement submitWE;
	
	@FindBy(css="[class*='toast-message']")
	WebElement errorMessageWE;
	
	public ProductCatalogue loginApplication(String email,String password) {
		userEmailWE.sendKeys(email);
		passwordWE.sendKeys(password);
		submitWE.click();
		
		ProductCatalogue productCatalogue=new ProductCatalogue(driver);	
		return productCatalogue;
	}
	public String getErrorMessage() {
		waitForWebElementToAppear(errorMessageWE);
		return errorMessageWE.getText();
	}
	
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client");
	}	

}
