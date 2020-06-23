package servicenow.get.cr;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.xml.XmlPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class GetAllCRXML {

@Test
public void getAllCR() {
	//step 1: get URL/endpoint
	RestAssured.baseURI ="https://dev97765.service-now.com/api/now/table/change_request";
	
	//Add params
	Map<String, String> parmsMap = new HashMap<String, String>();
	parmsMap.put("sysparm_fields","number,sys_id");
	parmsMap.put("state","-5");
	parmsMap.put("type","normal");

	//step2 : Authentication(basic)
	RestAssured.authentication = RestAssured.basic("admin", "Pranathi75@");
	
	//step3 :ReqType -->Get - contentType : XML
	Response resp = RestAssured
			.given()
			.params(parmsMap)
			.accept(ContentType.XML)
			.get();
	resp.prettyPrint();
	
	XmlPath xmlresp = resp.xmlPath();
	
	//System.out.println(xmlresp);
	List<String> crList = xmlresp.getList("response.result.number");
	System.out.println(crList.size());
	
	
	
	
	
	
	
}

}
