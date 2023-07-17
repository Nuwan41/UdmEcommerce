package nTech.SeleniumFrameworkDesign;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;

import nTech.SeleniumFrameworkDesign.pageobjects.CartPage;
import nTech.SeleniumFrameworkDesign.pageobjects.CheckoutPage;
import nTech.SeleniumFrameworkDesign.pageobjects.OrderConfirmationPage;
import nTech.SeleniumFrameworkDesign.pageobjects.OrderPage;
import nTech.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;
import nTech.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	

		@Test(dataProvider="getData",groups= {"Purchase"})
		public void submitOrder(HashMap<String,String> input) throws IOException {		
				
			
		
		ProductCatalogue productCatalogue=landingPage.loginApplication(input.get("email"), input.get("password"));  
//		ProductCatalogue productCatalogue=landingPage.loginApplication("nuwan@gmail.com", "Jelly@123");  
		productCatalogue.addProductToCart(input.get("product"));		//		Adding given product to cart
		
		CartPage cartPage=productCatalogue.clickCartButton();
		boolean match=cartPage.verifyCartProductMatches(input.get("product"));		
		AssertJUnit.assertTrue(match);		
		
		CheckoutPage checkoutPage=cartPage.goToCheckout();			
		checkoutPage.typeInSelectBox("united");		
		checkoutPage.selectCountry("United States");		
		checkoutPage.typeInNameOnCardTextBox("Nuwan Weeraratne");		
		
		OrderConfirmationPage confirmationpage=checkoutPage.clickPlaceOrderButton();	
		String confirmMessage=confirmationpage.getOrderConfirmation();		
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	
	}
		@Test(dependsOnMethods= {"submitOrder"})
		public void OrderHistoryTest() 
		{
			String productName="ADIDAS ORIGINAL";				

			ProductCatalogue productCatalogue=landingPage.loginApplication("nuwan@gmail.com", "Jelly@123");
			OrderPage ordersPage=productCatalogue.clickOrdersButton();
			
			Assert.assertTrue(ordersPage.verifyOrderDisplay(productName));				
		}
//		public String getScreenshot(String testCaseName) throws IOException {
//			TakesScreenshot ts=(TakesScreenshot)driver;
//			File source=ts.getScreenshotAs(OutputType.FILE);
//			File file=new File(System.getProperty("user.dir")+"\\reports"+testCaseName+".png");
//			FileUtils.copyFile(source, file);
//			
//			return System.getProperty("user.dir")+"\\reports"+testCaseName+".png";
//		}
		
		@DataProvider
		public Object[][] getData() throws IOException {
//			HashMap<String,String> map=new HashMap<String,String>();
//			map.put("email", "nuwan@gmail.com");
//			map.put("password", "Jelly@123");
//			map.put("product", "ADIDAS ORIGINAL");
//			
//			HashMap<Object,Object> map1=new HashMap<Object,Object>();
//			map1.put("email", "nethkantha@gmail.com");
//			map1.put("password", "Kitkat123");
//			map1.put("product", "ZARA COAT 3");
			
//			return new Object[][] {{map},{map1}};
			
			List<HashMap<String, String>> data =getJsonDataToMap(System.getProperty("user.dir")+"\\src\\test\\java\\nTech\\data\\PurchaseOrder.json");
			
			return new Object[][] {{data.get(0)},{data.get(1)}};
		}
		
}
