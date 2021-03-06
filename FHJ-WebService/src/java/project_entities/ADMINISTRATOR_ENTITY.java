/**
 * This file was generated by the JPA Modeler
 */
package project_entities;

import java.io.Serializable;
import java.sql.Date;
import javax.persistence.Entity;

/**
 * Administrator
 */
@Entity
public class ADMINISTRATOR_ENTITY extends PERSON_ENTITY implements Serializable, PERSON {

    
    public ADMINISTRATOR_ENTITY(){}

    public ADMINISTRATOR_ENTITY(String PERSON_PK, String ADMINSEX, Date BIRTHDATE, String FIRST_NAME, boolean ISVALID, String LAST_NAME, String PASSWORD, int SVNR, String TITLE, String USERNAME){
        this.setPERSON_PK(Long.parseLong(PERSON_PK));
        this.setADMINSEX(ADMINSEX);
        this.setBIRTHDATE(BIRTHDATE);
        this.setFIRSTNAME(FIRST_NAME);
        this.setISVALID(ISVALID);
        this.setLASTNAME(LAST_NAME);
        this.setPASSWORD(PASSWORD);
        this.setSSN(SVNR);
        this.setTITLE(TITLE);
        this.setUSERNAME(USERNAME);
    }
    
    
      @Override
    public PERSON_ENTITY getClone(){
        ADMINISTRATOR_ENTITY administrator = null;
        try {
            administrator = (ADMINISTRATOR_ENTITY) super.clone();
            
        }
        catch(CloneNotSupportedException ex){
            System.out.println(ex.getMessage());
        }
        return administrator;
    }
}
