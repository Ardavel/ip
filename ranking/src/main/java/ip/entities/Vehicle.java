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
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;

/**
 *
 * @author PiotrGrzelak
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Vehicle.findAll", query = "SELECT v FROM Vehicle v"),
    @NamedQuery(name = "Vehicle.findById", query = "SELECT v FROM Vehicle v WHERE v.id = :id"),
    @NamedQuery(name = "Vehicle.findByMass", query = "SELECT v FROM Vehicle v WHERE v.mass = :mass"),
    @NamedQuery(name = "Vehicle.findByEngineVolume", query = "SELECT v FROM Vehicle v WHERE v.engineVolume = :engineVolume"),
    @NamedQuery(name = "Vehicle.findByPower", query = "SELECT v FROM Vehicle v WHERE v.power = :power")})
public class Vehicle implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    @Basic(optional = false)
    private double mass;
    @Basic(optional = false)
    @Column(name = "engine_volume")
    private double engineVolume;
    @Basic(optional = false)
    private double power;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle")
    private List<Driver> driverList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle")
    private List<Run> runList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "vehicle")
    private List<Architecture> architectureList;
    @JoinColumn(name = "model", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Model model;

    public Vehicle() {
    }

    public Vehicle(Integer id) {
        this.id = id;
    }

    public Vehicle(Integer id, double mass, double engineVolume, double power) {
        this.id = id;
        this.mass = mass;
        this.engineVolume = engineVolume;
        this.power = power;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getMass() {
        return mass;
    }

    public void setMass(double mass) {
        this.mass = mass;
    }

    public double getEngineVolume() {
        return engineVolume;
    }

    public void setEngineVolume(double engineVolume) {
        this.engineVolume = engineVolume;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public List<Driver> getDriverList() {
        return driverList;
    }

    public void setDriverList(List<Driver> driverList) {
        this.driverList = driverList;
    }

    public List<Run> getRunList() {
        return runList;
    }

    public void setRunList(List<Run> runList) {
        this.runList = runList;
    }

    public List<Architecture> getArchitectureList() {
        return architectureList;
    }

    public void setArchitectureList(List<Architecture> architectureList) {
        this.architectureList = architectureList;
    }

    public Model getModel() {
        return model;
    }

    public void setModel(Model model) {
        this.model = model;
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
        if (!(object instanceof Vehicle)) {
            return false;
        }
        Vehicle other = (Vehicle) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ip.entities.Vehicle[ id=" + id + " ]";
    }
    
}
