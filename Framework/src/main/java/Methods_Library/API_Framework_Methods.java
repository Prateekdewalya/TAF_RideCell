package Methods_Library;

import com.relevantcodes.extentreports.LogStatus;

public class API_Framework_Methods {
	
	public static int getStatusCode()
	{
		// to validate the response code for given API End Point 
		int currentStatusCode = API_Application_Methods.response.getStatusCode(); 
		return currentStatusCode;
	
	}

}
