package leaseplan.stepdefinitions;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import leaseplan.actions.CommonActions;
import leaseplan.actions.SearchProductActions;
import net.thucydides.core.annotations.Steps;

/**
 * @author Sathya 
 * 
 */
public class SearchProductSteps {

	@Steps
	public CommonActions commonActions;

	@Steps
	public SearchProductActions searchProductActions;

	@When("^User call \"(.*)\" endpoint$")
	public void iCallTheGetSearchProductEndpoint(String product) {
		searchProductActions.UserCallEndpoint(product);
	}

	@When("^User call the get search test endpoint")
	public void iCallTheGetSearchTestEndpoint() {
		searchProductActions.userCallTheGetSearchTestEndpoint();
	}

	@Then("^User should see the response code as (.*)$")
	public void theSearchResultsOfProductShouldBeDisplayed(int responseCode){
		commonActions.userShouldGetTheResponseCode(responseCode);
	}

	@Then("^Verify the product list should not be empty in Search results")
	public void theProductShouldBeDisplayedInSearchResults() {
		commonActions.responseShouldNotBeEmptyList();
	}

	@Then("^User should see the results displayed for \"(.*)\"$")
	public void theProductShouldBedInSearchResults(String product) {
		searchProductActions.UserSeeTheResultsDisplayedFor(product);
	}

	@Then("^User should get the response has not found")
	public void notFoundErrorShouldBeDisplayedInSearchResult() {
		searchProductActions.userShouldGetTheResponse();
	}

	@Then("^User should get unauthorized error in search result")
	public void unauthorizedErrorShouldBeDisplayedInSearchResult() {
		searchProductActions.userShouldGetUnauthorizedErrorInSearchResult();
	}

	@And("^Verify schema should match with the specification defined in \"(.*)\"$")
	public void the_schema_should_match_with_the_specification(String spec) {
		commonActions.verifyResponseSchema(spec);
	}
}
