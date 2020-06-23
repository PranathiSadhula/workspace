package week3.day1;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;

import static org.hamcrest.Matchers.*;
public class CookiesHeadersMatchers {

	@Test
	public void getAllIssuesCreatedIn24Hrs() throws ParseException {
		// step 1: get URL/endpoint
		RestAssured.baseURI = "https://api-mar2020.atlassian.net/rest/api/2/search";

		// step2 : Authentication(basic)
		RestAssured.authentication = RestAssured
									.preemptive()
									.basic("rajalakshmi.govindarajan@testleaf.com","kEJxzmhkQzvdeP8iysWN2D1B");

		
		List<Header> hList = new ArrayList<Header>();
		hList.add(new Header("content-type","application/json"));
		hList.add(new Header("accept","*/*"));
		Headers hMap = new Headers(hList);


		// step3 :ReqType -->Get
				Response resp1 = RestAssured.given().accept(ContentType.JSON).get();
		// step3 :ReqType -->Get
					RestAssured
						.given()
						.log()
						.all()
						.headers(hMap)

						.get()
						.then()
						.statusCode(200)
						.contentType(ContentType.JSON)
						.assertThat()
						.body("total", equalTo(148))
						;
		
		
		//System.out.println("cookies in resp :"+resp1.getCookies());

	}

}
