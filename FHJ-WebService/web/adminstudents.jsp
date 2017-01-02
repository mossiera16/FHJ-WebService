<%-- 
/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
--%>
<jsp:useBean id="adminStudentsMessage" class="project_classes.MessageHandler"></jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="project_classes.PERSON"%>
<%@page import="java.sql.ResultSet" %>
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
        <title>Studentenverwaltung</title>

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
            var table;
            var studentPKsToDelete = [];
            $(document).ready(function () {
                table = $('#resultTable').DataTable({
                    "order": [[0, "asc"]],
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json"
                    }
                });
                $('#commit').click(function () {
                    var data = table.$('input, select').serialize();
                    var urlString = generataUrlString(studentPKsToDelete);
                    
                    if(urlString === ""){
                        if(data !== ""){
                            urlString = "?insert-update=" + data;
                        }
                    } else {
                        urlString = "?delete=" + urlString + "&insert-update=" + data;
                    }
                    
                    document.getElementById("commit").style.background = "#A7D177";
                    commited = true;
                    window.location.replace("adminstudents.jsp" + urlString);
                });
                
                function generataUrlString(studentPKs){
                   var urlString = "";
                    for (var i = 0; i < studentPKs.length; i++) {
                        urlString += studentPKs[i] +"=";
                    }
                    urlString = urlString.substring(0, urlString.length-1);
                    return urlString;
                }
                
                $('#add').on('click', function () {
                    var myTr = $(this).closest('tr');
                    var clone = myTr.clone();
                    table.row.add(clone);
                    myTr.after(clone);
                    table.destroy();
                    table = $('#resultTable').DataTable({
                        "order": [[0, "asc"]],
                        "language": {
                            "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json"
                        }});
                });
                $('#resultTable tbody').on('click', 'tr', function () {
                    if ($(this).hasClass('selected')) {
                        $(this).removeClass('selected');
                    } else {
                        table.$('tr.selected').removeClass('selected');
                        $(this).addClass('selected');
                    }
                });
            });

            function removeStudent(studentPK) {
                table.row('.selected').remove().draw(false);
                studentPKsToDelete.push(studentPK);
            };

            function setUncommitedStyle() {
                document.getElementById("commit").style.background = "#F79E9E";
                commited = false;
            }
            window.onbeforeunload = function () {
                if (!commited)
                    return 'Wollen Sie die vorgenommenen Änderungen beibehalten?';
            };


        </script>
    </head>
    <%
        session.setAttribute("siteName", "students");
        ResultSet rsStudents = null;
        String data = "";
        PERSON person = (PERSON) session.getAttribute("currentSessionUser");
        if (person == null) {
            session.setAttribute("userState", 2);
            String redirectURL = "index.jsp";
            response.sendRedirect(redirectURL);
            return;
        } else {
            rsStudents = person.getStudents();
            if (request.getParameter("delete") != null || request.getParameter("insert-update") != null) {
                data = request.getQueryString();
                person.administrateStudents(data);
                response.sendRedirect("adminstudents.jsp");
            }
        }
    %>
    <body>
        <%@include  file="navbar.jsp" %>

        <div class="container-fluid">
            <div class="row">
                <%@include  file="sidebar.jsp" %>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <div>
                        <h1 class="page-header">Studentenverwaltung</h1>
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
                            Vorname
                        </th>
                        <th>
                            Nachname
                        </th>
                        <th>
                            Benutzername
                        </th>
                        <th>
                            Semester
                        </th>
                        <th>
                            Sozialversicherungsnummer
                        </th>
                        <th>
                            Studentennummer
                        </th>
                        <th>
                            Titel
                        </th>
                        <th>
                            Studium
                        </th>
                        </thead>
                        <tbody>
                            <%= adminStudentsMessage.getStudentDetailsForAdmin(rsStudents)%>
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
