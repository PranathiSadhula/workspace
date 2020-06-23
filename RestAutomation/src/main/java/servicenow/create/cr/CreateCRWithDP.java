package servicenow.create.cr;
import java.io.File;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class CreateCRWithDP {
	
@DataProvider(name="DP")
public String[] getFiles() {
	
	String[] data = new String[2];
	data[0] = "./data1.json";
	data[1] = "./data2.json";
	return data;
	
}

@Test(dataProvider = "DP")
public void createCRWithDP(String fileName) {
	//step 1: get URL/endpoint
	RestAssured.baseURI ="https://dev97765.service-now.com/api/now/table/change_request";
	
	//step2 : Authentication(basic)
	RestAssured.authentication = RestAssured.basic("admin", "Pranathi75@");
	
	File fileSrc = new File(fileName);
	//step3 :ReqType -->Post
	Response resp = RestAssured
					.given()
					.contentType(ContentType.JSON)
					.body(fileSrc)
					.post();
	
	//step4 validate resp with resp status code
	System.out.println("status code should be 201  :"+resp.getStatusCode());
	resp.prettyPrint();
	JsonPath jrep = resp.jsonPath();
	System.out.println(jrep.get("result.sys_id"));
	
	
	
}

}
