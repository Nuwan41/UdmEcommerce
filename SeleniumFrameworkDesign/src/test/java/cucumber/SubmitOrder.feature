
@tag
Feature: Purchase the Order From Ecommerce Website
  I want to use this template for my feature file

	Background: 
	Given I landed on Ecommerce Page
  
  @Regression
  Scenario Outline: Title of your scenario outline
    Given Logged in with username <name> and password <password> 
    When I add product <productName> from Cart
    And Checkout <productName> and submit the order
    Then I verify  "THANKYOU FOR THE ORDER." message is displayed on ConfirmationPage

    Examples: 
      | name  								| password	| productName |
      | nuwan@gmail.com				| Jelly@123 | ZARA COAT 3	|
      