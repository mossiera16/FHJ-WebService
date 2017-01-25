<%-- 
/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 * Seite: index.jsp
 * Beschreibung: Startseite des Projektes (Anmeldefenster für Studenten, Vortragende und Administratoren
 */
--%>
<%@page import="project_classes.LoginServlet"%>
<%@page import="project_classes.DBAccess"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ page session="true" %>
<%@ page import="java.io.*,java.util.*" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <link rel="stylesheet" href="font-awesome/css/font-awesome.min.css">
        <!-- Latest compiled and minified CSS -->
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <link rel="SHORTCUT ICON" href="images/favicon.png" type="image/png">
        <script src="bootstrap/js/jquery.js" type="text/javascript"></script>
        <!-- Custom styles for this template -->
        <link href="css/dashboard.css" rel="stylesheet">
        <title>FH-Joanneum Kursverwaltungssystem</title>
    </head>
    <%
        //Die nächsten 3 Zeilen für das Einfügen der Testdaten auskommentieren 
//        DBAccess dbAccess = new DBAccess(true);
//        dbAccess.DBInsertSampleData();
//        dbAccess.DBCloseAccess();
        if (session.getAttribute("userState") == null) {
            session.setAttribute("userState", 0);
        }
    %>
    <body>
        <div class="container">
            <div style="margin-left: auto; margin-right:auto; width: 20em;">
                <h1 style="text-align: center;">Login</h1>
                <!--Aufruf des LoginServlets: LoginServlet.java zur Überprüfung der eingegebenen Daten --> 
                <form action="LoginServlet">
                    <div class="input-group margin-bottom-sm">
                        <span class="input-group-addon"><i class="fa fa-user fa-fw" aria-hidden="true"></i></span>
                        <!--Passworteingabe: required >> Überprüfunge ob Feld leer ist -->
                        <input class="form-control" type="text" name="username" autofocus="true" placeholder="Benutzername" required>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-key fa-fw" aria-hidden="true"></i></span>
                        <!--Passworteingabe: required >> Überprüfunge ob Feld leer ist -->
                        <input class="form-control" type="password" name="password" placeholder="Passwort" required>
                    </div>
                    <div style="width: 100%; display: inline-flex;">
                        <input class="btn btn-default" style="width: 50%;" type="submit" value="Login">
                        <input class="btn btn-default" style="width: 50%;" type="cancel" value="Abbrechen">
                    </div>
                </form>
                <div id="message-container" style="text-align: center;">
                    <!--Nachrichtenbox >> Benachrichtigung je nachdem ob eine Person sich erfolgreich abgemeldet/nicht erfolgreich angemeldet hat oder ohne vorheriger Anmeldung auf eine Seite navigiert ist, die für Benutzer ohne Anmeldung nicht erreichbar sein sollte -->
                    <jsp:useBean id="message" class="project_classes.Data2HTMLConverterBean"></jsp:useBean>
                    <%= message.getIndexSiteMessage((int) session.getAttribute("userState"))%>
                    
                </div>
            </div>
        </div>
        <!-- Bootstrap core JavaScript-->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>
