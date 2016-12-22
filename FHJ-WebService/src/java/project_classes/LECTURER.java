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
public class LECTURER {
    @Basic(optional = false)
    private int EMPLOEE_NR;

    @Basic(optional = false)
    private String FIELD_OF_STUDY_1;

    @Basic
    private String FIELD_OF_STUDY_2;

    

    public int getEMPLOEE_NR() {
        return this.EMPLOEE_NR;
    }

    public void setEMPLOEE_NR(int EMPLOEE_NR) {
        this.EMPLOEE_NR = EMPLOEE_NR;
    }

    public String getFIELD_OF_STUDY_1() {
        return this.FIELD_OF_STUDY_1;
    }

    public void setFIELD_OF_STUDY_1(String FIELD_OF_STUDY_1) {
        this.FIELD_OF_STUDY_1 = FIELD_OF_STUDY_1;
    }

    public String getFIELD_OF_STUDY_2() {
        return this.FIELD_OF_STUDY_2;
    }

    public void setFIELD_OF_STUDY_2(String FIELD_OF_STUDY_2) {
        this.FIELD_OF_STUDY_2 = FIELD_OF_STUDY_2;
    }
    
}
