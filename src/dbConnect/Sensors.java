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
@Table(name = "sensors")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sensors.findAll", query = "SELECT s FROM Sensors s"),
    @NamedQuery(name = "Sensors.findByIdsensors", query = "SELECT s FROM Sensors s WHERE s.idsensors = :idsensors"),
    @NamedQuery(name = "Sensors.findByLong1", query = "SELECT s FROM Sensors s WHERE s.long1 = :long1"),
    @NamedQuery(name = "Sensors.findByLat", query = "SELECT s FROM Sensors s WHERE s.lat = :lat"),
    @NamedQuery(name = "Sensors.findByProperty", query = "SELECT s FROM Sensors s WHERE s.property = :property"),
    @NamedQuery(name = "Sensors.findByIdcells", query = "SELECT s FROM Sensors s WHERE s.idcells = :idcells")})
public class Sensors implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "idsensors")
    private Integer idsensors;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "long")
    private Float long1;
    @Column(name = "lat")
    private Float lat;
    @Column(name = "property")
    private String property;
    @Basic(optional = false)
    @Column(name = "idcells")
    private String idcells;

    public Sensors() {
    }

    public Sensors(Integer idsensors) {
        this.idsensors = idsensors;
    }

    public Sensors(Integer idsensors, String idcells) {
        this.idsensors = idsensors;
        this.idcells = idcells;
    }

    public Integer getIdsensors() {
        return idsensors;
    }

    public void setIdsensors(Integer idsensors) {
        this.idsensors = idsensors;
    }

    public Float getLong1() {
        return long1;
    }

    public void setLong1(Float long1) {
        this.long1 = long1;
    }

    public Float getLat() {
        return lat;
    }

    public void setLat(Float lat) {
        this.lat = lat;
    }

    public String getProperty() {
        return property;
    }

    public void setProperty(String property) {
        this.property = property;
    }

    public String getIdcells() {
        return idcells;
    }

    public void setIdcells(String idcells) {
        this.idcells = idcells;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idsensors != null ? idsensors.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sensors)) {
            return false;
        }
        Sensors other = (Sensors) object;
        if ((this.idsensors == null && other.idsensors != null) || (this.idsensors != null && !this.idsensors.equals(other.idsensors))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbConnect.Sensors[ idsensors=" + idsensors + " ]";
    }
    
}
