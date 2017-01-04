<%-- 
/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
--%>
<jsp:useBean id="adminLecturerMessages" class="project_classes.MessageHandler"></jsp:useBean>
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
        <link href="css/datatables.css" rel="stylesheet">
        <script src="bootstrap/js/jquery.js" type="text/javascript"></script>
        <script src="bootstrap/js/jquery-datatables.js" type="text/javascript"></script>
        <script src="administration.js" type="text/javascript"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

        <!-- Custom styles for this template -->
        <link href="css/dashboard.css" rel="stylesheet">

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
    </head>
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
    <body onload="administration('adminlecturers.jsp')">
        <%@include  file="navbar.jsp" %>

        <div class="container-fluid">
            <div class="row">
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
