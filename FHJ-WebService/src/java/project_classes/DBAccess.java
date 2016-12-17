package project_classes;
import java.sql.ResultSet;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Notebook
 */
public class DBAccess {
    static EntityManagerFactory emf;
    static EntityManager em;
    static String persistencyUnit = "PROJECT_PU";
    
    public DBAccess() {
        try {
            emf = Persistence.createEntityManagerFactory(persistencyUnit);
            em = emf.createEntityManager();
        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(e.getMessage());
            if (emf != null) {
                emf.close();
            }
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }
    
    public List findWithName(String name) {
        return em.createQuery(
            "SELECT c FROM STUDENT_ENTITY c WHERE c.ADMINSEX LIKE :custName")
            .setParameter("custName", name)
            .setMaxResults(10)
            .getResultList();
    }
    
    public List executeSQLStatement(String sqlStatement){
        return em.createQuery(sqlStatement).getResultList();
    }

    public void DBPersistObject(Object objectToPersist) {
        if (em != null) {
            em.persist(objectToPersist);
            DBTransaction();
        }
    }
    
    public void DBUpdateObject(Object objectToUpdate){
         if (em != null) {
            em.merge(objectToUpdate);
            DBTransaction();
        }
    }

    private void DBTransaction() {
        em.getTransaction().begin();
        em.getTransaction().commit();
    }

    public void DBCloseAccess() {
        if (emf != null) {
            emf.close();
        }
        if (em != null && em.isOpen()) {
            em.close();
        }
    }
    
    public void DBGetObjectToUpdate(Class objectClass, Long id){
        if (em != null) {
            DBUpdateObject(em.find(objectClass, id));
        }
    }
}
