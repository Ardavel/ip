package ip.facades;

import ip.entities.Brand;
import java.io.Serializable;
import javax.persistence.*;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import ip.entities.Model;
import ip.facades.exceptions.IllegalOrphanException;
import ip.facades.exceptions.NonexistentEntityException;
import org.eclipse.persistence.internal.jpa.EntityManagerFactoryProvider;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Wojciech Szałapski
 */
public class BrandJpaController implements Serializable {

    public BrandJpaController() {
        emf = Persistence.createEntityManagerFactory("ipRankingPU");
    }
    private EntityManagerFactory emf;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Brand brand) {
        if (brand.getModelList() == null) {
            brand.setModelList(new ArrayList<Model>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            List<Model> attachedModelList = new ArrayList<Model>();
            for (Model modelListModelToAttach : brand.getModelList()) {
                modelListModelToAttach = em.getReference(modelListModelToAttach.getClass(), modelListModelToAttach.getId());
                attachedModelList.add(modelListModelToAttach);
            }
            brand.setModelList(attachedModelList);
            em.persist(brand);
            for (Model modelListModel : brand.getModelList()) {
                Brand oldBrandOfModelListModel = modelListModel.getBrand();
                modelListModel.setBrand(brand);
                modelListModel = em.merge(modelListModel);
                if (oldBrandOfModelListModel != null) {
                    oldBrandOfModelListModel.getModelList().remove(modelListModel);
                    oldBrandOfModelListModel = em.merge(oldBrandOfModelListModel);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Brand brand) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Brand persistentBrand = em.find(Brand.class, brand.getId());
            List<Model> modelListOld = persistentBrand.getModelList();
            List<Model> modelListNew = brand.getModelList();
            List<String> illegalOrphanMessages = null;
            for (Model modelListOldModel : modelListOld) {
                if (!modelListNew.contains(modelListOldModel)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Model " + modelListOldModel + " since its brand field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            List<Model> attachedModelListNew = new ArrayList<Model>();
            for (Model modelListNewModelToAttach : modelListNew) {
                modelListNewModelToAttach = em.getReference(modelListNewModelToAttach.getClass(), modelListNewModelToAttach.getId());
                attachedModelListNew.add(modelListNewModelToAttach);
            }
            modelListNew = attachedModelListNew;
            brand.setModelList(modelListNew);
            brand = em.merge(brand);
            for (Model modelListNewModel : modelListNew) {
                if (!modelListOld.contains(modelListNewModel)) {
                    Brand oldBrandOfModelListNewModel = modelListNewModel.getBrand();
                    modelListNewModel.setBrand(brand);
                    modelListNewModel = em.merge(modelListNewModel);
                    if (oldBrandOfModelListNewModel != null && !oldBrandOfModelListNewModel.equals(brand)) {
                        oldBrandOfModelListNewModel.getModelList().remove(modelListNewModel);
                        oldBrandOfModelListNewModel = em.merge(oldBrandOfModelListNewModel);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = brand.getId();
                if (findBrand(id) == null) {
                    throw new NonexistentEntityException("The brand with id " + id + " no longer exists.");
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
            Brand brand;
            try {
                brand = em.getReference(Brand.class, id);
                brand.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The brand with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Model> modelListOrphanCheck = brand.getModelList();
            for (Model modelListOrphanCheckModel : modelListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Brand (" + brand + ") cannot be destroyed since the Model " + modelListOrphanCheckModel + " in its modelList field has a non-nullable brand field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(brand);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Brand> findBrandEntities() {
        return findBrandEntities(true, -1, -1);
    }

    public List<Brand> findBrandEntities(int maxResults, int firstResult) {
        return findBrandEntities(false, maxResults, firstResult);
    }

    private List<Brand> findBrandEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Brand.class));
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

    public Brand findBrand(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Brand.class, id);
        } finally {
            em.close();
        }
    }

    public int getBrandCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Brand> rt = cq.from(Brand.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
