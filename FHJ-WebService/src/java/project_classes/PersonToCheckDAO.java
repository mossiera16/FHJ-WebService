/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
package project_classes;

import java.util.List;
import project_entities.PERSON_ENTITY;


public class PersonToCheckDAO {

    static List resultList = null;
    
    
    public static PERSON login(PERSON personToCheck) {
        
        String username = personToCheck.getUSERNAME();
        String password = personToCheck.getPASSWORD();
        String[]person_entities = new String[]{"STUDENT_ENTITY", "LECTURER_ENTITY", "ADMINISTRATOR_ENTITY"};

        String[]parameterValues = new String[] {username, password};
        
        for (String person_entity : person_entities) {
            personToCheck = checkLoginData(personToCheck, person_entity, parameterValues);
            if(personToCheck.getISVALID())
                return personToCheck;
        }
        return personToCheck;
    }
    
    private static PERSON checkLoginData(PERSON personToCheck,String person_entity, String[] parameterValues){
        try {
            String[]parameterStrings = new String[] {"username", "password"};
            String searchQuery = "SELECT c FROM " + person_entity + " c WHERE c.USERNAME = :username AND c.PASSWORD = :password";
            
            DBAccess dbAccess = new DBAccess(true);

            resultList = dbAccess.DBgetSQLResultList(searchQuery, parameterStrings, parameterValues);
            boolean isAvailable = resultList.iterator().hasNext();

            if (!isAvailable) {
                personToCheck.setISVALID(false);
            } else if (isAvailable) {
                Object elem = resultList.iterator().next();
                PERSON_ENTITY person = (PERSON_ENTITY) elem;
                personToCheck.setISVALID(true);
                personToCheck.setPERSON_PK(person.getPERSON_PK());
                personToCheck.setFIRSTNAME(person.getFIRSTNAME());
                personToCheck.setLASTNAME(person.getLASTNAME());
                personToCheck.setADMINSEX(person.getADMINSEX());
                personToCheck.setPERSON_TYPE(person_entity);
            }
            dbAccess.DBCloseAccess();
        } catch (Exception ex) {
            System.out.println("Login-Vorgang wurde aufgrund einer Ausnahme abgebrochen! " + ex);
        }
        return personToCheck;
    }
}
