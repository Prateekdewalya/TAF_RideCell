package Framework_Utils;

import java.util.Iterator;

import org.json.JSONArray;
import org.json.JSONObject;

public class ParseDyanmicJson {

	public static void getKey (JSONObject json, String key)

	{
		boolean exists  = json.has(key);
		Iterator<?> keys;
		String nextKeys;
		if (!exists)
		{
			keys = json.keys();
			while (keys.hasNext())
			{
				nextKeys = (String) keys.next();
				try
				{
					if(json.get(nextKeys) instanceof JSONObject)
					{
						if (exists == false)
						{
							getKey(json.getJSONObject(nextKeys), key);
						}
					}
					else if (json.get(nextKeys) instanceof JSONArray)
					{
						JSONArray jsonarray = json.getJSONArray(nextKeys);
						for (int i=0;i<jsonarray.length();i++)
						{
							String jsonarraystring = jsonarray.get(i).toString();
							 JSONObject innerJSON = new JSONObject(jsonarraystring);
							 
							 if (exists==false)
							 {
								 getKey(innerJSON, key);
							 }
							
						}
							
					}
				}
				catch (Exception e)
				{

				}
			}
		}

		else
		{
			parseObject(json,key);
		}

	}

	public static void parseObject(JSONObject json, String key) {
		// TODO Auto-generated method stub
		//System.out.println(json.has(key));
		System.out.println(json.get(key));

	}
	
	/*public static void main (String args[])
	{
		String inputJSON = "{\r\n" + 
				"   \"Message\":\"Number of Post office(s) found: 2\",\r\n" + 
				"   \"Status\":\"Success\",\r\n" + 
				"   \"PostOffice\":[\r\n" + 
				"      {\r\n" + 
				"         \"Name\":\"New Delhi G.P.O.\",\r\n" + 
				"         \"Description\":\"\",\r\n" + 
				"         \"PINCode\":\"110001\",\r\n" + 
				"         \"BranchType\":\"Head Post Office\",\r\n" + 
				"         \"DeliveryStatus\":\"Delivery\",\r\n" + 
				"         \"Circle\":\"New Delhi\",\r\n" + 
				"         \"District\":\"New Delhi\",\r\n" + 
				"         \"Division\":\"New Delhi GPO\",\r\n" + 
				"         \"Region\":\"Delhi\",\r\n" + 
				"         \"State\":\"Delhi\",\r\n" + 
				"         \"Country\":\"India\"\r\n" + 
				"      },\r\n" + 
				"      {\r\n" + 
				"         \"Name\":\"New Delhi South Ext-II\",\r\n" + 
				"         \"Description\":\"\",\r\n" + 
				"         \"PINCode\":\"110049\",\r\n" + 
				"         \"BranchType\":\"Sub Post Office\",\r\n" + 
				"         \"DeliveryStatus\":\"Non-Delivery\",\r\n" + 
				"         \"Circle\":\"New Delhi\",\r\n" + 
				"         \"District\":\"South Delhi\",\r\n" + 
				"         \"Division\":\"New Delhi South\",\r\n" + 
				"         \"Region\":\"Delhi\",\r\n" + 
				"         \"State\":\"Delhi\",\r\n" + 
				"         \"Country\":\"India\"\r\n" + 
				"      }\r\n" + 
				"   ]\r\n" + 
				"}";
				
		
		JSONObject jsonobject1 = new JSONObject(inputJSON);
		getKey(jsonobject1, "Name");
		*/
	}
	

