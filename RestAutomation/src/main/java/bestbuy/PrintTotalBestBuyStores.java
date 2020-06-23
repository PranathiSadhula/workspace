package bestbuy;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class PrintTotalBestBuyStores {
	
	@Test
	public void printTotalBestBuySTores() {
		//Step1 : Base url setup
		RestAssured.baseURI = "https://api.bestbuy.com/v1/stores";
		
		//step2 : Authentication
		
		//step3 : build params
		Map<String, String> pMap = new HashMap<String, String>();
		pMap.put("apiKey","qUh3qMK14GdwAs9bO59QRSCJ");
		pMap.put("format","json");
		pMap.put("show","storeId,storeType,name,city,region");
		
		Response resp = RestAssured
										.given()
										.params(pMap)
										.get();
		
		JsonPath jsonresp = resp.jsonPath();
		jsonresp.prettyPrint();
		System.out.println("Total Store count : "+jsonresp.getList("stores").size());
		System.out.println("Expected status code : 200 and actual :"+resp.getStatusCode());
	}

}
