import static org.testng.Assert.assertEquals;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class TC003_Get_Request {
	
	@Test
	public void googleMapTest() {
		
		RestAssured.baseURI = "https://maps.googleapis.com";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		Response response = httpRequest.request(Method.GET,"/maps/api/place/nearbysearch/xml?location=-33.8670522,151.1957362&radius=1500&type=supermarket&key=AIzaSyBjGCE3VpLU4lgTqSTDmHmJ2HoELb4Jy1s");
		
		
		
		String responseBody = response.getBody().asString();
		
		System.out.println("Response body is: "+responseBody);
		
		//validating headers
		String contentType = response.header("Content-Type");
		assertEquals(contentType, "application/xml; charset=UTF-8");
	}

}
