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
import ip.entities.Brand;
import ip.entities.Model;
import ip.entities.Vehicle;
import ip.facades.exceptions.IllegalOrphanException;
import ip.facades.exceptions.NonexistentEntityException;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ardavel
 */
public class ModelJpaController implements Serializable {

    public ModelJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Model model) {
        if (model.getVehicleList() == null) {
            model.setVehicleList(new ArrayList<Vehicle>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Brand brand = model.getBrand();
            if (brand != null) {
                brand = em.getReference(brand.getClass(), brand.getId());
                model.setBrand(brand);
            }
            List<Vehicle> attachedVehicleList = new ArrayList<Vehicle>();
            for (Vehicle vehicleListVehicleToAttach : model.getVehicleList()) {
                vehicleListVehicleToAttach = em.getReference(vehicleListVehicleToAttach.getClass(), vehicleListVehicleToAttach.getId());
                attachedVehicleList.add(vehicleListVehicleToAttach);
            }
            model.setVehicleList(attachedVehicleList);
            em.persist(model);
            if (brand != null) {
                brand.getModelList().add(model);
                brand = em.merge(brand);
            }
            for (Vehicle vehicleListVehicle : model.getVehicleList()) {
                Model oldModelOfVehicleListVehicle = vehicleListVehicle.getModel();
                vehicleListVehicle.setModel(model);
                vehicleListVehicle = em.merge(vehicleListVehicle);
                if (oldModelOfVehicleListVehicle != null) {
                    oldModelOfVehicleListVehicle.getVehicleList().remove(vehicleListVehicle);
                    oldModelOfVehicleListVehicle = em.merge(oldModelOfVehicleListVehicle);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Model model) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Model persistentModel = em.find(Model.class, model.getId());
            Brand brandOld = persistentModel.getBrand();
            Brand brandNew = model.getBrand();
            List<Vehicle> vehicleListOld = persistentModel.getVehicleList();
            List<Vehicle> vehicleListNew = model.getVehicleList();
            List<String> illegalOrphanMessages = null;
            for (Vehicle vehicleListOldVehicle : vehicleListOld) {
                if (!vehicleListNew.contains(vehicleListOldVehicle)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Vehicle " + vehicleListOldVehicle + " since its model field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (brandNew != null) {
                brandNew = em.getReference(brandNew.getClass(), brandNew.getId());
                model.setBrand(brandNew);
            }
            List<Vehicle> attachedVehicleListNew = new ArrayList<Vehicle>();
            for (Vehicle vehicleListNewVehicleToAttach : vehicleListNew) {
                vehicleListNewVehicleToAttach = em.getReference(vehicleListNewVehicleToAttach.getClass(), vehicleListNewVehicleToAttach.getId());
                attachedVehicleListNew.add(vehicleListNewVehicleToAttach);
            }
            vehicleListNew = attachedVehicleListNew;
            model.setVehicleList(vehicleListNew);
            model = em.merge(model);
            if (brandOld != null && !brandOld.equals(brandNew)) {
                brandOld.getModelList().remove(model);
                brandOld = em.merge(brandOld);
            }
            if (brandNew != null && !brandNew.equals(brandOld)) {
                brandNew.getModelList().add(model);
                brandNew = em.merge(brandNew);
            }
            for (Vehicle vehicleListNewVehicle : vehicleListNew) {
                if (!vehicleListOld.contains(vehicleListNewVehicle)) {
                    Model oldModelOfVehicleListNewVehicle = vehicleListNewVehicle.getModel();
                    vehicleListNewVehicle.setModel(model);
                    vehicleListNewVehicle = em.merge(vehicleListNewVehicle);
                    if (oldModelOfVehicleListNewVehicle != null && !oldModelOfVehicleListNewVehicle.equals(model)) {
                        oldModelOfVehicleListNewVehicle.getVehicleList().remove(vehicleListNewVehicle);
                        oldModelOfVehicleListNewVehicle = em.merge(oldModelOfVehicleListNewVehicle);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = model.getId();
                if (findModel(id) == null) {
                    throw new NonexistentEntityException("The model with id " + id + " no longer exists.");
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
            Model model;
            try {
                model = em.getReference(Model.class, id);
                model.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The model with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Vehicle> vehicleListOrphanCheck = model.getVehicleList();
            for (Vehicle vehicleListOrphanCheckVehicle : vehicleListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Model (" + model + ") cannot be destroyed since the Vehicle " + vehicleListOrphanCheckVehicle + " in its vehicleList field has a non-nullable model field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Brand brand = model.getBrand();
            if (brand != null) {
                brand.getModelList().remove(model);
                brand = em.merge(brand);
            }
            em.remove(model);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Model> findModelEntities() {
        return findModelEntities(true, -1, -1);
    }

    public List<Model> findModelEntities(int maxResults, int firstResult) {
        return findModelEntities(false, maxResults, firstResult);
    }

    private List<Model> findModelEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Model.class));
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

    public Model findModel(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Model.class, id);
        } finally {
            em.close();
        }
    }

    public int getModelCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Model> rt = cq.from(Model.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
