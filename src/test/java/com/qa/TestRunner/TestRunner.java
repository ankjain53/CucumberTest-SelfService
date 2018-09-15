package com.qa.TestRunner;

import cucumber.api.CucumberOptions;
import com.cucumber.listener.Reporter;
import cucumber.api.testng.AbstractTestNGCucumberTests;
import cucumber.api.junit.Cucumber;

import java.io.File;

import org.junit.AfterClass;
import org.junit.runner.RunWith;	

@RunWith(Cucumber.class)
@CucumberOptions(
		features = {"C:/Users/ankur.jain/eclipse-workspace/ESS_Cucumber_Test/src/main/java/com/qa/FeatuerFile/",
		}, 
		//the path of the feature files
		//format= {"html:target/site/cucumber-pretty;json:target/cucumber.json&quot"},
		glue={"com.qa.StepDefinition"}, //the path of the step definition files
		plugin= {"pretty","html:target/site/cucmber-pretty", "json:target/cucumber/cucumber.json", "junit:target/cucumber.xml","com.cucumber.listener.ExtentCucumberFormatter:target/html/ExtentReport.html"}, //to generate different types of reporting
		monochrome = true, //display the console output in a proper readable format
		strict = true, //it will check if any step is not defined in step definition file
		dryRun = false//to check the mapping is proper between feature file and step def file
		//tags = {"@all"}			
		)

public class TestRunner 
{

	@AfterClass
	public static void setup() {
		Reporter.loadXMLConfig(new File("src/test/resources/extent-config.xml"));
		//Reporter.setSystemInfo("Test User", System.getProperty("user.name"));
		Reporter.setSystemInfo("User Name", "Ankur Jain");
		Reporter.setSystemInfo("Application Name", "JIO ESS");
		Reporter.setSystemInfo("Operating System Type", System.getProperty("os.name").toString());
		Reporter.setSystemInfo("Environment", "Production");
		Reporter.setTestRunnerOutput("ESS Test Execution Cucumber Report");
	}


}



//ORed : tags = {"@SmokeTest , @RegressionTest"} -- execute all tests tagged as @SmokeTest OR @RegressionTest
//ANDed : tags = tags = {"@SmokeTest" , "@RegressionTest"} -- execute all tests tagged as @SmokeTest AND @RegressionTest
