<%-- 
    Document   : dashboard
    Created on : 27.11.2016, 17:42:19
    Author     : Notebook
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="LoginPackage.PERSON"%>
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
        <title>Kurse - Kursverwaltungssystem</title>

        <!-- Bootstrap core CSS -->
        <link href="bootstrap/dist/css/bootstrap.min.css" rel="stylesheet">

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
        session.setAttribute("siteName", "courses");
        PERSON person = (PERSON) session.getAttribute("currentSessionUser");
        if (person == null) {
            session.setAttribute("userState", 2);
            String redirectURL = "index.jsp";
            response.sendRedirect(redirectURL);
            return;
        }
    %>
    <body>
        <%@include  file="navbar.jsp"  %>

        <div class="container-fluid">
            <div class="row">
                <%@include  file="sidebar.jsp" %>
                <div class="col-sm-9 col-sm-offset-3 col-md-10 col-md-offset-2 main">
                    <div>
                        <h1 class="page-header">Kurse</h1>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>