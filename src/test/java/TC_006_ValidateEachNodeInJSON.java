import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class TC_006_ValidateEachNodeInJSON {
	
	@Test
	public void validate_weatherAPI_body() {

		// Specify base URI
		RestAssured.baseURI = "https://restapi.demoqa.com/utilities/weather/city";

		// Type of request
		RequestSpecification httpRequest = RestAssured.given();

		// Create Reponse object
		Response response = httpRequest.request(Method.GET, "/Hyderabad");

		

		JsonPath jsonpath = response.jsonPath();
		System.out.println(jsonpath.get("City"));
		System.out.println(jsonpath.get("Temperature"));
		System.out.println(jsonpath.get("Humidity"));
		System.out.println(jsonpath.get("WeatherDescription"));
		System.out.println(jsonpath.get("WindSpeed"));
		System.out.println(jsonpath.get("WindDirectionDegree"));
		
		
		Assert.assertEquals(jsonpath.get("Temperature"), "26.16 Degree celsius");
		

	}


}
