/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
package project_classes;

import java.util.List;

/**
 *
 * @author Notebook
 */
public class MessageHandler {

    public String getIndexSiteMessage(int messageId) {
        switch (messageId) {
            case 0:
                return "";
            case 1:
                return "<p class='bg-warning'>Ungültiger Benutzername und/oder Passwort!</p>";
            case 2:
                return "<p class='bg-info'>Bitte vorher anmelden.</p>";
            case 3:
                return "<p class='bg-success'>Erfolgreich abgemeldet</p>";
            default:
                return "";
        }
    }

    public String getDashboardWelcomeMessage(PERSON person) {
        switch (person.getPERSON_TYPE()) {
            case "STUDENT_ENTITY":
                return "– Willkommen Student " + person.getFIRSTNAME() + " " + person.getLASTNAME() + "! ";
            case "LECTURER_ENTITY":
                return "– Willkommen Vortragender " + person.getFIRSTNAME() + " " + person.getLASTNAME() + "! ";
            case "ADMINISTRATOR_ENTITY":
                return "– Willkommen Administrator " + person.getFIRSTNAME() + " " + person.getLASTNAME() + "! ";
            default:
                return "";
        }
    }

    public String getSidebarNavigationElements(String siteName) {
        String activeString1 = "class=\"active\"";
        String activeString2 = "<span class=\"sr-only\">(current)</span>";

        String dashboard = "<li><a href=\"dashboard.jsp\">Übersicht</a></li>";
        String courses = "<li><a href=\"courses.jsp\">Kurse</a></li>";
        String results = "<li><a href=\"results.jsp\">Ergebnisse</a></li>";

        String SidebarNavigationElementsString = "";
        switch (siteName) {
            case "dashboard":
                SidebarNavigationElementsString = "<li " + activeString1 + "><a href=\"dashboard.jsp\">Übersicht" + activeString2 + "</a></li>" + courses + results;
                return SidebarNavigationElementsString;
            case "courses":
                SidebarNavigationElementsString = dashboard + "<li " + activeString1 + "><a href=\"courses.jsp\">Kurse" + activeString2 + "</a></li>" + results;
                return SidebarNavigationElementsString;
            case "results":
                SidebarNavigationElementsString = dashboard + courses + "<li " + activeString1 + "><a href=\"results.jsp\">Ergebnisse" + activeString2 + "</a></li>";
                return SidebarNavigationElementsString;
        }
        return "";
    }

    public String getCourseDetails(List<COURSE> courses) {
        if (courses != null) {
            String result = "";

            for (int i = 0; i < courses.size(); i++) {
                result += "<tr class='clickable-row' data-href='results.jsp?courseNumber="+courses.get(i).getCOURSE_PK().toString()+"'>";
                result += "<td> " + courses.get(i).getCOURSE_PK() + "</td>";
                result += "<td> " + courses.get(i).getCOURSE_NAME() + "</td>";
                result += "<td> " + courses.get(i).getSEMESTER()+ "</td>";
                result += "<td> " + courses.get(i).getSTUDY()+ "</td>";
                result += "</tr>";
            }
            return result;
        }
        return null;
    }
}
