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
        String viplisaysaction = req.getParameter("viplisaysaction");
        String vippoistoaction = req.getParameter("vippoistoaction");
        String kayttajapoistoaction = req.getParameter("kayttajapoistoaction");
        List<Kayttaja> kayttajat = TkKayttaja.haeKayttajat();
        req.setAttribute("kayttajat", kayttajat);
        for (Kayttaja kayttaja : kayttajat) {
            req.setAttribute("kayttaja.tunnus", kayttaja.getTunnus());
            req.setAttribute("kayttaja.sahkoposti", kayttaja.getSahkoposti());
            req.setAttribute("kayttaja.vipoikeudet", kayttaja.isVipOikeudet());
            req.setAttribute("kayttaja.id", kayttaja.getId());
        }
        if (viplisaysaction == null && vippoistoaction == null && kayttajapoistoaction == null) {
            avaaSivu("/WEB-INF/jsp/kayttajanakymat/kayttajalistaus.jsp", req, resp);
        }
        if (viplisaysaction != null) {
            TkKayttaja.paivitaVipOikeudet(Integer.parseInt(viplisaysaction), false);
            siirrySivulle("/arkisto/kayttajalistaus", req, resp);
        }
        if (vippoistoaction != null) {
            TkKayttaja.paivitaVipOikeudet(Integer.parseInt(vippoistoaction), true);
            siirrySivulle("/arkisto/kayttajalistaus", req, resp);
        }
        if (kayttajapoistoaction != null) {
            TkKayttaja.poistaKayttaja(Integer.parseInt(kayttajapoistoaction));
            siirrySivulle("/arkisto/kayttajalistaus", req, resp);
        }

    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
