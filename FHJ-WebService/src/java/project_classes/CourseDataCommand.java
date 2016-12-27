/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
package project_classes;

/**
 *
 * @author Notebook
 * Befehle
 */
public interface CourseDataCommand {
    public void push(int grade, STUDENT studentToGrade, COURSE course);
    public void unsetGrade(int gradePK, STUDENT studentToGrade, COURSE course);
    public void changeGrade(int gradePK, STUDENT studentToGrade, COURSE course);
}

