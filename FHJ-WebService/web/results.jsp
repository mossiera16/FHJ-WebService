<%-- 
/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
--%>
<jsp:useBean id="resultMessages" class="project_classes.MessageHandler"></jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="project_classes.PERSON"%>
<%@page import="project_classes.GRADE"%>
<%@page import="project_classes.COURSE"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
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

        <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
        <!--[if lt IE 9]>
          <script src="https://oss.maxcdn.com/html5shiv/3.7.3/html5shiv.min.js"></script>
          <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
        <![endif]-->

        <script type="text/javascript">
            $(document).ready(function () {
                $('#resultTable').DataTable({
                    "order": [[3, "desc"]],
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json"
                    }
                });
            });

            jQuery(document).ready(function ($) {
                $(".clickable-row").click(function () {
                    window.document.location = $(this).data("href");
                });
            });
        </script>
    </head>

    <%
        List<GRADE> gradeResult = null;
        List<COURSE> courseDetails = null;

        Integer courseNumber = null;
        session.setAttribute("siteName", "results");
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
            gradeResult = person.getGradeDetailsForPerson(courseNumber);
        }
    %>
    <body>
        <%@include  file="navbar.jsp"  %>

        <div class="container-fluid">
            <div class="row">
                <%@include  file="sidebar.jsp" %>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <div>
                        <h1 class="page-header">Ergebnisse
                            <%= resultMessages.getResultCaption(courseNumber, courseDetails)%>
                        </h1>
                        <div style="margin-top: 1em; margin-bottom: 1em;">
                            <a href="results.jsp" style="text-decoration: none;">
                                <button type="button" class="btn btn-default btn-sm">
                                    Ergebnisse aller Kurse anzeigen
                                </button>
                            </a>
                            <a href="courses.jsp" style="text-decoration: none;">
                                <button type="button" class="btn btn-default btn-sm">
                                    zurück zu den Kursen
                                </button>
                            </a>
                        </div>
                        <%
                        %>
                        <table id="resultTable" class="table table-hover table-striped display"  cellspacing="0" width="100%">
                            <thead>
                            <th>
                                #
                            </th>
                            <th>
                                Kursname
                            </th>
                            <th>
                                Note
                            </th>
                            <th>
                                Semester
                            </th>
                            <%
                                if (person.getPERSON_TYPE() == "LECTURER_ENTITY") {
                                    out.print("<th>Vorname</th><th>Nachname</th><th>Studentennummer</th>");
                                }
                            %>
                            </thead>
                            <tbody>
                                <%= resultMessages.getGradeDetails(gradeResult, courseDetails, person.getPERSON_TYPE())%>
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
