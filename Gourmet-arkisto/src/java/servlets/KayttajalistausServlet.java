/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.TkKayttaja;
import models.Kayttaja;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Valeria
 */
public class KayttajalistausServlet extends YleisServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        List<Kayttaja> kayttajat = TkKayttaja.haeKayttajat();
        req.setAttribute("kayttajat", kayttajat);
        for (Kayttaja kayttaja : kayttajat) {
            req.setAttribute("kayttaja.tunnus", kayttaja.getTunnus());
            req.setAttribute("kayttaja.sahkoposti", kayttaja.getSahkoposti());
            if (kayttaja.isVipOikeudet()) {
                req.setAttribute("kayttaja.vipoikeudet", "On oikeudet");
            } else {
                req.setAttribute("kayttaja.vipoikeudet", "Anna oikeudet");
            }
        }
        if (action.equals("kayttajat")) {
            avaaSivu("/WEB-INF/jsp/kayttajanakymat/kayttajalistaus.jsp", req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
