/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbConnect;

import cityflow_retrieveserver.PolygonFloat;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Transient;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Miquel
 */
@Entity
@Table(name = "districts")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Districts.findAll", query = "SELECT d FROM Districts d"),
    @NamedQuery(name = "Districts.findByIdDistrict", query = "SELECT d FROM Districts d WHERE d.idDistrict = :idDistrict"),
    @NamedQuery(name = "Districts.findByName", query = "SELECT d FROM Districts d WHERE d.name = :name"),
    @NamedQuery(name = "Districts.findByBounds", query = "SELECT d FROM Districts d WHERE d.bounds = :bounds"),
    @NamedQuery(name = "Districts.findByCenter", query = "SELECT d FROM Districts d WHERE d.center = :center")})
public class Districts implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idDistrict")
    private Integer idDistrict;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "bounds")
    private String bounds;
    @Basic(optional = false)
    @Column(name = "center")
    private String center;
    @Transient
    private PolygonFloat poly;
    @Transient
    private List<Neighbourhoods> neighbourhoods;

    public Districts() {
    }

    public Districts(Integer idDistrict) {
        this.idDistrict = idDistrict;
    }

    public Districts(Integer idDistrict, String name, String bounds, String center) {
        this.idDistrict = idDistrict;
        this.name = name;
        this.bounds = bounds;
        this.center = center;
    }

    public Integer getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(Integer idDistrict) {
        this.idDistrict = idDistrict;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBounds() {
        return bounds;
    }

    public void setBounds(String bounds) {
        this.bounds = bounds;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    public PolygonFloat getPoly() {
        return poly;
    }

    public void setPoly(PolygonFloat poly) {
        this.poly = poly;
    }
    
    public List<Neighbourhoods> getNeighbourhoods() {
        return neighbourhoods;
    }

    public void addNeighbourhoods(List<Neighbourhoods> neighs) {
        this.neighbourhoods= new ArrayList<>();
        for(Neighbourhoods neigh : neighs){
            if(this.getIdDistrict()==neigh.getIdDistrict()){
                this.neighbourhoods.add(neigh);
            }
        }
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idDistrict != null ? idDistrict.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Districts)) {
            return false;
        }
        Districts other = (Districts) object;
        if ((this.idDistrict == null && other.idDistrict != null) || (this.idDistrict != null && !this.idDistrict.equals(other.idDistrict))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbConnect.Districts[ idDistrict=" + idDistrict + " ]";
    }
    
    
    public void doPoly() {
        int prec = 5;
        
        String[] loc = this.bounds.split("[|]");
        double[] x = new double[loc.length];
        double[] y = new double[loc.length];
        
        int i =0;
        String[] location;
        for (String loc1 : loc) {
            location = loc1.split(",");
            x[i]=Double.parseDouble(location[0]);
            y[i]=Double.parseDouble(location[1]);
            i++;
        }
        setPoly(new PolygonFloat(x,y,x.length,prec));
    }
    
    
}
