package RestApi.APITest;

import org.testng.annotations.Test;

import io.restassured.RestAssured;
import io.restassured.path.json.JsonPath;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Authentication {
	
	
	//authentication header is provided. but with wrong un/pwd or blank un/pwd - status code 401
	
	public String FaultId; // "OPERATION_SUCCESS","FAULT_USER_INVALID_USER_PASSWORD",
    public String Fault; // "Operation completed successfully","Invalid username or password"
    
    // status code 200 ok
    public String UsernamePassword; //"ToolsQA:TestPassword",
    public String AuthenticationType; // "Basic"
    
    // No authentication header is provided. status code is 401
    public String StatusID; // "FAULT_USER_INVALID_USER_PASSWORD",
    public String Status; // "Invalid or expired Authentication key provided"
    
    
    @Test
    public void AuthenticationBasics()
       {
    	
    	// get the thread name and print it
   	 
   			System.out.println(Thread.currentThread().getName());
   	
	     RestAssured.baseURI = "http://restapi.demoqa.com/authentication/CheckForAuthentication";
	     RequestSpecification request = RestAssured.given();
	     request.auth().basic("ToolsQA", "TestPassword");
	     Response response = request.get();
	     
	     System.out.println("Status code: " + response.getStatusCode());
	     System.out.println("Status message " + response.body().asString());
	     
	      // First get the JsonPath object instance from the Response interface
		  //JsonPath jsonPathEvaluator = response.jsonPath();
		 
		  if (response.statusCode()==200 & response.jsonPath().get("FaultId").equals("OPERATION_SUCCESS")) {
			  System.out.println("Authentication Successful!");
		  }
		  else if (response.statusCode()==401 & response.jsonPath().get("FaultId").equals("FAULT_USER_INVALID_USER_PASSWORD")) {
			  System.out.println("Authentication Failed! Provide valid un/pwd");
		  }
		  
    }
    
}
