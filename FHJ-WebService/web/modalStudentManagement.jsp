<%-- 
 /*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 * Seite: modalStudentManagement.jsp
 * Beschreibung: Dialogfenster für die Verwaltung der Studenten innerhalb eines Kurses (für Vortragende)
 */
--%>
<jsp:useBean id="modalContainerMessages" class="project_classes.Data2HTMLConverterBean"></jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="project_classes.PERSON"%>
<%@page import="project_classes.COURSE"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Enumeration"%>
<%@page import="java.sql.ResultSet" %>
<!DOCTYPE html>
<html>
    <head>
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
            ResultSet courseDetails = null;
            ResultSet rsStudentsToEnroll = null;
            ResultSet rs = null;
            Integer courseNumber = null;
            String updateData = "";
            session.setAttribute("siteName", "results");
            PERSON person = (PERSON) session.getAttribute("currentSessionUser");

            if (request.getParameter("courseNumber") != null) {
                courseNumber = (Integer) Integer.parseInt(request.getParameter("courseNumber"));
            }
            //Abfrage der Kursdaten der Studenten
            rs = person.getGradeDetailsForPerson(courseNumber);
            //Abfrage für die Auswahl der auszuwählenden Studenten
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
        <!-- Datatables CSS -->
        <link href="css/datatables.css" rel="stylesheet">
        <!-- Bootstrap core JavaScript -->
        <script src="bootstrap/js/jquery.js" type="text/javascript"></script>
        <script src="bootstrap/js/jquery-datatables.js" type="text/javascript"></script>
        
        <link href="css/dashboard.css" rel="stylesheet">
        <link href="css/results.css" rel="stylesheet">
        <script type="text/javascript">
            var table;
            var studentPKsToDelete = [], coursePKsToDelete = [];
            var studentPKsToAdd = [], coursePKsToAdd = [];
            //Initialisierung der Datentabelle für Sortierung, Filterung und ästhetische Darstellung der Informationen
            $(document).ready(function () {
                 table = $('#resultTable2').DataTable({
                    "order": [[1, "asc"]],
                    "language": {
                        "url": "//cdn.datatables.net/plug-ins/9dcbecd42ad/i18n/German.json"
                    }
                });
                 //Falls auf den Button mit der ID: 'commit' geklickt wird, wird diese Funktion aufgerufen
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
                //In dieser Funktion wird ein Teil des URL-Strings generiert.
                //Die übergebenen Primärschlüssel der zu löschenden Datensätze wird übergeben.
                function generataUrlString(studentPKs, coursePKs){
                   var urlString = "";
                    for (var i = 0; i < studentPKs.length; i++) {
                        urlString += studentPKs[i] + "=" + coursePKs[i]+"&";
                    }
                    urlString = urlString.substring(0, urlString.length-1);
                    return urlString;
                }
                
                //Falls auf den Button mit der ID: 'add' geklickt wird, wird diese Funktion aufgerufen
                //Hierbei wird die letzte Zeile geklont und eine Zeile darunter eingefügt.
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
            //Funktion für das Entfernen (Zeile wird ausgeblendet und PK wird in ein Array gespeichert) 
            function remove(studentPK, coursePK) {
                studentPKsToDelete.push(studentPK);
                coursePKsToDelete.push(coursePK);
                document.getElementById(studentPK + coursePK).style.display = "none";
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