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
 */
public class PERSON_TYPE_ENUM {
    public int personType;
    
    public PERSON_TYPE_ENUM(String personType){
        switch(personType){
            case "LECTURER_ENTITY":
                this.personType = 1;
                break;
            case "STUDENT_ENTITY":
                this.personType = 2;
                break;
            case "ADMINISTRATOR_ENTITY":
                this.personType = 3;
                break;
        }
    }
    
    public int stringToEnum(String personType){
        switch(personType){
            case "LECTURER_ENTITY":
                return 1;
            case "STUDENT_ENTITY":
                return 2;
            case "ADMINISTRATOR_ENTITY":
                return 3;
            default:
                return 0;
        }
    }
    
    public String enumToString(int personType){
        switch(personType){
            case 1:
                return "LECTURER_ENTITY";
            case 2:
                return "STUDENT_ENTITY";
            case 3:
                return "ADMINISTRATOR_ENTITY";
            default:
                return "";
        }
    }
}
