/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.TkResepti;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import models.PaaraakaAine;
import models.Ruokalaji;

/**
 *
 * @author Valeria
 */
public class ReseptinLisaysServlet extends YleisServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        List<Ruokalaji> ruokalajit = TkResepti.haeRuokalajit();
        req.setAttribute("ruokalajit", ruokalajit);
        for (Ruokalaji ruokalaji : ruokalajit) {
            req.setAttribute("ruokalaji.id", ruokalaji.getId());
            req.setAttribute("ruokalaji.ruokalaji", ruokalaji.getRuokalaji());
        }
        List<PaaraakaAine> paaraakaAineet = TkResepti.haePaaraakaAineet();
        req.setAttribute("paaraakaAineet", paaraakaAineet);
        for (PaaraakaAine paaraakaAine : paaraakaAineet) {
            req.setAttribute("paaraakaAine.id", paaraakaAine.getId());
            req.setAttribute("paaraakaAine.paaraakaAine", paaraakaAine.getPaaraakaAine());
        }
        if (action == null) {
            avaaSivu("/WEB-INF/jsp/reseptinakymat/reseptinlisays.jsp", req, resp);
        } else if (action.equals("reseptinlisays")) {
            lisaaResepti(req, resp);
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }

    private void lisaaResepti(HttpServletRequest req, HttpServletResponse resp) {
        String nimi = req.getParameter("nimi");
        String kuvaUrl = req.getParameter("kuvaUrl");
        String[] ruokalajit = req.getParameterValues("ruokalajiCheckbox");
        String paaraakaAine = req.getParameter("paaraakaAineRadio");
        String ohje = req.getParameter("ohje");

        StringBuilder virheviestit = new StringBuilder();
        if (nimi == null || nimi.equals("")) {
            virheviestit.append("Reseptin nimi puuttuu! <br/>");
        }
        if (ruokalajit == null) {
            virheviestit.append("Reseptin ruokalaji puuttuu! <br/>");
        }
        if (ohje == null || ohje.equals("")) {
            virheviestit.append("Reseptin kuvaus puuttuu! <br/>");
        }
        if (virheviestit.length() > 0) {
            lisaaVirheViesti(req, virheviestit.toString());
            lisaaSessioon(req, "nimi", nimi);
            lisaaSessioon(req, "kuvaUrl", kuvaUrl);
            lisaaSessioon(req, "ohje", ohje);
        }
        siirrySivulle("/arkisto/reseptinlisays", req, resp);
    }
}
