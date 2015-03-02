/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cityflow_retrieveserver;

import dbConnect.Districts;
import dbConnect.Neighbourhoods;
import dbConnect.Posts;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.json.*;
import java.util.Iterator;

/**
 *
 * @author Miquel and Sergi
 */
public class CityFlow_retrieveServer {

    /**
     * @param args the command line arguments
     * @throws java.lang.Exception
     */
    public static void main(String[] args) throws Exception {
        
		
 
                
                //Float lat = (float) 41.389639;
                //Float lng = (float) 2.176743;
                Float rad = (float) 500;
                Long min_timestamp = (long)300;
                System.out.println("Testing 1 - Search Instagram by Location");
                System.out.println("Starting retrieving service... rad="+rad.toString()+" time_interval="+min_timestamp.toString());

                EntityManager entityManager = Persistence.createEntityManagerFactory("CityFlow_retrieveServerPU").createEntityManager();
                
                //Retrieve districts and neighbourhoods list from DB
                Query query = entityManager.createNamedQuery("Districts.findAll");
                List<Districts> districtsList = query.getResultList();
                for(Districts dist : districtsList){
                    dist.doPoly();
                }
                query = entityManager.createNamedQuery("Neighbourhoods.findAll");
                List<Neighbourhoods> neighbourhoodsList = query.getResultList();
                for(Neighbourhoods neig : neighbourhoodsList){
                    neig.doPoly();
                }
                Integer ndist= districtsList.size();
                Integer nneig= neighbourhoodsList.size();
		System.out.println(nneig.toString()+" neighbourhoods & "+ndist.toString()+" districts retrieved from DB...");

                
                entityManager.close();
                
                // Polygon bounds defining city limits
                double[] boundslat = {41.404301, 41.324095, 41.417569, 41.471839};
                double[] boundslng = {2.070192, 2.158517, 2.236232, 2.184016};
                
                //Find centers of the circles to search for insta posts
                ArrayList<double[]> centers = new ArrayList<>();
                centers = findCenterCircles(boundslng,boundslat,rad);
                Integer ncent = centers.size();
                System.out.println(ncent.toString()+" cells created...");
//                for (double[] center : centers){
//                    Double x = center[0];
//                    Double y = center[1];
//                    System.out.println(x.toString()+","+y.toString());
//                }
                
                //Loop, search for posts every "min_timestamp" minutes
                Integer i = 0;
                while(true){
                    i++;
                    System.out.println(i.toString()+" retrieval started...");
                    entityManager = Persistence.createEntityManagerFactory("CityFlow_retrieveServerPU").createEntityManager();
                    entityManager.getTransaction().begin();

                    //Find all posts, once in each circle, repetitions are possible
                    ArrayList<Posts> postList = new ArrayList<>();
                    for (double[] center : centers){
                         postList.addAll(searchInstagramPostsByLocation((float)center[0],(float)center[1],rad,min_timestamp));
                    }
                    Integer npos=postList.size();
                    
                    //Post list to DB
                    for (Posts p : postList) {
                        entityManager.persist(p);
                        //entityManager.flush();
                        //entityManager.refresh(p);
                    }
                    entityManager.getTransaction().commit();
                    
                    System.out.println(npos.toString()+" posts added to DB... waiting "+min_timestamp.toString()+" seconds for next retrieval...");

                    Thread.sleep(min_timestamp*1000);             
                    
                }
                
    }
    
    private static ArrayList<Posts> searchInstagramPostsByLocation(Float lat,Float lng, Float rad, long min_timestamp) throws Exception  {

        String response; 
        HttpClient http = new HttpClient();

        response = http.searchInstagramByLocation(lat,lng,rad,min_timestamp);

        JSONObject obj = new JSONObject(response);
        JSONArray arr = obj.getJSONArray("data");


        ArrayList<Posts> postList = new ArrayList<>();

        for (int i = 0; i < arr.length(); i++)
        {
            JSONObject jpost = arr.getJSONObject(i);

            String type = jpost.getString("type");
               
            Date dat = new Date(jpost.getLong("created_time")*1000);
            Posts post = new Posts(0,type,dat,0);
                    
            String caption;
            if(jpost.isNull("caption")){
                caption = "";
            }else{
                caption = jpost.getJSONObject("caption").getString("text");
            }
            post.setCaption(caption);
            
            String tags = "";
            JSONArray tagArr = jpost.getJSONArray("tags");
            for (int j = 0; j < tagArr.length(); j++){
                if(j==0){
                     tags=tagArr.getString(j);
                }else{
                    tags=tags.concat(","+tagArr.getString(j));
                }
            }
            post.setTags(tags);
            
            String im_link = jpost.getJSONObject("images").getJSONObject("standard_resolution").getString("url");
            post.setImLink(im_link);
            
            Float latitude = (float) jpost.getJSONObject("location").getDouble("latitude");
            Float longitude = (float) jpost.getJSONObject("location").getDouble("longitude");
            post.setLat(latitude);
            post.setLong1(longitude);
            
            Integer likes = jpost.getJSONObject("likes").getInt("count");
            post.setLikes(likes);
            

            // CALCULAR IDNEIGHBOURHOOD

            //post.setIdNeighb(idNeighb);

            postList.add(post);
            
        }
        return postList;
    }
    
    private static ArrayList<double[]> findCenterCircles(double[] x_bounds, double[] y_bounds, double r){
		//Example polygon that can be used for Barcelona that surrounds all the neighbourhoods
		    //double[] y = {41.404301,41.324095,41.417569,41.471839};
			//double[] x = {2.070192,2.158517,2.236232,2.184016};
        int prec = 5;
        
        double rx=r*0.005/557;
        double ry=r*0.01/834;
        double a=0.86602540378*ry;
        
        double y,ymin,x,xmin,xmax,ymax;
        ArrayList<double[]> centers = new ArrayList<>();
        
        //Create the centers of the circles, the bounds used is the square that surrounds the given polygon
        ymin = min(x_bounds);
        xmin = min(y_bounds);
        ymax = max(x_bounds);
        xmax = max(y_bounds);
        y=ymin;
        int i = 0;
        while(y<ymax){
            x = xmin;           
            if(i%2 == 0){
                while(x<xmax){
                    centers.add(new double[]{x,y});
                    x = x+3*rx;
                }
            }else{
		x = x+1.5*rx;
                while(x<xmax){
                    centers.add(new double[]{x,y});
                    x = x+3*rx;
                }
            } 
        y = y+a;
        i++;
        }
        // We remove all the centers that are not inside the polygon
        
        Iterator itr = centers.iterator();
        ArrayList<double[]> final_centers = new ArrayList<double[]>();
        double[] center = new double[2];
        PolygonFloat pol = new PolygonFloat(x_bounds,y_bounds,x_bounds.length,prec);
        while(itr.hasNext()){
            center = (double[])itr.next();
            if (pol.contains(center[1], center[0])){
                final_centers.add(center);
            }
        }
        return final_centers;
    }
    
    public static double min(double[] array) {
      // Validates input
      if (array == null) {
          throw new IllegalArgumentException("The Array must not be null");
      } else if (array.length == 0) {
          throw new IllegalArgumentException("Array cannot be empty.");
      }
  
      // Finds and returns min
      Double min = array[0];
      for (int i = 1; i < array.length; i++) {
          if (Double.isNaN(array[i])) {
              return Double.NaN;
          }
          if (array[i] < min) {
              min = array[i];
          }
      }
  
      return min;
    }
    
    public static double max(double[] array) {
      // Validates input
      if (array == null) {
          throw new IllegalArgumentException("The Array must not be null");
      } else if (array.length == 0) {
          throw new IllegalArgumentException("Array cannot be empty.");
      }

      // Finds and returns max
      double max = array[0];
      for (int j = 1; j < array.length; j++) {
          if (Double.isNaN(array[j])) {
              return Double.NaN;
          }
          if (array[j] > max) {
              max = array[j];
          }
      }

      return max;
    }

}
