package Framework_Utils;

import java.io.FileInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class FileandEnv {

	public static Map<String,String> fileandenv = new HashMap<String,String>();
	public static Properties propMain = new Properties();
	public static Properties propPreSet = new Properties();

	public static Map<String,String> envAndFile()
	{
		String environment = "test"; // Can change as per requirement
		try
		{
			if(environment.equalsIgnoreCase("production"))
			{
				FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "/inputs/Production.properties"); 
				propMain.load(fisDev);
				fileandenv.put("ServerURL", propMain.getProperty("ServerURL"));

			}

			else if(environment.equalsIgnoreCase("test"))
			{
				FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "/inputs/Production.properties"); 
				propMain.load(fisDev);
				fileandenv.put("ServerURL", propMain.getProperty("ServerURL"));

			}

			else if(environment.equalsIgnoreCase("staging"))
			{
				FileInputStream fisDev = new FileInputStream(System.getProperty("user.dir") + "/inputs/Production.properties"); 
				propMain.load(fisDev);
				fileandenv.put("ServerURL", propMain.getProperty("ServerURL"));

			}
		}

		catch (Exception e)
		{

		}
		return fileandenv;

	}

	public static Map<String,String> getConfigReader()
	{

		if (fileandenv==null)
		{
			fileandenv = envAndFile();
		}
		return fileandenv;

	}
}
