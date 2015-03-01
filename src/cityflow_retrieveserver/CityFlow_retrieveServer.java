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
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;
import org.json.*;
import java.util.Iterator;
import java.awt.Polygon;

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
        
		
 
		System.out.println("Testing 1 - Search Instagram by Location");
                
                Float lat = (float) 41.389639;
                Float lng = (float) 2.176743;
                Float rad = (float) 1000;
                long min_timestamp = 300;
                
                EntityManager entityManager = Persistence.createEntityManagerFactory("CityFlow_retrieveServerPU").createEntityManager();
                entityManager.getTransaction().begin();
                
                Query query = entityManager.createNamedQuery("Districts.findAll");
                List<Districts> districtsList = query.getResultList();
                query = entityManager.createNamedQuery("Neighbourhoods.findAll");
                List<Neighbourhoods> neighbourhoodsList = query.getResultList();
                
                ArrayList<Posts> postList = searchInstagramPostsByLocation(lat,lng,rad,min_timestamp);
                //for (Posts p : postList) {
                    //entityManager.persist(p);
                    //entityManager.flush();
                    //entityManager.refresh(p);
                //}

                entityManager.persist(postList.get(1));
                entityManager.getTransaction().commit();
                entityManager.close();

                
                System.out.println("#Districts: " + districtsList.size());
                System.out.println("#Neighbourhoods: " + neighbourhoodsList.size());
                
                System.out.println("\n");
                
		//System.out.println("\nTesting 2 - Send Http POST request");
		//http.sendPost();
 
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
               
            Date now = new Date();
            Posts post = new Posts(0,type,now,0);
                    
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
    
    public ArrayList<double[]> findCenterCicles(double[] x_bounds, double[] y_bounds, int npoints, double r){
        int prec = 5;
        
        double y,ymin,x,xmin,xmax,ymax;
        ArrayList<double[]> centers = new ArrayList<double[]>();
        
        //Create the centers of the circles, the bounds used is the square that surrounds the given polygon
        ymin = min(x_bounds);
        xmin = min(y_bounds);
        ymax = max(x_bounds);
        xmax = max(y_bounds);
        y=ymin;
        int i = 0, j = 0;
        while(y<ymax){
            x = xmin;
            j = 0;
            y = y+2*i*r;
            if(i%2 == 0){
                while(x<xmax){
                    x = x+2*j*r;
                    centers.add(new double[]{x,y});
                    j++;
                }
            }else{
                while(x<xmax){
                    x = x+2*j*r+r;
                    centers.add(new double[]{x,y});
                    j++;
                }
            } 
            i++;
        }
        // We remove all the centers that are not inside the polygon
        
        Iterator itr = centers.iterator();
        ArrayList<double[]> final_centers = new ArrayList<double[]>();
        double[] center = new double[2];
        PolygonFloat pol = new PolygonFloat(x_bounds,y_bounds,npoints,prec);
        while(itr.hasNext()){
            center = (double[])itr.next();
            if (pol.contains(center[0], center[1])){
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
