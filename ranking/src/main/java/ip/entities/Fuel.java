/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.entities;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author PiotrGrzelak
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Fuel.findAll", query = "SELECT f FROM Fuel f"),
    @NamedQuery(name = "Fuel.findById", query = "SELECT f FROM Fuel f WHERE f.id = :id"),
    @NamedQuery(name = "Fuel.findByName", query = "SELECT f FROM Fuel f WHERE f.name = :name"),
    @NamedQuery(name = "Fuel.findByRatio", query = "SELECT f FROM Fuel f WHERE f.ratio = :ratio"),
    @NamedQuery(name = "Fuel.findByDensity", query = "SELECT f FROM Fuel f WHERE f.density = :density")})
public class Fuel implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    
    @Basic(optional = false)
    private String name;
    
    @Basic(optional = false)
    private double ratio;
    
    @Basic(optional = false)
    private double density;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "fuel")
    private List<Architecture> architectureList;

    public Fuel() {
    }

    public Fuel(Integer id) {
        this.id = id;
    }

    public Fuel(Integer id, String name, double ratio, double density) {
        this.id = id;
        this.name = name;
        this.ratio = ratio;
        this.density = density;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getRatio() {
        return ratio;
    }

    public void setRatio(double ratio) {
        this.ratio = ratio;
    }

    public double getDensity() {
        return density;
    }

    public void setDensity(double density) {
        this.density = density;
    }

    public List<Architecture> getArchitectureList() {
        return architectureList;
    }

    public void setArchitectureList(List<Architecture> architectureList) {
        this.architectureList = architectureList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Fuel)) {
            return false;
        }
        Fuel other = (Fuel) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ip.entities.Fuel[ id=" + id + " ]";
    }

}
