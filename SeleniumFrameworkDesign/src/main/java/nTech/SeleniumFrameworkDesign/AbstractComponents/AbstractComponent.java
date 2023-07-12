package nTech.SeleniumFrameworkDesign.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import nTech.SeleniumFrameworkDesign.pageobjects.CartPage;
import nTech.SeleniumFrameworkDesign.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	
//	Cart button WebElement
	@FindBy(css="button[routerlink*='cart']")
	WebElement cartButton;
	
//	Orders WebElement
	@FindBy(css="button[routerlink*='myorders']")
	WebElement OrdersButton;


	public void waitForElementToAppear(By bylocator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		wait.until(ExpectedConditions.visibilityOfElementLocated(bylocator));
		
	}
	public void waitForWebElementToAppear(WebElement locator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		wait.until(ExpectedConditions.visibilityOf(locator));
		
	}
	
	
	
	
//	public void waitForElementToDisapear(By bylocator) {
//		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
//		wait.until(ExpectedConditions.invisibilityOfElementLocated(bylocator));
//		System.out.println("Old Web Element Method");
//		
//	}
	
	public void waitForElementToDisapear(WebElement locator) {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.invisibilityOf(locator));
		System.out.println("New WebElement Method is working");
		
	}
	
	public CartPage clickCartButton() {
		cartButton.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	public OrderPage clickOrdersButton() {
		OrdersButton.click();
		OrderPage ordersPage=new OrderPage(driver);
		return ordersPage;
	}
	
	
	
	
	
}
