/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cityflow_retrieveserver;

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
        
		HttpClient http = new HttpClient();
 
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
    
}
