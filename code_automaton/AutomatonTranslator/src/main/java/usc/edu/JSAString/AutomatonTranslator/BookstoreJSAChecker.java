package usc.edu.JSAString.AutomatonTranslator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Set;

import org.json.JSONObject;
import org.json.JSONTokener;

public class BookstoreJSAChecker {
	public static void main(String args[]) throws IOException
	{
		InputStream is=new FileInputStream("/home/dingli/svnfiles/StringTestCases/WebappResult/groundtruth/database.txt");
		JSONTokener Jto=new JSONTokener(is);
		JSONObject jobj=new JSONObject(Jto);
		Set<String> keys=jobj.keySet();
		for(String key:keys)
		{
			System.out.println(key);
			System.out.println(jobj.get(key));

			break;
		}
		is.close();

		//jobj.
	}
}
