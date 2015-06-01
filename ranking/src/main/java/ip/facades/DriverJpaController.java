/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.facades;

import ip.entities.Driver;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ip.entities.Vehicle;
import ip.facades.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ardavel
 */
public class DriverJpaController implements Serializable {

    public DriverJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Driver driver) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehicle vehicle = driver.getVehicle();
            if (vehicle != null) {
                vehicle = em.getReference(vehicle.getClass(), vehicle.getId());
                driver.setVehicle(vehicle);
            }
            em.persist(driver);
            if (vehicle != null) {
                vehicle.getDriverList().add(driver);
                vehicle = em.merge(vehicle);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Driver driver) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Driver persistentDriver = em.find(Driver.class, driver.getId());
            Vehicle vehicleOld = persistentDriver.getVehicle();
            Vehicle vehicleNew = driver.getVehicle();
            if (vehicleNew != null) {
                vehicleNew = em.getReference(vehicleNew.getClass(), vehicleNew.getId());
                driver.setVehicle(vehicleNew);
            }
            driver = em.merge(driver);
            if (vehicleOld != null && !vehicleOld.equals(vehicleNew)) {
                vehicleOld.getDriverList().remove(driver);
                vehicleOld = em.merge(vehicleOld);
            }
            if (vehicleNew != null && !vehicleNew.equals(vehicleOld)) {
                vehicleNew.getDriverList().add(driver);
                vehicleNew = em.merge(vehicleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = driver.getId();
                if (findDriver(id) == null) {
                    throw new NonexistentEntityException("The driver with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Driver driver;
            try {
                driver = em.getReference(Driver.class, id);
                driver.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The driver with id " + id + " no longer exists.", enfe);
            }
            Vehicle vehicle = driver.getVehicle();
            if (vehicle != null) {
                vehicle.getDriverList().remove(driver);
                vehicle = em.merge(vehicle);
            }
            em.remove(driver);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Driver> findDriverEntities() {
        return findDriverEntities(true, -1, -1);
    }

    public List<Driver> findDriverEntities(int maxResults, int firstResult) {
        return findDriverEntities(false, maxResults, firstResult);
    }

    private List<Driver> findDriverEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Driver.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Driver findDriver(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Driver.class, id);
        } finally {
            em.close();
        }
    }

    public int getDriverCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Driver> rt = cq.from(Driver.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
