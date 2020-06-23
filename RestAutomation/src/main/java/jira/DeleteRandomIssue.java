package jira;

import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class DeleteRandomIssue {

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

				// issueMap.compute(key, remappingFunction)
				List<String> issueList = jsonresp.getList("issues.id");
				int count = issueList.size();
				int randIndex = (int) Math.floor(Math.random()*count);
				String randIssue = issueList.get(randIndex);
				System.out.println("randIssue ==> :" + randIssue);
				
				
				/**
				 * Delete randIssue
				 */
				//step 1: get URL/endpoint
				RestAssured.baseURI ="https://api-mar2020.atlassian.net/rest/api/2/issue/"+randIssue;
				
				RestAssured.authentication = RestAssured
						.preemptive()
						.basic("rajalakshmi.govindarajan@testleaf.com","kEJxzmhkQzvdeP8iysWN2D1B");

				// step3 :ReqType -->Get
				Response respDelete = RestAssured
							.given().accept(ContentType.JSON).delete();
				
				//step4 validate resp with resp status code
				System.out.println("status code should be 204  :"+respDelete.getStatusCode());
				
								
				
				
				
				
	}
}
