package nTech.SeleniumFrameworkDesign;

import nTech.SeleniumFrameworkDesign.pageobjects.LandingPage;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import io.opentelemetry.exporter.logging.SystemOutLogRecordExporter;

public class StandAloneTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		String productName="ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		
		WebDriver driver=new ChromeDriver();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
		
		driver.get("https://rahulshettyacademy.com/client");
		LandingPage landingPage=new LandingPage(driver);
		
		driver.findElement(By.id("userEmail")).sendKeys("nuwan@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Jelly@123");	
		
		driver.findElement(By.id("login")).click();
		
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".mb-3"))));
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
		
		WebElement prod1 =
				products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName))
				.findFirst().orElse(null);
		
//		WebElement prod2 =
//				products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals("ZARA COAT 3"))
//				.findFirst().orElse(null);		
		
		
		prod1.findElement(By.cssSelector(".mb-3 button:last-of-type")).click();
//		prod2.findElement(By.cssSelector(".mb-3 button:last-of-type")).click();
				
		
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector("#toast-container"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		
//		.ng-animating
		
		driver.findElement(By.cssSelector("button[routerlink*='cart']")).click();
		
		System.out.println("Cart Text"+driver.findElement(By.cssSelector(".cartSection h3")).getText());
//		Xpath //*[@class='cartSection']//h3
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		
//		cartProducts.stream().filter(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		boolean match=cartProducts.stream().anyMatch(cartProduct->cartProduct.getText().equalsIgnoreCase(productName));
		
		Assert.assertTrue(match);
		
		driver.findElement(By.cssSelector("li[class='totalRow'] button")).click();
		
		//Type in Select country drop down box
		driver.findElement(By.cssSelector("input[placeholder='Select Country']")).sendKeys("united");
		
		List<WebElement> dropdownCountries=driver.findElements(By.cssSelector(".ng-star-inserted button span"));
		
		for(WebElement Country:dropdownCountries ) {
			if(Country.getText().equalsIgnoreCase("United States")) {
				Country.click();
				break;
			}
		}
		
		//filling credit card name
		driver.findElement(By.xpath("//div[text()='Name on Card ']/following-sibling::input[1]")).sendKeys("Nuwan Weeraratne");
				
		driver.findElement(By.cssSelector(".action__submit ")).click();
		
		String confirmMessage=driver.findElement(By.cssSelector(".hero-primary")).getText();
		
//		Assert.assertEquals(confirmMessage," Thankyou for the order. ");
//		
//		boolean b=confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER.");
		
		Assert.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		
//		System.out.println("Size of product web element list is "+products.size());		
		
//		System.out.println("Outside Element"+products.get(1).findElement(By.cssSelector("b")).getText());
	/*	
		for(WebElement p:products) {
			System.out.println("Element 1"+p.findElement(By.cssSelector("b")).getText());
		}
		*/		
		
//		products.stream().forEach(product->System.out.println(product.findElement(By.cssSelector("b")).getText()));
				
//		prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
		
//		.findFirst().orElse(null)
		
//		List<WebElement> products=driver.findElements(By.cssSelector(".card"));
		
//		products.get(1).findElement(By.xpath("//b")).getText();
		
//		System.out.println(driver.findElement(By.xpath("(//h5/b)[3]")).getText());
		
//		System.out.println(products.get(1).getText());
		
//		for(WebElement product:products) {
////			System.out.println(product.getText());
//			System.out.println(product.findElement(By.xpath("//b")).getText());
//		}		
//		.mb-3  .card	
		
		driver.close();

	}

}
