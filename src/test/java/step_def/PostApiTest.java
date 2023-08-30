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

public class PostApiTest extends TestBase{
Response resp;
JSONObject jsonObj=new JSONObject();
@Given("^provide valid endpoint to create user$")
public void provide_valid_endpoint_to_create_user() throws Throwable {
	// set the base url
	RestAssured.baseURI = readPropertyValue("baseUrl");
	RestAssured.basePath = readPropertyValue("serviceEndpointUrl");
 
}

@When("^I send the request to the server with requestbody$")
public void i_send_the_request_to_the_server_with_requestbody() throws Throwable {
	jsonObj.put("name", "Ram");
	jsonObj.put("job", "Test Engineer");
	resp = RestAssured.given()
            			.header("Accept", "application/json")
            			.header("Content-Type", "application/json")
            			.body(jsonObj.toString())
            		.when()
            			.post()
            		.then()
            		 .contentType("application/json")
            		 .extract().response();
	
	
	
}

@Then("^I validate the create user response status code$")
public void i_validate_the_create_user_response_status_code(DataTable dt) throws Throwable {
    
 	List<String>expData=dt.asList();
 	
 	int expStatusCode=Integer.parseInt(expData.get(0));
 	Assertions.assertEquals(resp.getStatusCode(), expStatusCode);
}

@Then("^validate the create user response body fields$")
public void validate_the_create_user_response_body_fields() throws Throwable {
   
	String actName=resp.path("name");
	System.out.println("Actual resposne name is:"+actName);
	Assertions.assertEquals(actName, jsonObj.get("name"));
	
	String actjob=resp.path("job");
	System.out.println("Actual resposne job is:"+actjob);
	Assertions.assertEquals(actjob, jsonObj.get("job"));
	
}



@When("I send the request to the server with requestbody as {string} and {string}")
public void i_send_the_request_to_the_server_with_requestbody_as_and(String name, String job) throws Throwable {
	jsonObj.put("name", name);
	jsonObj.put("job", job);
	resp = RestAssured.given()
            .header("Accept", "application/json")
            .header("Content-Type", "application/json")
            .body(jsonObj.toString())
          .when()
            .post()
          .then()
             .contentType("application/json")
             .extract().response();
	
}


}
