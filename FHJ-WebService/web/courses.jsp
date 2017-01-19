<%-- 
 /*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 * Seite: courses.jsp
 * Beschreibung: Seite für die Übersicht der Kurse (Vortragende und Studenten)
 */
--%>
<jsp:useBean id="coursesMessages" class="project_classes.Data2HTMLConverterBean"></jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="project_classes.PERSON"%>
<%@page import="java.util.List"%>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html lang="de">
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
        <meta name="description" content="">
        <meta name="author" content="">
        <link rel="SHORTCUT ICON" href="images/favicon.png" type="image/png">
        <title>Kurse - Kursverwaltungssystem</title>

        <!-- Bootstrap core CSS -->
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <!-- Datatables CSS -->
        <link href="css/datatables.css" rel="stylesheet">
        <!-- Bootstrap core JavaScript -->
        <script src="bootstrap/js/jquery.js" type="text/javascript"></script>
        <script src="bootstrap/js/jquery-datatables.js" type="text/javascript"></script>

        <!-- Standard - CSS -->
        <link href="css/dashboard.css" rel="stylesheet">
        <script type="text/javascript">
            $(document).ready(function () {
                //Initialisierung der Datentabelle für Sortierung, Filterung und ästhetische Darstellung der Informationen
                $('#courseTable').DataTable({
                    "order": [[1, "asc"]],
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json"
                    }
                });
            });
            
            //Initialisierung der Datentabelle für Sortierung, Filterung und ästhetische Darstellung der Informationen
            jQuery(document).ready(function ($) {
                $(".clickable-row").click(function () {
                    window.document.location = $(this).data("href");
                });
            });
        </script>

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
        PERSON person = (PERSON) session.getAttribute("currentSessionUser");
        if (person == null) {
            session.setAttribute("userState", 2);
            String redirectURL = "index.jsp";
            response.sendRedirect(redirectURL);
            return;
        }
    %>
    <body>
        <!--Inkludierung der Navigationsleiste (oberhalb)-->
        <%@include  file="navbar.jsp"  %>
        <div class="container-fluid">
            <div class="row">
                <!--Inkludierung der Seitenleiste (links)-->
                <%@include  file="sidebar.jsp" %>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <div>
                        <h1 class="page-header">Kurse</h1>
                        <%                            
                            ResultSet result = person.getCourseDetails(null, false);
                        %>
                        <!--Datentabelle mithilfe von DataTable(JavaScript & jQuery) geladen-->
                        <table id="courseTable" class="table table-hover table-striped display"  cellspacing="0" width="100%">
                            <thead>
                            <th>
                                #
                            </th>
                            <th>
                                Kursname
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
                                <%= coursesMessages.getCourseDetails(result)%>
                            </tbody>
                        </table>

                    </div>
                </div>
            </div>
        </div>
        <!-- Bootstrap core JavaScript-->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>
