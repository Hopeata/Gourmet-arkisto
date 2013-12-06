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
            if (req.getParameter("nimenlisaysaction") != null) {
                String nimenlisays = req.getParameter("nimenlisays");
                TkResepti.lisaaReseptinNimi(Integer.parseInt(action), nimenlisays);
                siirrySivulle("/arkisto/reseptintiedot?action=" + resepti.getId() + "", req, resp);
            } else if (req.getParameter("nimenpoistoaction") != null) {
                String nimi = req.getParameter("nimi");
                TkResepti.poistaReseptinNimi(Integer.parseInt(action), nimi);
                siirrySivulle("/arkisto/reseptintiedot?action=" + resepti.getId() + "", req, resp);
            } else {
                if (req.getParameter("lisays") == null && req.getParameter("muokkaus") == null
                        && req.getParameter("poisto") == null) {
                    req.setAttribute("nimi", resepti.getPaanimi());
                    if (resepti.getTekija() != null) {
                        req.setAttribute("tekija", resepti.getTekija().getTunnus());
                    }
                    if (resepti.getPaaraakaAine() != null) {
                        req.setAttribute("paaraakaAine", resepti.getPaaraakaAine().getPaaraakaAine());
                    }
                    req.setAttribute("lisaysAika", resepti.getLisaysaikaFormatoitu());
                    req.setAttribute("ohje", resepti.getOhje().replaceAll("\n", "<br/>"));
                    HttpSession session = req.getSession();
                    req.setAttribute("onAdmin", onAdminOikeudet(session));
                    req.setAttribute("ehdotus", resepti.isEhdotus());
                    req.setAttribute("resepti", resepti);
                    avaaSivu("/WEB-INF/jsp/reseptinakymat/reseptintiedot.jsp", req, resp);
                } else if (req.getParameter("lisays") != null) {
                    TkResepti.pavitaEhdotusReseptiksi(resepti.getId());
                    siirrySivulle("/arkisto/reseptintiedot?action=" + resepti.getId() + "", req, resp);
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
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
