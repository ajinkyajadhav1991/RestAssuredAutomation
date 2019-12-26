package dataDrivenTesting;

import java.io.IOException;

import org.json.simple.JSONObject;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.http.Method;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import junit.framework.Assert;

public class DataDrivenTest_AddNewEmployee {
	
	@Test(dataProvider="empDataProvider")
	void postNewEmployees(String ename, String eSalary, String eAge) {
		
		RestAssured.baseURI = "http://dummy.restapiexample.com/api/v1";
		
		RequestSpecification httpRequest = RestAssured.given();
		
		//Create Json body
		JSONObject reqParams = new JSONObject();
		reqParams.put("name", ename);
		reqParams.put("salary", eSalary);
		reqParams.put("age", eAge);
		
		httpRequest.header("Content-Type", "application/json");
		httpRequest.body(reqParams.toJSONString());
		
		//Post the request
		Response reponse = httpRequest.request(Method.POST, "/create");
		
		String  responseBody = reponse.getBody().asString();
		
		Assert.assertEquals(responseBody.contains(ename), true);
		
		
		int responseCode = reponse.getStatusCode();
		Assert.assertEquals(200, responseCode);
		
	}

	@DataProvider(name="empDataProvider")
	String[][] getEmpData() throws IOException{	
		
		String path = System.getProperty("user.dir") + "\\src\\test\\java\\dataDrivenTesting\\empdata.xlsx";
		
		int rownum = XLUtils.getRowCount(path, "Sheet1");
		int colcount = XLUtils.getCellCount(path, "Sheet1", 1);
		
		String empData[][] = new String[rownum][colcount];
		
		for(int i = 1; i <= rownum; i++) {
			for(int j = 0; j < colcount; j++) {
				empData[i - 1][j] = XLUtils.getCellData(path, "Sheet1", i, j);
				
			}
		}
		
		//String empData[][] = {{"Ajinkya123", "98000", "29"}, {"Ajinkya111", "98000", "29"}, {"Ajinkya222", "98000", "29"}};
		return (empData);		
	}
}
