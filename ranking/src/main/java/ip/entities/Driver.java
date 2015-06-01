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
    @NamedQuery(name = "Driver.findAll", query = "SELECT d FROM Driver d"),
    @NamedQuery(name = "Driver.findById", query = "SELECT d FROM Driver d WHERE d.id = :id"),
    @NamedQuery(name = "Driver.findByNickname", query = "SELECT d FROM Driver d WHERE d.nickname = :nickname"),
    @NamedQuery(name = "Driver.findByEcoPoints", query = "SELECT d FROM Driver d WHERE d.ecoPoints = :ecoPoints"),
    @NamedQuery(name = "Driver.findBySafePoints", query = "SELECT d FROM Driver d WHERE d.safePoints = :safePoints")})
public class Driver implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    private Integer id;
    
    @Basic(optional = false)
    private String nickname;
    
    @Basic(optional = false)
    @Column(name = "eco_points")
    private int ecoPoints;
    
    @Basic(optional = false)
    @Column(name = "safe_points")
    private int safePoints;
    
    @JoinColumn(name = "architecture", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Architecture architecture;

    public Driver() {
    }

    public Driver(Integer id) {
        this.id = id;
    }

    public Driver(Integer id, String nickname, int ecoPoints, int safePoints) {
        this.id = id;
        this.nickname = nickname;
        this.ecoPoints = ecoPoints;
        this.safePoints = safePoints;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public int getEcoPoints() {
        return ecoPoints;
    }

    public void setEcoPoints(int ecoPoints) {
        this.ecoPoints = ecoPoints;
    }

    public int getSafePoints() {
        return safePoints;
    }

    public void setSafePoints(int safePoints) {
        this.safePoints = safePoints;
    }

    public Architecture getArchitecture() {
        return architecture;
    }

    public void setArchitecture(Architecture architecture) {
        this.architecture = architecture;
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
        if (!(object instanceof Driver)) {
            return false;
        }
        Driver other = (Driver) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "ip.entities.Driver[ id=" + id + " ]";
    }
    
}
