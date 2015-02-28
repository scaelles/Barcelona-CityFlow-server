/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package cityflow_retrieveserver;

import java.util.Date;

/**
 *
 * @author Miquel
 */
public class InstagramPost {

    
    Integer id, likes, idneighbourhood;
    String type, caption, tags, im_link;
    Float lat,lng;
    Date date;
    
    public InstagramPost(Integer id, String type, Date date, String caption, String tags,
            String im_link, Float lat, Float lng, Integer likes, Integer idneighbourhood){
        this.id = id;
        this.caption=caption;
        this.date=date;
        this.idneighbourhood=idneighbourhood;
        this.im_link=im_link;
        this.lat=lat;
        this.lng=lng;
        this.tags=tags;
        this.type=type;
        this.likes=likes;
    }
    
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public Integer getIdneighbourhood() {
        return idneighbourhood;
    }

    public void setIdneighbourhood(Integer idneighbourhood) {
        this.idneighbourhood = idneighbourhood;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getCaption() {
        return caption;
    }

    public void setCaption(String caption) {
        this.caption = caption;
    }

    public String getTags() {
        return tags;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getIm_link() {
        return im_link;
    }

    public void setIm_link(String im_link) {
        this.im_link = im_link;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLng() {
        return lng;
    }

    public void setLng(Float lng) {
        this.lng = lng;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    
    
    
    
}
