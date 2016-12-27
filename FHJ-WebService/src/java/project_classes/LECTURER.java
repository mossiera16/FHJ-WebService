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
import javax.persistence.OneToMany;
import project_entities.COURSE_ENTITY;
import project_entities.LECTURER_ENTITY;
import project_entities.STUDENT_ENTITY;


/**
 *
 * @author standard
 */
public class LECTURER extends PERSON{
    @Basic(optional = false)
    private int EMPLOEE_NR;

    @Basic(optional = false)
    private String FIELD_OF_STUDY_1;

    @Basic
    private String FIELD_OF_STUDY_2;

    @OneToMany(targetEntity = COURSE_ENTITY.class)
    private List<COURSE_ENTITY> COURSE_ENTITies;

    

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
    
    public List<COURSE_ENTITY> getCOURSE_ENTITies() {
        return this.COURSE_ENTITies;
    }

    public void setCOURSE_ENTITies(List<COURSE_ENTITY> COURSE_ENTITies) {
        this.COURSE_ENTITies = COURSE_ENTITies;
    }
    
    
    public LECTURER_ENTITY convertToSTUDENT_ENTITY() {
        LECTURER_ENTITY lecturerToConvert = new LECTURER_ENTITY(
                this.getPERSON_PK().toString(),
                this.getADMINSEX(),
                this.getBIRTHDATE(),
                this.getEMPLOEE_NR(),
                this.getFIELD_OF_STUDY_1(),
                this.getFIELD_OF_STUDY_2(),
                this.getFIRSTNAME(),
                this.getISVALID(),
                this.getLASTNAME(),
                this.getPASSWORD(),
                this.getSSN(),
                this.getTITLE(),
                this.getUSERNAME(),
                this.getCOURSE_ENTITies());
        return lecturerToConvert;
    }
    
    public void convertToLecturer(LECTURER_ENTITY lecturerToConvert) {
        this.setPERSON_PK(lecturerToConvert.getPERSON_PK());
        this.setADMINSEX(lecturerToConvert.getADMINSEX());
        this.setBIRTHDATE(lecturerToConvert.getBIRTHDATE());
        this.setEMPLOEE_NR(lecturerToConvert.getEMPLOEE_NR());
        this.setFIELD_OF_STUDY_1(lecturerToConvert.getFIELD_OF_STUDY_1());
        this.setFIELD_OF_STUDY_2(lecturerToConvert.getFIELD_OF_STUDY_2());
        this.setFIRSTNAME(lecturerToConvert.getFIRSTNAME());
        this.setLASTNAME(lecturerToConvert.getLASTNAME());
        this.setISVALID(lecturerToConvert.getISVALID());
        this.setPASSWORD(lecturerToConvert.getPASSWORD());
        this.setSSN(lecturerToConvert.getSSN());
        this.setTITLE(lecturerToConvert.getTITLE());
        this.setUSERNAME(lecturerToConvert.getUSERNAME());
        this.setCOURSE_ENTITies(lecturerToConvert.getCOURSE_ENTITies());
    }
    
    
}
