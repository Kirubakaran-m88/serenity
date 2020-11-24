package org.restAssured.junit;

import java.util.ArrayList;
import java.util.List;

import org.hibernate.validator.constraints.SafeHtml.Tag;
import org.json.JSONArray;
import org.json.JSONObject;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.restAssured.runner.TestRunner;
import org.restAssured.utils.CommonUtils;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import net.serenitybdd.junit.runners.SerenityRunner;
import net.serenitybdd.rest.SerenityRest;
import net.thucydides.core.annotations.Step;

@RunWith(SerenityRunner.class)
public class FeatureSerenity {
	
	CommonUtils restCommonMethods = new CommonUtils();
	
	@BeforeClass
	public static void init()
	{
		RestAssured.baseURI = "http://restcountries.eu/rest/v1/name";
	}
	
	
	
	@Test
	@Step
	public void getDetails() throws InterruptedException
	{
		Response res = restCommonMethods.getRespose("/xyz");
		if(restCommonMethods.validateStatusCode() == 200)
		{
		JSONArray arr = new JSONArray(res.asString());
		for(int i=0;i<=arr.length()-1;i++)
		{
			String countryName = restCommonMethods.getValueFromResponseWithJsonObject(i, "name");
			if(countryName.equalsIgnoreCase("India"))
			{
				
				List<String> altSpellings = restCommonMethods.getValueFromResponseWithJsonObjectAndObjectArray(i, "altSpellings");
				for(String singleAltSpelling : altSpellings)
				if(singleAltSpelling.equalsIgnoreCase("Republic of India"))
				{
					System.out.println("Name : "+singleAltSpelling);
					break;
				}
				break;
			}
		}
		}
		else
		{
			System.out.println(res.asString());
		}
	}
	
	@Test
	public void verifyCapital() throws InterruptedException
	{
		Response res = SerenityRest.given()
		.when()
		.get("/abc");
	
		if(res.getStatusCode() == 200)
		{
		JSONArray arr = new JSONArray(res.asString());
		List<String> val = new ArrayList<String>();
		for(int i=0;i<=arr.length()-1;i++)
		{
			JSONObject obj = arr.getJSONObject(i);
			if(obj.getString("capital").equalsIgnoreCase("Oslo"))
			{
				System.out.println("County : "+obj.getString("name"));
				System.out.println("Capital Name : "+obj.getString("capital"));
				System.out.println("Norway's Info :"+res.asString());
			}
		}
		}
		else
		{
			System.out.println(res.asString());
		}
	}
	
}
