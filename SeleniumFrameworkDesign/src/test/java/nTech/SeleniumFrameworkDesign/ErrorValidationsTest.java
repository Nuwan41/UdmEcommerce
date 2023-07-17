package nTech.SeleniumFrameworkDesign;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

//import com.aventstack.extentreports.ExtentReports;
//import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import nTech.SeleniumFrameworkDesign.pageobjects.CartPage;
import nTech.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;
import nTech.TestComponents.BaseTest;

public class ErrorValidationsTest extends BaseTest {
	
		@Test(groups= {"ErrorHandling"})
		public void LoginErrorValidation() throws IOException {		
		
		landingPage.loginApplication("nuwan@gmail.com", "JellyBean");	
//		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email password.");
				
		
//		Recycle code ByN
//		String productName="ADIDAS ORIGINAL";		
//		landingPage.getErrorMessage();		
//		AssertJUnit.assertEquals(landingPage.getErrorMessage(),"Incorrect email or password.");	
	}
		
		@Test(groups={"ErrorHandling"})
		public void ProductErrorValidation() throws IOException {		
				
		String productName="ADIDAS ORIGINAL";	
		
		ProductCatalogue productCatalogue=landingPage.loginApplication("nuwan@gmail.com", "Jelly@123");  
		productCatalogue.addProductToCart(productName);		//		Adding given product to cart
		
		CartPage cartPage=productCatalogue.clickCartButton();
		boolean match=cartPage.verifyCartProductMatches("ADIDAS ORIGINAL Backstage");		
//		AssertJUnit.assertFalse(match);	
		Assert.assertFalse(match);
		
	}
}
