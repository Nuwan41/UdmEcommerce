package nTech.SeleniumFrameworkDesign.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nTech.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class OrderConfirmationPage extends AbstractComponent {
	WebDriver driver;

	public OrderConfirmationPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".hero-primary")
	WebElement orderConfirmationMessageWE;
	
	public String getOrderConfirmation() {
		String confirmMessage=orderConfirmationMessageWE.getText();
//		String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		return confirmMessage;
	}

}
