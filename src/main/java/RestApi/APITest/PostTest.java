package RestApi.APITest;

import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;
import org.json.simple.JSONObject;

public class PostTest {
	
	 @Test
	public void SimplePostTest() {
		 
		// get the thread name and print it
		 
		System.out.println(Thread.currentThread().getName());
					
		 // Specify the base URL to the RESTful web service
		 RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		 
		 // Get the RequestSpecification of the request that you want to sent
		 // to the server. The server is specified by the BaseURI that we have
		 // specified in the above step.
		 RequestSpecification request = RestAssured.given();
		 
		// JSONObject is a class that represents a Simple JSON.
		// We can add Key - Value pairs using the put method
		JSONObject requestParams = new JSONObject();
		requestParams.put("FirstName", "Virender32"); 
		requestParams.put("LastName", "Singh32");
		 
		requestParams.put("UserName", "simpleuser00132");
		requestParams.put("Password", "password132");
		requestParams.put("Email",  "someuser32@gmail.com");
		
		// Add a header stating the Request body is a JSON
		request.header("Content-Type", "application/json");
		 
		// Add the Json to the body of the request
		request.body(requestParams.toJSONString());
		 
		// Post the request and check the response
		Response response = request.post("/register");
		
		int statusCode = response.getStatusCode();
		//Assert.assertEquals(statusCode, 201);
		String successCode = response.jsonPath().get("SuccessCode");
		//Assert.assertEquals( successCode, "OPERATION_SUCCESS");
		
		String fault = response.jsonPath().get("fault");
		//Assert.assertEquals( fault, "FAULT_USER_ALREADY_EXISTS");
		
		if ((statusCode == 201) && (successCode.equals("OPERATION_SUCCESS")))
		{
			System.out.println("Register customer has been successful!");
			
			Assert.assertTrue(true);
		
		}
		else if ((statusCode == 200) && (fault.equals("FAULT_USER_ALREADY_EXISTS")))
		{
			System.out.println("customer already exists!");
			
			Assert.assertTrue(false);{
			
		}
	}
}
}