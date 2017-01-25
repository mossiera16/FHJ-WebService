/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 * Seite: LoginServlet.java
 * Beschreibung: Servlet für den Login-Vorgang
 */
package project_classes;

import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * * Servlet Implementierung LoginServlet
 */
public class LoginServlet extends HttpServlet {
    
    /*
    Called by the server (via the service method) to allow a servlet to handle a GET request.
    Overriding this method to support a GET request also automatically supports an HTTP HEAD request. A HEAD request is a GET request that returns no body in the response, only the request header fields.
    */
    @Override
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        try {
            //Instanzierung des aktuellen Session-Users
            PERSON person = new PERSON();
            //Abfrage des eingegebenen Benutzernamen und Passworts
            person.setUSERNAME(request.getParameter("username"));
            MessageDigest md = MessageDigest.getInstance("SHA-1");
            String shaPassword = byteArrayToHexString(md.digest(request.getParameter("password").getBytes()));
            
            person.setPASSWORD(shaPassword);
            //Überprüfung der eingebenen Daten
            person = PersonToCheckDAO.login(person);
            //Erzeugung einer HTTP-Session
            HttpSession session = request.getSession(true);
            if (person.getISVALID()) {
                session.setAttribute("currentSessionUser", person);
                session.setAttribute("userState", 0);
                response.sendRedirect("dashboard.jsp"); //Startseite nach erfolgreichem Login
            } else {
                session.setAttribute("userState", 1);
                response.sendRedirect("index.jsp"); //Seite nach fehlerhaftem Login 
            }
        } catch (IOException | NoSuchAlgorithmException ex) {
            System.out.println(ex);
        }

    }
    /*
        Funktion zur Umwandlung von einem Byte-Array zu einem String
    */
    public static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result
                    += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}
