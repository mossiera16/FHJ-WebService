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
public class GRADE {
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
}
