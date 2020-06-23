package week3.day1.chaining;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateCRWithOutBody extends BaseRequest{

@Test
public void createCRWithOutBody() {
		
	//step3 :ReqType -->Post 
	Response resp = request
					.post();
	
	//step4 validate resp with resp status code
	System.out.println("status code should be 201  :"+resp.getStatusCode());
	
	JsonPath jrep = resp.jsonPath();
	System.out.println(jrep.get("result.sys_id"));
	sys_id = jrep.get("result.sys_id");
	
	
	
}

}
