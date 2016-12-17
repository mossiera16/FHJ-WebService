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
import project_entities.STUDENT_ENTITY;

/**
 *
 * @author Notebook
 */
public class PersonToCheck {

    static List resultList = null;

    public static STUDENT_ENTITY login(STUDENT_ENTITY studentToCheck) {
        Statement stmt = null;
        DBAccess dbAccess = new DBAccess();
        String username = studentToCheck.getUSERNAME();
        String password = studentToCheck.getPASSWORD();
        String searchQuery = "SELECT c FROM STUDENT_ENTITY c WHERE c.USERNAME='" + username + "' AND c.PASSWORD='" + password + "'";
        try {

            resultList = dbAccess.executeSQLStatement(searchQuery);
            boolean isAvailable = resultList.iterator().hasNext();

            if (!isAvailable) {
                System.out.println("Passwort oder Benutzername leider nicht korrekt!");
                studentToCheck.setISVALID(false);
            } else if (isAvailable) {
                studentToCheck.setISVALID(true);
            }
            dbAccess.DBCloseAccess();
        } catch (Exception ex) {
            System.out.println("Log In failed: An Exception has occurred! " + ex);
        }
        return studentToCheck;
    }
}
