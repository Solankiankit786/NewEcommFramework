package Ecomm_BDD_framework.cucumber;

import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;

@CucumberOptions(features = "src/test/java/Ecomm_BDD_framework/cucumber",glue = {"Ecomm_BDD_framework.stepDefinition"}
,monochrome = true,plugin = {"html:target/cucumber.html"},tags = "@ErrorValidation")
public class TestNGRunner extends AbstractTestNGCucumberTests {
	
}
