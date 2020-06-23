package week3.day1.chaining;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;

public class DeleteCR extends BaseRequest{

@Test(dependsOnMethods = "week3.day1.chaining.CreateCRWithOutBody.createCRWithOutBody")
public void deleteCR() {
	
	//step3 :ReqType -->Delete
	Response resp = request
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
