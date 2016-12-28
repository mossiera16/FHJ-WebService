/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
package project_classes;

import java.sql.ResultSet;
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
                result += "<tr class='clickable-row' data-href='results.jsp?courseNumber=" + courses.get(i).getCOURSE_PK().toString() + "' data-toggle=\"tooltip\" title='Ergebnisse von "+ courses.get(i).getCOURSE_NAME() + "'>";
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

    public String getGradeDetails(ResultSet rs, String personType) {
        if (rs != null) {
            String result = "";
            if (personType.equals("LECTURER_ENTITY")) {
                result = getGradeDetailsForLecturer(rs);
            } else {
                result = getGradeDetailsForStudent(rs);
            }

            return result;
        }
        return null;
    }

    private String getGradeDetailsForLecturer(ResultSet rs) {
        try {
            String result = "";
            String grade0 = "<option value=\"0\">noch keine Note</option>";
            String grade1 = "<option value=\"1\">1</option>";
            String grade2 = "<option value=\"2\">2</option>";
            String grade3 = "<option value=\"3\">3</option>";
            String grade4 = "<option value=\"4\">4</option>";
            String grade5 = "<option value=\"5\">5</option>";
            int count = 1;
            while (rs.next()) {
                result += "<tr>";
                result += "<td> " + count + "</td>";
                result += "<td> " + rs.getString("COURSE_NAME") + "</td>";
                result += "<td><select size=\"1\" id=\"row-"+count+"-grade\" name=\"row-"+count+"-grade\">";
                if (rs.getInt("GRADE")==0) {
                    result += "<option value=\"0\" selected=\"selected\">noch keine Note</option>" + grade1 + grade2 + grade3 + grade4 + grade5;
                } else if(rs.getInt("GRADE")==1) {
                    result += grade0 + "<option value=\"1\" selected=\"selected\">1</option>" + grade2 + grade3 + grade4 + grade5;
                }else if(rs.getInt("GRADE")==2) {
                    result += grade0 + grade1 + "<option value=\"2\" selected=\"selected\">2</option>"  + grade3 + grade4 + grade5;
                }else if(rs.getInt("GRADE")==3) {
                    result += grade0 + grade1 + grade2 + "<option value=\"3\" selected=\"selected\">3</option>" + grade4 + grade5;
                }else if(rs.getInt("GRADE")==4) {
                    result += grade0 + grade1 + grade2 + grade3 +  "<option value=\"4\" selected=\"selected\">4</option>" + grade5;
                }else if(rs.getInt("GRADE")==5) {
                    result += grade0 + grade1 + grade2 + grade3 +  grade4 + "<option value=\"5\" selected=\"selected\">5</option>";
                }
                result += "<td> " + rs.getInt("SEMESTER") + "</td>";
                result += "<td> " + rs.getString("FIRSTNAME") + "</td>";
                result += "<td> " + rs.getString("LASTNAME") + "</td>";
                result += "<td> " + rs.getInt("STUDENT_NR") + "</td>";
                result += "</tr>";
                count++;
            }

            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    private String getGradeDetailsForStudent(ResultSet rs) {
        try {
            String result = "";
            int count = 1;
            while (rs.next()) {
                result += "<tr>";
                result += "<td> " + count + "</td>";
                result += "<td> " + rs.getString("COURSE_NAME") + "</td>";
                if (rs.getInt("GRADE") == 0) {
                    result += "<td>noch keine Note</td>";
                } else {
                    result += "<td> " + rs.getInt("GRADE") + "</td>";
                }
                result += "<td> " + rs.getInt("SEMESTER") + "</td>";
                result += "</tr>";
                count++;
            }

            return result;
        } catch (Exception ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    
}
