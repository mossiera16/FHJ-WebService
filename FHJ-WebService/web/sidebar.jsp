<%-- 
    Document   : sidebar
    Created on : 13.12.2016, 19:13:50
    Author     : Notebook
--%>
<jsp:useBean id="sidebarMessage" class="project_classes.MessageHandler"></jsp:useBean>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-sm-3 col-md-2 sidebar">
    <ul class="nav nav-sidebar">
        <%= sidebarMessage.getSidebarNavigationElements((String)session.getAttribute("siteName"))%>
    </ul> 
</div>
