package week3.day1.chaining;

import org.testng.annotations.BeforeSuite;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.specification.RequestSpecification;

public class BaseRequest {
	
	public static RequestSpecification request;
	public static String sys_id;
	@BeforeSuite
	public void initialise() {
		
		//step 1: get URL/endpoint
		RestAssured.baseURI ="https://dev97765.service-now.com/api/now/table/change_request";
		
		//step2 : Authentication(basic)
		RestAssured.authentication = RestAssured.basic("admin", "Pranathi75@");
		
		request = RestAssured.given().log().all().contentType(ContentType.JSON);
	
	}

}
