/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
package project_classes;

import java.util.ArrayList;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import javax.persistence.Basic;
import project_entities.COURSE_ENTITY;


/**
 *
 * @author standard
 */
public class COURSE<T> {
    @Basic
    private String COURSE_NAME;

    @Basic
    private int LECTURER_PK;
    
    private Long COURSE_PK;
    
    private int SEMESTER;
    
    private String STUDY;

    public String getCOURSE_NAME() {
        return this.COURSE_NAME;
    }

    public void setCOURSE_NAME(String COURSE_NAME) {
        this.COURSE_NAME = COURSE_NAME;
    }

    public int getLECTURER_PK() {
        return this.LECTURER_PK;
    }

    public void setLECTURER_PK(int LECTURER_PK) {
        this.LECTURER_PK = LECTURER_PK;
    }

    public Long getCOURSE_PK() {
        return COURSE_PK;
    }

    public void setCOURSE_PK(Long COURSE_PK) {
        this.COURSE_PK = COURSE_PK;
    }
    
    public int getSEMESTER() {
        return SEMESTER;
    }

    public void setSEMESTER(int SEMESTER) {
        this.SEMESTER = SEMESTER;
    }

    public String getSTUDY() {
        return STUDY;
    }

    public void setSTUDY(String STUDY) {
        this.STUDY = STUDY;
    }
    
    
    
    public COURSE_ENTITY convertToCOURSE_ENTITY(){
        COURSE_ENTITY courseToConvert = new COURSE_ENTITY();
        courseToConvert.setCOURSE_NAME(this.getCOURSE_NAME());
        courseToConvert.setLECTURER_PK(this.getLECTURER_PK());
        courseToConvert.setCOURSE_PK(this.getCOURSE_PK());
        courseToConvert.setSEMESTER(this.getSEMESTER());
        courseToConvert.setSTUDY(this.getSTUDY());
        return courseToConvert;
    }
    
    public COURSE convertToCOURSE(COURSE_ENTITY courseToConvert){
        this.setCOURSE_NAME(courseToConvert.getCOURSE_NAME());
        this.setLECTURER_PK(courseToConvert.getLECTURER_PK());
        this.setCOURSE_PK(courseToConvert.getCOURSE_PK());
        this.setSEMESTER(courseToConvert.getSEMESTER());
        this.setSTUDY(courseToConvert.getSTUDY());

        return this;
    }
    
    public List<COURSE> getCourses(String[] parameterStrings, T[] parameterValues, String sqlStatement){
        DBAccess dbAccess = new DBAccess();
        List<COURSE> result = new ArrayList();
        dbAccess.DBgetSQLResultList(sqlStatement, parameterStrings, parameterValues);
        
        
        
        return result;
    }
    
    
}
