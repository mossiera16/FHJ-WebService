/**
 * This file was generated by the JPA Modeler
 */
package project_entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 * @author Notebook
 */
@Entity
public class GRADE_ENTITY implements Serializable {

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

    public Long getGRADE_PK() {
        return this.GRADE_PK;
    }

    public void setGRADE_PK(Long GRADE_PK) {
        this.GRADE_PK = GRADE_PK;
    }

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