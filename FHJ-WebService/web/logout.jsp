<%-- 
/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
--%>
<%
    session.removeAttribute("currentSessionUser");
    session.setAttribute("userState", 3);
    String redirectURL = "index.jsp";
    response.sendRedirect(redirectURL);
%>
