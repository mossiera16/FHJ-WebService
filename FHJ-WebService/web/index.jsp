<%-- 
/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
--%>
<%@page import="LoginPackage.LoginServlet"%>
<%@page import="project_classes.MessageHandler"%>
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
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
        <!-- Optional theme -->
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap-theme.min.css" integrity="sha384-rHyoN1iRsVXV4nD0JutlnGaslCJuC7uwjduW9SVrLvRYooPp2bWYgmgJQIXwl/Sp" crossorigin="anonymous">
        <link rel="SHORTCUT ICON" href="images/favicon.png" type="image/png">
        <!-- Latest compiled and minified JavaScript -->
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js" integrity="sha384-Tc5IQib027qvyjSMfHjOMaLkfuWVxZxUPnCJA7l2mCWNIpG9mGCD8wGNIcPD7Txa" crossorigin="anonymous"></script>
        <title>FH-Joanneum Kursverwaltungssystem</title>
    </head>
    <%
        DBAccess dbAccess = new DBAccess(true);
        //dbAccess.DBInsertSampleData();
        if (session.getAttribute("userState") == null) {
            session.setAttribute("userState", 0);
        }
        //dbAccess.DBCloseAccess();
    %>

    <body>
        <div class="container">
            <div style="margin-left: auto; margin-right:auto; width: 20em;">
                <h1 style="text-align: center;">Login</h1>
                <form action="LoginServlet">
                    <div class="input-group margin-bottom-sm">
                        <span class="input-group-addon"><i class="fa fa-user fa-fw" aria-hidden="true"></i></span>
                        <input class="form-control" type="text" name="username" autofocus="true" placeholder="Benutzername" required>
                    </div>
                    <div class="input-group">
                        <span class="input-group-addon"><i class="fa fa-key fa-fw" aria-hidden="true"></i></span>
                        <input class="form-control" type="password" name="password" placeholder="Passwort" required>
                    </div>
                    <div style="width: 100%; display: inline-flex;">
                        <input class="btn btn-default" style="width: 50%;" type="submit" value="Login">
                        <input class="btn btn-default" style="width: 50%;" type="cancel" value="Abbrechen">
                    </div>
                </form>
                <div id="message-container" style="text-align: center;">
                    <jsp:useBean id="message" class="project_classes.MessageHandler"></jsp:useBean>
                    <%= message.getIndexSiteMessage((int) session.getAttribute("userState"))%>
                </div>
            </div>
        </div>
    </body>
</html>
