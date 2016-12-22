/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
package project_classes;

import javax.persistence.Basic;
/**
 *
 * @author standard
 */
public class ADDRESS {
    
    @Basic(optional = false)
    private String STREETNAME;

    @Basic(optional = false)
    private int HOUSENUMBER;

    @Basic(optional = false)
    private int POSTALCODE;

    @Basic(optional = false)
    private String CITY;

    @Basic
    private String STATE;

    @Basic(optional = false)
    private String COUNTRY;
    
    
    
    public String getSTREETNAME() {
        return this.STREETNAME;
    }

    public void setSTREETNAME(String STREETNAME) {
        this.STREETNAME = STREETNAME;
    }

    public int getHOUSENUMBER() {
        return this.HOUSENUMBER;
    }

    public void setHOUSENUMBER(int HOUSENUMBER) {
        this.HOUSENUMBER = HOUSENUMBER;
    }

    public int getPOSTALCODE() {
        return this.POSTALCODE;
    }

    public void setPOSTALCODE(int POSTALCODE) {
        this.POSTALCODE = POSTALCODE;
    }

    public String getCITY() {
        return this.CITY;
    }

    public void setCITY(String CITY) {
        this.CITY = CITY;
    }

    public String getSTATE() {
        return this.STATE;
    }

    public void setSTATE(String STATE) {
        this.STATE = STATE;
    }

    public String getCOUNTRY() {
        return this.COUNTRY;
    }

    public void setCOUNTRY(String COUNTRY) {
        this.COUNTRY = COUNTRY;
    }
}
