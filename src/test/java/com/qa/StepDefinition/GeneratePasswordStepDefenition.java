package com.qa.StepDefinition;

import org.junit.Assert;
import org.openqa.selenium.By;


import com.qa.BaseClass.TestBaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import com.qa.Utility.TakeScreenshot;

public class GeneratePasswordStepDefenition extends TestBaseClass
{

	@Given("^user is on ESS Generate Password Page$")
	public void user_is_on_ESS_Generate_Password_Page() 
	{
		logger.info("Welcome to ESS Generate Password Page");
	}

	@Then("^user validates link & tittle for Generate Password page$")
	public void user_validates_link_tittle_for_Generate_Password_page() throws InterruptedException  
	{
		driver.findElement(By.xpath("//a[@href='https://idm.jioconnect.com/pwd/generatePassword.jsp']")).click();

		Thread.sleep(3000);


		System.out.println("Tittle of ESS Generate Password Page is\t:"+driver.getTitle());

		Assert.assertEquals("Generate your Jio password?",driver.getTitle());

		System.out.println("Tittle of ESS Generate Password Page is matched");

	}

	@Then("^user validates Static Text and image on Generate Password Page$")
	public void user_validates_Static_Text_and_image_on_Generate_Password_Page() throws Exception 
	{

		boolean generatePasswordImage=driver.findElement(By.xpath("//*[@id=\"header\"]/img")).isDisplayed();

		System.out.println("Image on the JIO ESS Generate Password Page is Displayed:\t"+generatePasswordImage);

		Assert.assertTrue( "Image on the JIO ESS Generate Password Page is not Displayed",generatePasswordImage);

		String GeneratePwdFirstTextCheck= driver.findElement(By.xpath("//*[@id=\"content-inner\"]/div[1]/p")).getText();

		Assert.assertEquals("Generate Password",GeneratePwdFirstTextCheck);

		System.out.println("Fist Static Text of JIO Generate Password page is\t:"+GeneratePwdFirstTextCheck);


		String GeneratePwdSecondTextCheck= driver.findElement(By.xpath("//*[@id=\"signin-msg\"]/p")).getText();

		Assert.assertEquals("Enter store user id to generate your password.",GeneratePwdSecondTextCheck);

		System.out.println("Second Static Text of JIO Generate Password page is\t:"+GeneratePwdSecondTextCheck);		

		Thread.sleep(3000);

	}

	@Then("^Validates username field & continue button  on Generate Password Page$")
	public void validates_username_field_continue_button_on_Generate_Password_Page() throws Exception 
	{

		boolean userName=driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/input[1]")).isEnabled();
		System.out.println("Username field on Generate Password Page is:\t"+userName);
		Assert.assertTrue("Username field on Generate Password Page is not Displayed",userName);


		boolean Continue1=driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/input[2]")).isEnabled();
		System.out.println("Login button on Generate Password Page is enabled\t"+Continue1);
		Assert.assertTrue("Login button on Generate Password Page is not enabled",Continue1);

		driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/input[2]")).click();

		TakeScreenshot.captuerScreenshot(driver,"ESS Generate Password Page");


	}

	@Then("^Validates invalid username and its corresponding error message  on Generate Password Page$")
	public void validates_invalid_username_and_its_corresponding_error_message_on_Generate_Password_Page() throws Exception 
	{

		driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/input[1]")).sendKeys("Kite");
		driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/input[2]")).click();


		String invaliduserCheck= driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/p")).getText();
		System.out.println("User Name Validation failed on Generate Password Page due to\t:"+invaliduserCheck);

		Assert.assertEquals("User Kite does not exist!",invaliduserCheck);

		System.out.println("Error Message Matched for invalid user name matched on Generate Password");


		TakeScreenshot.captuerScreenshot(driver,"Generate Password-Invalid user id");

	}

	@Then("^Validates error message when no username is entered on Generate Password Page$")
	public void validates_error_message_when_no_username_is_entered_on_Generate_Password_Page() 
	{

		driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/input[2]")).click();

		System.out.println("Error Message on not entering any username of Generate Forgot password page:Please fill out this field.");


		try {
			TakeScreenshot.captuerScreenshot(driver,"Generate Password-Invalid user id");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		driver.navigate().back();
		driver.navigate().back();

	}






}
