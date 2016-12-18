package project_classes;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author Notebook
 */
public class DBAccess<T> {

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

    public List DBgetSQLResultList(String sqlStatement, String[] parameterStrings, T[] parameterValues) {
        if (parameterStrings.length == parameterValues.length) {
            Query typedQuery = em.createQuery(sqlStatement);
            for (int i = 0; i < parameterStrings.length; i++) {
                typedQuery = typedQuery.setParameter(parameterStrings[i], parameterValues[i]);
            }
            return typedQuery.getResultList();
        } else {
            DBCloseAccess();
            return null;
        }
    }

    public void DBPersistObject(Object objectToPersist) {
        if (em != null) {
            em.persist(objectToPersist);
            DBTransaction();
        }
    }

    public void DBUpdateObject(Object objectToUpdate) {
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

    public void DBGetObjectToUpdate(Class objectClass, Long id) {
        if (em != null) {
            DBUpdateObject(em.find(objectClass, id));
        }
    }
}
