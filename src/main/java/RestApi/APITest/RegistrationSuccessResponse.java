package RestApi.APITest;

import org.json.simple.JSONObject;
import org.testng.Assert;
import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.response.ResponseBody;
import io.restassured.specification.RequestSpecification;

	public class RegistrationSuccessResponse {
	 
	 // Variable where value of SuccessCode node
	 // will be copied
	 // Note: The name should be exactly as the node name is 
	 // present in the Json
	 public String SuccessCode;
	 
	 // Variable where value of Message node will
	 // be copied
	 // Note: The name should be exactly as the node name is 
	 // present in the Json
	 public String Message;
	 
	 // in case registration is not successful then use the following two variables
	 
	 public String FaultId;
	 public String fault;

	
	@Test
	public void RegistrationSuccessful()
	{ 
		// get the thread name and print it
		 
			System.out.println(Thread.currentThread().getName());
		
		 RestAssured.baseURI ="http://restapi.demoqa.com/customer";
		 RequestSpecification request = RestAssured.given();
		 
		 JSONObject requestParams = new JSONObject();
		 requestParams.put("FirstName", "sridar5"); // Cast
		 requestParams.put("LastName", "kilvidi5");
		 requestParams.put("UserName", "63userf2d3d20115");
		 requestParams.put("Password", "password15"); 
		 requestParams.put("Email",  "ed26dff395@gmail.com");
		 
		 request.body(requestParams.toJSONString());
		 
		 Response response = request.post("/register");
		 
		 ResponseBody body = response.getBody();
		 
		// Deserialize the Response body into RegistrationSuccessResponse
		 
		 RegistrationSuccessResponse responseBody = body.as(RegistrationSuccessResponse.class);
		 
		 if (response.statusCode() == 201)// success
		 { 
			 
			 
			 // Use the RegistrationSuccessResponse class instance to Assert the values of Response.
			 
			 System.out.println(responseBody.SuccessCode);
			 System.out.println(responseBody.Message);
			 
			 Assert.assertEquals(responseBody.SuccessCode, "OPERATION_SUCCESS");
			 Assert.assertEquals(responseBody.Message, "Operation completed successfully");
		 }
		 else if (response.statusCode() == 200)
		 {
			 System.out.println(responseBody.FaultId);
			 System.out.println(responseBody.fault);
			 // Use the RegistrationFailureResponse class instance to Assert the values of  Response.
			 Assert.assertEquals(responseBody.FaultId,"User already exists");
			 Assert.assertEquals(responseBody.fault,"FAULT_USER_ALREADY_EXISTS"); 
		 }

	}
}
