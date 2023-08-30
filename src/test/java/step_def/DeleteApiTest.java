package step_def;

import java.util.List;

import org.json.JSONObject;
import org.junit.jupiter.api.Assertions;

import io.cucumber.datatable.DataTable;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import utils.TestBase;

public class DeleteApiTest extends TestBase{
Response resp;

@Given("^I want to provide valid endpoint$")
public void i_want_to_provide_valid_endpoint() throws Throwable {
	// set the base url
			RestAssured.baseURI = readPropertyValue("baseUrl");
			RestAssured.basePath = readPropertyValue("serviceEndpointUrl");
}

@When("^I send the request to the server$")
public void i_send_the_request_to_the_server() throws Throwable {
	resp = RestAssured.given()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
          .when()
            .delete("/2")
          .then()
           .extract().response(); 
}

@Then("^I validate the delete response statuscode$")
public void i_validate_the_delete_response_statuscode(DataTable dt) throws Throwable {
    
 	List<String>expData=dt.asList();
 	
 	int expStatusCode=Integer.parseInt(expData.get(0));
 	Assertions.assertEquals(resp.getStatusCode(), expStatusCode);
}


}