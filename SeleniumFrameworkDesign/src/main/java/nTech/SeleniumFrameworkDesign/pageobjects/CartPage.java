package nTech.SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


import nTech.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent  {
WebDriver driver;
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css="li[class='totalRow'] button")
	WebElement checkout;
	
	
	public List<WebElement> getCartProductList(){
		return cartProducts;
		
	}
	public boolean verifyCartProductMatches(String productName) {
//		Error Tracking Starts
//		boolean match2;
//		System.out.println("<N> Start of Error Tracking Code");
//		List<WebElement> etsProducts=getCartProductList();
//		
//		for(WebElement etsProduct:etsProducts ) {
//			System.out.println(etsProduct.getText());
//			match2=etsProduct.getText().contentEquals(productName);
//			System.out.println("Bolean is"+ match2);
//		}
//		System.out.println("<N> End of Error Tracking Code");
		
//		Error Tracking Code Addtions end
		boolean match=getCartProductList().stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));		
		
		
		return match;
	}
	
	public CheckoutPage goToCheckout() {
		checkout.click();
		CheckoutPage checkoutPage=new CheckoutPage(driver);
		
		return checkoutPage;
	}
	
	public void errorhanding_verifyCartProductMatches(String productName) {
//		Error Tracking Starts
		
		boolean match2;
		System.out.println("<N> Start of Error Tracking Code");
		List<WebElement> etsProducts=getCartProductList();
		
		for(WebElement etsProduct:etsProducts ) {
			System.out.println(etsProduct.getText());
			match2=etsProduct.getText().contentEquals(productName);
			System.out.println("Bolean is"+ match2);
		}
		System.out.println("<N> End of Error Tracking Code");
		
//		Error Tracking Code Addtions end
	}
	
	

}
