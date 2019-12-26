import org.json.simple.JSONObject;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC002_Post_Request {

	@Test
	public void getWeatherDetails() {

		// Specify base URI
		RestAssured.baseURI = "https://restapi.demoqa.com/customer";

		// Type of request
		RequestSpecification httpRequest = RestAssured.given();

		// Create Response object

		// Request payload sending along with post request
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "John111");
		requestParams.put("LastName", "David222");
		requestParams.put("UserName", "John111");
		requestParams.put("Password", "David222");
		requestParams.put("Email", "John111@gmail.com");

		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(requestParams.toJSONString());

		Response response = httpRequest.request(Method.POST, "/register");

		// Print response in console window
		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: " + responseBody);

		// Status code validation
		int statusCode = response.getStatusCode();
		System.out.println("Status Code: " + statusCode);
		Assert.assertEquals(statusCode, 201);

		String successCode = response.jsonPath().get("SuccessCode");
		

		Assert.assertEquals(successCode, "OPERATION_SUCCESS");

	}

}
