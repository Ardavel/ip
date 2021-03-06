package ip.facades;

import ip.entities.Run;
import ip.facades.exceptions.NonexistentEntityException;
import java.io.Serializable;
import java.util.List;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

/**
 *
 * @author Wojciech Szałapski
 */
public class RunJpaController implements Serializable {

    public RunJpaController() {
        emf = Persistence.createEntityManagerFactory("ipRankingPU");
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
            em.persist(run);
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
            run = em.merge(run);
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
