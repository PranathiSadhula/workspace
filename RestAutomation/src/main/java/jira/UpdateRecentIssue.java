package jira;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class UpdateRecentIssue {

	@Test
	public void deleteRandomIssue() {
		
		// step 1: get URL/endpoint
				RestAssured.baseURI = "https://api-mar2020.atlassian.net/rest/api/2/search";

				// step2 : Authentication(basic)
				RestAssured.authentication = RestAssured
											.preemptive()
											.basic("rajalakshmi.govindarajan@testleaf.com","kEJxzmhkQzvdeP8iysWN2D1B");

				// step3 :ReqType -->Get
				Response resp = RestAssured
								.given().accept(ContentType.JSON).get();

				JsonPath jsonresp = resp.jsonPath();


				String recentIssue = (String) jsonresp.getList("issues.key").get(0);
				System.out.println("randIssue ==> :" + recentIssue);
				
				
				/**Update recent issue : recentIssue**/
				

				//step 1: get URL/endpoint
				RestAssured.baseURI ="https://api-mar2020.atlassian.net/rest/api/2/issue/"+recentIssue;
				
				// step2 : Authentication(basic)
				RestAssured.authentication = RestAssured
											.preemptive()
											.basic("rajalakshmi.govindarajan@testleaf.com","kEJxzmhkQzvdeP8iysWN2D1B");
				//step3 : Request type : PUT
				Response respupdate = RestAssured
										.given()
										.contentType(ContentType.JSON)
										.body("{\r\n" + 
												"    \"fields\": {\r\n" + 
												"       \r\n" + 
												"       \"summary\": \"new Defect Updated RA -Pranathi S\"\r\n" + 
												"\r\n" + 
												"        \r\n" + 
												"   }\r\n" + 
												"}")
										.put();
				respupdate.prettyPrint();
				//step4 validate resp with resp status code
				System.out.println("status code should be 200  :"+respupdate.getStatusCode());
				
								
				
				
				
				
	}
}
