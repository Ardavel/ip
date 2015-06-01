/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.facades;

import ip.entities.Run;
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
public class RunJpaController implements Serializable {

    public RunJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Run run) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehicle vehicle = run.getVehicle();
            if (vehicle != null) {
                vehicle = em.getReference(vehicle.getClass(), vehicle.getId());
                run.setVehicle(vehicle);
            }
            em.persist(run);
            if (vehicle != null) {
                vehicle.getRunList().add(run);
                vehicle = em.merge(vehicle);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Run run) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Run persistentRun = em.find(Run.class, run.getId());
            Vehicle vehicleOld = persistentRun.getVehicle();
            Vehicle vehicleNew = run.getVehicle();
            if (vehicleNew != null) {
                vehicleNew = em.getReference(vehicleNew.getClass(), vehicleNew.getId());
                run.setVehicle(vehicleNew);
            }
            run = em.merge(run);
            if (vehicleOld != null && !vehicleOld.equals(vehicleNew)) {
                vehicleOld.getRunList().remove(run);
                vehicleOld = em.merge(vehicleOld);
            }
            if (vehicleNew != null && !vehicleNew.equals(vehicleOld)) {
                vehicleNew.getRunList().add(run);
                vehicleNew = em.merge(vehicleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = run.getId();
                if (findRun(id) == null) {
                    throw new NonexistentEntityException("The run with id " + id + " no longer exists.");
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
            Run run;
            try {
                run = em.getReference(Run.class, id);
                run.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The run with id " + id + " no longer exists.", enfe);
            }
            Vehicle vehicle = run.getVehicle();
            if (vehicle != null) {
                vehicle.getRunList().remove(run);
                vehicle = em.merge(vehicle);
            }
            em.remove(run);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Run> findRunEntities() {
        return findRunEntities(true, -1, -1);
    }

    public List<Run> findRunEntities(int maxResults, int firstResult) {
        return findRunEntities(false, maxResults, firstResult);
    }

    private List<Run> findRunEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Run.class));
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

    public Run findRun(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Run.class, id);
        } finally {
            em.close();
        }
    }

    public int getRunCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Run> rt = cq.from(Run.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
