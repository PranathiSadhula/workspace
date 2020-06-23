package jira.steps;

import java.io.File;
import java.util.List;

import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class CreateJiraIssue {
	
	public static RequestSpecification request;
	public static Response response;
	public static JsonPath jsonresp;
	public static String latestIssue;
	@Given("Request URL is initiated by Jira")
	public void initiateReqUrl() {
		// step 1: get URL/endpoint RestAssured.baseURI
		RestAssured.baseURI = "https://api-mar2020.atlassian.net/rest/api/2/";

	}

	@And("Authentication is performed")
	public void autherise() {
		// step2 : Authentication(basic)
		RestAssured.authentication = RestAssured.preemptive().basic("rajalakshmi.govindarajan@testleaf.com",
				"kEJxzmhkQzvdeP8iysWN2D1B");

	}
	
	@When("Body is posted with summary")
	public void postRequest() {
		
		Response postresp = RestAssured.given().contentType(ContentType.JSON).log().all().body("{\r\n" + "    \"fields\": {\r\n" +
				  "       \"project\":\r\n" + "       {\r\n" + "          \"key\": \"MAR\"\r\n"
				  + "       },\r\n" +
				  "       \"summary\": \"New Defect Pranathi for Week 3 Day1 Cucumber assignment\",\r\n"
				  +
				  "       \"description\": \"Creating of an issue for Week 3 Day1 Cucumber  assignment\",\r\n"
				  + "\r\n" + "       \"issuetype\": {\r\n" + "    \r\n" +
				  "          \"name\": \"Bug\"\r\n" + "       }\r\n" + "        \r\n" +
				  "   }\r\n" + "}")
				.post("issue");
		response = postresp;
		JsonPath jsonresp = response.jsonPath();
		jsonresp.prettyPrint();
		System.out.println("createdIssue ==> :" + jsonresp.get("id"));

	}
	
	@Then("post staus code should be (.*)$")
	public void verifyStatusCOde(int code) {
		if(response.getStatusCode() == code) {
			System.out.println(response.getStatusCode()+" Matches "+code);
		}
		else {
			System.out.println(response.getStatusCode()+"doesnt Matches "+code);
		}
	}
	
	@Then("get staus code should be (.*)$")
	public void verifygetStatusCOde(int code) {
		if(response.getStatusCode() == code) {
			System.out.println(response.getStatusCode()+" Matches "+code);
		}
		else {
			System.out.println(response.getStatusCode()+"doesnt Matches "+code);
		}
	}
	
	@And("Verify response is within 5 secs")
	public void verifyRespTime() {
		if(response.getTime() < 5000) {
			System.out.println("less than 5000");
		}
		else {
			System.out.println("not less than 5000");
		}
	}
	
	@When("Recently created Jira issue is passed")
	public void getRequestForJiraIssueId() {
		
		Response getresp = RestAssured
				.given().accept(ContentType.JSON).get("search?project=MAR");
				response = getresp;
				jsonresp = response.jsonPath();
				
				// issueMap.compute(key, remappingFunction)
				List<String> issueList = jsonresp.getList("issues.id");				
				latestIssue = issueList.get(0);
				System.out.println("latestIssue ==> :" + latestIssue);
	}
	
	@When("Delete Recently created Jira issue is passed")
	public void deleteJiraIssue() {
		Response getresp = RestAssured
				.given().accept(ContentType.JSON).delete("issue/"+latestIssue);
				response = getresp;
				jsonresp = response.jsonPath();
				
		
	}
	
	
	@Then("Delete staus code should be (.*)$")
	public void verifydeleteStatusCOde(int code) {
		if(response.getStatusCode() == code) {
			System.out.println(response.getStatusCode()+" Matches "+code);
		}
		else {
			System.out.println(response.getStatusCode()+"doesnt Matches "+code);
		}
	}
	
	

}
