<%-- 
/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
--%>
<jsp:useBean id="addEditResultsMessages" class="project_classes.MessageHandler"></jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="project_classes.PERSON"%>
<%@page import="project_classes.GRADE"%>
<%@page import="project_classes.COURSE"%>
<%@page import="project_classes.STUDENT"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@ page import="java.sql.ResultSet" %>
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
        <title>Ergebnisse - Kursverwaltungssystem</title>

        <!-- Bootstrap core CSS -->
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">
        <link href="css/datatables.css" rel="stylesheet">
        <script src="bootstrap/js/jquery.js" type="text/javascript"></script>
        <script src="bootstrap/js/jquery-datatables.js" type="text/javascript"></script>
        <!-- IE10 viewport hack for Surface/desktop Windows 8 bug -->

        <!-- Custom styles for this template -->
        <link href="css/dashboard.css" rel="stylesheet">
        <link href="css/results.css" rel="stylesheet">
        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->
        <script type="text/javascript">
            $(document).ready(function () {
                var table = $('#resultTable').DataTable();

                $('button').click(function () {
                    var data = table.$('input, select').serialize();
                    alert(
                            "The following data would have been submitted to the server: \n\n" +
                            data.substr(0, 120) + '...'
                            );
                    return false;
                });
            });
        </script>
    </head>
    <%
        Integer courseNumber = null;
        List<COURSE> courseDetails = null;
        session.setAttribute("siteName", "add-edit-results");
        PERSON person = (PERSON) session.getAttribute("currentSessionUser");
        if (person == null) {
            session.setAttribute("userState", 2);
            String redirectURL = "index.jsp";
            response.sendRedirect(redirectURL);
            return;
        } else {
            if (request.getParameter("courseNumber") != null) {
                courseNumber = (Integer) Integer.parseInt(request.getParameter("courseNumber"));
            }
            courseDetails = person.getCourseDetails(courseNumber, true);
        }
    %>
    <body>
        <%@include  file="navbar.jsp"  %>
        <div class="container-fluid">
            <div class="row">
                <%@include  file="sidebar.jsp" %>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <div>
                        <h1 class="page-header">Kursverwaltung <%= addEditResultsMessages.getResultCaption(courseNumber, courseDetails)%></h1>
                        <div style="margin-top: 1em; margin-bottom: 1em;">
                            <a href="results.jsp" style="text-decoration: none;" role="button" class="btn btn-default btn-sm">
                                zurück zu den Ergebnissen
                            </a>
                        </div>

                        <table id="resultTable" class="table table-hover table-striped display" width="100%" cellspacing="0">
                            <thead>
                                <tr>
                                    <th>#</th>
                                    <th>Kursname</th>
                                    <th>Note</th>
                                    <th>Semester</th>
                                    <th>Vorname</th>
                                    <th>Nachname</th>
                                    <th>Studentennummer</th>
                                </tr>
                            </thead>
                            <tbody>
                                <tr>
                                    <td>1</td>
                                    <td>Softwarearchitekturen</td>
                                    <td><select size="1" id="row-1-office" name="row-1-office">
                                            <option value="0" selected="selected">
                                                noch keine Note
                                            </option>
                                            <option value="1">
                                                1
                                            </option>
                                            <option value="2">
                                                2
                                            </option>
                                            <option value="3">
                                                3
                                            </option>
                                            <option value="4">
                                                4
                                            </option>
                                            <option value="5">
                                                5
                                            </option>
                                        </select></td>
                                        <td>1</td>
                                        <td>Andreas</td>
                                        <td>Mossier</td>
                                        <td>1310288011</td>
                                </tr>

                            </tbody>
                        </table>
                    </div>
                </div>
            </div>
        </div>
        <!-- Bootstrap core JavaScript
================================================== -->
        <!-- Placed at the end of the document so the pages load faster -->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>