<%-- 
    Document   : modalContainer
    Created on : 29.12.2016, 16:20:32
    Author     : Notebook
--%>
<jsp:useBean id="modalContainerMessages" class="project_classes.MessageHandler"></jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="project_classes.PERSON"%>
<%@page import="project_classes.COURSE"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html lang="en">
    <head>
        <%
            List<COURSE> courseDetails = null;
            ResultSet rsStudentsToEnroll = null;
            ResultSet rs = null;
            Integer courseNumber = null;
            String updateData = "";
            session.setAttribute("siteName", "results");
            PERSON person = (PERSON) session.getAttribute("currentSessionUser");

            if (request.getParameter("courseNumber") != null) {
                courseNumber = (Integer) Integer.parseInt(request.getParameter("courseNumber"));
            }
            rs = person.getGradeDetailsForPerson(courseNumber);
            rsStudentsToEnroll = person.getStudents();
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

            if (request.getParameter("add") != null) {
                response.sendRedirect("index.jsp");
            }

        %>
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
            var table;
            var studentPKsToDelete = [], coursePKsToDelete = [];
            var studentPKsToAdd = [], coursePKsToAdd = [];
            $(document).ready(function () {
                 table = $('#resultTable2').DataTable({
                    "order": [[1, "asc"]],
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json"
                    }
                });

                $('#commit').click(function () {
                    var data = table.$('input, select').serialize();
                    var urlString = generataUrlString(studentPKsToDelete, coursePKsToDelete);
                    
                    if(urlString === ""){
                        if(data !== ""){
                            urlString = "?insert=" + data;
                        }
                    } else {
                        urlString = "?delete=" + urlString + "&insert=" + data;
                    }

                    window.location.replace("results.jsp" + urlString);
                });
                
                function generataUrlString(studentPKs, coursePKs){
                   var urlString = "";
                    for (var i = 0; i < studentPKs.length; i++) {
                        urlString += studentPKs[i] + "=" + coursePKs[i]+"&";
                    }
                    urlString = urlString.substring(0, urlString.length-1);
                    return urlString;
                }

                $('#add').on('click', function () {
                    var myTr = $(this).closest('tr');
                    var clone = myTr.clone();
                    table.row.add(clone); myTr.after(clone);
                    table.destroy();
                    table = $('#resultTable2').DataTable({
                    "order": [[1, "asc"]],
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json"
                    }});
                });
            });
            function remove(studentPK, coursePK) {
                studentPKsToDelete.push(studentPK);
                coursePKsToDelete.push(coursePK);
                document.getElementById(studentPK + coursePK).style.display = "none";
                //window.location.replace("results.jsp?personPK=" + studentPK + "&coursePK=" + coursePK);
            }
        </script>
    </head>
    <body>
        <div class="modal-content" style="width: 50em;">
            <div class="modal-header">
                <a href="results.jsp" role="button" class="close" data-dismiss="modal" onclick="window.location.reload();">&times;</a>
                <h4><p>Studentenverwaltung</p></h4>

            </div>
            <div class="modal-body">
                <table id="resultTable2" class="table table-hover table-striped display"  cellspacing="0" width="100%">
                    <thead>
                    <th>
                    </th>
                    <th>
                        #
                    </th>
                    <th>
                        Student
                    </th>
                    <th>
                        Kursname
                    </th>
                    </thead>
                    <tbody>
                        <%= modalContainerMessages.getGradeDetailsForModal(rs, rsStudentsToEnroll, courseDetails)%>
                    </tbody>
                </table>
            </div>
            <div class="modal-footer">
                <button id="commit" type="submit" class="btn btn-success">Eingaben bestätigen</button>
            </div>
        </div>
        <!-- Bootstrap core JavaScript-->
        <!-- Placed at the end of the document so the pages load faster-->
        <script src="bootstrap/dist/js/bootstrap.min.js"></script>
    </body>
</html>