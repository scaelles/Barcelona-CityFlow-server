/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dbConnect;

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
    
}
