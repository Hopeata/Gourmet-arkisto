/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.TkKayttaja;
import exceptions.GourmetException;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Valeria
 */
public class KirjautumisServlet extends YleisServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            avaaSivu("/WEB-INF/jsp/kayttajanakymat/kirjautuminen.jsp", req, resp);
        } else if (action.equals("login")) {
            kirjaudu(req, resp);
        } else {
            req.setAttribute("virheViesti", "Tuntematon action " + action);
            avaaSivu("/WEB-INF/jsp/kayttajanakymat/kirjautuminen.jsp", req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    protected void kirjaudu(HttpServletRequest request, HttpServletResponse response) {

        String tunnus = request.getParameter("username");
        String salasana = request.getParameter("password");
        if (tunnus == null || tunnus.equals("") || salasana == null || salasana.equals("")) {
            request.setAttribute("virheViesti", "Syötä tunnus ja salasana");
            avaaSivu("/WEB-INF/jsp/kayttajanakymat/kirjautuminen.jsp", request, response);
        }
    }
}
