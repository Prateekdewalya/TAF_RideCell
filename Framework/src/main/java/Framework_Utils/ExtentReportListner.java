package Framework_Utils;

import java.io.File;

import org.testng.ITestContext;
import org.testng.ITestListener;
import org.testng.ITestResult;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class ExtentReportListner implements ITestListener {
	public static ExtentReports reports;
	public static ExtentTest test;

	private static String resultpath = getResultPath();

	public static void deleteDirectory(File directory)
	{
		if (directory.exists())
		{
			File[] files = directory.listFiles();
			if(null!= files)
			{
				for (int i=0; i<files.length; i++)
				{
					System.out.println(files[i].getName());
					if(files[i].isDirectory())
					{
						deleteDirectory(files[i]);
					}
					else
					{
						files[i].delete();
					}
				}
			}
		}
	}

	private static String getResultPath() {
		// TODO Auto-generated method stub

		resultpath  = "test";
		if (!new File(resultpath).isDirectory())
		{
			new File(resultpath);
		}
		return resultpath;
	}

	String ReportLocation = "ExtentReport/" + resultpath + "/";
	public void onTestStart (ITestResult result)
	{
		test = reports.startTest(result.getMethod().getDescription());
		//test.log(LogStatus.INFO, result.getMethod().getDescription());

		System.out.println(result.getMethod().getDescription());	
		System.out.println(result.getMethod().getMethodName());

	}

	public void onTestSuccess(ITestResult result)
	{
		test.log(LogStatus.PASS, "Test is passed for this method");
	}

	public void onTestFailure(ITestResult result)
	{
		test.log(LogStatus.FAIL, "Test is Fail");
	}

	public void onTestSkip(ITestResult result)
	{
		test.log(LogStatus.SKIP, "Test is Skipped");
	}

	public void onTestError(ITestResult result)
	{
		test.log(LogStatus.ERROR, "Test is failed due to Error");
	}

	public void onTestWarning(ITestResult result)
	{
		test.log(LogStatus.WARNING, "Test is Failed due to WARNING");
	}
	public void onStart(ITestContext context)
	{
		System.out.println("ReportLocation -  "+ ReportLocation );
		reports = new ExtentReports(ReportLocation + "ExtentReport.html");
		//test = reports.startTest("");
	}

	public void onFinish(ITestContext context)
	{
		reports.endTest(test);
		reports.flush();
	}
}
