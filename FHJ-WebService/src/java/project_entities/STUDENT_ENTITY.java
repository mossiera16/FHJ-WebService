/**
 * This file was generated by the JPA Modeler
 */
package project_entities;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

/**
 * @author Notebook
 */
@Entity
public class STUDENT_ENTITY extends PERSON_ENTITY implements Serializable, PERSON {

    public STUDENT_ENTITY(){}
    
    public STUDENT_ENTITY(String PERSON_PK, String ADMINSEX, Date BIRTHDATE, String FIRST_NAME, boolean ISVALID, String LAST_NAME, String PASSWORD, String SEMESTER, int SVNR, int STUDENT_NR, String TITLE, String TYPE_OF_STUDY, String USERNAME){
        this.setPERSON_PK(Long.parseLong(PERSON_PK));
        this.setADMINSEX(ADMINSEX);
        this.setBIRTHDATE(BIRTHDATE);
        this.setFIRSTNAME(FIRST_NAME);
        this.setISVALID(ISVALID);
        this.setLASTNAME(LAST_NAME);
        this.setPASSWORD(PASSWORD);
        this.setSEMESTER(SEMESTER);
        this.setSSN(SVNR);
        this.setSTUDENT_NR(STUDENT_NR);
        this.setTITLE(TITLE);
        this.setTYPE_OF_STUDY(TYPE_OF_STUDY);
        this.setUSERNAME(USERNAME);
    }
    @Basic(optional = false)
    private int STUDENT_NR;

    @Basic(optional = false)
    private String TYPE_OF_STUDY;

    @Basic
    private String SEMESTER;

    @OneToMany(targetEntity = GRADE_ENTITY.class)
    private List<GRADE_ENTITY> GRADE_ENTITies;

    public int getSTUDENT_NR() {
        return this.STUDENT_NR;
    }

    public void setSTUDENT_NR(int STUDENT_NR) {
        this.STUDENT_NR = STUDENT_NR;
    }

    public String getTYPE_OF_STUDY() {
        return this.TYPE_OF_STUDY;
    }

    public void setTYPE_OF_STUDY(String TYPE_OF_STUDY) {
        this.TYPE_OF_STUDY = TYPE_OF_STUDY;
    }

    public String getSEMESTER() {
        return this.SEMESTER;
    }

    public void setSEMESTER(String SEMESTER) {
        this.SEMESTER = SEMESTER;
    }

    public List<GRADE_ENTITY> getGRADE_ENTITies() {
        return this.GRADE_ENTITies;
    }

    public void setGRADE_ENTITies(List<GRADE_ENTITY> GRADE_ENTITies) {
        this.GRADE_ENTITies = GRADE_ENTITies;
    }
    
    @Override
    public PERSON_ENTITY getClone(){
        STUDENT_ENTITY student = null;
        try {
            student = (STUDENT_ENTITY) super.clone();
            
        }
        catch(CloneNotSupportedException ex){
            System.out.println(ex.getMessage());
        }
        return student;
    }
}
