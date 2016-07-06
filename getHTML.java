import java.net.*;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Calendar;


public class getHTML {



	public String serviceURL;
	public JsonRpcRequestViaHttp server;
	public static int id = 0;

	public getHTML(String serviceURL) 
	{
		this.serviceURL = serviceURL;

		try 
		{
			this.server = new JsonRpcRequestViaHttp(new URL(serviceURL));
		} 

		catch (Exception ex) 
		{
			System.out.println("Malformed URL " + ex.getMessage());
		}
	}

	

	public getHTML() {
		// TODO Auto-generated constructor stub
	}



	public JSONObject fetchObject(){
		JSONObject res = null;
		try{
			//System.out.println("sending: "+jsonStr);
			String resString = server.call("noMessage");
			//System.out.println("got back: "+resString);
			res = new JSONObject(resString);
			
			
		}catch(Exception ex){
			System.out.println("exception in rpc call to get json object: "+ex.getMessage());
		}
		
		return res;
	}
	
	public JSONObject getLocation()
	{
		String url = "http://ip-api.com/json";
		getHTML cjc = new getHTML(url);
		JSONObject sendThis = null;

		
		try{
			JSONObject obj = cjc.fetchObject();
			
			
	
			//pj will hold the JSON object that will be sent back to the request
			ParseJson pj = new ParseJson(obj);
			
	
	         

	        
	         sendThis = pj.toJson();
	         

		}catch (Exception ex) {
			System.out.println("Exception importing from json: "+ex.getStackTrace());
		}
		
		return sendThis;
	}

	
	
	
	
	
	
	
	public static void main(String[] args) throws Exception 
	{
		
	
		getHTML gh = new getHTML();
		
		JSONObject jo = new JSONObject();
		jo = gh.getLocation();
		
		String [] names = JSONObject.getNames(jo);
        
        System.out.print("names are: ");
        
        for(int j=0; j< names.length; j++)
        {
           System.out.print(names[j]+", ");
        }
        
        ParseJson pj = new ParseJson(jo);
        System.out.print(pj.getLat());
        System.out.print(pj.getLon());
        

		
	}
	



}
