/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
package project_entities;

/**
 * Schnittstelle PERSON um das Entwurfsmuster PROTOTYPE zu demonstrieren
 */
public interface PERSON extends Cloneable {
    public PERSON_ENTITY getClone();
}
