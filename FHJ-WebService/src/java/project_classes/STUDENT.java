/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
package project_classes;

import javax.persistence.Basic;

/**
 *
 * @author standard
 */
public class STUDENT {
    @Basic(optional = false)
    private int STUDENT_NR;

    @Basic(optional = false)
    private String TYPE_OF_STUDY;

    @Basic
    private String SEMESTER;
    
    
    
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
}
