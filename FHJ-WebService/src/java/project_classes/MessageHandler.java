/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
package project_classes;

import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import project_entities.GRADE_ENTITY;

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

    public String getResultCaption(Integer courseNumber, List<COURSE> courseDetails) {
        String result = "<span style='font-size: 20px; vertical-align: middle;'>";

        if (courseNumber == null) {
            result += "aller Kurse</span>";
        } else {
            result += "von " + courseDetails.get(0).getCOURSE_NAME() + "</span>";
        }

        return result;
    }

    public String getCourseDetails(List<COURSE> courses) {
        if (courses != null) {
            String result = "";

            for (int i = 0; i < courses.size(); i++) {
                result += "<tr class='clickable-row' data-href='results.jsp?courseNumber=" + courses.get(i).getCOURSE_PK().toString() + "'>";
                result += "<td> " + (i + 1) + "</td>";
                result += "<td> " + courses.get(i).getCOURSE_NAME() + "</td>";
                result += "<td> " + courses.get(i).getSEMESTER() + "</td>";
                result += "<td> " + courses.get(i).getSTUDY() + "</td>";
                result += "</tr>";
            }
            return result;
        }
        return null;
    }

    public String getGradeDetails(List<GRADE> grades, List<COURSE> courses, String personType) {
        if (grades != null) {
            String result = "";
            if (personType == "LECTURER_ENTITY") {
                result = getGradeDetailsForLecturer(grades, courses);
            } else {
                result = getGradeDetailsForStudent(grades, courses);
            }

            return result;
        }
        return null;
    }

    private String getGradeDetailsForLecturer(List<GRADE> grades, List<COURSE> courses) {
        String result = "";
        for (int j = 0; j < courses.size(); j++) {
            List<GRADE_ENTITY> courseGrades = courses.get(j).getGRADE_ENTITies();
            for (int i = 0; i < courseGrades.size(); i++) {

                result += "<tr class='clickable-row' data-href=''>";

                result += "<td> " + (i + j + 1) + "</td>";
                result += "<td> " + courses.get(j).getCOURSE_NAME() + "</td>";
                if (courseGrades.get(i).getGRADE() == 0) {
                    result += "<td>noch keine Note</td>";
                } else {
                    result += "<td> " + courseGrades.get(i).getGRADE() + "</td>";
                }
                result += "<td> " + courseGrades.get(i).getSEMESTER() + "</td>";
                result += "<td> " + "Andreas" + "</td>";
                result += "<td> " + "Mossier" + "</td>";
                result += "<td> " + "1310288011" + "</td>";
                result += "</tr>";
            }
        }
        return result;
    }

    private String getGradeDetailsForStudent(List<GRADE> grades, List<COURSE> courses) {
        String result = "";

        for (int i = 0; i < grades.size(); i++) {
            result += "<tr>";
            result += "<td> " + (i + 1) + "</td>";
            result += "<td> " + courses.get(i).getCOURSE_NAME() + "</td>";
            if (grades.get(i).getGRADE() == 0) {
                    result += "<td>noch keine Note</td>";
                } else {
                    result += "<td> " + grades.get(i).getGRADE() + "</td>";
                }
            result += "<td> " + grades.get(i).getSEMESTER() + "</td>";
            result += "</tr>";
        }
        return result;
    }
}
