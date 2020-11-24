package org.restAssured.utils;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.Assert;
import org.restAssured.model.CustomerModel;

import io.restassured.RestAssured;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import net.serenitybdd.rest.SerenityRest;

public class CommonUtils {

	static Response response;
	List<String> innerArrayValueOfJSONObject = new ArrayList<String>();
	
	public Response getRespose(String url)
	{
		try
		{
			 return response = SerenityRest.given()
					.when()
					.get(url);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}
	
	public Response getResponseWithAuthSecurity(String endPoint,String username,String password)
	{
		return response = SerenityRest.given()
				.auth().basic(username, password)
				.when()
				.get(endPoint);
				
	}
	
	public Response postAndGetRespose(String url,CustomerModel customerObject)
	{
		try
		{
			 return response = RestAssured.given()
					 .spec(RestSpecificationClass.requestSpecification())
					.when()
					.body(customerObject)
					.post(url);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return response;
	}
	
	public int validateStatusCode()
	{
		try
		{
			int status = response.getStatusCode();
			if(response.getStatusCode()==200 || response.getStatusCode() ==201 || response.getStatusCode() == 202)
			{
				return response.getStatusCode();
			}
			else
			{
				System.out.println("Failed");
			}
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return 0;
		
	}
	
	public String getValueFromResponseWithJsonObject(int count,String jsonObject)
	{
		try
		{
			JSONArray arr = new JSONArray(response.asString());
				JSONObject obj = arr.getJSONObject(count);
				return obj.getString(jsonObject);
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return jsonObject;
	}
	
	public List getValueFromResponseWithJsonObjectAndObjectArray(int count,String jsonObjectArrayValue)
	{
		try
		{
			JSONArray arr = new JSONArray(response.asString());
				JSONObject obj = arr.getJSONObject(count);
				JSONArray jsonObjectArray = obj.getJSONArray(jsonObjectArrayValue);
				for(int i=0;i<=jsonObjectArray.length()-1;i++)
				{
					innerArrayValueOfJSONObject.add(jsonObjectArray.getString(i));
				}
				return innerArrayValueOfJSONObject;
		}
		catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return innerArrayValueOfJSONObject;
	}
	
	public String getValueFromJsonObjectForStringValue(String object)
	{
		JSONObject json = new JSONObject(response.asString());
		return json.getString(object);
	}
	
	public Boolean getValueFromJsonObjectForBooleanValue(String object)
	{
		JSONObject json = new JSONObject(response.asString());
		return json.getBoolean(object);
	}
	
}
