/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.TkKayttaja;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Kayttaja;

/**
 *
 * @author Valeria
 */
public class KayttajanTiedotServlet extends YleisServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");
        req.setAttribute("tunnus", kayttaja.getTunnus());
        if (kayttaja.getSahkoposti() != null && !kayttaja.getSahkoposti().equals("")) {
            req.setAttribute("sahkoposti", kayttaja.getSahkoposti());
        } else {
            req.setAttribute("sahkoposti", "sähköpostia ei annettu");
        }
        if (req.getParameter("muokkaus") == null && req.getParameter("poisto") == null) {
            avaaSivu("/WEB-INF/jsp/kayttajanakymat/kayttajantiedot.jsp", req, resp);
        } else if (req.getParameter("poisto") != null) {
            TkKayttaja.poistaKayttaja(kayttaja.getId());
            lisaaVirheViesti(req, "Käyttäjätilisi on poistettu!");
            siirrySivulle("/kirjautuminen", req, resp);
        } else if (req.getParameter("muokkaus") != null) {
            siirrySivulle("/arkisto/tietojenmuokkaus", req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
