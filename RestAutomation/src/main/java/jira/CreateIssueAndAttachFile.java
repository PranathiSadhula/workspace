package jira;

import java.io.File;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateIssueAndAttachFile {
	
	@Test
	public void createIssueAndAttachFIle() {
		
		 //step 1: get URL/endpoint RestAssured.baseURI
		 RestAssured.baseURI ="https://api-mar2020.atlassian.net/rest/api/2/issue/";
		 // step2 : Authentication(basic)
		 RestAssured.authentication = RestAssured
		 				.preemptive()
		  .basic("rajalakshmi.govindarajan@testleaf.com","kEJxzmhkQzvdeP8iysWN2D1B");
		  
		  
		  //step3 :ReqType -->Post
		 Response resp = RestAssured .given()
		  .contentType(ContentType.JSON) .body("{\r\n" + "    \"fields\": {\r\n" +
		  "       \"project\":\r\n" + "       {\r\n" + "          \"key\": \"MAR\"\r\n"
		  + "       },\r\n" +
		  "       \"summary\": \"New Defect Pranathi for Week 2 Day2 Rest Assured assignment\",\r\n"
		  +
		  "       \"description\": \"Creating of an issue for Week 2 Day2 Rest Assured  assignment --> To add attachment\",\r\n"
		  + "\r\n" + "       \"issuetype\": {\r\n" + "    \r\n" +
		  "          \"name\": \"Bug\"\r\n" + "       }\r\n" + "        \r\n" +
		  "   }\r\n" + "}") .post();
		  
		  //step4 validate resp with resp status code
		  System.out.println("status code should be 201  :"+resp.getStatusCode());
		  
		  //step 5: get issue id and store in a variable : issue_id
		  JsonPath jrep = resp.jsonPath(); 
		  jrep.prettyPrint(); 
		  String issue_id = jrep.get("id");
		 
		
		/**Attach files to th created issue**/
		

		//step 1: get URL/endpoint
		RestAssured.baseURI ="https://api-mar2020.atlassian.net/rest/api/2/issue/"+issue_id+"/attachments";
		
		// step2 : Authentication(basic)
		RestAssured.authentication = RestAssured
									.preemptive()
									.basic("rajalakshmi.govindarajan@testleaf.com","kEJxzmhkQzvdeP8iysWN2D1B");

		
		//step3 : Generate a file for attachment
		File file = new File("./jiraattchmnt1.txt");
		//step4 : Create a header X-Atlassian-Token : no-check ---> mandatory for JIRA attachments
		//Header header = new Header("X-Atlassian-Token", "no-check");
		//step5 :ReqType -->Post
		Response respAttach = RestAssured
						.given()
//						.header("Accept", "application/json")
						//.contentType(ContentType.JSON)
						.header("X-Atlassian-Token", "no-check")
						.multiPart(file)
						.post();
		respAttach.prettyPrint();
		//step4 validate resp with resp status code
				System.out.println("status code should be 200  :"+respAttach.getStatusCode());
				
	}

}
