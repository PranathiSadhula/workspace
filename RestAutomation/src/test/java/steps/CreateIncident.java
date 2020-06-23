package steps;

import java.io.File;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateIncident {
	
	public static RequestSpecification request;
	
	public static String sys_id;
	public static Response response;
	public static JsonPath jsonresp;
	
	@Given("Request URL is initiated")
	public void initiateReqUrl() {
		//step 1: get URL/endpoint
		RestAssured.baseURI ="https://dev97765.service-now.com/api/now/table/incident";
		
		
	}
	
	@And("Auth is performed")
	public void autherise() {
		//step2 : Authentication(basic)
				RestAssured.authentication = RestAssured.basic("admin", "Pranathi75@");
			
	}
	
	@When("Body is posted with Json file (.*)$")
	public void postRequest(String filename) {
		System.out.println("filename : "+filename);
		response = RestAssured.given().contentType(ContentType.JSON).log().all().body(new File(filename)).post();
		

	}
	
	@Then("staus code should be (.*)$")
	public void verifyStatusCOde(int code) {
		if(response.getStatusCode() == code) {
			System.out.println(response.getStatusCode()+" Matches "+code);
		}
		else {
			System.out.println(response.getStatusCode()+"doesnt Matches "+code);
		}
	}
	@And("Get Incident Number")
	public void getIncidentNumber() {
		jsonresp = response.jsonPath();
		System.out.println(jsonresp.get("result.number"));
	}
	
	@And("Response is within 5 secs")
	public void verifyRespTime() {
		if(response.getTime() < 5000) {
			System.out.println("less than 5000");
		}
		else {
			System.out.println("not less than 5000");
		}
	}
	
	
}
