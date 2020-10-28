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


}


