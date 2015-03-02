/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cityflow_retrieveserver;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
 
import javax.net.ssl.HttpsURLConnection;
import org.json.*;

public class HttpClient {
 
	private final String USER_AGENT = "Mozilla/5.0";
 
	
 
	
	public String searchInstagramByLocation(Float lat,Float lng, Float rad, long min_timestamp) throws Exception {
 
		          
                String url = "https://api.instagram.com/v1/media/search";
                Date now = new Date();
                Long timestamp;
                timestamp = now.getTime()/1000-min_timestamp;
                url = url.concat("?lat=").concat(lat.toString())
                        .concat("&lng=").concat(lng.toString())
                        .concat("&distance=").concat(rad.toString())
                        .concat("&min_timestamp=").concat(timestamp.toString())
                        .concat("&client_id=f270041f78bc441f9998a741db100261"); //Instagram developer ID
                //System.out.println(url);
 
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		// optional default is GET
		con.setRequestMethod("GET");
 
		//add request header
		con.setRequestProperty("User-Agent", USER_AGENT);
 
		//int responseCode = con.getResponseCode();
                
		//System.out.println("\nSending 'GET' request to URL : " + url);
		//System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//return result
		return response.toString();
 
	}
 
 
}