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
import ip.entities.Model;
import ip.entities.Driver;
import java.util.ArrayList;
import java.util.List;
import ip.entities.Run;
import ip.entities.Architecture;
import ip.entities.Vehicle;
import ip.facades.exceptions.IllegalOrphanException;
import ip.facades.exceptions.NonexistentEntityException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

/**
 *
 * @author Ardavel
 */
public class VehicleJpaController implements Serializable {

    public VehicleJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Vehicle vehicle) {
        if (vehicle.getDriverList() == null) {
            vehicle.setDriverList(new ArrayList<Driver>());
        }
        if (vehicle.getRunList() == null) {
            vehicle.setRunList(new ArrayList<Run>());
        }
        if (vehicle.getArchitectureList() == null) {
            vehicle.setArchitectureList(new ArrayList<Architecture>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Model model = vehicle.getModel();
            if (model != null) {
                model = em.getReference(model.getClass(), model.getId());
                vehicle.setModel(model);
            }
            List<Driver> attachedDriverList = new ArrayList<Driver>();
            for (Driver driverListDriverToAttach : vehicle.getDriverList()) {
                driverListDriverToAttach = em.getReference(driverListDriverToAttach.getClass(), driverListDriverToAttach.getId());
                attachedDriverList.add(driverListDriverToAttach);
            }
            vehicle.setDriverList(attachedDriverList);
            List<Run> attachedRunList = new ArrayList<Run>();
            for (Run runListRunToAttach : vehicle.getRunList()) {
                runListRunToAttach = em.getReference(runListRunToAttach.getClass(), runListRunToAttach.getId());
                attachedRunList.add(runListRunToAttach);
            }
            vehicle.setRunList(attachedRunList);
            List<Architecture> attachedArchitectureList = new ArrayList<Architecture>();
            for (Architecture architectureListArchitectureToAttach : vehicle.getArchitectureList()) {
                architectureListArchitectureToAttach = em.getReference(architectureListArchitectureToAttach.getClass(), architectureListArchitectureToAttach.getId());
                attachedArchitectureList.add(architectureListArchitectureToAttach);
            }
            vehicle.setArchitectureList(attachedArchitectureList);
            em.persist(vehicle);
            if (model != null) {
                model.getVehicleList().add(vehicle);
                model = em.merge(model);
            }
            for (Driver driverListDriver : vehicle.getDriverList()) {
                Vehicle oldVehicleOfDriverListDriver = driverListDriver.getVehicle();
                driverListDriver.setVehicle(vehicle);
                driverListDriver = em.merge(driverListDriver);
                if (oldVehicleOfDriverListDriver != null) {
                    oldVehicleOfDriverListDriver.getDriverList().remove(driverListDriver);
                    oldVehicleOfDriverListDriver = em.merge(oldVehicleOfDriverListDriver);
                }
            }
            for (Run runListRun : vehicle.getRunList()) {
                Vehicle oldVehicleOfRunListRun = runListRun.getVehicle();
                runListRun.setVehicle(vehicle);
                runListRun = em.merge(runListRun);
                if (oldVehicleOfRunListRun != null) {
                    oldVehicleOfRunListRun.getRunList().remove(runListRun);
                    oldVehicleOfRunListRun = em.merge(oldVehicleOfRunListRun);
                }
            }
            for (Architecture architectureListArchitecture : vehicle.getArchitectureList()) {
                Vehicle oldVehicleOfArchitectureListArchitecture = architectureListArchitecture.getVehicle();
                architectureListArchitecture.setVehicle(vehicle);
                architectureListArchitecture = em.merge(architectureListArchitecture);
                if (oldVehicleOfArchitectureListArchitecture != null) {
                    oldVehicleOfArchitectureListArchitecture.getArchitectureList().remove(architectureListArchitecture);
                    oldVehicleOfArchitectureListArchitecture = em.merge(oldVehicleOfArchitectureListArchitecture);
                }
            }
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Vehicle vehicle) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Vehicle persistentVehicle = em.find(Vehicle.class, vehicle.getId());
            Model modelOld = persistentVehicle.getModel();
            Model modelNew = vehicle.getModel();
            List<Driver> driverListOld = persistentVehicle.getDriverList();
            List<Driver> driverListNew = vehicle.getDriverList();
            List<Run> runListOld = persistentVehicle.getRunList();
            List<Run> runListNew = vehicle.getRunList();
            List<Architecture> architectureListOld = persistentVehicle.getArchitectureList();
            List<Architecture> architectureListNew = vehicle.getArchitectureList();
            List<String> illegalOrphanMessages = null;
            for (Driver driverListOldDriver : driverListOld) {
                if (!driverListNew.contains(driverListOldDriver)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Driver " + driverListOldDriver + " since its vehicle field is not nullable.");
                }
            }
            for (Run runListOldRun : runListOld) {
                if (!runListNew.contains(runListOldRun)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Run " + runListOldRun + " since its vehicle field is not nullable.");
                }
            }
            for (Architecture architectureListOldArchitecture : architectureListOld) {
                if (!architectureListNew.contains(architectureListOldArchitecture)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Architecture " + architectureListOldArchitecture + " since its vehicle field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (modelNew != null) {
                modelNew = em.getReference(modelNew.getClass(), modelNew.getId());
                vehicle.setModel(modelNew);
            }
            List<Driver> attachedDriverListNew = new ArrayList<Driver>();
            for (Driver driverListNewDriverToAttach : driverListNew) {
                driverListNewDriverToAttach = em.getReference(driverListNewDriverToAttach.getClass(), driverListNewDriverToAttach.getId());
                attachedDriverListNew.add(driverListNewDriverToAttach);
            }
            driverListNew = attachedDriverListNew;
            vehicle.setDriverList(driverListNew);
            List<Run> attachedRunListNew = new ArrayList<Run>();
            for (Run runListNewRunToAttach : runListNew) {
                runListNewRunToAttach = em.getReference(runListNewRunToAttach.getClass(), runListNewRunToAttach.getId());
                attachedRunListNew.add(runListNewRunToAttach);
            }
            runListNew = attachedRunListNew;
            vehicle.setRunList(runListNew);
            List<Architecture> attachedArchitectureListNew = new ArrayList<Architecture>();
            for (Architecture architectureListNewArchitectureToAttach : architectureListNew) {
                architectureListNewArchitectureToAttach = em.getReference(architectureListNewArchitectureToAttach.getClass(), architectureListNewArchitectureToAttach.getId());
                attachedArchitectureListNew.add(architectureListNewArchitectureToAttach);
            }
            architectureListNew = attachedArchitectureListNew;
            vehicle.setArchitectureList(architectureListNew);
            vehicle = em.merge(vehicle);
            if (modelOld != null && !modelOld.equals(modelNew)) {
                modelOld.getVehicleList().remove(vehicle);
                modelOld = em.merge(modelOld);
            }
            if (modelNew != null && !modelNew.equals(modelOld)) {
                modelNew.getVehicleList().add(vehicle);
                modelNew = em.merge(modelNew);
            }
            for (Driver driverListNewDriver : driverListNew) {
                if (!driverListOld.contains(driverListNewDriver)) {
                    Vehicle oldVehicleOfDriverListNewDriver = driverListNewDriver.getVehicle();
                    driverListNewDriver.setVehicle(vehicle);
                    driverListNewDriver = em.merge(driverListNewDriver);
                    if (oldVehicleOfDriverListNewDriver != null && !oldVehicleOfDriverListNewDriver.equals(vehicle)) {
                        oldVehicleOfDriverListNewDriver.getDriverList().remove(driverListNewDriver);
                        oldVehicleOfDriverListNewDriver = em.merge(oldVehicleOfDriverListNewDriver);
                    }
                }
            }
            for (Run runListNewRun : runListNew) {
                if (!runListOld.contains(runListNewRun)) {
                    Vehicle oldVehicleOfRunListNewRun = runListNewRun.getVehicle();
                    runListNewRun.setVehicle(vehicle);
                    runListNewRun = em.merge(runListNewRun);
                    if (oldVehicleOfRunListNewRun != null && !oldVehicleOfRunListNewRun.equals(vehicle)) {
                        oldVehicleOfRunListNewRun.getRunList().remove(runListNewRun);
                        oldVehicleOfRunListNewRun = em.merge(oldVehicleOfRunListNewRun);
                    }
                }
            }
            for (Architecture architectureListNewArchitecture : architectureListNew) {
                if (!architectureListOld.contains(architectureListNewArchitecture)) {
                    Vehicle oldVehicleOfArchitectureListNewArchitecture = architectureListNewArchitecture.getVehicle();
                    architectureListNewArchitecture.setVehicle(vehicle);
                    architectureListNewArchitecture = em.merge(architectureListNewArchitecture);
                    if (oldVehicleOfArchitectureListNewArchitecture != null && !oldVehicleOfArchitectureListNewArchitecture.equals(vehicle)) {
                        oldVehicleOfArchitectureListNewArchitecture.getArchitectureList().remove(architectureListNewArchitecture);
                        oldVehicleOfArchitectureListNewArchitecture = em.merge(oldVehicleOfArchitectureListNewArchitecture);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = vehicle.getId();
                if (findVehicle(id) == null) {
                    throw new NonexistentEntityException("The vehicle with id " + id + " no longer exists.");
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
            Vehicle vehicle;
            try {
                vehicle = em.getReference(Vehicle.class, id);
                vehicle.getId();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The vehicle with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            List<Driver> driverListOrphanCheck = vehicle.getDriverList();
            for (Driver driverListOrphanCheckDriver : driverListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vehicle (" + vehicle + ") cannot be destroyed since the Driver " + driverListOrphanCheckDriver + " in its driverList field has a non-nullable vehicle field.");
            }
            List<Run> runListOrphanCheck = vehicle.getRunList();
            for (Run runListOrphanCheckRun : runListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vehicle (" + vehicle + ") cannot be destroyed since the Run " + runListOrphanCheckRun + " in its runList field has a non-nullable vehicle field.");
            }
            List<Architecture> architectureListOrphanCheck = vehicle.getArchitectureList();
            for (Architecture architectureListOrphanCheckArchitecture : architectureListOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Vehicle (" + vehicle + ") cannot be destroyed since the Architecture " + architectureListOrphanCheckArchitecture + " in its architectureList field has a non-nullable vehicle field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Model model = vehicle.getModel();
            if (model != null) {
                model.getVehicleList().remove(vehicle);
                model = em.merge(model);
            }
            em.remove(vehicle);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Vehicle> findVehicleEntities() {
        return findVehicleEntities(true, -1, -1);
    }

    public List<Vehicle> findVehicleEntities(int maxResults, int firstResult) {
        return findVehicleEntities(false, maxResults, firstResult);
    }

    private List<Vehicle> findVehicleEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Vehicle.class));
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

    public Vehicle findVehicle(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Vehicle.class, id);
        } finally {
            em.close();
        }
    }

    public int getVehicleCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Vehicle> rt = cq.from(Vehicle.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }

}
