package leaseplan.actions;

import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;
import org.hamcrest.Matchers;

import io.restassured.RestAssured;
import leaseplan.common.EndPoint;
import leaseplan.pojo.product.Product;

import static net.serenitybdd.rest.SerenityRest.then;
import static org.assertj.core.api.Assertions.assertThat;

import java.util.Arrays;
import java.util.List;

/**
 * The SearchProductActions class contains methods for testing the search product functionality of the API.
 *  @author Sathya 
 */
public class SearchProductActions {
	
	static {
		RestAssured.basePath = EndPoint.SEARCH.toString();
	}
	
	/**
	This method calls the search endpoint with the given product parameter.
	It returns a Response object representing the HTTP response from the endpoint.
	@param product the product parameter to send in the search request
	@return a Response object representing the HTTP response from the endpoint
	*/
	@Step("^User call {string} endpoint$")
	public void UserCallEndpoint(String product) {	
		SerenityRest.get(product);
	}
	
	/**
	*Verifies that the HTTP response contains the expected product.
	*@param product the product to look for in the HTTP response
	*@throws AssertionError if the expected product is not present in the HTTP response
	*/
	@Step("^User see the results displayed for {string}$")
	public void UserSeeTheResultsDisplayedFor(String product) {
		String failMessage= "The endpoint for "+product+ " product"
				+ "\n\t is expecting to contains "+product+" in all results title"
				+ "\n\tBut some of the title could not found.";		
		List<Product> products = Arrays.asList(then().extract().as(Product[].class));
		assertThat(products).extracting(prod->prod.getTitle()
												  .toString()
												  .toLowerCase())
							.as(failMessage)
							.contains(product);
	}
	
	/**
	*Verifies that the HTTP response for product which is not found.
	*@throws AssertionError if the expected body message not contains in the HTTP response
	*/
	@Step("^User see the results displayed for not found$")
	public void userShouldGetTheResponse() {
		assertThat(then().statusCode(404));
		String message = "Not found";
		assertThat(then().body("detail.message", Matchers.is(message)));
	}

	/**
	*This method calls the search endpoint without the product provided.
	*It returns a Response with a detail message.
	@return a Response object representing the HTTP response from the endpoint
	*/
	@Step("^User call the get search test endpoint$")
	public void userCallTheGetSearchTestEndpoint() {
		SerenityRest.get();
	}

	/**
	*Verifies that the HTTP response with a detail message for search endpoint
	*without the product.
	@return a Response object representing the HTTP response from the endpoint
	*/
	@Step("^User should get unauthorized error in search result")
	public void userShouldGetUnauthorizedErrorInSearchResult() {
		assertThat(then().statusCode(401));
		String message = "Not authenticated";
		assertThat(then().body("detail", Matchers.is(message)));
	}
}
