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
import models.Kayttaja;

/**
 *
 * @author Valeria
 */
public class KayttajanLisaysServlet extends YleisServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action == null) {
            avaaSivu("/WEB-INF/jsp/kayttajanakymat/kayttajanlisays.jsp", req, resp);
        } else if (action.equals("kayttajanlisays")) {
            lisaaKayttaja(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void lisaaKayttaja(HttpServletRequest req, HttpServletResponse resp) {
        String tunnus = req.getParameter("username");
        String sahkoposti = req.getParameter("email");
        String salasana = req.getParameter("password");
        String salasana2 = req.getParameter("password2");

        StringBuilder virheviestit = new StringBuilder();
        if (tunnus == null || tunnus.equals("")) {
            virheviestit.append("Tunnus puuttuu! <br/>");
        } else {
            if (TkKayttaja.tunnusOlemassa(tunnus)) {
                virheviestit.append("Tunnus " + tunnus + " on varattu! <br/>");
            }
        }
        if (salasana == null || salasana.equals("")) {
            virheviestit.append("Salasana puuttuu! <br/>");
        }
        if (salasana2 == null || salasana2.equals("")) {
            virheviestit.append("Salasanan validointi puuttuu! <br/>");
        }
        if (salasana != null && !salasana.equals("") && salasana2 != null
                && !salasana2.equals("") && !salasana.equals(salasana2)) {
            virheviestit.append("Salasanat eivät täsmää! <br/>");
        }
        if (virheviestit.length() > 0) {
            lisaaVirheViesti(req, virheviestit.toString());
            lisaaSessioon(req, "tunnus", tunnus);
            lisaaSessioon(req, "sahkoposti", sahkoposti);
            siirrySivulle("/kayttajanlisays", req, resp);
        }

        Kayttaja uusiKayttaja = new Kayttaja(-1, tunnus, sahkoposti, false, false);
        TkKayttaja.lisaaKayttaja(uusiKayttaja, salasana);
        lisaaVirheViesti(req, "Käyttäjätilisi on aktivoitu!");
        siirrySivulle("/kirjautuminen", req, resp);
    }
}
