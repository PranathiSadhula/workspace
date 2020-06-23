package servicenow.create.cr;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateCRWithBody {

@Test
public void createCRWithBody() {
	//step 1: get URL/endpoint
	RestAssured.baseURI ="https://dev97765.service-now.com/api/now/table/change_request";
	
	//step2 : Authentication(basic)
	RestAssured.authentication = RestAssured.basic("admin", "Pranathi75@");
	
	//step3 :ReqType -->Post
	Response resp = RestAssured
					.given()
					.contentType(ContentType.JSON)
					.body("{\r\n" + 
							"	\r\n" + 
							"	\"short_description\" : \"Good start to Delete\",\r\n" + 
							"	\"description\" : \"Pranathi Sadhula\",\r\n" + 
							"	\"category\" : \"Software\"\r\n" + 
							"}")
					.post();
	
	//step4 validate resp with resp status code
	System.out.println("status code should be 201  :"+resp.getStatusCode());
	
	JsonPath jrep = resp.jsonPath();
	System.out.println(jrep.get("result.sys_id"));
	
	
	
}

}
