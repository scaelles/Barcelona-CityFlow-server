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
public class Neighbourhood {
    Integer id, iddistrict;
    String name, bounds;

    public Neighbourhood(Integer id, Integer iddistrict, String name, String bounds) {
        this.id = id;
        this.iddistrict = iddistrict;
        this.name = name;
        this.bounds = bounds;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIddistrict() {
        return iddistrict;
    }

    public void setIddistrict(Integer iddistrict) {
        this.iddistrict = iddistrict;
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
    
}
