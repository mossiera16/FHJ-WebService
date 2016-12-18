/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LoginPackage;

import java.sql.Statement;
import java.util.Iterator;
import java.util.List;
import project_classes.DBAccess;
import project_entities.PERSON_ENTITY;
import project_entities.STUDENT_ENTITY;

/**
 *
 * @author Notebook
 */
public class PersonToCheckDAO {

    static List resultList = null;
    
    
    public static PERSON login(PERSON personToCheck) {
        
        String username = personToCheck.getUSERNAME();
        String password = personToCheck.getPASSWORD();
        String[]person_entities = new String[]{"STUDENT_ENTITY", "LECTURER_ENTITY", "ADMINISTRATOR_ENTITY"};

        String[]parameterValues = new String[] {username, password};
        
        for(int i = 0; i < person_entities.length; i++){
            personToCheck = checkLoginData(personToCheck,person_entities[i], parameterValues);
            if(personToCheck.getISVALID())
                return personToCheck;
        }
        return personToCheck;
    }
    
    private static PERSON checkLoginData(PERSON personToCheck,String person_entity, String[] parameterValues){
        try {
            String[]parameterStrings = new String[] {"username", "password"};
            String searchQuery = "SELECT c FROM " + person_entity + " c WHERE c.USERNAME = :username AND c.PASSWORD = :password";
            
            DBAccess dbAccess = new DBAccess();

            resultList = dbAccess.DBgetSQLResultList(searchQuery, parameterStrings, parameterValues);
            boolean isAvailable = resultList.iterator().hasNext();

            if (!isAvailable) {
                personToCheck.setISVALID(false);
            } else if (isAvailable) {
                Object elem = resultList.iterator().next();
                PERSON_ENTITY person = (PERSON_ENTITY) elem;
                personToCheck.setISVALID(true);
                personToCheck.setFIRSTNAME(person.getFIRSTNAME());
                personToCheck.setLASTNAME(person.getLASTNAME());
                personToCheck.setPERSON_TYPE(person_entity);
            }
            dbAccess.DBCloseAccess();
        } catch (Exception ex) {
            System.out.println("Login-Vorgang wurde aufgrund einer Ausnahme abgebrochen! " + ex);
        }
        return personToCheck;
    }
}
