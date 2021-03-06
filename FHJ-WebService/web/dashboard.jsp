<%-- 
/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 * Seite: dashboard.jsp
 * Beschreibung: Startseite nach erfolgreichem Login
 */
--%>
<jsp:useBean id="dashboardMessage" class="project_classes.Data2HTMLConverterBean"></jsp:useBean>
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
        <title>Dashboard - Kursverwaltungssystem</title>

        <!-- Bootstrap core CSS -->
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
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
        session.setAttribute("siteName", "dashboard");

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
        <%@include  file="navbar.jsp" %>
        <div class="container-fluid">
            <div class="row">
                <!--Inkludierung der Seitenleiste (links)-->
                <%@include  file="sidebar.jsp" %>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <div>
                        <h1 class="page-header">Dashboard <%= dashboardMessage.getDashboardWelcomeMessage((PERSON) person)%></h1>
                    </div>
                        <%= dashboardMessage.getDashboardButtonGroup(person)%>
                </div>
            </div>
        </div>

        <!-- Bootstrap core JavaScript-->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>
        <script>window.jQuery || document.write('<script src="bootstrap/assets/js/vendor/jquery.min.js"><\/script>');</script>
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>
