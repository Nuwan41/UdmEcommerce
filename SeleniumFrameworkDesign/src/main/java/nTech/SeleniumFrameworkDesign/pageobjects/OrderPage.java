package nTech.SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import nTech.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class OrderPage extends AbstractComponent  {
WebDriver driver;

@FindBy(css="tr td:nth-child(3)")
List<WebElement> productNames;

	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	
//	
//	@FindBy(css="li[class='totalRow'] button")
//	WebElement checkout;
//	
	
//	public List<WebElement> getCartProductList(){
//		return cartProducts;
		
//	}
	public boolean verifyOrderDisplay(String productName) {
		boolean match=productNames.stream().anyMatch(Product->Product.getText().equalsIgnoreCase(productName));		
		
		return match;
	}
	
	
	
	

}
