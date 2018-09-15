package com.qa.StepDefinition;

import org.junit.Assert;

import org.openqa.selenium.By;
import com.qa.BaseClass.TestBaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import com.qa.Utility.TakeScreenshot;


public class ForgotPasswordStepDefenition extends TestBaseClass


{

	@Given("^user is on ESS Forgot Password Page$")
	public void user_is_on_ESS_Forgot_Password_Page() 
	{
		logger.info("Welcome to Forgot Password Page");
	}

	@Then("^user validates Logo and Tittle Forgot Password home page$")
	public void user_validates_Logo_and_Tittle_Forgot_Password_home_page() 
	{
		driver.findElement(By.xpath("//a[@href='https://idm.jioconnect.com/oam-forgot-password/forgotPassword.jsp?resource_url=%252Fuser%252Floginsso']")).click();
		
		
		Assert.assertEquals("Forgot Jio password?",driver.getTitle());
		
		System.out.println("Tittle of Page is\t:"+driver.getTitle());
		
        boolean forgotPasswordImage=driver.findElement(By.xpath("//*[@id=\"header\"]/img")).isDisplayed();
		
		System.out.println("Image on the JIO Forgot Password Page is Displayed:\t"+forgotPasswordImage);
		
		Assert.assertTrue("Image on the JIO Forgot Password Page is not Displayed",forgotPasswordImage);
		
		logger.info("Image on the JIO Forgot Password Page is Displayed");
	}

	@Then("^user validates second and third tittle of Forgot Password home page$")
	public void user_validates_second_and_third_tittle_of_Forgot_Password_home_page() 
	{
		
	String secondTittleCheckF= driver.findElement(By.xpath("//*[@id=\"content-inner\"]/div[1]/p")).getText();
		
		Assert.assertEquals("Forgot Password?",secondTittleCheckF);
		
		System.out.println("Second Tittle of ESS Forgot Password Page is\t:"+secondTittleCheckF);
		
				
		// Third Tittle Validation on ESS Forgot Password Page
		
		String thirdTittleCheckF=driver.findElement(By.xpath("//*[@id=\"signin-msg\"]/p")).getText();
		
		Assert.assertEquals("Enter the user name to reset your password.",thirdTittleCheckF);
		
		System.out.println("Third Tittle of ESS Forgot Password Page is\t:"+thirdTittleCheckF);
		
		
		boolean Continue=driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/input[2]")).isEnabled();
		
		Assert.assertTrue("Continue button is not enabled",Continue);
		
		System.out.println("Continue button is enabled\t"+Continue);
	 
	}

	@Then("^Validates Error Message on entering incorrect userid/pwd on Password page$")
	public void validates_Error_Message_on_entering_incorrect_userid_pwd_on_Password_page() 
	{
	driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/input[1]")).sendKeys("ankur.j");
		
		driver.findElement(By.xpath("//input[@name='action']")).click();
		
           
	    String element2=driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/p")).getText();
	    
	    Assert.assertEquals("User is not present in OIAM",element2);
	    	    	    
	     System.out.println("Forgot Pasword Failed with error message\t:"+element2);
	     
	     try 
	     {
			Thread.sleep(3000);
			 
			 TakeScreenshot.captuerScreenshot(driver,"ESS Forgot Password Page");
		}
		 catch (Exception e)
	     {
			System.out.println("Error Message is:/t"+e.getMessage());
			e.printStackTrace();
		}
	  
	}

	@Then("^Validates Error Message on not entering any userid/pwd on forgot Password page$")
	public void validates_Error_Message_on_not_entering_any_userid_pwd_on_forgot_Password_page() 
	{
		
	driver.findElement(By.xpath("//input[@name='action']")).click();
		
		System.out.println("Error Message on not entering any username of Forgot password page:Please fill out this field.");
		
		
		try 
		{
			TakeScreenshot.captuerScreenshot(driver,"Generate Forgot-Invalid user id");
		} 
		catch (Exception e)
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	    
	}

	@Then("^Validates Message on not entering correct userid/pwd on Forgot Password page$")
	public void validates_Message_on_not_entering_correct_userid_pwd_on_Forgot_Password_page() 
	{
	  
		driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/input[1]")).sendKeys("ankur.jain");
		
		driver.findElement(By.xpath("//input[@name='action']")).click();
		
		System.out.println("Moved to next page of forgot password page");
		
	   String element2=driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[2]/div[1]/div[1]/p[1]")).getText();
		
	   Assert.assertEquals("Confirm your details",element2);
	   
	   System.out.println("Validation for Correct user id password on Forgot password is passed");
	   
	   driver.navigate().back();
	  driver.navigate().back();
	  driver.navigate().back();
		
	}
	
	
	
	
	
	
}
