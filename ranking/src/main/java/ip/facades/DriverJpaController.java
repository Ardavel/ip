package ip.facades;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ip.entities.Architecture;
import ip.entities.Driver;
import ip.facades.exceptions.NonexistentEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Wojciech Szałapski
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
            Architecture architecture = driver.getArchitecture();
            if (architecture != null) {
                architecture = em.getReference(architecture.getClass(), architecture.getId());
                driver.setArchitecture(architecture);
            }
            em.persist(driver);
            if (architecture != null) {
                architecture.getDriverList().add(driver);
                architecture = em.merge(architecture);
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
            Architecture architectureOld = persistentDriver.getArchitecture();
            Architecture architectureNew = driver.getArchitecture();
            if (architectureNew != null) {
                architectureNew = em.getReference(architectureNew.getClass(), architectureNew.getId());
                driver.setArchitecture(architectureNew);
            }
            driver = em.merge(driver);
            if (architectureOld != null && !architectureOld.equals(architectureNew)) {
                architectureOld.getDriverList().remove(driver);
                architectureOld = em.merge(architectureOld);
            }
            if (architectureNew != null && !architectureNew.equals(architectureOld)) {
                architectureNew.getDriverList().add(driver);
                architectureNew = em.merge(architectureNew);
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
            Architecture architecture = driver.getArchitecture();
            if (architecture != null) {
                architecture.getDriverList().remove(driver);
                architecture = em.merge(architecture);
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
