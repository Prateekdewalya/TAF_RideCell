package Web_Base;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.testng.annotations.Listeners;

import com.relevantcodes.extentreports.LogStatus;

import Framework_Utils.ExtentReportListner;
import Framework_Utils.TestUtils;

@Listeners(ExtentReportListner.class)
public class TestBase extends ExtentReportListner {

	static public WebDriver driver;
	static public Properties prop;
	static public String  frameworkLocationPath = System.getProperty("user.dir");
	static Logger log = Logger.getLogger(TestBase.class);

	/**
	 * @Constructor Name - TestBase()
	 * @Objective -  Constructor to Initialize the property files object
	 * @author - Prateek Gupta
	 * @version -v1.0
	 * @creation_date - 27/Oct/2020
	 * @Modify -  N/A
	 */

	public TestBase()
	{
		try
		{
			prop = new Properties();
			String configFileLocation = frameworkLocationPath + "\\src\\main\\java\\Framework_Config\\Config.properties";
			//System.out.println(configFileLocation); // For internal debugging only
			FileInputStream fis = new FileInputStream(configFileLocation);
			prop.load(fis);
			log.info("property file loaded successfully");
		}

		catch (FileNotFoundException e)
		{
			e.printStackTrace();
			log.error("property file not loaded due to some exception" + e.getMessage());

		}
		catch (Exception e)
		{
			e.printStackTrace();
			log.error("property file not loaded due to some exception" + e.getMessage());
		}
	}


	/**
	 * @Method_Name - initialization()
	 * @Objective -  Objective to initialization of driver 
	 * with respect the configuration as per property file
	 * @Author - Prateek Gupta
	 * @version - v1.0
	 * @creation_date - 27/Oct/2020
	 * @Modify -  N/A
	 */


	public static void initialization() 
	{
		try
		{
			String browserName = prop.getProperty("browser");
			if(browserName.equalsIgnoreCase("chrome"))
			{
				System.setProperty("webdriver.chrome.driver",frameworkLocationPath + "\\lib\\DriverExe\\chromedriver.exe");	
				driver = new ChromeDriver();
			}
			else if(browserName.equalsIgnoreCase("edge"))
			{
				System.setProperty("webdriver.edge.driver",frameworkLocationPath + "\\lib\\DriverExe\\edgedriver.exe");	
				driver = new EdgeDriver();
			}
			driver.manage().window().maximize();
			driver.manage().deleteAllCookies();
			driver.manage().timeouts().pageLoadTimeout(TestUtils.PAGE_LOAD_TIMEOUT, TimeUnit.SECONDS);
			driver.manage().timeouts().implicitlyWait(TestUtils.IMPLICIT_TIME_WAIT, TimeUnit.SECONDS);
			// launch the browser and get the browser from property file
			driver.get(prop.getProperty("url"));
			log.info("Web Driver invoked successfully for URL :   " +prop.getProperty("url") );
		}
		catch (Exception e)
		{
			System.out.println(e.getMessage());
			log.info("Web Driver not invoked  for URL :   " +prop.getProperty("url")  + "  due to exception : " + e.getMessage());

		}
	}

}
