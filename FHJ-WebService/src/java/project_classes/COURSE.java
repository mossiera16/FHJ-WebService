/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
package project_classes;

import java.util.List;
import javax.persistence.Basic;


/**
 *
 * @author standard
 */
public class COURSE {
    @Basic
    private String COURSE_NAME;

    @Basic
    private int LECTURER_PK;

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
}
