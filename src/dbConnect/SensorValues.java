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
@Table(name = "sensor_values")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "SensorValues.findAll", query = "SELECT s FROM SensorValues s"),
    @NamedQuery(name = "SensorValues.findByIdvalues", query = "SELECT s FROM SensorValues s WHERE s.idvalues = :idvalues"),
    @NamedQuery(name = "SensorValues.findByIdsensor", query = "SELECT s FROM SensorValues s WHERE s.idsensor = :idsensor"),
    @NamedQuery(name = "SensorValues.findByDate", query = "SELECT s FROM SensorValues s WHERE s.date = :date"),
    @NamedQuery(name = "SensorValues.findByValue", query = "SELECT s FROM SensorValues s WHERE s.value = :value")})
public class SensorValues implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "idvalues")
    private Integer idvalues;
    @Basic(optional = false)
    @Column(name = "idsensor")
    private int idsensor;
    @Basic(optional = false)
    @Column(name = "date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date date;
    @Column(name = "value")
    private Integer value;

    public SensorValues() {
    }

    public SensorValues(Integer idvalues) {
        this.idvalues = idvalues;
    }

    public SensorValues(Integer idvalues, int idsensor, Date date) {
        this.idvalues = idvalues;
        this.idsensor = idsensor;
        this.date = date;
    }

    public Integer getIdvalues() {
        return idvalues;
    }

    public void setIdvalues(Integer idvalues) {
        this.idvalues = idvalues;
    }

    public int getIdsensor() {
        return idsensor;
    }

    public void setIdsensor(int idsensor) {
        this.idsensor = idsensor;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getValue() {
        return value;
    }

    public void setValue(Integer value) {
        this.value = value;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (idvalues != null ? idvalues.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof SensorValues)) {
            return false;
        }
        SensorValues other = (SensorValues) object;
        if ((this.idvalues == null && other.idvalues != null) || (this.idvalues != null && !this.idvalues.equals(other.idvalues))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "dbConnect.SensorValues[ idvalues=" + idvalues + " ]";
    }
    
}
