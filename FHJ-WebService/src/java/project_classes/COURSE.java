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
import javax.persistence.OneToMany;
import project_entities.COURSE_ENTITY;
import project_entities.GRADE_ENTITY;


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
    
  @OneToMany(targetEntity = GRADE_ENTITY.class)
    private List<GRADE_ENTITY> GRADE_ENTITies;

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

    public List<GRADE_ENTITY> getGRADE_ENTITies() {
        return GRADE_ENTITies;
    }

    public void setGRADE_ENTITies(List<GRADE_ENTITY> GRADE_ENTITies) {
        this.GRADE_ENTITies = GRADE_ENTITies;
    }
    
  
    
    public COURSE_ENTITY convertToCOURSE_ENTITY(){
        COURSE_ENTITY courseToConvert = new COURSE_ENTITY();
        courseToConvert.setCOURSE_NAME(this.getCOURSE_NAME());
        courseToConvert.setLECTURER_PK(this.getLECTURER_PK());
        courseToConvert.setCOURSE_PK(this.getCOURSE_PK());
        courseToConvert.setSEMESTER(this.getSEMESTER());
        courseToConvert.setSTUDY(this.getSTUDY());
        courseToConvert.setGRADE_ENTITies(this.getGRADE_ENTITies());
        return courseToConvert;
    }
    
    public COURSE convertToCOURSE(COURSE_ENTITY courseToConvert){
        this.setCOURSE_NAME(courseToConvert.getCOURSE_NAME());
        this.setLECTURER_PK(courseToConvert.getLECTURER_PK());
        this.setCOURSE_PK(courseToConvert.getCOURSE_PK());
        this.setSEMESTER(courseToConvert.getSEMESTER());
        this.setSTUDY(courseToConvert.getSTUDY());
        this.setGRADE_ENTITies(courseToConvert.getGRADE_ENTITies());
        return this;
    }
    
     public boolean equals(Object obj) {
        if (obj == this) {
            return true;
        }
        if (!(obj instanceof PERSON)) {
            return false;
        }
        COURSE other = (COURSE) obj;
        return this.getCOURSE_PK().equals(other.getCOURSE_PK());
    }
}
