/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
package project_classes;

import static java.lang.String.format;
import java.sql.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;
import project_entities.ADMINISTRATOR_ENTITY;
import project_entities.COURSE_ENTITY;
import project_entities.GRADE_ENTITY;
import project_entities.LECTURER_ENTITY;
import project_entities.STUDENT_ENTITY;

public class DBAccess<T> {

    static EntityManagerFactory emf;
    static EntityManager em;
    static String persistencyUnit = "PROJECT_PU";

    public DBAccess() {
        try {
            emf = Persistence.createEntityManagerFactory(persistencyUnit);
            em = emf.createEntityManager();
        } catch (Exception e) {
            String message = e.getMessage();
            System.out.println(e.getMessage());
            if (emf != null) {
                emf.close();
            }
            if (em != null && em.isOpen()) {
                em.close();
            }
        }
    }

    public List DBgetSQLResultList(String sqlStatement, String[] parameterStrings, T[] parameterValues) {
        if (parameterStrings.length == parameterValues.length) {
            Query typedQuery = em.createQuery(sqlStatement);
            for (int i = 0; i < parameterStrings.length; i++) {
                typedQuery = typedQuery.setParameter(parameterStrings[i], parameterValues[i]);
            }
            return typedQuery.getResultList();
        } else {
            DBCloseAccess();
            return null;
        }
    }

    public void DBPersistObject(Object objectToPersist) {
        if (em != null) {
            em.persist(objectToPersist);
        }
    }

    public void DBPersistObjects(List<Object> objectsToPersist) {
        for (int i = 0; i < objectsToPersist.size(); i++) {
            DBPersistObject(objectsToPersist.get(i));
        }
    }

    public void DBUpdateObject(Object objectToUpdate) {
        if (em != null) {
            em.merge(objectToUpdate);
        }
    }

    public void DBExecuteSQLStatements(String sqlStatement, String[] parameterStrings, T[] parameterValues) {
        Query typedQuery;
        if (sqlStatement != "" && parameterStrings != null && parameterValues != null && parameterStrings.length == parameterValues.length) {
            typedQuery = em.createQuery(sqlStatement);
            for (int i = 0; i < parameterStrings.length; i++) {
                typedQuery = typedQuery.setParameter(parameterStrings[i], parameterValues[i]);
            }
            typedQuery.executeUpdate();
        } else if (sqlStatement != "") {
            typedQuery = em.createQuery(sqlStatement);
            typedQuery.executeUpdate();
        }
    }

    public void DBTransaction() {
        em.getTransaction().begin();
        em.getTransaction().commit();
    }

    public void DBCloseAccess() {
        if (emf != null) {
            emf.close();
        }
        if (em != null && em.isOpen()) {
            em.close();
        }
    }

    public void DBGetObjectToUpdate(Class objectClass, Long id) {
        if (em != null) {
            DBUpdateObject(em.find(objectClass, id));
        }
    }

    public void DBInsertSampleData() {
        try {
            //GRADE
            List<GRADE_ENTITY> gradeList1 = new ArrayList<GRADE_ENTITY>();
            GRADE_ENTITY grade1 = new GRADE_ENTITY("1", 1, 1, 1, 1);//Standards in der Gesundheitsinformatik
            GRADE_ENTITY grade2 = new GRADE_ENTITY("2", 2, 1, 1, 1);//Business Intelligence
            GRADE_ENTITY grade9 = new GRADE_ENTITY("9", 5, 2, 2, 1);//HCIT
            GRADE_ENTITY grade10 = new GRADE_ENTITY("10", 6, 3, 2, 1);//Selected Topics in Medical informatics
            GRADE_ENTITY grade11 = new GRADE_ENTITY("11", 2, 3, 1, 1);//Business Intelligence
            GRADE_ENTITY grade12 = new GRADE_ENTITY("12", 3, 2, 3, 1);//Softwarearchitekturen
            GRADE_ENTITY grade13 = new GRADE_ENTITY("13", 3, 1, 3, 1);//Softwarearchitekturen
            gradeList1.add(grade1);
            gradeList1.add(grade2);
            gradeList1.add(grade9);
            gradeList1.add(grade10);
            gradeList1.add(grade11);
            gradeList1.add(grade12);
            gradeList1.add(grade13);         
            

            DBPersistObjects((List<Object>) (List<?>) gradeList1);

            List<GRADE_ENTITY> gradeList2 = new ArrayList<GRADE_ENTITY>();
            GRADE_ENTITY grade3 = new GRADE_ENTITY("3", 1, 1, 1, 2);
            GRADE_ENTITY grade4 = new GRADE_ENTITY("4", 2, 1, 1, 2);
            gradeList2.add(grade3);
            gradeList2.add(grade4);

            DBPersistObjects((List<Object>) (List<?>) gradeList2);

            List<GRADE_ENTITY> gradeList3 = new ArrayList<GRADE_ENTITY>();
            GRADE_ENTITY grade5 = new GRADE_ENTITY("5", 1, 1, 1, 4);
            GRADE_ENTITY grade6 = new GRADE_ENTITY("6", 2, 1, 1, 4);
            GRADE_ENTITY grade14 = new GRADE_ENTITY("14", 3, 0, 3, 4);
            gradeList3.add(grade5);
            gradeList3.add(grade6);
            gradeList3.add(grade14);

            DBPersistObjects((List<Object>) (List<?>) gradeList3);

            List<GRADE_ENTITY> gradeList4 = new ArrayList<GRADE_ENTITY>();
            GRADE_ENTITY grade7 = new GRADE_ENTITY("7", 1, 1, 1, 5);
            GRADE_ENTITY grade8 = new GRADE_ENTITY("8", 2, 1, 1, 5);
            gradeList4.add(grade7);
            gradeList4.add(grade8);

            DBPersistObjects((List<Object>) (List<?>) gradeList4);

            //#####################################
            //Cloning-Example-----START-----STUDENT
            //#####################################
            DateFormat format = new SimpleDateFormat("DD.MM.YYYY", Locale.GERMAN);
            List<STUDENT_ENTITY> students = new ArrayList<STUDENT_ENTITY>();
            STUDENT_ENTITY student1 = new STUDENT_ENTITY("1", "m", new java.sql.Date(format.parse("16.10.1992").getTime()), "Andreas", false, "Mossier", "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", "1", 3333, 1310288011, "BSc", "Masters programme", "mossiera16", gradeList1);

            STUDENT_ENTITY clonedStudent = (STUDENT_ENTITY) student1.getClone();
            clonedStudent.setBIRTHDATE(new java.sql.Date(format.parse("20.11.1990").getTime()));
            clonedStudent.setFIRSTNAME("Eva");
            clonedStudent.setLASTNAME("Wurzer");
            clonedStudent.setTITLE("BA");
            clonedStudent.setUSERNAME("wurzere16");
            clonedStudent.setPERSON_PK(Long.parseLong("2"));

            STUDENT_ENTITY student2 = new STUDENT_ENTITY("3", "m", new java.sql.Date(format.parse("18.4.1969").getTime()), "Heinz", false, "Kurt", "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", "1", 3335, 1310288013, "BSc", "Masters programme", "kurth16", gradeList2);
            STUDENT_ENTITY student3 = new STUDENT_ENTITY("4", "w", new java.sql.Date(format.parse("18.4.1989").getTime()), "Romana", false, "Ausim", "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", "1", 33367, 1310288014, "BSc", "Masters programme", "ausimr16", gradeList3);
            STUDENT_ENTITY student4 = new STUDENT_ENTITY("5", "w", new java.sql.Date(format.parse("12.4.1984").getTime()), "Mina", false, "Shokrollahi", "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", "1", 4000, 1310288015, "BSc", "Masters programme", "shokrollahim16", gradeList4);

            students.add(student1);
            students.add(clonedStudent);
            students.add(student2);
            students.add(student3);
            students.add(student4);

            DBPersistObjects((List<Object>) (List<?>) students);

            //##############################
            //Cloning-Example-----END-------
            //##############################
            //COURSES
            List<COURSE_ENTITY> courses1 = new ArrayList<COURSE_ENTITY>();
            List<COURSE_ENTITY> courses2 = new ArrayList<COURSE_ENTITY>();
            List<COURSE_ENTITY> courses3 = new ArrayList<COURSE_ENTITY>();
            List<COURSE_ENTITY> courses4 = new ArrayList<COURSE_ENTITY>();
            List<COURSE_ENTITY> courses5 = new ArrayList<COURSE_ENTITY>();
            List<COURSE_ENTITY> courses6 = new ArrayList<COURSE_ENTITY>();
            List<GRADE_ENTITY> gradeCourseList1 = new ArrayList<GRADE_ENTITY>();
            List<GRADE_ENTITY> gradeCourseList2 = new ArrayList<GRADE_ENTITY>();
            List<GRADE_ENTITY> gradeCourseList3 = new ArrayList<GRADE_ENTITY>();
            List<GRADE_ENTITY> gradeCourseList5 = new ArrayList<GRADE_ENTITY>();
            List<GRADE_ENTITY> gradeCourseList6 = new ArrayList<GRADE_ENTITY>();
            
            gradeCourseList1.add(grade1);
            gradeCourseList1.add(grade3);
            gradeCourseList1.add(grade5);
            gradeCourseList1.add(grade7);
            
            gradeCourseList2.add(grade2);
            gradeCourseList2.add(grade11);
            gradeCourseList2.add(grade4);
            gradeCourseList2.add(grade6);
            gradeCourseList2.add(grade8);
            
            gradeCourseList3.add(grade12);
            gradeCourseList3.add(grade13);
            gradeCourseList3.add(grade14);
            
            gradeCourseList5.add(grade9);
            
            gradeCourseList6.add(grade10);
            
            COURSE_ENTITY course1 = new COURSE_ENTITY("1", "Standards in der Gesundheitsinformatik", 1, 1, "Masterstudium eHealth", gradeCourseList1);
            COURSE_ENTITY course2 = new COURSE_ENTITY("2", "Business Intelligence", 2, 1, "Masterstudium eHealth", gradeCourseList2);
            COURSE_ENTITY course3 = new COURSE_ENTITY("3", "Softwarearchitekturen", 3, 3, "Bachelorstudium eHealth", gradeCourseList3);
            COURSE_ENTITY course4 = new COURSE_ENTITY("4", "Softwareentwicklung", 4, 1, "Masterstudium eHealth", null);
            COURSE_ENTITY course5 = new COURSE_ENTITY("5", "HCIT", 4, 2, "Masterstudium eHealth", gradeCourseList5);
            COURSE_ENTITY course6 = new COURSE_ENTITY("6", "Selected Topics in Medical Informatics", 3, 2, "Masterstudium eHealth", gradeCourseList6);
            
            courses1.add(course1);
            courses2.add(course2);
            courses3.add(course3);
            courses4.add(course4);
            courses5.add(course5);
            courses6.add(course6);
            
            DBPersistObjects((List<Object>) (List<?>) courses1);
            DBPersistObjects((List<Object>) (List<?>) courses2);
            DBPersistObjects((List<Object>) (List<?>) courses3);
            DBPersistObjects((List<Object>) (List<?>) courses4);
            DBPersistObjects((List<Object>) (List<?>) courses5);
            DBPersistObjects((List<Object>) (List<?>) courses6);

            //LECTURERS
            List<LECTURER_ENTITY> lecturers = new ArrayList<LECTURER_ENTITY>();
            LECTURER_ENTITY lecturer1 = new LECTURER_ENTITY("1", "m", new java.sql.Date(format.parse("16.10.1955").getTime()), 1, "test", "test", "Jozef", false, "Aerts", "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", 3333, "DDr.", "aertsjozef", courses1);
            LECTURER_ENTITY lecturer2 = new LECTURER_ENTITY("2", "w", new java.sql.Date(format.parse("16.10.1975").getTime()), 2, "test", "test", "Johanna", false, "Wieser", "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", 3337, "Dr.", "wieserjohanna", courses2);
            LECTURER_ENTITY lecturer3 = new LECTURER_ENTITY("3", "m", new java.sql.Date(format.parse("01.5.1985").getTime()), 3, "test", "test", "Peter", false, "Seifter", "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", 3338, "Dr.", "seifterpeter", courses3);
            lecturers.add(lecturer1);
            lecturers.add(lecturer2);
            lecturers.add(lecturer3);

            DBPersistObjects((List<Object>) (List<?>) lecturers);

            //ADMINISTRATORS
            List<ADMINISTRATOR_ENTITY> administrators = new ArrayList<ADMINISTRATOR_ENTITY>();
            ADMINISTRATOR_ENTITY administrator1 = new ADMINISTRATOR_ENTITY("1", "m", new java.sql.Date(format.parse("16.10.1992").getTime()), "Andreas", false, "Mossier", "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", 3333, "BSc", "mossieraadmin");
            ADMINISTRATOR_ENTITY administrator2 = new ADMINISTRATOR_ENTITY("2", "w", new java.sql.Date(format.parse("18.4.1989").getTime()), "Romana", false, "Ausim", "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", 3366, "BSc", "ausimradmin");
            ADMINISTRATOR_ENTITY administrator3 = new ADMINISTRATOR_ENTITY("3", "w", new java.sql.Date(format.parse("12.4.1984").getTime()), "Mina", false, "Shokrollahi", "a94a8fe5ccb19ba61c4c0873d391e987982fbbd3", 3355, "BSc", "shokrollahimadmin");
            administrators.add(administrator1);
            administrators.add(administrator2);
            administrators.add(administrator3);
           
            DBPersistObjects((List<Object>) (List<?>) administrators);

            
            DBTransaction();
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }

    }
}
