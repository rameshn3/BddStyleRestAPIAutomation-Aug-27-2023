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

public class PutApiTest extends TestBase{
Response resp;
JSONObject jsonObj=new JSONObject();
@Given("^I want to provide valid endpoint to update user$")
public void i_want_to_provide_valid_endpoint_to_update_user() throws Throwable {
	// set the base url
		RestAssured.baseURI = readPropertyValue("baseUrl");
		RestAssured.basePath = readPropertyValue("serviceEndpointUrl"); 
}

@When("^I send a request to the server with valid request body to update$")
public void i_send_a_request_to_the_server_with_valid_request_body_to_update() throws Throwable {
	jsonObj.put("name", "morpheus");
	jsonObj.put("job", "zion resident");
	resp = RestAssured.given()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .body(jsonObj.toString())
          .when()
            .put("/2")
          .then()
             .contentType("application/json")
             .extract().response();
}

@Then("^I validate update user response status code$")
public void i_validate_update_user_response_status_code(DataTable dt) throws Throwable {
    
 	List<String>expData=dt.asList();
 	
 	int expStatusCode=Integer.parseInt(expData.get(0));
 	Assertions.assertEquals(resp.getStatusCode(), expStatusCode);
}

@Then("^validate the update user response body fields$")
public void validate_the_update_user_response_body_fields() throws Throwable {

	String actName=resp.path("name");
	System.out.println("Actual resposne name is:"+actName);
	Assertions.assertEquals(actName, jsonObj.get("name"));
	
	String actjob=resp.path("job");
	System.out.println("Actual resposne job is:"+actjob);
	Assertions.assertEquals(actjob, jsonObj.get("job"));  
}


}