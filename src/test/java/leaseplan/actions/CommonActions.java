package leaseplan.actions;

import net.thucydides.core.annotations.Step;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.HashMap;
import java.util.List;

import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchemaInClasspath;
import static net.serenitybdd.rest.SerenityRest.lastResponse;
import static net.serenitybdd.rest.SerenityRest.then;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * common used methods that 
 * can be reused for multiple case are written here
 * @author Sathya           
 */
public class CommonActions {

    private static final Logger LOGGER = LoggerFactory.getLogger(CommonActions.class);

    /**
	 * Verify the HTTP response is not empty
	 * @throws AssertionError if the response size is 0
	 */
    @Step("Verify that response list isn't an empty list")
    public void responseShouldNotBeEmptyList() {
        List<HashMap<String, Object>> res = lastResponse().getBody().jsonPath().getList("$");
        LOGGER.info("Response list size is {}", res.size());
        assertThat(res.size()).isNotZero();
    }

    /**
	 * Verifies the HTTP response schema of the API call against the provided schema JSON file.
	 * @param schemaJson the file name of the schema JSON file to be used for validation
	 * @throws AssertionError if the response schema does not match the expected schema
	 */

    @Step("Verify response schema with {string}")
    public void verifyResponseSchema(String schemaJson) {
    	assertThat(then().body(matchesJsonSchemaInClasspath("schema/" + schemaJson)));
    }
    
    /**
   	 * Verify that the response code received is same as expected 
   	 * @param responseCode  expected response code 
   	 * 
   	 */
    @Step("User should get the response code {int}")
    public void userShouldGetTheResponseCode(int responseCode) {
      assertThat(then().statusCode(responseCode));
    }




}
