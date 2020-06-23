package steps;
import static io.restassured.RestAssured.given;
import static org.hamcrest.Matchers.containsInAnyOrder;

import static org.hamcrest.Matchers.equalTo;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import io.restassured.specification.RequestSpecification;

public class ChangeManagement {
	private Response response;
	private ValidatableResponse json;
    private RequestSpecification request;
    
   
    /*server=dev72635.service-now.com
    		resources=api/now/table
    		username=admin
    		password=Sruthi@29*/
    		
	@Given("User to be authenticated")
	public void setUp(){ 
		
		RestAssured.baseURI = "https://dev97765.service-now.com/api/now/table/change_request";
		RestAssured.authentication = RestAssured.basic("admin", "Pranathi75@");
		request = given().log().all();
	}
	
	@And("CR will be added with short_desc")
	public void cr_will_be_added_with_short_desc(){
		String short_desc="description from matschie";
		request = request.and().body("{\r\n" + 
				"\"short_description\" : \"Good start\",\r\n" + 
				"\"category\" : \"software\",\r\n" + 
				"\"description\" : \"sruthi\"\r\n" + 
				"}");
		
	}
	
	@When("new CR is created")
	
	public void a_new_CR_created(){
		System.out.println(request);
		response = request.when().contentType(ContentType.JSON).post();
		System.out.println("response: " + response.prettyPrint());
}
	@Then("the status code should be (\\d+)$")
	public void verify_status_code(int statusCode){
		json = response.then().statusCode(statusCode);
	}


}
