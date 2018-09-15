package com.qa.StepDefinition;

import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.events.EventFiringWebDriver;

import com.cucumber.listener.Reporter;
import com.google.common.io.Files;
import com.qa.BaseClass.TestBaseClass;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import com.qa.Utility.TakeScreenshot;
import com.qa.Utility.WebElementListener;
import java.io.File;
import java.io.IOException;

//import cucumber.TestContext;


public class HomePageStepDefenition extends TestBaseClass
{


	@Before(order=1)
	public void Setup()
	{

		logger = Logger.getLogger("ESS_Test");
		PropertyConfigurator.configure(".\\src\\main\\java\\com\\qa\\Config\\log4j.properties ");

		System.setProperty("webdriver.chrome.driver","D://Chrome Driver 2.38//chromedriver_win32//chromedriver.exe");
		driver=new ChromeDriver();

		driver.manage().window().maximize();
		driver.get("https://ess.jioconnect.com");

		// Initializing EventFiringWebDriver using Firefox WebDriver instance
		e_driver = new EventFiringWebDriver(driver);

		// Now create object of EventListerHandler to register it with EventFiringWebDriver
		eventListener = new WebElementListener();

		e_driver.register(eventListener);
		driver=e_driver;
	}
	
	@After(order=1)
		
		public void afterScenario(Scenario scenario) 
	{
			if (scenario.isFailed())
			{
			String screenshotName = scenario.getName().replaceAll(" ", "_");
			try 
			{
			//This takes a screenshot from the driver at save it to the specified location
			//File sourcePath = ((TakesScreenshot) testContext.getWebDriverManager().getDriver()).getScreenshotAs(OutputType.FILE);
			scenario.embed(((TakesScreenshot)driver).getScreenshotAs(OutputType.BYTES), "image/png");
			//Building up the destination path for the screenshot to save
			//Also make sure to create a folder 'screenshots' with in the cucumber-report folder
			File destinationPath = new File(System.getProperty("user.dir") + "/target/cucumber-reports/screenshots/" + screenshotName + ".png");
			//Copy taken screenshot from source location to destination location
			//Files.copy(sourcePath, destinationPath);  
			 
			//This attach the specified screenshot to the test
			Reporter.addScreenCaptureFromPath(destinationPath.toString());
			}
			catch (IOException e)
			{
				
			}
			
			}
			}

	

	@After(order=0)
	public void TearDown()
	{

		driver.quit();

	}

	@Given("^user is on ESS Home Page$")
	public void user_is_on_ESS_Home_Page() 
	{

		System.out.println("Welcome to ESS Home Page");
	}

	@Then("^user validates logo and tittle Page$")
	public void user_validates_logo_and_tittle_Page() throws InterruptedException 
	{
		boolean homepageImage=driver.findElement(By.xpath("//*[@id=\"header\"]/img")).isDisplayed();
		Thread.sleep(3000);


		System.out.println("Image on the JIO ESS HomePage is Displayed:\t"+homepageImage);

		Assert.assertTrue( "Image on the JIO ESS HomePage is Not Displayed",homepageImage);

		logger.info("Image on the JIO ESS HomePage is Displayed");

		Assert.assertEquals("Jio SSO Login",driver.getTitle());

		System.out.println("Tittle of ESS Login Page is\t:"+driver.getTitle());

		logger.info("Tittle the JIO ESS HomePage is Displayed");
	}

	@Then("^user validates second and third tittle$")
	public void user_validates_second_and_third_tittle() throws Exception 
	{
		String secondTittleCheck= driver.findElement(By.xpath("//*[@id=\"content-inner\"]/div[1]/p")).getText();

		Assert.assertEquals("Welcome to Reliance Jio",secondTittleCheck);

		System.out.println("Second Tittle of ESS Login Page is\t:"+secondTittleCheck);

		logger.info("Second Tittle of ESS Login Page is Displayed");

		// Third Tittle Validation on ESS Home Page


		String thirdTittleCheck=driver.findElement(By.xpath("//*//*[@id=\"signin-msg\"]/p")).getText();

		Assert.assertEquals("Sign in with Jio account",thirdTittleCheck);

		System.out.println("Third Tittle of ESS Login Page is\t:"+thirdTittleCheck);

		logger.info("Third Tittle of ESS Login Page is Displayed");


		TakeScreenshot.captuerScreenshot(driver,"ESS LoginPage");




	}

	@Then("^Validates Error Message on entering incorrect \"([^\"]*)\" and \"([^\"]*)\" on Home page$")
	public void validates_Error_Message_on_entering_incorrect_and_on_Home_page(String userid, String pwd)throws Exception 
	{
		boolean username=driver.findElement(By.xpath("//input[@type='text']")).isDisplayed();
		System.out.println("Username/Email field on JIO ESS Home page is Displayed:\t"+username);
		Assert.assertTrue( "Username/Email field on JIO ESS Home page is Not Displayed",username);

		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(userid);

		boolean password=driver.findElement(By.xpath("//input[@type='text']")).isDisplayed();
		System.out.println("Password field on JIO ESS Home page is Displayed:\t"+password);
		Assert.assertTrue("Password field on JIO ESS Home page is not Displayed",username);

		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(pwd);


		boolean Login=driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/input[5]")).isEnabled();
		System.out.println("Login button on JIO ESS Home page is enabled:\t"+Login);

		Assert.assertTrue("Login button on JIO ESS Home page is not enabled",Login);

		driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/input[5]")).click();

		Thread.sleep(3000);

		TakeScreenshot.captuerScreenshot(driver,"Incorrect Login Credentials");


		String element=driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/p")).getText();

		System.out.println("Message is"+element);

		Assert.assertEquals("Invalid credentials",element);


		System.out.println("ESSLogin Failed with error message\t:"+element);
		
		
	}

	
	/*//@Then("^Validates Error Message on entering incorrect userid/pwd on Home page$")
	public void validates_Error_Message_on_entering_incorrect_userid_pwd_on_Home_page() throws Exception 
	{

		boolean username=driver.findElement(By.xpath("//input[@type='text']")).isDisplayed();
		System.out.println("Username/Email field on JIO ESS Home page is Displayed:\t"+username);
		Assert.assertTrue( "Username/Email field on JIO ESS Home page is Not Displayed",username);

		driver.findElement(By.xpath("//input[@type='text']")).sendKeys("ankur.jain");

		boolean password=driver.findElement(By.xpath("//input[@type='text']")).isDisplayed();
		System.out.println("Password field on JIO ESS Home page is Displayed:\t"+password);
		Assert.assertTrue("Password field on JIO ESS Home page is not Displayed",username);

		driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Word09Pass");


		boolean Login=driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/input[5]")).isEnabled();
		System.out.println("Login button on JIO ESS Home page is enabled:\t"+Login);

		Assert.assertTrue("Login button on JIO ESS Home page is not enabled",Login);

		driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/input[5]")).click();

		Thread.sleep(3000);

		TakeScreenshot.captuerScreenshot(driver,"Incorrect Login Credentials");


		String element=driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/p")).getText();

		System.out.println("Message is"+element);

		Assert.assertEquals("Invalid credentials",element);


		System.out.println("ESSLogin Failed with error message\t:"+element);


	}*/

	@Then("^Validates Tool Tip validation for Forgot/Generate Password on Home Page$")
	public void validates_Tool_Tip_validation_for_Forgot_Generate_Password_on_Home_Page() throws InterruptedException 
	{

		WebElement element = driver.findElement(By.xpath("//span[@style='Padding-right:20px;']//a[@href='#']"));
		Actions toolAct = new Actions(driver);
		toolAct.moveToElement(element).build().perform();
		Thread.sleep(3000);
		// WebElement toolTipElement = driver.findElement(By.xpath("//span[@style='Padding-right:20px;']//a[@href='#']"));
		String toolTipText = element.getText();

		Assert.assertEquals("This forgot password functionality is for business partners only.", toolTipText);

		logger.info("Tool Tip for Forgot password functionality is displayed");

		System.out.println("Tool Tip for Forgot Password is:\t"+toolTipText+ "and it is validated"); 

		Thread.sleep(3000);	
		WebElement element1 = driver.findElement(By.xpath("//*[@id=\"link\"]/span[2]/a[2]/img"));
		Actions toolAct1 = new Actions(driver);
		toolAct1.moveToElement(element1).build().perform();
		Thread.sleep(3000);
		//WebElement toolTipElement = driver.findElement(By.cssSelector("a.tooltip"));
		String toolTipText1 = element1.getText();
		System.out.println("Tool Tip for Generate Password is:\t"+toolTipText1); 


	}






}
