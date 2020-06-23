package jira;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;

public class GetAllIssuesCreatedIn24Hrs {

	@Test
	public void getAllIssuesCreatedIn24Hrs() throws ParseException {
		// step 1: get URL/endpoint
		RestAssured.baseURI = "https://api-mar2020.atlassian.net/rest/api/2/search";

		// step2 : Authentication(basic)
		RestAssured.authentication = RestAssured
									.preemptive()
									.basic("rajalakshmi.govindarajan@testleaf.com","kEJxzmhkQzvdeP8iysWN2D1B");

		// step3 :ReqType -->Get
		Response resp = RestAssured.given().accept(ContentType.JSON).get();

		JsonPath jsonresp = resp.jsonPath();

		// issueMap.compute(key, remappingFunction)
		List<String> issueList = jsonresp.getList("issues.id");
		List<String> createList = jsonresp.getList("issues.fields.created");
		System.out.println(createList);
		System.out.println(createList.size());
		System.out.println("Total Issue count :" + issueList.size());

		// get issue id created i 24hours
		long currTime = new Date().getTime();
		// console.log("currTime :"+currTime);
		long last24thHr = 24 * 60 * 60 * 1000;
		// console.log("last24thHr :"+last24thHr)
		long expectedCreatedTime = currTime - last24thHr;
		int issueCountCreatedin24hrs = 0;
		for ( int i = 0; i < createList.size(); i++) { // Creating date format
			String[] splitC = createList.get(i).split("\\.");
			String myDate = splitC[0].replace("-", "").replace("T", "").replace(":", "");
			
			SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
			Date date = sdf.parse(myDate);
			long createdTime = date.getTime();
			
			if (createdTime > expectedCreatedTime) {
				System.out.println(issueList.get(i)+" created on "+splitC[0]);
				issueCountCreatedin24hrs++;
			}
		}
		System.out.println("issue count created in 24hrs "+issueCountCreatedin24hrs);

	}

}
