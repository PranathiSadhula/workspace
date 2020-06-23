package servicenow.get.cr;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class GetAllCR {

@Test
public void getAllCR() {
	//step 1: get URL/endpoint
	RestAssured.baseURI ="https://dev97765.service-now.com/api/now/table/change_request";
	
	//step2 : Authentication(basic)
	RestAssured.authentication = RestAssured.basic("admin", "Pranathi75@");
	
	//step3 :ReqType -->Get
	Response resp = RestAssured.get();
	
	//step4 validate resp with resp status code
	System.out.println("status code should be 200  :"+resp.getStatusCode());
	
	//step5 validate resp time 
	System.out.println(resp.getTime());
	System.out.println(resp.getTimeIn(TimeUnit.SECONDS));
	
	//step6 : resp content type
	System.out.println(resp.contentType());
	
	//step7 : pretty print
	resp.prettyPrint();
}

}
