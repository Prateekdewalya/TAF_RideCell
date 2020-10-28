package Methods_Library;

import java.util.ArrayList;
import org.json.JSONArray;
import org.json.JSONObject;
import org.testng.annotations.Listeners;
import com.relevantcodes.extentreports.LogStatus;
import API_Config.APIPath;
import io.restassured.RestAssured;
import io.restassured.response.Response;
import Framework_Utils.ExtentReportListner;

@Listeners(ExtentReportListner.class)
public class API_Application_Methods extends ExtentReportListner {
	JSONObject obj_JSON ;
	ArrayList<String> repoList =null;
	public static Response response;

	/**
	 * @Method_Name - Get_API_Repo_Details()
	 * @Objective -  Objective to return the name of all repositories for given end point 
	 * with respect the configuration 
	 * @Author - Prateek Gupta
	 * @version - v1.0
	 * @creation_date - 27/Oct/2020
	 * @Modify -  N/A
	 */

	public ArrayList<String> Get_API_Repo_Details(int expectedStatusCode, String setAttributeValue)
	{
		try
		{
			response = RestAssured.given().get(APIPath.apiPath.GET_LIST_OF_Repositories);
			
			// to validate the response code for given API End Point 
			int actualStatusCode = response.getStatusCode(); 
			if (actualStatusCode==expectedStatusCode) 
			{
				test.log(LogStatus.PASS, "API End Point Status code is expected : " + actualStatusCode ); 
			} 
			else
			{ 
				test.log(LogStatus.FAIL, "API End Point Status code is not expected: " + actualStatusCode );
			}
			
			// to validate the performance of given API End Point 

			long actualResponseTime = response.getTime();
			if (actualResponseTime<APIPath.apiPath.expectedResponseTime)
			{ 
				test.log(LogStatus.PASS, "API End Point Response Time is under expected limit  which is : " +APIPath.apiPath.expectedResponseTime + " and current actual response time : " +  actualResponseTime);
			}
			else 
			{
				test.log(LogStatus.FAIL, "API End Point Response Time is not under expected limit  which is :  " +APIPath.apiPath.expectedResponseTime + " and current actual response time : " +  actualResponseTime);
			}

			// to check the JSON Array response and extract the name field details as per give problem statement
			JSONArray jsonarray = new JSONArray(response.getBody().asString());
			{
				repoList = new ArrayList<String>();
				for (int i=0; i<=jsonarray.length()-1;i++) 
				{ 
					obj_JSON = jsonarray.getJSONObject(i);
					System.out.println(obj_JSON.get(setAttributeValue)); // Command line Debugging purpose
					repoList.add(obj_JSON.get(setAttributeValue).toString()); // Adding the extract value for name attribute and storing in Arraylist for future validation
				}
				//test.log(LogStatus.PASS, "Repo details " + repoList);
				System.out.println(repoList); // Debugging purpose
			}
			return repoList;
		}

		catch(Exception e)
		{
			System.out.println("Exception occured" + e.getMessage());
			test.log(LogStatus.ERROR, "Exception occured during the testing" + e.getMessage());
			return repoList;
		}
	}
}
