package GetCCLocation;
import java.io.*;
import java.net.*;

import org.json.JSONException;
import org.json.JSONString;
import org.json.JSONObject;
import org.json.JSONTokener;




public class ParseJson 
{
	double lat, lon;
	
	public ParseJson() 
	{
	}

	
	public ParseJson(JSONObject obj) throws JSONException
	{
	    
		lat = obj.getDouble("lon");
		lon = obj.getDouble("lat");
		
	}
	
	
	

	public JSONObject toJson() throws JSONException
	{
		JSONObject obj = new JSONObject();
		obj.put("lat",lat);
		obj.put("lon",lon);
		obj.put("statusCode", 200);
		
		return obj;
	}
	
	public double getLat() {
		return lat;
	}


	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLon() {
		return lon;
	}

	public void setLon(double lon) {
		this.lon = lon;
	}



	
	

}