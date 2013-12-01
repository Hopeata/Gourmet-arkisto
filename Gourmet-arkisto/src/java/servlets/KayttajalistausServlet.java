/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.TkKayttaja;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.Kayttaja;

/**
 *
 * @author Valeria
 */
public class KayttajalistausServlet extends YleisServlet {
    
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String viplisaysaction = req.getParameter("viplisaysaction");
        String vippoistoaction = req.getParameter("vippoistoaction");
        String etsintaaction = req.getParameter("etsintaaction");
        String kayttajapoistoaction = req.getParameter("kayttajapoistoaction");
        List<Kayttaja> kayttajat = null;
        if (etsintaaction != null) {
            String hakusana = req.getParameter("haku");
            kayttajat = TkKayttaja.haeKayttajaa(hakusana);
            lisaaSessioon(req, "hakusana", hakusana);
        } else {
            kayttajat = TkKayttaja.haeKayttajat();
        }
        if (!kayttajat.isEmpty()) {
            req.setAttribute("kayttajat", kayttajat);
        }
        for (Kayttaja kayttaja : kayttajat) {
            req.setAttribute("kayttaja.tunnus", kayttaja.getTunnus());
            req.setAttribute("kayttaja.sahkoposti", kayttaja.getSahkoposti());
            req.setAttribute("kayttaja.vipoikeudet", kayttaja.isVipOikeudet());
            req.setAttribute("kayttaja.id", kayttaja.getId());
        }
        if (viplisaysaction == null && vippoistoaction == null && kayttajapoistoaction == null) {
            avaaSivu("/WEB-INF/jsp/kayttajanakymat/kayttajalistaus.jsp", req, resp);
        } else if (viplisaysaction != null) {
            TkKayttaja.paivitaVipOikeudet(Integer.parseInt(viplisaysaction), false);
            siirrySivulle("/arkisto/kayttajalistaus", req, resp);
        } else if (vippoistoaction != null) {
            TkKayttaja.paivitaVipOikeudet(Integer.parseInt(vippoistoaction), true);
            siirrySivulle("/arkisto/kayttajalistaus", req, resp);
        } else if (kayttajapoistoaction != null) {
            TkKayttaja.poistaKayttaja(Integer.parseInt(kayttajapoistoaction));
            siirrySivulle("/arkisto/kayttajalistaus", req, resp);
        }
        
        
    }
    
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
