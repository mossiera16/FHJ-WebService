<%-- 
/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 * Seite: admincourses.jsp
 * Beschreibung: Adminseite für die Verwaltung der Kursdaten
 */
--%>
<jsp:useBean id="adminCoursesMessage" class="project_classes.Data2HTMLConverterBean"></jsp:useBean>
<%@page import="java.sql.ResultSet" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="project_classes.PERSON"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="charset=ISO-8859-1">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="SHORTCUT ICON" href="images/favicon.png" type="image/png">
        <title>Kursverwaltung</title>

        <!-- Bootstrap core CSS -->
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Datatables CSS -->
        <link href="css/datatables.css" rel="stylesheet">
        <!-- Bootstrap core JavaScript -->
        <script src="bootstrap/js/jquery.js" type="text/javascript"></script>
        <script src="bootstrap/js/jquery-datatables.js" type="text/javascript"></script>
        <script src="administration.js" type="text/javascript"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

        <!-- Custom styles for this template -->
        <link href="css/dashboard.css" rel="stylesheet">

    </head>
    <!--Erste Überprüfung mithilfe von Java:
    * Setzung des Seitennamens
    * Notwendige ResultSets instanzieren
    * Session abfragen und überprüfen, ob Person angemeldet ist.
        Wenn erfolgreich:
        - Abfrage der notwendigen Daten für die Seite (ResultSets werden befüllt)
        - Abfrage, ob der URL-Parameter delete oder insert-update für die Datenmanipulation gesetzt sind.
            Wenn ja:
            -- URL-String wird abgefragt und an eine Methode übergeben zur Durchführung der Datenmanipulationen 
        Wenn nicht erfolgreich:
        - Weiterleitung zur index.jsp Seite
    --> 
    <%
        session.setAttribute("siteName", "courses");
        ResultSet rsCourses = null;
        ResultSet rsLecturers = null;
        String errorMessage = "";
        String data = "";
        int result = 0;
        PERSON person = (PERSON) session.getAttribute("currentSessionUser");
        if (person == null) {
            session.setAttribute("userState", 2);
            String redirectURL = "index.jsp";
            response.sendRedirect(redirectURL);
            return;
        } else {

            rsCourses = person.getCourses();
            rsLecturers = person.getLecturers();
            if (request.getParameter("delete") != null || request.getParameter("insert-update") != null) {
                data = request.getQueryString();
                data = java.net.URLDecoder.decode(data, "UTF-8").toString();
                result = person.administrateData(data, "courses", 5);
                session.setAttribute("result", result);

                response.sendRedirect("admincourses.jsp?result="+result);
           
            }
            if(request.getParameter("result")!= null){
                String resultString = request.getQueryString().replace("result=", "");
                if(resultString.equals("-1"))
                errorMessage = "Foreign-Key-Constraint Violation. Übergeordneter Datensatz vorhanden";
            }
        }
        %>
    <!--onload: Aufruf der JavaScript-Funktion administration()-->
    <body onload="administration('admincourses.jsp')">
        <!--Inkludierung der Navigationsleiste (oberhalb)-->
        <%@include  file="navbar.jsp" %>

        <div class="container-fluid">
            <div class="row">
                <!--Inkludierung der Seitenleiste (links)-->
                <%@include  file="sidebar.jsp" %>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <div>
                        <h1 class="page-header">Kursverwaltung</h1>
                    </div>
                    <div class="btn-group" style="margin-top: 1em; margin-bottom: 1em;">
                        <a style="text-decoration: none;" role="button" id="commit" class="btn btn-default btn-sm">
                            <span class='glyphicon glyphicon-floppy-disk' aria-hidden="true"></span>Eingaben bestätigen
                        </a>

                       
                    </div>
                     <% if (errorMessage != "") {
                                out.print("<div class='alert alert-danger' role='alert'>");
                                out.print("<span class='glyphicon glyphicon-exclamation-sign' aria-hidden='true'></span>");
                                out.print("<span class='sr-only'>Error:</span>");
                                out.print(errorMessage);
                                out.print("</div>");
                            }
                        %>
                    <!--Datentabelle mithilfe von DataTable(JavaScript & jQuery) geladen-->
                    <table id="resultTable" class="table table-hover table-striped display"  cellspacing="0" width="100%">
                        <thead>
                        <th></th>
                        <th>
                            #
                        </th>
                        <th>
                            Kursname
                        </th>
                        <th>
                            Vortragender
                        </th>
                        <th>
                            Semester
                        </th>
                        <th>
                            Studium
                        </th>
                        </thead>
                        <tbody>
                            <!--Informationen aus der Datenbank in HTML umwandeln (Übergabe der ResultSets)-->
                            <%= adminCoursesMessage.getCourseDetailsForAdmin(rsCourses, rsLecturers, person)%>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>
