import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC001_GeET_Request {
	
	@Test
	public void getWeatherDetails() {
		
		//Specify base URI
		RestAssured.baseURI = "https://restapi.demoqa.com/utilities/weather/city";
		
		//Type of request
		RequestSpecification httpRequest = RestAssured.given();
		
		//Create Reponse object
		Response response = httpRequest.request(Method.GET, "/Hyderabad");
		
		//Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: "+responseBody);
		
		//Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: "+statusCode);
		Assert.assertEquals(statusCode, 200);
		
		//Status line verification
		String statusLine = response.getStatusLine();
		System.out.println(statusLine);
		Assert.assertEquals(statusLine, "HTTP/1.1 200 OK");
		
		
	}

}
