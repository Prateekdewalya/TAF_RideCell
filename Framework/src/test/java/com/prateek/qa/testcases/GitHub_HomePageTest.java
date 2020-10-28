package com.prateek.qa.testcases;
import java.util.ArrayList;
import java.util.Collections;

import org.testng.Assert;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import com.relevantcodes.extentreports.LogStatus;

import Methods_Library.API_Application_Methods;
import Web_Base.TestBase;
import Web_Pages.GitHub_HomePage;



public class GitHub_HomePageTest extends TestBase
{
	GitHub_HomePage obj_Github_HomePage;
	API_Application_Methods obj_Github_API_App_Methods;
	public GitHub_HomePageTest() 
	{
		super(); // to int the parent constructor 
	}

	@BeforeMethod
	public void setup()
	{
		initialization();
		obj_Github_HomePage = new GitHub_HomePage();
		obj_Github_API_App_Methods = new API_Application_Methods();
	}

	@Test(enabled =true, priority=1, description= "GitHub django - Web and API  Test Case for Avaiable Repositories Validation")

	public void GitHub_TC_PrintAllRepoName()
	{
		
		obj_Github_HomePage.clickonRepoLink();
		ArrayList<String> web_list = obj_Github_HomePage.getTheLinkDetails();
		ArrayList<String> api_list = obj_Github_API_App_Methods.Get_API_Repo_Details(200,"name");
		Collections.sort(web_list); // Sorting the  ArrayList data for Web Platform
		Collections.sort(api_list);// Sorting the  ArrayList data for API Platform
		boolean blnStatus = web_list.equals(api_list);
		//Assert.assertEquals(api_list, web_list, "Not having the correct order");
		if (blnStatus)
		{
			test.log(LogStatus.PASS, " Web Platform data is matching with API Platform Data which have total : " + web_list.size() +" records : " + web_list );
		}
		else
		{
			test.log(LogStatus.FAIL, " Web Platform data has " + web_list.size()+ " which is not matching with API Platform count: " + api_list.size()
			+  " \n Data : Web Platform Data : - " + web_list + "\n API Platform Data : - " + api_list);
		}

	}

	@AfterMethod
	public void tearDown()
	{
		driver.quit();
	}
}
