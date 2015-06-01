package ip.facades;

import ip.entities.Architecture;
import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ip.entities.Fuel;
import ip.entities.Vehicle;
import ip.facades.exceptions.NonexistentEntityException;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public class ArchitectureJpaController implements Serializable {

    public ArchitectureJpaController() {
        emf = Persistence.createEntityManagerFactory("ipRankingPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Architecture architecture) {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fuel fuel = architecture.getFuel();
            if (fuel != null) {
                fuel = em.getReference(fuel.getClass(), fuel.getId());
                architecture.setFuel(fuel);
            }
            Vehicle vehicle = architecture.getVehicle();
            if (vehicle != null) {
                vehicle = em.getReference(vehicle.getClass(), vehicle.getId());
                architecture.setVehicle(vehicle);
            }
            em.persist(architecture);
            if (fuel != null) {
                fuel.getArchitectureList().add(architecture);
                fuel = em.merge(fuel);
            }
            if (vehicle != null) {
                vehicle.getArchitectureList().add(architecture);
                vehicle = em.merge(vehicle);
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Architecture architecture) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Architecture persistentArchitecture = em.find(Architecture.class, architecture.getId());
            Fuel fuelOld = persistentArchitecture.getFuel();
            Fuel fuelNew = architecture.getFuel();
            Vehicle vehicleOld = persistentArchitecture.getVehicle();
            Vehicle vehicleNew = architecture.getVehicle();
            if (fuelNew != null) {
                fuelNew = em.getReference(fuelNew.getClass(), fuelNew.getId());
                architecture.setFuel(fuelNew);
            }
            if (vehicleNew != null) {
                vehicleNew = em.getReference(vehicleNew.getClass(), vehicleNew.getId());
                architecture.setVehicle(vehicleNew);
            }
            architecture = em.merge(architecture);
            if (fuelOld != null && !fuelOld.equals(fuelNew)) {
                fuelOld.getArchitectureList().remove(architecture);
                fuelOld = em.merge(fuelOld);
            }
            if (fuelNew != null && !fuelNew.equals(fuelOld)) {
                fuelNew.getArchitectureList().add(architecture);
                fuelNew = em.merge(fuelNew);
            }
            if (vehicleOld != null && !vehicleOld.equals(vehicleNew)) {
                vehicleOld.getArchitectureList().remove(architecture);
                vehicleOld = em.merge(vehicleOld);
            }
            if (vehicleNew != null && !vehicleNew.equals(vehicleOld)) {
                vehicleNew.getArchitectureList().add(architecture);
                vehicleNew = em.merge(vehicleNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = architecture.getId();
                if (findArchitecture(id) == null) {
                    throw new NonexistentEntityException("The architecture with id " + id + " no longer exists.");
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
            Architecture architecture;
            try {
                architecture = em.getReference(Architecture.class, id);
                architecture.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The architecture with id " + id + " no longer exists.", enfe);
            }
            Fuel fuel = architecture.getFuel();
            if (fuel != null) {
                fuel.getArchitectureList().remove(architecture);
                fuel = em.merge(fuel);
            }
            Vehicle vehicle = architecture.getVehicle();
            if (vehicle != null) {
                vehicle.getArchitectureList().remove(architecture);
                vehicle = em.merge(vehicle);
            }
            em.remove(architecture);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Architecture> findArchitectureEntities() {
        return findArchitectureEntities(true, -1, -1);
    }

    public List<Architecture> findArchitectureEntities(int maxResults, int firstResult) {
        return findArchitectureEntities(false, maxResults, firstResult);
    }

    private List<Architecture> findArchitectureEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Architecture.class));
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

    public Architecture findArchitecture(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Architecture.class, id);
        } finally {
            em.close();
        }
    }

    public int getArchitectureCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Architecture> rt = cq.from(Architecture.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
