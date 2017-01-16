<%-- 
/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 * Seite: adminlecturers.jsp
 * Beschreibung: Adminseite für die Verwaltung der Vortragenden
 */
--%>
<jsp:useBean id="adminLecturerMessages" class="project_classes.Data2HTMLConverterBean"></jsp:useBean>
<%@page import="java.sql.ResultSet" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="project_classes.PERSON"%>
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="SHORTCUT ICON" href="images/favicon.png" type="image/png">
        <title>Vortragendenverwaltung</title>

       <!-- Bootstrap core CSS -->
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Datatables CSS -->
        <link href="css/datatables.css" rel="stylesheet">
        <!-- Bootstrap core JavaScript -->
        <script src="bootstrap/js/jquery.js" type="text/javascript"></script>
        <script src="bootstrap/js/jquery-datatables.js" type="text/javascript"></script>
        <script src="administration.js" type="text/javascript"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

        <!-- Standard - CSS -->
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
        session.setAttribute("siteName", "lecturers");
        ResultSet rsLecturers = null;
        String data = "";
        PERSON person = (PERSON) session.getAttribute("currentSessionUser");
        if (person == null) {
            session.setAttribute("userState", 2);
            String redirectURL = "index.jsp";
            response.sendRedirect(redirectURL);
            return;
        }else {
            rsLecturers = person.getLecturers();
            if (request.getParameter("delete") != null || request.getParameter("insert-update") != null) {
                data = request.getQueryString();
                person.administrateData(data, "lecturers", 9);
                response.sendRedirect("adminlecturers.jsp");
            }
        }
        %>
    <!--onload: Aufruf der JavaScript-Funktion administration()-->
    <body onload="administration('adminlecturers.jsp')">
        <!--Inkludierung der Navigationsleiste (oberhalb)-->
        <%@include  file="navbar.jsp" %>

        <div class="container-fluid">
            <div class="row">
                <!--Inkludierung der Seitenleiste (links)-->
                <%@include  file="sidebar.jsp" %>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <div>
                        <h1 class="page-header">Vortragendenverwaltung</h1>
                    </div>
                       <div class="btn-group" style="margin-top: 1em; margin-bottom: 1em;">
                        <a style="text-decoration: none;" role="button" id="commit" class="btn btn-default btn-sm">
                            <span class='glyphicon glyphicon-floppy-disk' aria-hidden="true"></span>Eingaben bestätigen
                        </a>
                    </div>
                    <!--Datentabelle mithilfe von DataTable(JavaScript & jQuery) geladen-->
                    <table id="resultTable" class="table table-hover table-striped display"  cellspacing="0" width="100%">
                        <thead>
                        <th></th>
                        <th>
                            #
                        </th>
                        <th>
                            Geschlecht
                        </th>
                        <th>
                            Geburtsdatum
                        </th>
                        <th>
                            Mitarbeiternummer
                        </th>
                        <th>
                            Vorname
                        </th>
                        <th>
                            Nachname
                        </th>
                        <th>
                            Benutzername
                        </th>
                        <th>
                            Sozialversicherungsnummer
                        </th>
                        <th>
                            Titel
                        </th>
                        </thead>
                        <tbody>
                            <!--Informationen aus der Datenbank in HTML umwandeln (Übergabe der ResultSets)-->
                            <%= adminLecturerMessages.getLecturerDetailsForAdmin(rsLecturers) %>
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
