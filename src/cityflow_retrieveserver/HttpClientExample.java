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
import java.util.Date;
 
import javax.net.ssl.HttpsURLConnection;
import org.json.*;

public class HttpClientExample {
 
	private final String USER_AGENT = "Mozilla/5.0";
 
	public static void main(String[] args) throws Exception {
 
		HttpClientExample http = new HttpClientExample();
 
		System.out.println("Testing 1 - Search Instagram by Location");
                Float lat = (float) 41.389639;
                Float lng = (float) 2.176743;
                Float rad = (float) 1000;
                long min_timestamp = 300;
		String response; 
                response = http.searchInstagramByLocation(lat,lng,rad,min_timestamp);
                //JSONObject obj = new JSONObject(response);
//                String pageName = obj.getJSONObject("pageInfo").getString("pageName");
//                JSONArray arr = obj.getJSONArray("posts");
//                for (int i = 0; i < arr.length(); i++)
//                {
//                    String post_id = arr.getJSONObject(i).getString("post_id");
//                    ......
//                }
                System.out.println("\n"+response);
                
		//System.out.println("\nTesting 2 - Send Http POST request");
		//http.sendPost();
 
	}
 
	// HTTP GET request
	private String searchInstagramByLocation(Float lat,Float lng, Float rad, long min_timestamp) throws Exception {
 
		          
                String url = "https://api.instagram.com/v1/media/search";
                Date now = new Date();
                Long timestamp;
                timestamp = now.getTime()/1000-min_timestamp;
                url = url.concat("?lat=").concat(lat.toString())
                        .concat("&lng=").concat(lng.toString())
                        .concat("&distance=").concat(rad.toString())
                        .concat("&min_timestamp=").concat(timestamp.toString())
                        .concat("&client_id=f270041f78bc441f9998a741db100261"); //Instagram developer ID
                
 
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
 
	// HTTP POST request
	private void sendPost() throws Exception {
 
		String url = "https://selfsolve.apple.com/wcResults.do";
		URL obj = new URL(url);
		HttpsURLConnection con = (HttpsURLConnection) obj.openConnection();
 
		//add reuqest header
		con.setRequestMethod("POST");
		con.setRequestProperty("User-Agent", USER_AGENT);
		con.setRequestProperty("Accept-Language", "en-US,en;q=0.5");
 
		String urlParameters = "sn=C02G8416DRJM&cn=&locale=&caller=&num=12345";
 
		// Send post request
		con.setDoOutput(true);
		DataOutputStream wr = new DataOutputStream(con.getOutputStream());
		wr.writeBytes(urlParameters);
		wr.flush();
		wr.close();
 
		int responseCode = con.getResponseCode();
		System.out.println("\nSending 'POST' request to URL : " + url);
		System.out.println("Post parameters : " + urlParameters);
		System.out.println("Response Code : " + responseCode);
 
		BufferedReader in = new BufferedReader(
		        new InputStreamReader(con.getInputStream()));
		String inputLine;
		StringBuffer response = new StringBuffer();
 
		while ((inputLine = in.readLine()) != null) {
			response.append(inputLine);
		}
		in.close();
 
		//print result
		System.out.println(response.toString());
 
	}
 
}