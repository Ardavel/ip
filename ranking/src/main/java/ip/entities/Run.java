/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.entities;

import java.io.Serializable;
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 *
 * @author PiotrGrzelak
 */
@Entity
@NamedQueries({
    @NamedQuery(name = "Run.findAll", query = "SELECT r FROM Run r"),
    @NamedQuery(name = "Run.findById", query = "SELECT r FROM Run r WHERE r.id = :id"),
    @NamedQuery(name = "Run.findByAvgImap", query = "SELECT r FROM Run r WHERE r.avgImap = :avgImap"),
    @NamedQuery(name = "Run.findByEndingTime", query = "SELECT r FROM Run r WHERE r.endingTime = :endingTime")})
public class Run implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;

    @Basic(optional = false)
    @Column(name = "avg_imap")
    private double avgImap;

    @Basic(optional = false)
    @Column(name = "ending_time")
    @Temporal(TemporalType.TIMESTAMP)
    private Date endingTime;

    @JoinColumn(name = "vehicle", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Vehicle vehicle;

    public Run() {
    }

    public Run(Integer id) {
        this.id = id;
    }

    public Run(Integer id, double avgImap, Date endingTime) {
        this.id = id;
        this.avgImap = avgImap;
        this.endingTime = endingTime;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public double getAvgImap() {
        return avgImap;
    }

    public void setAvgImap(double avgImap) {
        this.avgImap = avgImap;
    }

    public Date getEndingTime() {
        return endingTime;
    }

    public void setEndingTime(Date endingTime) {
        this.endingTime = endingTime;
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
        if (!(object instanceof Run)) {
            return false;
        }
        Run other = (Run) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ip.entities.Run[ id=" + id + " ]";
    }

}
