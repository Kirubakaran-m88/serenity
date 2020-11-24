package org.restAssured.StepDefinition;


import org.restuAssured.cucumber.serenity.Steps;

import cucumber.api.java.en.Given;

public class StepDefinition {

	Steps serenitySteps = new Steps();
	
	@Given("^Hit the end point and validate feature$")
	public void hit_and_validate()
	{
		serenitySteps.getPostResponse();
	}
}
