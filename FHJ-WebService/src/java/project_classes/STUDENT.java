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
import javax.persistence.Basic;
import project_entities.STUDENT_ENTITY;

/**
 *
 * @author standard
 */
public class STUDENT<T> extends PERSON {

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

    public STUDENT_ENTITY convertToSTUDENT_ENTITY() {
        STUDENT_ENTITY studentToConvert = new STUDENT_ENTITY();
        studentToConvert.setPERSON_PK(this.getPERSON_PK());
        studentToConvert.setADMINSEX(this.getADMINSEX());
        studentToConvert.setBIRTHDATE(this.getBIRTHDATE());
        studentToConvert.setFIRSTNAME(this.getFIRSTNAME());
        studentToConvert.setLASTNAME(this.getLASTNAME());
        studentToConvert.setISVALID(this.getISVALID());
        studentToConvert.setPASSWORD(this.getPASSWORD());
        studentToConvert.setSEMESTER(this.getSEMESTER());
        studentToConvert.setSSN(this.getSSN());
        studentToConvert.setSTUDENT_NR(this.getSTUDENT_NR());
        studentToConvert.setTITLE(this.getTITLE());
        studentToConvert.setTYPE_OF_STUDY(this.getTYPE_OF_STUDY());
        studentToConvert.setUSERNAME(this.getUSERNAME());

        return studentToConvert;
    }

    public void convertToStudent(STUDENT_ENTITY studentToConvert) {
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
    }

   
}
