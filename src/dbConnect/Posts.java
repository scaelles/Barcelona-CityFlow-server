/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbConnect;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miquel
 */
@Entity
@Table(name = "posts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Posts.findAll", query = "SELECT p FROM Posts p"),
    @NamedQuery(name = "Posts.findByIdPost", query = "SELECT p FROM Posts p WHERE p.idPost = :idPost"),
    @NamedQuery(name = "Posts.findByTypeMedia", query = "SELECT p FROM Posts p WHERE p.typeMedia = :typeMedia"),
    @NamedQuery(name = "Posts.findByDate", query = "SELECT p FROM Posts p WHERE p.date = :date"),
    @NamedQuery(name = "Posts.findByCaption", query = "SELECT p FROM Posts p WHERE p.caption = :caption"),
    @NamedQuery(name = "Posts.findByTags", query = "SELECT p FROM Posts p WHERE p.tags = :tags"),
    @NamedQuery(name = "Posts.findByImLink", query = "SELECT p FROM Posts p WHERE p.imLink = :imLink"),
    @NamedQuery(name = "Posts.findByLat", query = "SELECT p FROM Posts p WHERE p.lat = :lat"),
    @NamedQuery(name = "Posts.findByLong1", query = "SELECT p FROM Posts p WHERE p.long1 = :long1"),
    @NamedQuery(name = "Posts.findByLikes", query = "SELECT p FROM Posts p WHERE p.likes = :likes"),
    @NamedQuery(name = "Posts.findByIdNeighb", query = "SELECT p FROM Posts p WHERE p.idNeighb = :idNeighb")})
public class Posts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idPost")
    private Integer idPost;
    @Basic(optional = false)
    @Column(name = "type_media")
    private String typeMedia;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "caption")
    private String caption;
    @Column(name = "tags")
    private String tags;
    @Column(name = "im_link")
    private String imLink;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "lat")
    private Float lat;
    @Column(name = "long")
    private Float long1;
    @Column(name = "likes")
    private Integer likes;
    @Basic(optional = false)
    @Column(name = "idNeighb")
    private int idNeighb;

    public Posts() {
    }

    public Posts(Integer idPost) {
        this.idPost = idPost;
    }

    public Posts(Integer idPost, String typeMedia, Date date, int idNeighb) {
        this.idPost = idPost;
        this.typeMedia = typeMedia;
        this.date = date;
        this.idNeighb = idNeighb;
    }

    public Integer getIdPost() {
        return idPost;
    }

    public void setIdPost(Integer idPost) {
        this.idPost = idPost;
    }

    public String getTypeMedia() {
        return typeMedia;
    }

    public void setTypeMedia(String typeMedia) {
        this.typeMedia = typeMedia;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
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

    public String getImLink() {
        return imLink;
    }

    public void setImLink(String imLink) {
        this.imLink = imLink;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public Float getLong1() {
        return long1;
    }

    public void setLong1(Float long1) {
        this.long1 = long1;
    }

    public Integer getLikes() {
        return likes;
    }

    public void setLikes(Integer likes) {
        this.likes = likes;
    }

    public int getIdNeighb() {
        return idNeighb;
    }

    public void setIdNeighb(int idNeighb) {
        this.idNeighb = idNeighb;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idPost != null ? idPost.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Posts)) {
            return false;
        }
        Posts other = (Posts) object;
        if ((this.idPost == null && other.idPost != null) || (this.idPost != null && !this.idPost.equals(other.idPost))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbConnect.Posts[ idPost=" + idPost + " ]";
    }
    
}
