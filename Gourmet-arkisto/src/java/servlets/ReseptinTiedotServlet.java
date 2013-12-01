/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.TkResepti;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Kayttaja;
import models.Resepti;

/**
 *
 * @author Valeria
 */
public class ReseptinTiedotServlet extends YleisServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        if (action != null) {
            Resepti resepti = TkResepti.haeResepti(Integer.parseInt(action));
            if (req.getParameter("muokkaus") == null && req.getParameter("poisto") == null) {
                req.setAttribute("nimi", resepti.getPaanimi());
                if (resepti.getTekija() != null) {
                    req.setAttribute("tekija", resepti.getTekija().getTunnus());
                }
                if (resepti.getPaaraakaAine() != null) {
                    req.setAttribute("paaraakaAine", resepti.getPaaraakaAine().getPaaraakaAine());
                }
                req.setAttribute("lisaysAika", resepti.getLisaysaika());
                req.setAttribute("ohje", resepti.getOhje());
                HttpSession session = req.getSession();
                req.setAttribute("onAdmin", onAdminOikeudet(session));
                avaaSivu("/WEB-INF/jsp/reseptinakymat/reseptintiedot.jsp", req, resp);
            } else if (req.getParameter("poisto") != null) {
                TkResepti.poistaResepti(resepti.getId());
                siirrySivulle("/arkisto/reseptilistaus", req, resp);
            } else if (req.getParameter("muokkaus") != null) {
                lisaaSessioon(req, "action", "reseptinmuokkaus");
                lisaaSessioon(req, "resepti", resepti);
                siirrySivulle("/arkisto/reseptinlisays", req, resp);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
