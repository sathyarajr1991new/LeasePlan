### Please use endpoint GET https://waarkoop-server.herokuapp.com/api/v1/search/demo/{product} for getting the products.
### Available products: "orange", "apple", "pasta", "cola"
### Prepare Positive and negative scenarios

Feature: Search for the product

 @POSITIVE
Scenario Outline: As a API user, when I call valid product endpoints, I should get corresponding values
	When User call "<product>" endpoint
	Then User should see the response code as <status>
	And User should see the results displayed for "<product>"
	And Verify schema should match with the specification defined in "<jsonSchema>"
	Examples:
  	| product|status|jsonSchema	   		|
  	| orange |200	|search_product.json|
  	| apple  |200	|search_product.json|
  	| pasta  |200  	|search_product.json|
  	| cola   |200   |search_product.json|

  @NEGATIVE1
  Scenario Outline: As a API user, when I call invalid endpoints, I should get not found message in the response
    When User call "<product>" endpoint
    Then User should get the response has not found
    Examples:
    | product |
    | car     |

 @NEGATIVE2
  Scenario: Search test without product
    When User call the get search test endpoint
    And User should get unauthorized error in search result
