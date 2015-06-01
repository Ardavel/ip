/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 *
 * @author PiotrGrzelak
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Architecture.findAll", query = "SELECT a FROM Architecture a"),
    @NamedQuery(name = "Architecture.findById", query = "SELECT a FROM Architecture a WHERE a.id = :id"),
    @NamedQuery(name = "Architecture.findByMeanImap", query = "SELECT a FROM Architecture a WHERE a.meanImap = :meanImap"),
    @NamedQuery(name = "Architecture.findBySdFactorImap", query = "SELECT a FROM Architecture a WHERE a.sdFactorImap = :sdFactorImap"),
    @NamedQuery(name = "Architecture.findByRuns", query = "SELECT a FROM Architecture a WHERE a.runs = :runs")})
public class Architecture implements Serializable {

    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    
    @Basic(optional = false)
    @Column(name = "mean_imap")
    private double meanImap;
    
    @Basic(optional = false)
    @Column(name = "sd_factor_imap")
    private double sdFactorImap;
    
    @Basic(optional = false)
    private int runs;
    
    @JoinColumn(name = "fuel", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Fuel fuel;
    
    @JoinColumn(name = "vehicle", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Vehicle vehicle;

    public Architecture() {
    }

    public Architecture(Integer id) {
        this.id = id;
    }

    public Architecture(Integer id, double meanImap, double sdFactorImap, int runs) {
        this.id = id;
        this.meanImap = meanImap;
        this.sdFactorImap = sdFactorImap;
        this.runs = runs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMeanImap() {
        return meanImap;
    }

    public void setMeanImap(double meanImap) {
        this.meanImap = meanImap;
    }

    public double getSdFactorImap() {
        return sdFactorImap;
    }

    public void setSdFactorImap(double sdFactorImap) {
        this.sdFactorImap = sdFactorImap;
    }

    public int getRuns() {
        return runs;
    }

    public void setRuns(int runs) {
        this.runs = runs;
    }

    public Fuel getFuel() {
        return fuel;
    }

    public void setFuel(Fuel fuel) {
        this.fuel = fuel;
    }

    public Vehicle getVehicle() {
        return vehicle;
    }

    public void setVehicle(Vehicle vehicle) {
        this.vehicle = vehicle;
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
        if (!(object instanceof Architecture)) {
            return false;
        }
        Architecture other = (Architecture) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ip.entities.Architecture[ id=" + id + " ]";
    }

}
