package nTech.SeleniumFrameworkDesign.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import nTech.SeleniumFrameworkDesign.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent {
	WebDriver driver; 
	
	public ProductCatalogue(WebDriver driver) {
//		Initialization
		super(driver);
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
	//PageFactory
	@FindBy(css=".mb-3")
	List<WebElement> products;	
	
//	@FindBy(css=".ng-animating")
//	@FindBy(css=".ng-star-inserted")
	@FindBy(css=".ngx-spinner-overlay")	
	WebElement ngAnimatingWE;
	
	@FindBy(css="#toast-container")
	WebElement toastMessageWE;
	
	
	//By locator for common all products
	By productsBy=By.cssSelector(".mb-3");
	//By locator for message after adding to cart
	By toastMessage=By.cssSelector("#toast-container");
	
//	By ngAnimating=By.cssSelector(".ng-animating");
	
	public List<WebElement> getProductList() {
		
		waitForElementToAppear(productsBy);
		
		return products;
	}
	public WebElement getProductByNameLegacy(String productName) {
//		 getProductByNameLegacy() Legacy Method Re coded by <NW> to replace Method getProductByName()
		WebElement product = null;
		for(WebElement p:getProductList()) {
			if(p.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)) {
				System.out.println("<<<<Powerd By>>>><<<<Legacy Method-getProductByNameLegacy()>>>>");
				product=p;
				break;				
			}
		}
//		for(int i=0;i<getProductList().size();i++) {
//			if(getProductList().get(i).findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)) {
//				System.out.println("<<<<Powerd By>>>><<<<Legacy Method-Tradional For Loop>>>>");
//				product=getProductList().get(i);
//				break;				
//			}
//		}
		
		
		return product;
	}
	
	public WebElement getProductByName(String productName) {
		WebElement prod =
				getProductList().stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);		
		
		return prod;
	}
	public void addProductToCart(String productName) {
		waitForElementToDisapear(toastMessageWE);
//		WebElement prod=getProductByName(productName);
		WebElement prod=getProductByNameLegacy(productName);    // getProductByNameLegacy() Legacy Method Re coded by <NW>
		
		prod.findElement(By.cssSelector(".card button:last-of-type")).click();		
		
		waitForElementToAppear(toastMessage);
		System.out.println("Wait for toastMessage Done");
//		waitForElementToDisapear(ngAnimating);
		/* //Commenting this method Since it takes too much time. 
		waitForElementToDisapear(ngAnimatingWE);
		*/
		System.out.println("Wait for ngAnimating dissapear Done");
		
	}

	
}
