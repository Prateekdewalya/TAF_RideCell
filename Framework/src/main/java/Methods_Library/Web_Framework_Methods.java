package Methods_Library;

import org.openqa.selenium.WebElement;

import Web_Base.TestBase;

public class Web_Framework_Methods extends TestBase {
	/*
	 * public static boolean verifyPageTitile(String expectedPageTitle) { try {
	 * String currentPageTitle = driver.getTitle(); if
	 * (currentPageTitle.equals(expectedPageTitle)) { return true; } else { return
	 * false; } } catch (Exception e) { System.out.println(e.getMessage()); } return
	 * false; }
	 */

	public static boolean clickonObject(WebElement strObject)
	{
		boolean blnStatus = false;
		try 
		{ 
			strObject.click();
			blnStatus= true;
			return blnStatus;
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			return blnStatus;
		}
	}

}
