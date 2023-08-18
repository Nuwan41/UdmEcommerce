package nTech.stepDefinitions;

import java.io.IOException;

import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import nTech.SeleniumFrameworkDesign.pageobjects.CartPage;
import nTech.SeleniumFrameworkDesign.pageobjects.CheckoutPage;
import nTech.SeleniumFrameworkDesign.pageobjects.LandingPage;
import nTech.SeleniumFrameworkDesign.pageobjects.OrderConfirmationPage;
import nTech.SeleniumFrameworkDesign.pageobjects.ProductCatalogue;
import nTech.TestComponents.BaseTest;

public class StepDefinitionImpl extends BaseTest {
	public LandingPage landingPage;
	public ProductCatalogue productCatalogue;
	public OrderConfirmationPage confirmationpage;
	
	public CartPage cartPage;
	
	
	@Given("I landed on Ecommerce Page")
	public void I_landed_on_Ecommerce_Page() throws IOException {
		System.out.println("<N> This is a Cucumber Test <N>");
		landingPage=launchApplication();
	}
	
//	Logged in with username <name> and password <password> 
	@Given("^Logged in with username (.+) and password (.+)$")
	public void logged_in_with_username_and_password(String username,String password)
	{
		productCatalogue=landingPage.loginApplication(username,password); 
	}
	
	@When("^I add product (.+) from Cart$")
	public void i_add_product_from_Cart(String productName) {
		productCatalogue.addProductToCart(productName);
		
//		<N> Error validation codes
//		cartPage=productCatalogue.clickCartButton();
//		boolean match=cartPage.verifyCartProductMatches(productName);		
//		AssertJUnit.assertTrue(match);	
	}
	
//	 And Checkout <productName> and submit the order 
	
	@When("^Checkout (.+) and submit the order$")
	public void Checkout_and_submit_the_order(String productName) {
		
		cartPage=productCatalogue.clickCartButton();
		boolean match=cartPage.verifyCartProductMatches(productName);
//		Error tracking codes
//		boolean match=cartPage.verifyCartProductMatches(productName.trim());
//		System.out.println("<N> Error Tracking Service: "+productName );
//		System.out.println("<N> ETS Boolean: "+match );
		AssertJUnit.assertTrue(match);		
//		
		CheckoutPage checkoutPage=cartPage.goToCheckout();			
		checkoutPage.typeInSelectBox("united");		
		checkoutPage.selectCountry("United States");		
		checkoutPage.typeInNameOnCardTextBox("Nuwan Weeraratne");		
		
		confirmationpage=checkoutPage.clickPlaceOrderButton();	
		 
		
	}
	
//	Then I verify  "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage 
	
//	@Then("I verify {string} message is displayed on ConfirmationPage")
//	public void message_displayed_ConfirmationPage(String string) {
//		
//		String confirmMessage=confirmationpage.getOrderConfirmation();		
//		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase(string));
//		
//	}
	
	@Then("I verify  {string} message is displayed on ConfirmationPage")
	public void i_verify_message_is_displayed_on_confirmation_page(String string) {
		String confirmMessage=confirmationpage.getOrderConfirmation();		
		AssertJUnit.assertTrue(confirmMessage.equalsIgnoreCase(string));
		driver.close();
	}
	
	@Then("{string} message is displayed")
	public void i_verify_Incorrect_email_or_password_message_is_displayed(String string) {
		Assert.assertEquals(landingPage.getErrorMessage(), "Incorrect email or password.");
		driver.close();
	}

}
