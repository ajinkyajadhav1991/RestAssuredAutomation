import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC005_GET_ValidatingJSONResponse {

	@Test
	public void validate_weatherAPI_body() {

		// Specify base URI
		RestAssured.baseURI = "https://restapi.demoqa.com/utilities/weather/city";

		// Type of request
		RequestSpecification httpRequest = RestAssured.given();

		// Create Reponse object
		Response response = httpRequest.request(Method.GET, "/Hyderabad");

		String responseBody = response.getBody().asString();
		System.out.println("Response Body is: " + responseBody);
		
		String s = response.jsonPath().get("SuccessCode");
		System.out.println("SuccessCode is: "+s);

		Assert.assertEquals(responseBody.contains("Hyderabad"), true);

	}

}
