/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 * Seite: Data2HTMLConverterBean.java
 * Beschreibung: Java-Klasse zur Umwandlung von Daten, welche zum Teil aus der Datenbank abgefragt wurden zu HTML
 */
package project_classes;

import java.sql.ResultSet;
import java.sql.SQLException;

public class Data2HTMLConverterBean {

    /*
        Funktion für die Generierung der Links für die Navigationsleiste (oberhalb des Fensters)
    */
    public String getNavbarLinks(PERSON person) {
        String result = "";
        if (person.getPERSON_TYPE().personType == 1 || person.getPERSON_TYPE().personType == 2) {
            result += "<li><a href=\"dashboard.jsp\">Dashboard</a></li>\n"
                    + "<li><a href=\"courses.jsp\">Kurse</a></li>\n"
                    + "<li><a href=\"results.jsp\">Ergebnisse</a></li>\n"
                    + "<li><a href=\"logout.jsp\">Logout</a></li>";
        } else {
            result += "<li><a href=\"dashboard.jsp\">Dashboard</a></li>\n"
                    + "<li><a href=\"admincourses.jsp\">Kursverwaltung</a></li>\n"
                    + "<li><a href=\"adminstudents.jsp\">Studentenverwaltung</a></li>\n"
                    + "<li><a href=\"adminlecturers.jsp\">Vortragendenverwaltung</a></li>\n"
                    + "<li><a href=\"logout.jsp\">Logout</a></li>";
        }

        return result;
    }

    /*
        Funktion für die Generierung der Buttons der Sidebar
    */
    public String getDashboardButtonGroup(PERSON person) {
        String result = "<div class=\"btn-group btn-group-justified\" role=\"group\">";
        if (person.getPERSON_TYPE().personType == 2 || person.getPERSON_TYPE().personType == 1) {
            result += "<div class=\"btn-group\" role=\"group\">\n"
                    + " <a href=\"courses.jsp\" title=\"Kursübersicht\"> <button type=\"button\" class=\"btn btn-default\">Kursübersicht</button></a>\n"
                    + "</div>\n"
                    + "<div class=\"btn-group\" role=\"group\">\n"
                    + " <a href=\"results.jsp\" title=\"Ergebnisübersicht\"> <button type=\"button\" class=\"btn btn-default\">Ergebnisübersicht</button></a>\n"
                    + "</div>";
        } else {
            result += "<div class=\"btn-group\" role=\"group\">\n"
                    + " <a href=\"admincourses.jsp\" title=\"Kursverwaltung\"> <button type=\"button\" class=\"btn btn-default\">Kursverwaltung</button></a>\n"
                    + "</div>\n"
                    + "<div class=\"btn-group\" role=\"group\">\n"
                    + " <a href=\"adminstudents.jsp\" title=\"Studentenverwaltung\"> <button type=\"button\" class=\"btn btn-default\">Studentenverwaltung</button></a>\n"
                    + "</div>"
                    + "<div class=\"btn-group\" role=\"group\">\n"
                    + " <a href=\"adminlecturers.jsp\" title=\"Vortragendenverwaltung\"> <button type=\"button\" class=\"btn btn-default\">Vortragendenverwaltung</button></a>\n"
                    + "</div>";
        }
        result += "</div>";
        return result;
    }

    /*
        Funktion für die Generierung der Nachricht auf der index.jsp-Seite
    */
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
    
    /*
        Funktion für die Generierung der Willkommensnachricht auf der dashboard.jsp-Seite
    */
    public String getDashboardWelcomeMessage(PERSON person) {
        switch (person.getPERSON_TYPE().personType) {
            case 2:
                if (person.getADMINSEX().equals("m")) {
                    return "<small> – Willkommen Student " + person.getFIRSTNAME() + " " + person.getLASTNAME() + "!</small> ";
                } else {
                    return "<small>– Willkommen Studentin " + person.getFIRSTNAME() + " " + person.getLASTNAME() + "!</small> ";
                }
            case 1:
                if (person.getADMINSEX().equals("m")) {
                    return "<small>– Willkommen Vortragender " + person.getFIRSTNAME() + " " + person.getLASTNAME() + "!</small> ";
                } else {
                    return "<small>– Willkommen Vortragende " + person.getFIRSTNAME() + " " + person.getLASTNAME() + "!</small> ";
                }
            case 3:
                if (person.getADMINSEX().equals("m")) {
                    return "<small>– Willkommen Administrator " + person.getFIRSTNAME() + " " + person.getLASTNAME() + "!</small> ";
                } else {
                    return "<small>– Willkommen Administratorin " + person.getFIRSTNAME() + " " + person.getLASTNAME() + "!</small> ";
                }
            default:
                return "";
        }
    }

    /*
        Funktion für die Generierung der Sidebar-Navigationsleiste
    */
    public String getSidebarNavigationElements(String siteName, PERSON person) {
        String SidebarNavigationElementsString = "";
        int personType = person.getPERSON_TYPE().personType;
        if (personType == 1 || personType == 2) {
            SidebarNavigationElementsString = getSidebarNavigationElementsForLecturersStudents(siteName);
        } else {
            SidebarNavigationElementsString = getSidebarNavigationElementsForAdministrators(siteName);
        }
        return SidebarNavigationElementsString;
    }
    
    /*
        Funktion für die Generierung der Sidebar-Navigationsleiste für Studenten und Vortragende
    */
    public String getSidebarNavigationElementsForLecturersStudents(String siteName) {
        String dashboard = "<li><a href=\"dashboard.jsp\">Übersicht</a></li>";
        String courses = "<li><a href=\"courses.jsp\">Kurse</a></li>";
        String results = "<li><a href=\"results.jsp\">Ergebnisse</a></li>";
        String activeString1 = "class=\"active\"";
        String activeString2 = "<span class=\"sr-only\">(current)</span>";
        String SidebarNavigationElementsString;
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

    /*
        Funktion für die Generierung der Sidebar-Navigationsleiste für Administratoren
    */
    public String getSidebarNavigationElementsForAdministrators(String siteName) {
        String dashboard = "<li><a href=\"dashboard.jsp\">Übersicht</a></li>";
        String courses = "<li><a href=\"admincourses.jsp\">Kursverwaltung</a></li>";
        String students = "<li><a href=\"adminstudents.jsp\">Studentenverwaltung</a></li>";
        String lecturers = "<li><a href=\"adminlecturers.jsp\">Vortragendenverwaltung</a></li>";

        String activeString1 = "class=\"active\"";
        String activeString2 = "<span class=\"sr-only\">(current)</span>";
        String SidebarNavigationElementsString;
        switch (siteName) {
            case "dashboard":
                SidebarNavigationElementsString = "<li " + activeString1 + "><a href=\"dashboard.jsp\">Übersicht" + activeString2 + "</a></li>" + courses + students + lecturers;
                return SidebarNavigationElementsString;
            case "courses":
                SidebarNavigationElementsString = dashboard + "<li " + activeString1 + "><a href=\"admincourses.jsp\">Kursverwaltung" + activeString2 + "</a></li>" + students + lecturers;
                return SidebarNavigationElementsString;
            case "students":
                SidebarNavigationElementsString = dashboard + courses + "<li " + activeString1 + "><a href=\"adminstudents.jsp\">Studentenverwaltung" + activeString2 + "</a></li>" + lecturers;
                return SidebarNavigationElementsString;
            case "lecturers":
                SidebarNavigationElementsString = dashboard + courses + students + "<li " + activeString1 + "><a href=\"adminlecturers.jsp\">Vortragendenverwaltung" + activeString2 + "</a></li>";
                return SidebarNavigationElementsString;
        }
        return "";
    }

    /*
        Funktion für die Generierung der Ergebnisüberschrift
    */
    public String getResultCaption(Integer courseNumber, ResultSet courseDetails) {
        try {
            String result = "<small>";
            
            if (courseNumber == null) {
                result += "aller Kurse</small>";
            } else if(courseDetails.next()) {
                result += "von " + courseDetails.getString("COURSE_NAME") + "</small>";
            }

            return result;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    /*
        Funktion für die Generierung der Kursdetails (Tabelle)
    */
    public String getCourseDetails(ResultSet courses) {
        try {
            if (courses != null) {
                String result = "";
                int i = 1;
                while (courses.next()) {
                    result += "<tr class='clickable-row' data-href='results.jsp?courseNumber=" + courses.getInt("COURSE_PK") + "' data-toggle=\"tooltip\" title='Ergebnisse von " + courses.getString("COURSE_NAME") + "'>";
                    result += "<td> " + i + "</td>";
                    result += "<td> " + courses.getString("COURSE_NAME") + "</td>";
                    result += "<td> " + courses.getInt("SEMESTER") + "</td>";
                    result += "<td> " + courses.getString("STUDY") + "</td>";
                    result += "</tr>";
                    i++;
                }
                return result;
            }
            return null;
        } catch (SQLException ex) {
            System.out.println("Error: " + ex.getMessage());
            return null;
        }
    }
    
    /*
        Funktion für die Generierung der Ergebnisdetails (Tabelle)
    */
    public String getGradeDetails(ResultSet rs, int personType) {
        if (rs != null) {
            String result;
            if (personType == 1) {
                result = getGradeDetailsForLecturer(rs);
            } else {
                result = getGradeDetailsForStudent(rs);
            }

            return result;
        }
        return null;
    }
    
    /*
        Funktion für die Generierung der Ergebnisdetails für Vortragende (Tabelle)
    */
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
                result += "<td><select onchange='setUncommitedStyle()' size=\"1\" id=\"" + rs.getInt("GRADE_PK") + "\" name=\"" + rs.getInt("GRADE_PK") + "\">";
                switch (rs.getInt("GRADE")) {
                    case 0:
                        result += "<option value=\"0\" selected=\"selected\">noch keine Note</option>" + grade1 + grade2 + grade3 + grade4 + grade5;
                        break;
                    case 1:
                        result += grade0 + "<option value=\"1\" selected=\"selected\">1</option>" + grade2 + grade3 + grade4 + grade5;
                        break;
                    case 2:
                        result += grade0 + grade1 + "<option value=\"2\" selected=\"selected\">2</option>" + grade3 + grade4 + grade5;
                        break;
                    case 3:
                        result += grade0 + grade1 + grade2 + "<option value=\"3\" selected=\"selected\">3</option>" + grade4 + grade5;
                        break;
                    case 4:
                        result += grade0 + grade1 + grade2 + grade3 + "<option value=\"4\" selected=\"selected\">4</option>" + grade5;
                        break;
                    case 5:
                        result += grade0 + grade1 + grade2 + grade3 + grade4 + "<option value=\"5\" selected=\"selected\">5</option>";
                        break;
                    default:
                        break;
                }
                result += "</select>";
                result += "<td> " + rs.getString("FIRSTNAME") + "</td>";
                result += "<td> " + rs.getString("LASTNAME") + "</td>";
                result += "<td> " + rs.getInt("STUDENT_NR") + "</td>";
                result += "</tr>";
                count++;
            }

            return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /*
        Funktion für die Generierung der Ergebnisdetails für Studenten (Tabelle)
    */
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
                result += "</tr>";
                count++;
            }

            return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /*
        Funktion für die Generierung der Ergebnisdetails im Dialogfenster für Vortragende (Tabelle)
    */
    public String getGradeDetailsForModal(ResultSet rs, ResultSet rsStudentsToEnroll, ResultSet courseDetails) {
        try {
            String result = "";
            int count = 1;
            while (rs.next()) {
                result += "<tr id='" + rs.getInt("PERSON_PK") + rs.getInt("COURSE_PK") + "'>";
                result += "<td><span style=\"cursor: pointer;\" onclick=\"remove('" + rs.getInt("PERSON_PK") + "', '" + rs.getInt("COURSE_PK") + "');\" class='glyphicon glyphicon-remove-circle' aria-hidden='true' data-toggle='tooltip' title='Student/-in entfernen'></span></td>";
                result += "<td> " + count + "</td>";
                result += "<td> " + rs.getString("FIRSTNAME") + " " + rs.getString("LASTNAME") + " - " + rs.getInt("STUDENT_NR") + "</td>";
                result += "<td> " + rs.getString("COURSE_NAME") + "</td>";
                result += "</tr>";
                count++;
            }

            result += "<td><span id='add' style=\"cursor: pointer;\" class='glyphicon glyphicon-plus-sign' aria-hidden='true'></span></td>";
            result += "<td>neu</td>";
            result += "<td class='duplicate'><select size=\"1\" id=\"id\" name=\"id\">";
            result += "<option value=\"0\" selected=\"selected\">\n"
                    + "Student auswählen\n"
                    + "</option>";
            while (rsStudentsToEnroll.next()) {
                result += "<option value=" + rsStudentsToEnroll.getString("PERSON_PK") + ">";
                result += rsStudentsToEnroll.getString("FIRSTNAME") + " " + rsStudentsToEnroll.getString("LASTNAME") + " - " + rsStudentsToEnroll.getInt("STUDENT_NR");
                result += "</option>";
            }
            result += "</select></td>";

            result += "<td class='duplicate'><select size=\"1\" id=\"id\" name=\"id\">";
            result += "<option value=\"0\" selected=\"selected\">\n"
                    + "Kurs auswählen\n"
                    + "</option>";
            while (courseDetails.next()) {

                result += "<option value=" + courseDetails.getInt("COURSE_PK") + ">";
                result += courseDetails.getString("COURSE_NAME");
                result += "</option>";
            }
            result += "</select></td>";

            return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /*
        Funktion für die Generierung der Studentendetails für Administratoren (Tabelle)
    */
    public String getStudentDetailsForAdmin(ResultSet rs) {
        try {
            String result = "";
            int count = 1;

            while (rs.next()) {
                result += "<tr id='" + rs.getString("PERSON_PK") + "'>";
                result += "<td><span id='remove' style=\"cursor: pointer;\" onclick=\"remove('" + rs.getInt("PERSON_PK") + "');\" class='glyphicon glyphicon-remove-circle' aria-hidden='true' data-toggle='tooltip' title='Student/-in entfernen'></span></td>";
                result += "<td> " + count + "</td>";
                result += "<td><select onchange='setUncommitedStyle()' size=\"1\" id=\"" + rs.getInt("PERSON_PK") + "\" name=\"" + rs.getInt("PERSON_PK") + "\">";
                result += getAdminSexSelectBox(rs.getString("ADMINSEX"));
                result += "</select></td>";
                result += "<td><input onchange='setUncommitedStyle()' type='text' id='" + rs.getInt("PERSON_PK") + "' name='" + rs.getInt("PERSON_PK") + "' value='" + rs.getDate("BIRTHDATE") + "'/></td>";
                result += "<td><input  onchange='setUncommitedStyle()' type='text' id='" + rs.getInt("PERSON_PK") + "' name='" + rs.getInt("PERSON_PK") + "' value='" + rs.getString("FIRSTNAME") + "'/></td>";
                result += "<td><input onchange='setUncommitedStyle()'  type='text' id='" + rs.getInt("PERSON_PK") + "' name='" + rs.getInt("PERSON_PK") + "' value='" + rs.getString("LASTNAME") + "'/></td>";
                result += "<td><input onchange='setUncommitedStyle()'  type='text' id='" + rs.getInt("PERSON_PK") + "' name='" + rs.getInt("PERSON_PK") + "' value='" + rs.getString("USERNAME") + "'/></td>";
                result += "<td><select onchange='setUncommitedStyle()' size=\"1\" id=\"" + rs.getInt("PERSON_PK") + "\" name=\"" + rs.getInt("PERSON_PK") + "\">";
                result += getSemesterSelectBox(rs.getInt("SEMESTER"));
                result += "</select></td>";
                result += "<td><input onchange='setUncommitedStyle()' type='text' id='" + rs.getInt("PERSON_PK") + "' name='" + rs.getInt("PERSON_PK") + "' value='" + rs.getInt("SSN") + "'/></td>";
                result += "<td><input onchange='setUncommitedStyle()'  type='text' id='" + rs.getInt("PERSON_PK") + "' name='" + rs.getInt("PERSON_PK") + "' value='" + rs.getInt("STUDENT_NR") + "'/></td>";
                result += "<td><input onchange='setUncommitedStyle()'  type='text' id='" + rs.getInt("PERSON_PK") + "' name='" + rs.getInt("PERSON_PK") + "' value='" + rs.getString("TITLE") + "'/></td>";
                result += "<td><input onchange='setUncommitedStyle()'  type='text' id='" + rs.getInt("PERSON_PK") + "' name='" + rs.getInt("PERSON_PK") + "' value='" + rs.getString("TYPE_OF_STUDY") + "'/></td>";

                result += "</tr>";
                count++;
            }
            result += "<tr>";
            result += "<td><span id='add' style=\"cursor: pointer;\" class='glyphicon glyphicon-plus-sign' aria-hidden='true'></span></td>";
            result += "<td>neu</td>";
            result += "<td><select onchange='setUncommitedStyle()' size=\"1\" id=\"0\" name=\"0\">";
            result += getAdminSexSelectBox("");
            result += "</select></td>";
            result += "<td><input onchange='setUncommitedStyle()' type='text' id='0' name='0' value=''/></td>";
            result += "<td><input  onchange='setUncommitedStyle()' type='text' id='0' name='0' value=''/></td>";
            result += "<td><input onchange='setUncommitedStyle()'  type='text' id='0' name='0' value=''/></td>";
            result += "<td><input onchange='setUncommitedStyle()'  type='text' id='0' name='0' value=''/></td>";
            result += "<td><select onchange='setUncommitedStyle()' size=\"1\" id=\"0\" name=\"0\">";
            result += getSemesterSelectBox(0);
            result += "</select></td>";
            result += "<td><input onchange='setUncommitedStyle()' type='text' id='0' name='0' value=''/></td>";
            result += "<td><input onchange='setUncommitedStyle()'  type='text' id='0' name='0' value=''/></td>";
            result += "<td><input onchange='setUncommitedStyle()'  type='text' id='0' name='0' value=''/></td>";
            result += "<td><input onchange='setUncommitedStyle()'  type='text' id='0' name='0' value=''/></td>";
            result += "</tr>";

            return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }
    
    /*
        Funktion für die Generierung der Kombobox für die Geschlechtsauswahl
    */
    private String getAdminSexSelectBox(String adminSex) {
        String result = "";
        String adminSexM = "<option value=\"m\">männlich</option>";
        String adminSexW = "<option value=\"w\">weiblich</option>";

        switch (adminSex) {
            case "m":
                result += "<option value=\"m\" selected=\"selected\">männlich</option>" + adminSexW;
                break;
            case "w":
                result += adminSexM + "<option value=\"w\" selected=\"selected\">weiblich</option>";
                break;
            default:
                result += "<option value=\"m\" selected=\"selected\">männlich</option>" + adminSexW;
                break;
        }

        return result;
    }
    
    /*
        Funktion für die Generierung der Kombobox für die Semesterauswahl
    */
    private String getSemesterSelectBox(int semester) {
        String result = "";

        String semester1 = "<option value=\"1\">Semester 1</option>";
        String semester2 = "<option value=\"2\">Semester 2</option>";
        String semester3 = "<option value=\"3\">Semester 3</option>";
        String semester4 = "<option value=\"4\">Semester 4</option>";
        String semester5 = "<option value=\"5\">Semester 5</option>";
        String semester6 = "<option value=\"6\">Semester 6</option>";

        switch (semester) {
            case 1:
                result += "<option value=\"1\" selected=\"selected\">Semester 1</option>" + semester2 + semester3 + semester4 + semester5 + semester6;
                break;
            case 2:
                result += semester1 + "<option value=\"2\" selected=\"selected\">Semester 2</option>" + semester3 + semester4 + semester5 + semester6;
                break;
            case 3:
                result += semester1 + semester2 + "<option value=\"3\" selected=\"selected\">Semester 3</option>" + semester4 + semester5 + semester6;
                break;
            case 4:
                result += semester1 + semester2 + semester3 + "<option value=\"4\" selected=\"selected\">Semester 4</option>" + semester5 + semester6;
                break;
            case 5:
                result += semester1 + semester2 + semester3 + semester4 + "<option value=\"5\" selected=\"selected\">Semester 5</option>" + semester6;
                break;
            case 6:
                result += semester1 + semester2 + semester3 + semester4 + semester5 + "<option value=\"6\" selected=\"selected\">Semester 6</option>";
                break;
            default:
                result += "<option value=\"1\" selected=\"selected\">Semester 1</option>" + semester2 + semester3 + semester4 + semester5 + semester6;
                break;
        }

        return result;
    }
    
    /*
        Funktion für die Generierung der Kursdetails für Administratoren (Tabelle)
    */
    public String getCourseDetailsForAdmin(ResultSet rs, ResultSet rsLecturers, PERSON person) {
        try {
            String result = "";
            int count = 1;
            while (rs.next()) {
                result += "<tr id='" + rs.getString("COURSE_PK") + "'>";
                result += "<td><span id='remove' style=\"cursor: pointer;\" onclick=\"remove('" + rs.getInt("COURSE_PK") + "');\" class='glyphicon glyphicon-remove-circle' aria-hidden='true' data-toggle='tooltip' title='Kurs entfernen'></span></td>";
                result += "<td> " + count + "</td>";
                result += "<td><input onchange='setUncommitedStyle()' type='text' id='" + rs.getInt("COURSE_PK") + "' name='" + rs.getInt("COURSE_PK") + "' value='" + rs.getString("COURSE_NAME") + "'/></td>";
                result += "<td><select onchange='setUncommitedStyle()' size=\"1\" id=\"" + rs.getInt("COURSE_PK") + "\" name=\"" + rs.getInt("COURSE_PK") + "\">";
                result += getLecturerSelectBox(rs.getInt("PERSON_PK"), rsLecturers);
                result += "</select></td>";
                result += "<td><input  onchange='setUncommitedStyle()' type='text' id='" + rs.getInt("COURSE_PK") + "' name='" + rs.getInt("COURSE_PK") + "' value='" + rs.getInt("SEMESTER") + "'/></td>";
                result += "<td><input onchange='setUncommitedStyle()'  type='text' id='" + rs.getInt("COURSE_PK") + "' name='" + rs.getInt("COURSE_PK") + "' value='" + rs.getString("STUDY") + "'/></td>";

                result += "</tr>";
                count++;
                rsLecturers = person.getLecturers();
            }
            result += "<tr>";
            result += "<td><span id='add' style=\"cursor: pointer;\" class='glyphicon glyphicon-plus-sign' aria-hidden='true'></span></td>";
            result += "<td>neu</td>";
            result += "<td><input onchange='setUncommitedStyle()' type='text' id='0' name='0' value=''/></td>";
            result += "<td><select onchange='setUncommitedStyle()' size=\"1\" id=\"0\" name=\"0\">";
            result += getLecturerSelectBox(0, rsLecturers);
            result += "</select></td>";
            result += "<td><input onchange='setUncommitedStyle()' type='text' id='0' name='0' value=''/></td>";
            result += "<td><input  onchange='setUncommitedStyle()' type='text' id='0' name='0' value=''/></td>";

            result += "</tr>";

            return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

    /*
        Funktion für die Generierung der Kombobox für die Vortragendenauswahl
    */
    private String getLecturerSelectBox(Integer LECTURER_PK, ResultSet rsLecturers) {
        try {
            String result = "";
            while (rsLecturers.next()) {
                if (rsLecturers.getInt("PERSON_PK") == LECTURER_PK) {
                    result += "<option value=\"" + LECTURER_PK + "\" selected=\"selected\">" + rsLecturers.getString("FIRSTNAME") + " " + rsLecturers.getString("LASTNAME") + "</option>";
                } else {

                    result += "<option value=\"" + rsLecturers.getInt("PERSON_PK") + "\">" + rsLecturers.getString("FIRSTNAME") + " " + rsLecturers.getString("LASTNAME") + "</option>";
                }
            }

            return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return "";
        }
    }

    /*
        Funktion für die Generierung der Vortragendendetails für Administratoren (Tabelle)
    */
    public String getLecturerDetailsForAdmin(ResultSet rs) {
        try {
            String result = "";
            int count = 1;

            while (rs.next()) {
                result += "<tr id='" + rs.getString("PERSON_PK") + "'>";
                result += "<td><span id='remove' style=\"cursor: pointer;\" onclick=\"remove('" + rs.getInt("PERSON_PK") + "');\" class='glyphicon glyphicon-remove-circle' aria-hidden='true' data-toggle='tooltip' title='Student/-in entfernen'></span></td>";
                result += "<td> " + count + "</td>";
                result += "<td><select onchange='setUncommitedStyle()' size=\"1\" id=\"" + rs.getInt("PERSON_PK") + "\" name=\"" + rs.getInt("PERSON_PK") + "\">";
                result += getAdminSexSelectBox(rs.getString("ADMINSEX"));
                result += "</select></td>";
                result += "<td><input onchange='setUncommitedStyle()' type='text' id='" + rs.getInt("PERSON_PK") + "' name='" + rs.getInt("PERSON_PK") + "' value='" + rs.getDate("BIRTHDATE") + "'/></td>";
                result += "<td><input  onchange='setUncommitedStyle()' type='text' id='" + rs.getInt("PERSON_PK") + "' name='" + rs.getInt("PERSON_PK") + "' value='" + rs.getInt("EMPLOEE_NR") + "'/></td>";
                result += "<td><input  onchange='setUncommitedStyle()' type='text' id='" + rs.getInt("PERSON_PK") + "' name='" + rs.getInt("PERSON_PK") + "' value='" + rs.getString("FIRSTNAME") + "'/></td>";
                result += "<td><input onchange='setUncommitedStyle()'  type='text' id='" + rs.getInt("PERSON_PK") + "' name='" + rs.getInt("PERSON_PK") + "' value='" + rs.getString("LASTNAME") + "'/></td>";
                result += "<td><input onchange='setUncommitedStyle()'  type='text' id='" + rs.getInt("PERSON_PK") + "' name='" + rs.getInt("PERSON_PK") + "' value='" + rs.getString("USERNAME") + "'/></td>";
                result += "<td><input onchange='setUncommitedStyle()' type='text' id='" + rs.getInt("PERSON_PK") + "' name='" + rs.getInt("PERSON_PK") + "' value='" + rs.getInt("SSN") + "'/></td>";
                result += "<td><input onchange='setUncommitedStyle()'  type='text' id='" + rs.getInt("PERSON_PK") + "' name='" + rs.getInt("PERSON_PK") + "' value='" + rs.getString("TITLE") + "'/></td>";

                result += "</tr>";
                count++;
            }
            result += "<tr>";
            result += "<td><span id='add' style=\"cursor: pointer;\" class='glyphicon glyphicon-plus-sign' aria-hidden='true'></span></td>";
            result += "<td>neu</td>";
            result += "<td><select onchange='setUncommitedStyle()' size=\"1\" id=\"0\" name=\"0\">";
            result += getAdminSexSelectBox("");
            result += "</select></td>";
            result += "<td><input onchange='setUncommitedStyle()' type='text' id='0' name='0' value=''/></td>";
            result += "<td><input onchange='setUncommitedStyle()' type='text' id='0' name='0' value=''/></td>";
            result += "<td><input  onchange='setUncommitedStyle()' type='text' id='0' name='0' value=''/></td>";
            result += "<td><input onchange='setUncommitedStyle()'  type='text' id='0' name='0' value=''/></td>";
            result += "<td><input onchange='setUncommitedStyle()'  type='text' id='0' name='0' value=''/></td>";
            result += "<td><input onchange='setUncommitedStyle()' type='text' id='0' name='0' value=''/></td>";
            result += "<td><input onchange='setUncommitedStyle()' type='text' id='0' name='0' value=''/></td>";
            result += "</tr>";

            return result;
        } catch (SQLException ex) {
            System.out.println(ex.getMessage());
            return null;
        }
    }

}
