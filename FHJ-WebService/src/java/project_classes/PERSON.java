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
import java.util.Iterator;
import java.util.List;
import static javafx.scene.input.KeyCode.T;
import javax.persistence.Basic;
import project_entities.COURSE_ENTITY;
import project_entities.STUDENT_ENTITY;

/**
 *
 * @author Notebook
 */
public class PERSON<T> {

    @Basic(optional = false)
    private String FIRSTNAME;

    @Basic(optional = false)
    private String LASTNAME;

    @Basic
    private String PASSWORD;

    @Basic
    private String USERNAME;

    @Basic
    private boolean ISVALID;

    @Basic
    private String PERSON_TYPE;

    @Basic
    private String TITLE;

    @Basic
    private Date BIRTHDATE;

    private Long PERSON_PK;

    @Basic(optional = false)
    private int SSN;

    @Basic
    private String ADMINSEX;

    public String getFIRSTNAME() {
        return this.FIRSTNAME;
    }

    public void setFIRSTNAME(String FIRSTNAME) {
        this.FIRSTNAME = FIRSTNAME;
    }

    public String getLASTNAME() {
        return this.LASTNAME;
    }

    public void setLASTNAME(String LASTNAME) {
        this.LASTNAME = LASTNAME;
    }

    public String getPASSWORD() {
        return this.PASSWORD;
    }

    public void setPASSWORD(String PASSWORD) {
        this.PASSWORD = PASSWORD;
    }

    public String getUSERNAME() {
        return this.USERNAME;
    }

    public void setUSERNAME(String USERNAME) {
        this.USERNAME = USERNAME;
    }

    public boolean getISVALID() {
        return this.ISVALID;
    }

    public void setISVALID(boolean ISVALID) {
        this.ISVALID = ISVALID;
    }

    public String getPERSON_TYPE() {
        return this.PERSON_TYPE;
    }

    public void setPERSON_TYPE(String PERSON_TYPE) {
        this.PERSON_TYPE = PERSON_TYPE;
    }

    public String getTITLE() {
        return this.TITLE;
    }

    public void setTITLE(String TITLE) {
        this.TITLE = TITLE;
    }

    public Date getBIRTHDATE() {
        return this.BIRTHDATE;
    }

    public void setBIRTHDATE(Date BIRTHDATE) {
        this.BIRTHDATE = BIRTHDATE;
    }

    public int getSSN() {
        return this.SSN;
    }

    public void setSSN(int SSN) {
        this.SSN = SSN;
    }

    public String getADMINSEX() {
        return this.ADMINSEX;
    }

    public void setADMINSEX(String ADMINSEX) {
        this.ADMINSEX = ADMINSEX;
    }

    public Long getPERSON_PK() {
        return PERSON_PK;
    }

    public void setPERSON_PK(Long PERSON_PK) {
        this.PERSON_PK = PERSON_PK;
    }

    public T getPERSON(String personType) {
        DBAccess dbAccess = new DBAccess();
        T result;
        switch (personType) {
            case "STUDENT_ENTITY": {
                result = (T) dbAccess.DBgetSQLResultList("SELECT * FROM STUDENT_ENTITY WHERE PERSON_PK = :PERSON_PK", new String[]{"PERSON_PK"}, new Long[]{this.PERSON_PK});
                dbAccess.DBCloseAccess();
                return result;
            }
            case "LECTURER_ENTITY": {
                result = (T) dbAccess.DBgetSQLResultList("SELECT * FROM LECTURER_ENTITY WHERE PERSON_PK = :PERSON_PK", new String[]{"PERSON_PK"}, new Long[]{this.PERSON_PK});
                dbAccess.DBCloseAccess();
                return result;

            }
            case "ADMINISTRATOR_ENTITY": {
                result = (T) dbAccess.DBgetSQLResultList("SELECT * FROM ADMINISTRATOR_ENTITY WHERE PERSON_PK = :PERSON_PK", new String[]{"PERSON_PK"}, new Long[]{this.PERSON_PK});
                dbAccess.DBCloseAccess();
                return result;
            }
            default:
                return null;
        }
    }

    public List<COURSE> getCourseDetails() {
        List<COURSE_ENTITY> dbResult = new ArrayList();
        switch (this.getPERSON_TYPE()) {
            case "STUDENT_ENTITY": {
                dbResult = getCourseDetailsForPerson("SELECT course FROM STUDENT_ENTITY student, COURSE_ENTITY course, GRADE_ENTITY grade\n"
                        + "WHERE student.PERSON_PK = grade.STUDENT_PK\n"
                        + "AND grade.COURSE_PK = course.COURSE_PK\n"
                        + "AND student.PERSON_PK = :PERSON_PK;");
                break;
            }
            case "LECTURER_ENTITY": {
                dbResult = getCourseDetailsForPerson("SELECT course FROM LECTURER_ENTITY lecturer, COURSE_ENTITY course\n"
                        + "WHERE course.LECTURER_PK = lecturer.PERSON_PK AND\n"
                        + "lecturer.PERSON_PK = :PERSON_PK;");
                break;
            }
        }

        List<COURSE> result = new ArrayList();
        COURSE courseToConvert;
        for (int i = 0; i < dbResult.size(); i++) {
            courseToConvert = new COURSE();
            result.add(i, courseToConvert.convertToCOURSE(dbResult.get(i)));
        }

        return result;
    }

    private List<COURSE_ENTITY> getCourseDetailsForPerson(String sqlStatement) {
        DBAccess dbAccess = new DBAccess();
        List<COURSE_ENTITY> dbResult = new ArrayList();
        String[] parameterStrings = new String[]{"PERSON_PK"};
        Long[] parameterValues = new Long[]{this.getPERSON_PK()};

        dbResult = dbAccess.DBgetSQLResultList(sqlStatement, parameterStrings, parameterValues);
        dbAccess.DBCloseAccess();
        return dbResult;
    }
}
