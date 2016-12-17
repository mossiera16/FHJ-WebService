<%-- 
    Document   : logout
    Created on : 17.12.2016, 20:52:05
    Author     : Notebook
--%>
<%
    session.removeAttribute("currentSessionUser");
    session.setAttribute("userState", 3);
    String redirectURL = "index.jsp";
    response.sendRedirect(redirectURL);
%>
