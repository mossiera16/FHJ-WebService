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
<%@page import="project_classes.STUDENT"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@ page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html lang="en">
    <%
        List<COURSE> courseDetails = null;
        ResultSet rs = null;
        Integer courseNumber = null;
        String updateData = "";
        String coursePK = "";
        String studentPK = "";
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
            rs = person.getGradeDetailsForPerson(courseNumber);
            courseDetails = person.getCourseDetails(courseNumber, true);
            if (request.getParameter("update") != null) {
                updateData = request.getQueryString();
                person.updateGradeDetails(updateData, courseNumber);
                if (courseNumber == null) {
                    response.sendRedirect("results.jsp");
                } else {
                    response.sendRedirect("results.jsp?courseNumber=" + courseNumber);
                }

            }
            if(request.getParameter("personPK")!=null&&request.getParameter("coursePK")!=null){
                studentPK = request.getParameter("personPK");
                coursePK = request.getParameter("coursePK");
                person.deleteStudentFromCourse(studentPK, coursePK);
            }
        }

    %>

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
            var commited = true;
            $(document).ready(function () {
                if(document.getElementById("commit")!=null)
                    document.getElementById("commit").style.background = "#A7D177";
                var table = $('#resultTable').DataTable({
                    "order": [[0, "asc"]],
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json"
                    }
                });
                $('#commit').click(function () {
                    var data = table.$('input, select').serialize();
                    var courseNumber = "<%=courseNumber%>";
                    document.getElementById("commit").style.background = "#A7D177";
                    commited = true;
                    if (courseNumber == "null")
                    {
                        window.location.replace("results.jsp?update=" + data);
                    } else
                    {
                        window.location.replace("results.jsp?courseNumber=" + courseNumber + "&update=" + data);
                    }
                    return false;
                });
            });

            jQuery(document).ready(function ($) {
                $(".clickable-row").click(function () {
                    window.document.location = $(this).data("href");
                });
            });

            function setUncommitedStyle() {
                document.getElementById("commit").style.background = "#F79E9E";
                commited = false;
            }
            function openModal() {
                jQuery('#modalContainer').load('teste');
                alert("test");
            }
            window.onbeforeunload = function () {
                if (!commited)
                    return 'Wollen Sie die vorgenommenen Änderungen beibehalten?';
            };
        </script>
    </head>
    <body>
        <div onunload="window.location.reload();" style="margin: auto;" class="modal fade" id="modalContainer" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content">

                </div>
            </div>
        </div>
        <%@include  file="navbar.jsp"  %>

        <div class="container-fluid" >
            <div class="row">
                <%@include  file="sidebar.jsp" %>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <div>
                        <h1 class="page-header">Ergebnisse
                            <%= resultMessages.getResultCaption(courseNumber, courseDetails)%>
                        </h1>
                        <div class="btn-group" style="margin-top: 1em; margin-bottom: 1em;">
                            <a href="results.jsp" style="text-decoration: none;" role="button" class="btn btn-default btn-sm <%if (courseNumber == null) {
                                    out.print(" disabled");
                                }%>">
                                <span class='glyphicon glyphicon-th' aria-hidden='true'></span>  Ergebnisse aller Kurse anzeigen
                            </a>
                            <a href="courses.jsp" style="text-decoration: none;" role="button" class="btn btn-default btn-sm">
                                <span class='glyphicon glyphicon-arrow-up' aria-hidden='true'></span> zurück zu den Kursen
                            </a>
                            <% if (person.getPERSON_TYPE().equals("LECTURER_ENTITY")) {
                                    out.print("<a href='results.jsp' style=text-decoration: none;' role='button' id='commit' class='btn btn-default btn-sm'>"
                                            + "<span class='glyphicon glyphicon-floppy-disk' aria-hidden='true'></span> Eingaben bestätigen"
                                            + "</a>"
                                            + "<a href='modalContainer.jsp' style='text-decoration: none;' role='button' id='addStudent' data-toggle='modal' data-target='#modalContainer' class='btn btn-default btn-sm'>"
                                            + "<span class='glyphicon glyphicon-edit' aria-hidden='true'></span> Studentenverwaltung"
                                            + "</a>");
                                }

                            %>


                        </div>
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
                            <%                                if (person.getPERSON_TYPE() == "LECTURER_ENTITY") {
                                    out.print("<th>Vorname</th><th>Nachname</th><th>Studentennummer</th>");
                                }
                            %>
                            </thead>
                            <tbody>
                                <%= resultMessages.getGradeDetails(rs, person.getPERSON_TYPE())%>
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
