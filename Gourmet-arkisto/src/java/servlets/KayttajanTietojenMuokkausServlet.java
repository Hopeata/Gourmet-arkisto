/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.TkKayttaja;
import models.Kayttaja;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 *
 * @author Valeria
 */
public class KayttajanTietojenMuokkausServlet extends YleisServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");
        if (action == null) {
            req.setAttribute("tunnus", kayttaja.getTunnus());
            req.setAttribute("sahkoposti", kayttaja.getSahkoposti());
            avaaSivu("/WEB-INF/jsp/kayttajanakymat/kayttajatietojenmuokkaus.jsp", req, resp);
        } else if (action.equals("muokkaus")) {
            paivitaTiedot(req, resp, kayttaja);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void paivitaTiedot(HttpServletRequest req, HttpServletResponse resp, Kayttaja kayttaja) {
        String tunnus = req.getParameter("username");
        String sahkoposti = req.getParameter("email");
        String salasana = req.getParameter("password");
        String salasana2 = req.getParameter("password2");

        StringBuilder virheviestit = new StringBuilder();
        if (tunnus == null || tunnus.equals("")) {
            virheviestit.append("Tunnus puuttuu! <br/>");
        } else {
            if (TkKayttaja.tunnusOlemassa(tunnus) && !kayttaja.getTunnus().equals(tunnus)) {
                virheviestit.append("Tunnus " + tunnus + " on varattu! <br/>");
            }
        }
        if (salasana != null && !salasana.equals("") && salasana2 != null
                && !salasana2.equals("") && !salasana.equals(salasana2)) {
            virheviestit.append("Salasanat eivät täsmää! <br/>");
        }
        if (virheviestit.length() > 0) {
            lisaaVirheViesti(req, virheviestit.toString());
            siirrySivulle("/arkisto/tietojenmuokkaus", req, resp);
        } else {
            kayttaja.setTunnus(tunnus);
            kayttaja.setSahkoposti(sahkoposti);
            TkKayttaja.paivitaKayttajaTiedot(kayttaja, salasana);
            siirrySivulle("/arkisto/kayttajantiedot", req, resp);
        }
    }
}
