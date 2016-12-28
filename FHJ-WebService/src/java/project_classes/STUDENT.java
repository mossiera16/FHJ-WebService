/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
package project_classes;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.OneToMany;
import project_entities.GRADE_ENTITY;
import project_entities.STUDENT_ENTITY;

/**
 *
 * @author standard
 */
public class STUDENT<T> extends PERSON {
    
    public STUDENT(){}
    
    public STUDENT(String PERSON_PK, String ADMINSEX, Date BIRTHDATE, String FIRST_NAME, boolean ISVALID, String LAST_NAME, String PASSWORD, String SEMESTER, int SVNR, int STUDENT_NR, String TITLE, String TYPE_OF_STUDY, String USERNAME){
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

    public STUDENT_ENTITY convertToSTUDENT_ENTITY() {
        STUDENT_ENTITY studentToConvert = new STUDENT_ENTITY(
                this.getPERSON_PK().toString(),
                this.getADMINSEX(),
                this.getBIRTHDATE(),
                this.getFIRSTNAME(),
                this.getISVALID(),
                this.getLASTNAME(),
                this.getPASSWORD(),
                this.getSEMESTER(),
                this.getSSN(),
                this.getSTUDENT_NR(),
                this.getTITLE(),
                this.getTYPE_OF_STUDY(),
                this.getUSERNAME(),
                this.getGRADE_ENTITies());
        return studentToConvert;
    }

    public STUDENT convertToStudent(STUDENT_ENTITY studentToConvert) {
        this.setPERSON_PK(studentToConvert.getPERSON_PK());
        this.setADMINSEX(studentToConvert.getADMINSEX());
        this.setBIRTHDATE(studentToConvert.getBIRTHDATE());
        this.setFIRSTNAME(studentToConvert.getFIRSTNAME());
        this.setLASTNAME(studentToConvert.getLASTNAME());
        this.setISVALID(studentToConvert.getISVALID());
        this.setPASSWORD(studentToConvert.getPASSWORD());
        this.setSEMESTER(studentToConvert.getSEMESTER());
        this.setSSN(studentToConvert.getSSN());
        this.setSTUDENT_NR(studentToConvert.getSTUDENT_NR());
        this.setTITLE(studentToConvert.getTITLE());
        this.setTYPE_OF_STUDY(studentToConvert.getTYPE_OF_STUDY());
        this.setUSERNAME(studentToConvert.getUSERNAME());
        this.setGRADE_ENTITies(studentToConvert.getGRADE_ENTITies());
        return this;
    }
}
