/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
package project_classes;

import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.persistence.Basic;
import project_entities.COURSE_ENTITY;

/**
 *
 * @author Notebook
 * @param <T>
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
    private PERSON_TYPE_ENUM PERSON_TYPE_ENUM;

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

    public PERSON_TYPE_ENUM getPERSON_TYPE() {
        return this.PERSON_TYPE_ENUM;
    }

    public void setPERSON_TYPE(String PERSON_TYPE) {
        this.PERSON_TYPE_ENUM = new PERSON_TYPE_ENUM(PERSON_TYPE);
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

    public ResultSet getCourseDetails(Integer COURSE_PK, boolean isResultCall) {
        ResultSet dbResult;
        String sqlStatement = "";
        switch (this.getPERSON_TYPE().personType) {
            case 2: {
                sqlStatement = "SELECT course.* FROM STUDENT_ENTITY student, COURSE_ENTITY course, GRADE_ENTITY grade\n"
                        + "WHERE student.PERSON_PK = grade.STUDENT_PK\n"
                        + "AND grade.COURSE_PK = course.COURSE_PK\n"
                        + "AND student.PERSON_PK = ?";
                break;
            }
            case 1: {
                sqlStatement = "SELECT course.* FROM LECTURER_ENTITY lecturer, COURSE_ENTITY course\n"
                        + "WHERE course.LECTURER_PK = lecturer.PERSON_PK AND\n"
                        + "lecturer.PERSON_PK = ?";

                break;
            }
        }

        if (COURSE_PK != null) {
            sqlStatement += " AND course.COURSE_PK = ?";
        } else if (!isResultCall) {
            sqlStatement += " GROUP BY course.COURSE_PK, course.COURSE_NAME, course.LECTURER_PK, course.SEMESTER, course.STUDY";
        }
        
        DBAccess dbAccess = new DBAccess(false);
        Long[] parameterValues = new Long[]{this.getPERSON_PK()};

        if (COURSE_PK != null) {
            parameterValues = new Long[]{this.getPERSON_PK(), Long.parseLong(COURSE_PK.toString())};
        }

        dbResult = dbAccess.DBgetSQLResultSet(sqlStatement,  parameterValues);
        dbAccess.DBCloseAccess();
        return dbResult;
    }

    public ResultSet getGradeDetailsForPerson(Integer COURSE_PK) {
        ResultSet rs;
        String sqlStatement = "";
        if (this.getPERSON_TYPE().personType == 1) {
            sqlStatement += getGradeDetailsForLecturer();
        } else {
            sqlStatement += getGradeDetailsForStudent();
        }
        Long[] parameterValues = new Long[]{this.getPERSON_PK()};

        if (COURSE_PK != null) {
            sqlStatement += " AND course.COURSE_PK = ? ";
            parameterValues = new Long[]{this.getPERSON_PK(), Long.parseLong(COURSE_PK.toString())};
        }

        DBAccess dbAccess = new DBAccess(false);

        rs = dbAccess.DBgetSQLResultSet(sqlStatement, parameterValues);
        dbAccess.DBCloseAccess();

        return rs;
    }

    public String getGradeDetailsForLecturer() {
        return "SELECT course.COURSE_PK, grade.GRADE_PK, course.COURSE_NAME, grade.GRADE, student.FIRSTNAME, student.LASTNAME, student.STUDENT_NR, student.PERSON_PK FROM LECTURER_ENTITY AS lecturer, COURSE_ENTITY AS course, GRADE_ENTITY AS grade, STUDENT_ENTITY AS student\n"
                + "WHERE lecturer.PERSON_PK = course.LECTURER_PK\n"
                + "AND grade.COURSE_PK = course.COURSE_PK\n"
                + "AND grade.STUDENT_PK = student.PERSON_PK\n"
                + "AND lecturer.PERSON_PK = ?";
    }

    public String getGradeDetailsForStudent() {
        return "SELECT course.COURSE_NAME, grade.GRADE FROM STUDENT_ENTITY AS student, COURSE_ENTITY AS course, GRADE_ENTITY AS grade\n"
                + "WHERE student.PERSON_PK = grade.STUDENT_PK\n"
                + "AND grade.COURSE_PK = course.COURSE_PK\n"
                + "AND student.PERSON_PK = ?";
    }

    public void updateGradeDetails(String updateData, Integer courseNumber) {
        if (!updateData.equals("")) {
            String cleanedUpdateData;
            if (courseNumber == null) {
                cleanedUpdateData = updateData.replace("update=", "");
            } else {
                cleanedUpdateData = updateData.replace("courseNumber=" + courseNumber + "&update=", "");
            }

            String[] splittedArray = cleanedUpdateData.split("&");
            String updateStatement = "UPDATE GRADE_ENTITY SET GRADE = ? WHERE GRADE_PK = ?";
            DBAccess dbAccess = new DBAccess(false);
            Long[] parameterValues;
            String[] updateArray;
            for (String splittedItem : splittedArray) {
                updateArray = splittedItem.split("=");
                parameterValues = new Long[]{Long.parseLong(updateArray[1]), Long.parseLong(updateArray[0])};
                dbAccess.DBupdateData(updateStatement, parameterValues);
            }
            dbAccess.DBCloseAccess();
        }
    }

    public ResultSet getStudents() {
        ResultSet rs;
        String sqlStatement = "SELECT * \n"
                + "FROM STUDENT_ENTITY AS student WHERE 1 = ?";
        DBAccess dbAccess = new DBAccess(false);
        Long[] parameterValues = new Long[]{Long.parseLong("1")};
        rs = dbAccess.DBgetSQLResultSet(sqlStatement, parameterValues);

        dbAccess.DBCloseAccess();
        return rs;
    }

    public ResultSet getCourses() {
        ResultSet rs;
        String sqlStatement = "SELECT course.*, lecturer.PERSON_PK, lecturer.FIRSTNAME, lecturer.LASTNAME \n"
                + "FROM COURSE_ENTITY AS course, LECTURER_ENTITY AS lecturer WHERE course.LECTURER_PK = lecturer.PERSON_PK AND 1 = ?";
        DBAccess dbAccess = new DBAccess(false);
        Long[] parameterValues = new Long[]{Long.parseLong("1")};
        rs = dbAccess.DBgetSQLResultSet(sqlStatement, parameterValues);

        dbAccess.DBCloseAccess();
        return rs;
    }

    public ResultSet getLecturers() {
        ResultSet rs;
        String sqlStatement = "SELECT * \n"
                + "FROM LECTURER_ENTITY AS lecturer WHERE 1 = ?";
        DBAccess dbAccess = new DBAccess(false);
        Long[] parameterValues = new Long[]{Long.parseLong("1")};
        rs = dbAccess.DBgetSQLResultSet(sqlStatement, parameterValues);

        dbAccess.DBCloseAccess();
        return rs;
    }

    public void deleteStudentFromCourse(String studentPK, String coursePK) {
        String sqlStatement = "DELETE FROM GRADE_ENTITY as grade WHERE grade.STUDENT_PK = ? AND grade.COURSE_PK = ?";

        DBAccess dbAccess = new DBAccess(false);
        Long[] parameterValues = new Long[]{Long.parseLong(studentPK), Long.parseLong(coursePK)};

        dbAccess.DBupdateData(sqlStatement, parameterValues);
        dbAccess.DBCloseAccess();
    }

    public void administrateData(String data, String adminType, Integer paramCount) {
        String insertStatement = "", deleteStatement = "", updateStatement = "";
        switch (adminType) {
            case "courses": {
                insertStatement = "INSERT INTO COURSE_ENTITY (COURSE_PK, COURSE_NAME, LECTURER_PK, SEMESTER, STUDY) VALUES(NEXT VALUE FOR SEQUENCE, ?, ?, ?, ?)";
                deleteStatement = "DELETE FROM COURSE_ENTITY WHERE COURSE_PK = ?";
                updateStatement = "UPDATE COURSE_ENTITY SET COURSE_NAME=?, LECTURER_PK=?, SEMESTER=?, STUDY=? WHERE COURSE_PK = ?";
                break;
            }
            case "lecturers": {
                insertStatement = "INSERT INTO LECTURER_ENTITY (PASSWORD, PERSON_PK, ADMINSEX, BIRTHDATE, EMPLOEE_NR, FIRSTNAME, LASTNAME, USERNAME, SSN, TITLE) VALUES('a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', NEXT VALUE FOR SEQUENCE, ?, ?, ?, ?, ?, ?, ?,?)";
                deleteStatement = "DELETE FROM LECTURER_ENTITY WHERE PERSON_PK = ?";
                updateStatement = "UPDATE LECTURER_ENTITY SET ADMINSEX=?, BIRTHDATE=?, EMPLOEE_NR=?, FIRSTNAME=?, LASTNAME=?, USERNAME=?, SSN=?, TITLE=? WHERE PERSON_PK = ?";
                break;
            }
            case "students": {
                insertStatement = "INSERT INTO STUDENT_ENTITY (PASSWORD, PERSON_PK, ADMINSEX, BIRTHDATE, FIRSTNAME, LASTNAME, USERNAME, SEMESTER, SSN, STUDENT_NR, TITLE, TYPE_OF_STUDY) VALUES('a94a8fe5ccb19ba61c4c0873d391e987982fbbd3', NEXT VALUE FOR SEQUENCE, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
                deleteStatement = "DELETE FROM STUDENT_ENTITY WHERE PERSON_PK = ?";
                updateStatement = "UPDATE STUDENT_ENTITY SET ADMINSEX=?, BIRTHDATE=?, FIRSTNAME=?, LASTNAME=?, USERNAME=?, SEMESTER=?, SSN=?, STUDENT_NR=?, TITLE=?, TYPE_OF_STUDY=? WHERE PERSON_PK = ?";
                break;
            }

        }

        String[] array = data.split("insert-update=");
        String deleteData = array[0].replace("delete=", "");
        adminDeleteData(deleteData, deleteStatement);
        String insertUpdateData = array[1];
        adminInsertUpdateData(insertUpdateData, insertStatement, updateStatement, paramCount);

    }

    private void adminDeleteData(String deleteData, String deleteStatement) {
        try {
            if (!deleteData.equals("")) {
                Long[] parameterValues;
                String[] deletePairs = deleteData.split("&");
                String[] deleteArray;
                DBAccess dbAccess = new DBAccess(false);
                for (String deleteItem : deletePairs) {
                    if (!deleteItem.equals("")) {
                        deleteArray = deleteItem.split("=");
                        parameterValues = new Long[]{Long.parseLong(deleteArray[0])};
                        dbAccess.DBupdateData(deleteStatement, parameterValues);
                    }
                }
                dbAccess.DBCloseAccess();
            }
        } catch (NumberFormatException ex) {
            System.out.print("Error: " + ex.getMessage());
        }
    }
    
    private void adminInsertUpdateData(String insertUpdateData,String insertStatement,String updateStatement,Integer paramCount){
        try {
            if (!insertUpdateData.equals("")) {
                String[] parameterValues = new String[paramCount];
                String[] insertUpdatePairs = insertUpdateData.split("&");
                String[] insertUpdateArray;
                DBAccess dbAccess = new DBAccess(false);
                int count = 0;
                for (String insertUpdatePair : insertUpdatePairs) {
                    if (!insertUpdatePair.equals("")) {
                        insertUpdateArray = insertUpdatePair.split("=");

                        if (insertUpdateArray.length == 1) {
                            break;
                        }
                        parameterValues[count] = insertUpdateArray[1].replace("+", " ");
                        count++;
                        if (count == (paramCount-1)) {
                            parameterValues[count] = insertUpdateArray[0];
                            if (insertUpdateArray[0].equals("0")) {
                                String[] parameterValuesToInsert = new String[(paramCount-1)];
                                parameterValuesToInsert = Arrays.copyOfRange(parameterValues, 0, (paramCount-1));
                                dbAccess.DBupdateData(insertStatement, parameterValuesToInsert);
                            } else {
                                dbAccess.DBupdateData(updateStatement, parameterValues);
                            }
                            count = 0;
                        }
                    }
                }
                dbAccess.DBCloseAccess();
            }
        } catch (NumberFormatException ex) {
            System.out.print("Error: " + ex.getMessage());
        }
    }

    public void deleteInsertStudents(String deleteInsertData) {
        String[] array = deleteInsertData.split("insert");
        deleteStudents(array[0].replace("delete=", ""));

        insertStudents(array[1].replace("id", "").replace("=", ""));
    }

    private void deleteStudents(String deleteData) {
        try {
            if (!deleteData.equals("")) {
                String deleteStatement = "DELETE FROM GRADE_ENTITY WHERE COURSE_PK = ? AND STUDENT_PK = ?";
                Long[] parameterValues;
                String[] deletePairs = deleteData.split("&");
                String[] deleteArray;
                DBAccess dbAccess = new DBAccess(false);
                for (String deletePair : deletePairs) {
                    if (!deletePair.equals("")) {
                        deleteArray = deletePair.split("=");
                        parameterValues = new Long[]{Long.parseLong(deleteArray[1]), Long.parseLong(deleteArray[0])};
                        dbAccess.DBupdateData(deleteStatement, parameterValues);
                    }
                }
                dbAccess.DBCloseAccess();
            }
        } catch (NumberFormatException ex) {
            System.out.print("Error: " + ex.getMessage());
        }
    }

    private void insertStudents(String insertData) {
        try {
            String[] insertArray = insertData.split("&");
            String checkStatement = "SELECT * FROM GRADE_ENTITY as grade WHERE grade.COURSE_PK = ? AND grade.STUDENT_PK = ?";
            ResultSet rs;
            Long[] parameterValues;
            String insertStatement = "INSERT INTO GRADE_ENTITY (GRADE_PK, COURSE_PK, GRADE, STUDENT_PK) VALUES(NEXT VALUE FOR SEQUENCE, ?, 0, ?)";
            DBAccess dbAccess = new DBAccess(false);
            for (int i = 1; i < insertArray.length; i++) {
                if (!insertArray[i - 1].equals("0") && !insertArray[i].equals("0")) {
                    parameterValues = new Long[]{Long.parseLong(insertArray[i]), Long.parseLong(insertArray[i - 1])};
                    rs = dbAccess.DBgetSQLResultSet(checkStatement, parameterValues);
                    if (rs != null && !rs.next()) {
                        dbAccess.DBupdateData(insertStatement, parameterValues);
                    }
                }
            }
            dbAccess.DBCloseAccess();
        } catch (NumberFormatException | SQLException ex) {
            System.out.print("Error: " + ex.getMessage());
        }

    }
}
