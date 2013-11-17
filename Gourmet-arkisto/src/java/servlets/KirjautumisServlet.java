/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.TkKayttaja;
import models.Kayttaja;
import exceptions.GourmetException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
        } else if (action.equals("logout")) {
            kirjauduUlos(req, resp);
        } else {
            req.setAttribute("virheViesti", "Tuntematon action " + action);
            avaaSivu("/WEB-INF/jsp/kayttajanakymat/kirjautuminen.jsp", req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void kirjaudu(HttpServletRequest request, HttpServletResponse response) {

        String tunnus = request.getParameter("username");
        String salasana = request.getParameter("password");
        if (tunnus == null || tunnus.equals("") || salasana == null || salasana.equals("")) {
            lisaaVirheViesti(request, "Syötä tunnus ja salasana");
            siirrySivulle("/kirjautuminen", request, response);
        } else {
            Kayttaja kirjautuja = TkKayttaja.kirjaudu(tunnus, salasana);
            if (kirjautuja == null) {
                lisaaVirheViesti(request, "Tunnus ja salasana eivät täsmää");
                siirrySivulle("/kirjautuminen", request, response);
            } else {
                HttpSession session = request.getSession();
                session.setAttribute("kirjautunut", kirjautuja);
                siirrySivulle("/arkisto/reseptilistaus", request, response);
            }
        }
    }

    private void kirjauduUlos(HttpServletRequest request, HttpServletResponse response) {
        HttpSession session = request.getSession(false);
        session.removeAttribute("kirjautunut");
        siirrySivulle("/kirjautuminen", request, response);
    }
}
