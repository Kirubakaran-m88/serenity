package org.restAssured.junit;

import org.junit.Test;
import org.restAssured.utils.CommonUtils;
import org.restuAssured.cucumber.serenity.Steps;

import io.restassured.response.Response;

public class FeatureTest {

	
	Steps steps = new Steps();
	CommonUtils restCommonMethods = new CommonUtils();
	
	String fname = "Kirubakaran";
	String job = "Engineer";
	
	String endPoint = "https://reqres.in/api/users";
	String authEndPoint = "https://postman-echo.com/digest-auth";
	
	String userName ="postman";
	String passWord = "password";
	
	
	@Test
	public void verifyAuthentication() throws InterruptedException
	{
		try
		{
		Response res = restCommonMethods.getResponseWithAuthSecurity(authEndPoint, userName, passWord);
		String respose = res.asString();
		boolean authentication = restCommonMethods.getValueFromJsonObjectForBooleanValue("authenticated");
		if(authentication==true)
		{ 
			System.out.println("Authentication Passed. User name : "+userName +", Password : " +passWord+ ", Authentication : "+authentication);
		}
		else
		{
			System.out.println("Failed to validate Authentication. Authentication - "+authentication);
		}
		passWord = passWord + "123";
		res = restCommonMethods.getResponseWithAuthSecurity(authEndPoint, userName, passWord);
		String response = res.asString();
		if(response.equalsIgnoreCase("Unauthorized"))
		{
			System.out.println(" Authentication Passed. User name : "+userName +", Password : " +passWord+ ", Authentication : "+response);
		}
		else
		{
			System.out.println("Failed to validate Authentication. Authentication - "+authentication);
		}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	
/*	@Test
	public void verifyCreate() throws InterruptedException
	{
		Response res = steps.createRecord(endPoint,fname,job);
		if(steps.getStatusCodeForCreateRecord() == 201)
		{
			System.out.println("Success. Status code --> "+steps.getStatusCodeForCreateRecord());
		}
		else
		{
			System.out.println("Status is wrong.");
		}
		String responseValue = res.asString();
		String s= res.getBody().asString();
		String v = res.getHeaders().toString();
		String createdValue = restCommonMethods.getValueFromJsonObjectForStringValue("createdAt");
		if(responseValue.contains("createdAt"))
		{
			System.out.println("Created At is displayed. Created At value - " +createdValue);
		}
		else
		{
			System.out.println("Created At is not displayed");
		}
	}*/
	
	
}
