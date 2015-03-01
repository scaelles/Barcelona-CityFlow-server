/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbConnect;

import cityflow_retrieveserver.PolygonFloat;
import java.io.Serializable;
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
@Table(name = "neighbourhoods")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Neighbourhoods.findAll", query = "SELECT n FROM Neighbourhoods n"),
    @NamedQuery(name = "Neighbourhoods.findByIdNeighb", query = "SELECT n FROM Neighbourhoods n WHERE n.idNeighb = :idNeighb"),
    @NamedQuery(name = "Neighbourhoods.findByName", query = "SELECT n FROM Neighbourhoods n WHERE n.name = :name"),
    @NamedQuery(name = "Neighbourhoods.findByBounds", query = "SELECT n FROM Neighbourhoods n WHERE n.bounds = :bounds"),
    @NamedQuery(name = "Neighbourhoods.findByIdDistrict", query = "SELECT n FROM Neighbourhoods n WHERE n.idDistrict = :idDistrict"),
    @NamedQuery(name = "Neighbourhoods.findByCenter", query = "SELECT n FROM Neighbourhoods n WHERE n.center = :center")})
public class Neighbourhoods implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idNeighb")
    private Integer idNeighb;
    @Basic(optional = false)
    @Column(name = "name")
    private String name;
    @Basic(optional = false)
    @Column(name = "bounds")
    private String bounds;
    @Basic(optional = false)
    @Column(name = "idDistrict")
    private int idDistrict;
    @Basic(optional = false)
    @Column(name = "center")
    private String center;
    @Transient
    private PolygonFloat poly;

    public Neighbourhoods() {
    }

    public Neighbourhoods(Integer idNeighb) {
        this.idNeighb = idNeighb;
    }

    public Neighbourhoods(Integer idNeighb, String name, String bounds, int idDistrict, String center) {
        this.idNeighb = idNeighb;
        this.name = name;
        this.bounds = bounds;
        this.idDistrict = idDistrict;
        this.center = center;
    }

    public Integer getIdNeighb() {
        return idNeighb;
    }

    public void setIdNeighb(Integer idNeighb) {
        this.idNeighb = idNeighb;
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

    public int getIdDistrict() {
        return idDistrict;
    }

    public void setIdDistrict(int idDistrict) {
        this.idDistrict = idDistrict;
    }

    public String getCenter() {
        return center;
    }

    public void setCenter(String center) {
        this.center = center;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idNeighb != null ? idNeighb.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Neighbourhoods)) {
            return false;
        }
        Neighbourhoods other = (Neighbourhoods) object;
        if ((this.idNeighb == null && other.idNeighb != null) || (this.idNeighb != null && !this.idNeighb.equals(other.idNeighb))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbConnect.Neighbourhoods[ idNeighb=" + idNeighb + " ]";
    }
    
}
