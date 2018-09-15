package com.qa.StepDefinition;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebElement;

import com.qa.BaseClass.TestBaseClass;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;

public class LoginStepDefenition extends TestBaseClass
{

	
	@Given("^user is able to login into ESS using valid username as \"([^\"]*)\" and password as \"([^\"]*)\"$")
	public void user_is_able_to_login_into_ESS_using_valid_username_as_and_password_as(String username, String password) 
	{
		boolean username1=driver.findElement(By.xpath("//input[@type='text']")).isDisplayed();
		System.out.println("Username/Email field on JIO ESS Home page is Displayed:\t"+username1);
		driver.findElement(By.xpath("//input[@type='text']")).sendKeys(username);

		boolean password1=driver.findElement(By.xpath("//input[@type='text']")).isDisplayed();
		System.out.println("Password field on JIO ESS Home page is Displayed:\t"+password1);
		driver.findElement(By.xpath("//input[@type='password']")).sendKeys(password);

		driver.findElement(By.xpath("//*[@id=\"content-inner\"]/form/input[5]")).click();

		System.out.println("Login Sucesfull");
		
		
	}

	@Then("^user validates Tittle and image on Jio Central Page$")
	public void user_validates_Tittle_and_image_on_Jio_Central_Page() throws Exception 
	{

		
		Thread.sleep(5000);

		Assert.assertEquals(driver.getTitle(),"Home - Jio Central");

		System.out.println("Tittle of page after Login Page is\t:"+driver.getTitle());
		Thread.sleep(5000);

		boolean jioCentralPageImage=driver.findElement(By.xpath("//a[@class='navbar-brand']")).isDisplayed();

		System.out.println("Image on the JIO ESS HomePage is Displayed:\t"+jioCentralPageImage);

		boolean jioCentralPagesideImage=driver.findElement(By.xpath("//a[@href='https://jiocentral.jioconnect.com/group/jiocentral/home'][contains(text(),'JioCentral')]")).isDisplayed();

		System.out.println("Side Image on the JIO ESS HomePage is Displayed:\t"+jioCentralPagesideImage);

	}

	@Then("^user validates Links and elements inside frames of Jio Central Page$")
	public void user_validates_Links_and_elements_inside_frames_of_Jio_Central_Page() throws InterruptedException 
	{
		Thread.sleep(2000);

		List<WebElement> links= driver.findElements(By.tagName("a"));

		System.out.println("Total number of links on JIO central are:\t"+links.size());

		for (int i = 1; i<links.size(); i=i+1)

		{


			System.out.println("Name of Links on JIO central are:\t"+links.get(i).getText());

		}

		/*	 --------------------------------------------*/
		//List<WebElement> links1= driver.findElements(By.xpath("//h4[@class='jioBoxHeading']"));

		List<WebElement> links2= driver.findElements(By.xpath("//div[@class='jioBoxContent']"));

		System.out.println("Total Number of boxes are :\t"+links2.size());

		HashMap<String,List<String>> tagsMap = new HashMap<String, List<String>>();

		for (int i = 1; i<links2.size(); i=i+1)
		{
			WebElement myElement = links2.get(i);
			WebElement parent = (WebElement) ((JavascriptExecutor) driver).executeScript(
					"return arguments[0].parentNode;", myElement);

			String splitStr [] = parent.getText().split("\n");
			List<String> contentList = new  ArrayList<String>();

			for (int j = 1; j < splitStr.length; j++) {
				contentList.add(splitStr[j]);
			}
			tagsMap.put(splitStr[0],contentList);

		}

		for(String key : tagsMap.keySet()) {
			System.out.println("===============================================");
			System.out.println("Header : "+key);
			System.out.println("===============================================");
			for(String value : tagsMap.get(key)) {
				System.out.print(value+", ");
			}
			System.out.println("----------------------------------------------------");
		}

	}

	@Then("^Validates frames and Moving window from Jio Central page to Employee Central$")
	public void validates_frames_and_Moving_window_from_Jio_Central_page_to_Employee_Central() throws InterruptedException 
	{
		int size=driver.findElements(By.xpath("//iframe")).size();

		System.out.println("Total Size of iframe is"+size);

		//Xpath for JIO ESS
		driver.findElement(By.xpath("//a[@href='https://ess.jio.com']")).click();

		Thread.sleep(3000);

		// It will return the parent window name as a String
		String parent=driver.getWindowHandle();

		// This will return the number of windows opened by Webdriver and will return Set of St//rings
		Set<String>s1=driver.getWindowHandles();

		// Now we will iterate using Iterator
		Iterator<String> I1= s1.iterator();

		while(I1.hasNext())
		{

			String child_window=I1.next();

			// Here we will compare if parent window is not equal to child window then we  will close

			if(!parent.equals(child_window))
			{
				driver.switchTo().window(child_window);

				System.out.println("Name of window is:\t"+driver.switchTo().window(child_window).getTitle());
			}
		}
	}

			@Then("^Validates Tittle and image on Employee Central Page$")
			public void validates_Tittle_and_image_on_Employee_Central_Page() 
			{

				Assert.assertEquals("Employee Central",driver.getTitle());

				System.out.println("Tittle of page after Login Page is\t:"+driver.getTitle());


				boolean jioEmpCentralPageImage=driver.findElement(By.xpath("//div[@class='logo']")).isDisplayed();

				System.out.println("Image on the JIO ESS Centeral Page is Displayed:\t"+jioEmpCentralPageImage);

				String jioEmpCentralPagesideImage=driver.findElement(By.xpath("//div[@class='portal-title']")).getText();

				System.out.println("Text of Image on the JIO ESS HomePage is Displayed:\t"+jioEmpCentralPagesideImage);
			}

		}




	
