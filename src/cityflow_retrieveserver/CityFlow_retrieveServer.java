/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cityflow_retrieveserver;

import java.util.ArrayList;
import java.util.Date;
import org.json.*;

/**
 *
 * @author Miquel
 */
public class CityFlow_retrieveServer {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
		
 
		System.out.println("Testing 1 - Search Instagram by Location");
                
                Float lat = (float) 41.389639;
                Float lng = (float) 2.176743;
                Float rad = (float) 1000;
                long min_timestamp = 300;
                
		ArrayList<InstagramPost> postList = searchInstagramPostsByLocation(lat,lng,rad,min_timestamp);
                
                
                System.out.println("\n");
                
		//System.out.println("\nTesting 2 - Send Http POST request");
		//http.sendPost();
 
    }
    
    private static ArrayList<InstagramPost> searchInstagramPostsByLocation(Float lat,Float lng, Float rad, long min_timestamp) throws Exception  {

        String response; 
        HttpClient http = new HttpClient();

        response = http.searchInstagramByLocation(lat,lng,rad,min_timestamp);

        JSONObject obj = new JSONObject(response);
        JSONArray arr = obj.getJSONArray("data");


        ArrayList<InstagramPost> postList = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++)
        {
            JSONObject post = arr.getJSONObject(i);

            String type = post.getString("type");

            String caption;
            if(post.isNull("caption")){
                caption = "";
            }else{
                caption = post.getJSONObject("caption").getString("text");
            }

            String tags = "";
            JSONArray tagArr = post.getJSONArray("tags");
            for (int j = 0; j < tagArr.length(); j++){
                if(j==0){
                     tags=tagArr.getString(j);
                }else{
                    tags=tags.concat(","+tagArr.getString(j));
                }
            }

            String im_link = post.getJSONObject("images").getJSONObject("standard_resolution").getString("url");

            Float latitude = (float) post.getJSONObject("location").getDouble("latitude");
            Float longitude = (float) post.getJSONObject("location").getDouble("longitude");

            Integer likes = post.getJSONObject("likes").getInt("count");

            Date now = new Date();

            // CALCULAR IDNEIGHBOURHOOD


            postList.add( new InstagramPost(type,now,caption,tags,im_link,latitude,longitude,likes,0));               
        }
        return postList;
    }
}
