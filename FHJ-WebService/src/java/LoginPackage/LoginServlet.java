/*
 * Autoren: Andreas Mossier, Mina Shokrollahi, Romana Ausim
 * Programm: software_architecture
 * Zweck: Kursverwaltungssystem --> Verwaltung von Studenten, Vortragenden, Kursen und Ergebnissen
 * Fachhochschule Joanneum
 * Datum: 16.12.2016
 */
package LoginPackage;

import project_classes.PERSON;
import java.security.MessageDigest;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import project_entities.STUDENT_ENTITY;

/**
 * * Servlet Implementierung LoginServlet
 */
public class LoginServlet extends HttpServlet {

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, java.io.IOException {
        try {
            PERSON person = new PERSON();
            person.setUSERNAME(request.getParameter("username"));
            MessageDigest md = MessageDigest.getInstance("SHA-1");;
            String shaPassword = byteArrayToHexString(md.digest(request.getParameter("password").getBytes()));

            person.setPASSWORD(shaPassword);
            person = PersonToCheckDAO.login(person);
            HttpSession session = request.getSession(true);
            if (person.getISVALID()) {
                session.setAttribute("currentSessionUser", person);
                session.setAttribute("userState", 0);
                response.sendRedirect("dashboard.jsp"); //logged-in page 
            } else {
                session.setAttribute("userState", 1);
                response.sendRedirect("index.jsp"); //error page 
            }
        } catch (Exception ex) {
            System.out.println(ex);
        }

    }

    public static String byteArrayToHexString(byte[] b) {
        String result = "";
        for (int i = 0; i < b.length; i++) {
            result
                    += Integer.toString((b[i] & 0xff) + 0x100, 16).substring(1);
        }
        return result;
    }
}
