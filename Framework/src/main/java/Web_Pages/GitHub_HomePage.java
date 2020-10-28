package Web_Pages;
import java.util.ArrayList;
import java.util.List;
import org.openqa.selenium.WebElement; 
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.How;
import org.openqa.selenium.support.PageFactory;
import org.testng.annotations.Listeners;

import com.relevantcodes.extentreports.LogStatus;

import Framework_Utils.ExtentReportListner;
import Methods_Library.Web_Lib_Methods;
import Web_Base.TestBase;

@Listeners(ExtentReportListner.class)
public class GitHub_HomePage extends TestBase
{
	// Object Repository for given page 
	@FindBy(xpath="//a[@class='UnderlineNav-item selected']") WebElement link_Repositories;
	@FindBy(how = How.XPATH, using = "//a[@data-hovercard-type='repository']")
	List<WebElement> name_Of_Repositories;

	//Initializing the Page Objects 
	public GitHub_HomePage()
	{
		PageFactory.initElements(driver, this); 
	}

	public void clickonRepoLink()
	{
		try
		{
			boolean blnStatus = Web_Lib_Methods.clickonObject(link_Repositories);
			if (blnStatus)
			{
				test.log(LogStatus.PASS, " Repostiory link is clicked successfully" );
			}
			else
			{
				test.log(LogStatus.FAIL, " Repostiory link is clicked successfully" );
			}
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			test.log(LogStatus.ERROR, " repostiory is not clicked and exeception occured " + e.getMessage());
		}
	}


	public ArrayList<String> getTheLinkDetails()
	{
		ArrayList<String> allRepoNames =null;
		try
		{ 
			allRepoNames = new ArrayList<String>();
			for (WebElement RepoName : name_Of_Repositories)
			{
				String currentRepoName = RepoName.getText();
				allRepoNames.add(currentRepoName);
			}

			if (!allRepoNames.isEmpty())
			{
				System.out.println("All the Repository " + allRepoNames.size() +  "  which are hosted on Github page :  " + allRepoNames.toString());
			}
			else
			{
				test.log(LogStatus.FAIL, " API reponse is empty or having issue- " + allRepoNames.toString() );
			}
			return allRepoNames;
		}
		catch (Exception e)
		{
			test.log(LogStatus.ERROR, " repostiory is not successfully and exeception occured " + e.getMessage());
			return allRepoNames;
		}
	}
}