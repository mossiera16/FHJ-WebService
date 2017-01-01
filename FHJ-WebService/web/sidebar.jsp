<%-- 
/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
--%>
<jsp:useBean id="sidebarMessage" class="project_classes.MessageHandler"></jsp:useBean>
<%@page import="project_classes.PERSON"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%PERSON personSidebar = (PERSON) session.getAttribute("currentSessionUser");%>
<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <%= sidebarMessage.getSidebarNavigationElements((String)session.getAttribute("siteName"), personSidebar)%>
    </ul> 
</div>
