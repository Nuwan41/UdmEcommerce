package nTech.SeleniumFrameworkDesign;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest_B {

	public static void main(String[] args) {
		
		String productName="IPHONE 13 PRO";
//		IPHONE 13 PRO,ADIDAS ORIGINAL
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		
		driver.findElement(By.id("userEmail")).sendKeys("nuwan@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Jelly@123");	
		
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mb-3"))));
		
//		CSS
//		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
//		XPATH
		List<WebElement> products=driver.findElements(By.xpath("//div[contains(@class,'mb-3')]"));		

		
		for(WebElement prod:products) {


			if(prod.findElement(By.cssSelector("b")).getText().equalsIgnoreCase(productName)) {
				

//				prod.findElement(By.cssSelector("button:nth-of-type(2)")).click();

				prod.findElement(By.xpath(".//button[2]")).click();				
				
				break;
				
			}
		}
		
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
		
//		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		driver.findElement(By.xpath("//button[contains(@routerlink,'cart')]")).click();
//		//button[contains(@routerlink,'cart')]
		
		
		
//		System.out.println("Cart Text"+driver.findElement(By.cssSelector(".cartSection h3")).getText());

		
//		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		
		List<WebElement> cartProducts=driver.findElements(By.xpath("//div[@class='cartSection']/h3"));
		
		for(int i=0;i<cartProducts.size();i++) {
			
			if(cartProducts.get(i).getText().equalsIgnoreCase(productName)) {
				boolean match=cartProducts.get(i).getText().equalsIgnoreCase(productName);				
				Assert.assertTrue(match);		
				System.out.println("Elements "+cartProducts.get(i).getText());
				break;
			}
			 
		}
		
		/*
		
		Boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		*/
		

	}

}
