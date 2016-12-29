<%-- 
/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
--%>
<%@page import="project_classes.PERSON"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%PERSON personNavbar = (PERSON) session.getAttribute("currentSessionUser");%>
<nav class="navbar navbar-inverse navbar-fixed-top">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar" aria-expanded="false" aria-controls="navbar">
                <span class="sr-only">Toggle navigation</span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <div style="display: inline;">
                <div style="display: inline;"><a class="navbar-brand" href="#">Kursverwaltungssystem</a></div>

            </div>
        </div>
        <div id="navbar" class="navbar-collapse collapse">
            <ul class="nav navbar-nav navbar-right">
                <li style="color: #9d9d9d; margin-top: 1.1em; margin-left: 1.1em;">
                    <% if(personNavbar.getPERSON_TYPE().equals("LECTURER_ENTITY")){
                        out.print("<image src='images/glyphicon-lecturer1.png' style='color: #78b832;' data-toggle='tooltip' title='Vortragende(r)'></image>");
                    }else{
                        out.print("<span style='color: #78b832;' class='glyphicon glyphicon-education' aria-hidden='true' data-toggle='tooltip' title='Student/-in'></span>");
                    }
                    %>
                    <span style="color: #78b832;"><%
                  out.print(personNavbar.getFIRSTNAME() + " " + personNavbar.getLASTNAME());
                        %>
                    </span>
                </li>
                <li><a href="dashboard.jsp">Dashboard</a></li>
                <li><a href="courses.jsp">Kurse</a></li>
                <li><a href="results.jsp">Ergebnisse</a></li>
                <li><a href="logout.jsp">Logout</a></li>
            </ul>

        </div>
    </div>
</nav>