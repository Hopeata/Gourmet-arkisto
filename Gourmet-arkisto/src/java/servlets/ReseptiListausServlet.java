/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.TkResepti;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Kayttaja;
import models.PaaraakaAine;
import models.Resepti;
import models.Ruokalaji;

/**
 *
 * @author Valeria
 */
public class ReseptiListausServlet extends YleisServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String reseptinetsintaaction = req.getParameter("reseptinetsintaaction");
        String perushakuaction = req.getParameter("perushakuaction");
        HttpSession session = req.getSession();
        Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");
        resp.setContentType("text/html;charset=UTF-8");
        req.setAttribute("kayttajatunnus", kayttaja.getTunnus());
        req.setAttribute("onAdmin", onAdminOikeudet(session));
        req.setAttribute("onVipOikeudet", onVipOikeudet(session));
        List<Ruokalaji> ruokalajit = (List<Ruokalaji>) session.getAttribute("ruokalajit");
        if (ruokalajit == null) {
            ruokalajit = TkResepti.haeRuokalajit();
        }
        lisaaSessioon(req, "ruokalajit", ruokalajit);
        List<PaaraakaAine> paaraakaAineet = (List<PaaraakaAine>) session.getAttribute("paaraakaAineet");
        if (paaraakaAineet == null) {
            paaraakaAineet = TkResepti.haePaaraakaAineet();
        }
        lisaaSessioon(req, "paaraakaAineet", paaraakaAineet);
        List<Resepti> reseptit = (List) session.getAttribute("reseptit");
        if (reseptit == null) {
            reseptit = new ArrayList<Resepti>();
        }
        boolean avaaSivu = false;
        if (reseptinetsintaaction != null) {
            String pikahakusana = req.getParameter("pikahaku");
            reseptit = TkResepti.haeReseptia(pikahakusana, null, null, false);
            lisaaSessioon(req, "pikahakusana", pikahakusana);
        } else if (perushakuaction != null) {
            String perushakusana = req.getParameter("perushaku");
            String[] ruokalajitulokset = req.getParameterValues("ruokalajiCheckbox");
            List<String> valitutRuokalajit = new ArrayList<String>();
            if (ruokalajitulokset != null) {
                valitutRuokalajit = Arrays.asList(ruokalajitulokset);
            }
            String[] paaraakaAinetulokset = req.getParameterValues("paaraakaAineCheckbox");
            List<String> valitutPaaraakaAineet = new ArrayList<String>();
            if (paaraakaAinetulokset != null) {
                valitutPaaraakaAineet = Arrays.asList(paaraakaAinetulokset);
            }
            reseptit = TkResepti.haeReseptia(perushakusana, ruokalajitulokset, paaraakaAinetulokset, false);
            lisaaSessioon(req, "perushakusana", perushakusana);
            for (Ruokalaji ruokalaji : ruokalajit) {
                if (valitutRuokalajit.contains("" + ruokalaji.getId())) {
                    ruokalaji.setChecked(true);
                }
            }
            for (PaaraakaAine paaraakaAine : paaraakaAineet) {
                if (valitutPaaraakaAineet.contains("" + paaraakaAine.getId())) {
                    paaraakaAine.setChecked(true);
                }
            }
        } else {
            if (session.getAttribute("reseptit") == null) {
                if (req.getParameter("ehdotukset") != null) {
                    reseptit = TkResepti.haeReseptit(true);
                    req.setAttribute("ehdotukset", true);
                } else {
                    reseptit = TkResepti.haeReseptit(false);
                }
            }
            avaaSivu = true;
        }
        lisaaSessioon(req, "reseptit", reseptit);
        if (avaaSivu) {
            avaaSivu("/WEB-INF/jsp/reseptinakymat/reseptilistaus.jsp", req, resp);
        } else {
            siirrySivulle("/arkisto/reseptilistaus", req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
