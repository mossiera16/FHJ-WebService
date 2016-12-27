/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
package project_classes;

import javax.persistence.Basic;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import project_entities.COURSE_ENTITY;
import project_entities.GRADE_ENTITY;

/**
 *
 * @author standard
 */
public class GRADE {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long GRADE_PK;
    
    @Basic
    private int STUDENT_PK;

    @Basic
    private int COURSE_PK;

    @Basic(optional = false)
    private int SEMESTER;

    @Basic
    private int GRADE;

    

    public int getSTUDENT_PK() {
        return this.STUDENT_PK;
    }

    public void setSTUDENT_PK(int STUDENT_PK) {
        this.STUDENT_PK = STUDENT_PK;
    }

    public int getCOURSE_PK() {
        return this.COURSE_PK;
    }

    public void setCOURSE_PK(int COURSE_PK) {
        this.COURSE_PK = COURSE_PK;
    }

    public int getSEMESTER() {
        return this.SEMESTER;
    }

    public void setSEMESTER(int SEMESTER) {
        this.SEMESTER = SEMESTER;
    }

    public int getGRADE() {
        return this.GRADE;
    }

    public void setGRADE(int GRADE) {
        this.GRADE = GRADE;
    }

    public Long getGRADE_PK() {
        return GRADE_PK;
    }

    public void setGRADE_PK(Long GRADE_PK) {
        this.GRADE_PK = GRADE_PK;
    }
    
    
    
    
    public GRADE_ENTITY convertToGRADE_ENTITY(){
        GRADE_ENTITY gradeToConvert = new GRADE_ENTITY();
        gradeToConvert.setCOURSE_PK(this.getCOURSE_PK());
        gradeToConvert.setGRADE(this.getGRADE());
        gradeToConvert.setGRADE_PK(this.getGRADE_PK());
        gradeToConvert.setSTUDENT_PK(this.getSTUDENT_PK());
        gradeToConvert.setSEMESTER(this.getSEMESTER());
        return gradeToConvert;
    }
    
    public GRADE convertToGRADE(GRADE_ENTITY gradeToConvert){
        this.setCOURSE_PK(gradeToConvert.getCOURSE_PK());
        this.setGRADE(gradeToConvert.getGRADE());
        this.setGRADE_PK(gradeToConvert.getGRADE_PK());
        this.setSEMESTER(gradeToConvert.getSEMESTER());
        this.setSTUDENT_PK(gradeToConvert.getSTUDENT_PK());
        return this;
    }
}
