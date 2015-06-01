/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ip.facades;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ip.entities.Architecture;
import ip.entities.Fuel;
import ip.facades.exceptions.IllegalOrphanException;
import ip.facades.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Wojciech Szałapski
 */
public class FuelJpaController implements Serializable {

    public FuelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Fuel fuel) {
        if (fuel.getArchitectureList() == null) {
            fuel.setArchitectureList(new ArrayList<Architecture>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Architecture> attachedArchitectureList = new ArrayList<Architecture>();
            for (Architecture architectureListArchitectureToAttach : fuel.getArchitectureList()) {
                architectureListArchitectureToAttach = em.getReference(architectureListArchitectureToAttach.getClass(), architectureListArchitectureToAttach.getId());
                attachedArchitectureList.add(architectureListArchitectureToAttach);
            }
            fuel.setArchitectureList(attachedArchitectureList);
            em.persist(fuel);
            for (Architecture architectureListArchitecture : fuel.getArchitectureList()) {
                Fuel oldFuelOfArchitectureListArchitecture = architectureListArchitecture.getFuel();
                architectureListArchitecture.setFuel(fuel);
                architectureListArchitecture = em.merge(architectureListArchitecture);
                if (oldFuelOfArchitectureListArchitecture != null) {
                    oldFuelOfArchitectureListArchitecture.getArchitectureList().remove(architectureListArchitecture);
                    oldFuelOfArchitectureListArchitecture = em.merge(oldFuelOfArchitectureListArchitecture);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Fuel fuel) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fuel persistentFuel = em.find(Fuel.class, fuel.getId());
            List<Architecture> architectureListOld = persistentFuel.getArchitectureList();
            List<Architecture> architectureListNew = fuel.getArchitectureList();
            List<String> illegalOrphanMessages = null;
            for (Architecture architectureListOldArchitecture : architectureListOld) {
                if (!architectureListNew.contains(architectureListOldArchitecture)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Architecture " + architectureListOldArchitecture + " since its fuel field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Architecture> attachedArchitectureListNew = new ArrayList<Architecture>();
            for (Architecture architectureListNewArchitectureToAttach : architectureListNew) {
                architectureListNewArchitectureToAttach = em.getReference(architectureListNewArchitectureToAttach.getClass(), architectureListNewArchitectureToAttach.getId());
                attachedArchitectureListNew.add(architectureListNewArchitectureToAttach);
            }
            architectureListNew = attachedArchitectureListNew;
            fuel.setArchitectureList(architectureListNew);
            fuel = em.merge(fuel);
            for (Architecture architectureListNewArchitecture : architectureListNew) {
                if (!architectureListOld.contains(architectureListNewArchitecture)) {
                    Fuel oldFuelOfArchitectureListNewArchitecture = architectureListNewArchitecture.getFuel();
                    architectureListNewArchitecture.setFuel(fuel);
                    architectureListNewArchitecture = em.merge(architectureListNewArchitecture);
                    if (oldFuelOfArchitectureListNewArchitecture != null && !oldFuelOfArchitectureListNewArchitecture.equals(fuel)) {
                        oldFuelOfArchitectureListNewArchitecture.getArchitectureList().remove(architectureListNewArchitecture);
                        oldFuelOfArchitectureListNewArchitecture = em.merge(oldFuelOfArchitectureListNewArchitecture);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = fuel.getId();
                if (findFuel(id) == null) {
                    throw new NonexistentEntityException("The fuel with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Fuel fuel;
            try {
                fuel = em.getReference(Fuel.class, id);
                fuel.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The fuel with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Architecture> architectureListOrphanCheck = fuel.getArchitectureList();
            for (Architecture architectureListOrphanCheckArchitecture : architectureListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Fuel (" + fuel + ") cannot be destroyed since the Architecture " + architectureListOrphanCheckArchitecture + " in its architectureList field has a non-nullable fuel field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(fuel);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Fuel> findFuelEntities() {
        return findFuelEntities(true, -1, -1);
    }

    public List<Fuel> findFuelEntities(int maxResults, int firstResult) {
        return findFuelEntities(false, maxResults, firstResult);
    }

    private List<Fuel> findFuelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Fuel.class));
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

    public Fuel findFuel(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Fuel.class, id);
        } finally {
            em.close();
        }
    }

    public int getFuelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Fuel> rt = cq.from(Fuel.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
