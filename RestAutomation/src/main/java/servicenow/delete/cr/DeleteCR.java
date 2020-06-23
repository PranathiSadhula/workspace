package servicenow.delete.cr;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteCR {

@Test
public void deleteCR() {
	//step 1: get URL/endpoint
	RestAssured.baseURI ="https://dev97765.service-now.com/api/now/table/change_request";
	
	//step2 : Authentication(basic)
	RestAssured.authentication = RestAssured.basic("admin", "Pranathi75@");
	
	//step3 :ReqType -->Delete
	Response resp = RestAssured
			.given()
			.log()
			.all()
			.contentType(ContentType.JSON)
			.delete("556a928d2f10901056cedcb6f699b6f1");
	
	//step4 validate resp with resp status code
	System.out.println("status code should be 204  :"+resp.getStatusCode());
	
	//step5 validate resp time 
	System.out.println(resp.getTime());
	System.out.println(resp.getTimeIn(TimeUnit.SECONDS));
	
	//step6 : resp content type
	System.out.println(resp.contentType());
	
	//step7 : pretty print
	resp.prettyPrint();
}

}
