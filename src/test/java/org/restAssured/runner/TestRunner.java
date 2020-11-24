package org.restAssured.runner;


import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import net.serenitybdd.cucumber.CucumberWithSerenity;

@RunWith(CucumberWithSerenity.class)                    
@CucumberOptions(
        features= {"src/test/resources/feature/CucumberRestAssured.feature"},  
        tags= {"@Scenario1"},
        glue = {"org.restAssured.StepDefinition"}) 

public class TestRunner {

	
}
