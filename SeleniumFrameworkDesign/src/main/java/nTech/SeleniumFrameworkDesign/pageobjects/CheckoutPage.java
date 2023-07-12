package nTech.SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nTech.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
WebDriver driver;
	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);		
	}
	
	@FindBy(css="input[placeholder='Select Country']")
	WebElement SelectCountry;
	
	@FindBy(css=".ng-star-inserted button span")
	List<WebElement> dropdownCountries;
	
	@FindBy(xpath="//div[text()='Name on Card ']/following-sibling::input[1]")
	WebElement NameOnCardTxt;
	
	@FindBy(css=".action__submit")
	WebElement btnPlaceOrder;
//	driver.findElement(By.cssSelector(".action__submit ")).click();
	
	
	public void typeInSelectBox(String serachText) {
		SelectCountry.sendKeys(serachText);
		
	}
	public void typeInNameOnCardTextBox(String typeTxt) {
		NameOnCardTxt.sendKeys(typeTxt);
	}
	
	public void selectCountry(String StrCountry) {
		
//		List<WebElement> dropdownCountries=driver.findElements(By.cssSelector(".ng-star-inserted button span"));
		
		for(WebElement Country:dropdownCountries ) {
			if(Country.getText().equalsIgnoreCase(StrCountry)) {
				Country.click();
				break;
			}
		}
		
	}
	public OrderConfirmationPage clickPlaceOrderButton() {
		btnPlaceOrder.click();
		OrderConfirmationPage confirmationpage=new OrderConfirmationPage(driver);
		
		return confirmationpage;
	}
//	driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("united");

}
