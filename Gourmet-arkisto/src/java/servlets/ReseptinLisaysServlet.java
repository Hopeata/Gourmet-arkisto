/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import database.TkResepti;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Kayttaja;
import models.PaaraakaAine;
import models.Resepti;
import models.ReseptinNimi;
import models.Ruokalaji;

/**
 *
 * @author Valeria
 */
public class ReseptinLisaysServlet extends YleisServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String action = req.getParameter("action");
        HttpSession session = req.getSession();
        req.setAttribute("onVipOikeudet", onVipOikeudet(session));
        Resepti resepti = (Resepti) session.getAttribute("resepti");
        if (resepti != null) {
            lisaaSessioon(req, "muokattavaResepti", resepti);
        }
        List<Ruokalaji> ruokalajit = TkResepti.haeRuokalajit();
        req.setAttribute("ruokalajit", ruokalajit);
        List<PaaraakaAine> paaraakaAineet = TkResepti.haePaaraakaAineet();
        req.setAttribute("paaraakaAineet", paaraakaAineet);
        if (action == null) {
            avaaSivu("/WEB-INF/jsp/reseptinakymat/reseptinlisays.jsp", req, resp);
        } else if (action.equals("reseptinlisays")) {
            lisaaResepti(req, resp);
        } else if (action.equals("reseptinmuokkaus")) {
            Resepti muokattavaResepti = (Resepti) session.getAttribute("muokattavaResepti");
            muokkaaReseptia(req, resp, muokattavaResepti);
            session.removeAttribute("muokattavaResepti");
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

        int paaraakaAineId = Integer.parseInt(paaraakaAine);
        PaaraakaAine paaraakaAineOlio = null;
        List<PaaraakaAine> paaraakaAineet = TkResepti.haePaaraakaAineet();
        for (PaaraakaAine paaraakaAine1 : paaraakaAineet) {
            if (paaraakaAine1.getId() == paaraakaAineId) {
                paaraakaAineOlio = paaraakaAine1;
            }
        }
        List<Ruokalaji> ruokalajiOliot = TkResepti.haeRuokalajit();
        List<Ruokalaji> valitutRuokalajit = new ArrayList<Ruokalaji>();
        //       List<Integer> ruokalajiIdt = new ArrayList<Integer>();
        for (String ruokalajiId : ruokalajit) {
            for (Ruokalaji ruokalajiOlio : ruokalajiOliot) {
                if (ruokalajiOlio.getId() == Integer.parseInt(ruokalajiId)) {
                    valitutRuokalajit.add(ruokalajiOlio);
                }
            }

        }
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
            List<ReseptinNimi> nimet = new ArrayList<ReseptinNimi>();
            nimet.add(new ReseptinNimi(-1, nimi, true));
            Resepti resepti = new Resepti(-1, null, ohje, kuvaUrl, null, paaraakaAineOlio, nimet, valitutRuokalajit, true);
            lisaaVirheViesti(req, virheviestit.toString());
            lisaaSessioon(req, "resepti", resepti);
            siirrySivulle("/arkisto/reseptinlisays", req, resp);
        } else {
            HttpSession session = req.getSession();
            Kayttaja tekija = (Kayttaja) session.getAttribute("kirjautunut");
            Resepti resepti;
            if (tekija.isVipOikeudet()) {
                resepti = new Resepti(-1, null, ohje, kuvaUrl, tekija, paaraakaAineOlio, null, valitutRuokalajit, false);
            } else {
                resepti = new Resepti(-1, null, ohje, kuvaUrl, tekija, paaraakaAineOlio, null, valitutRuokalajit, true);
            }
            TkResepti.lisaaResepti(resepti, nimi);
            siirrySivulle("/arkisto/reseptilistaus", req, resp);
        }
    }

    private void muokkaaReseptia(HttpServletRequest req, HttpServletResponse resp, Resepti resepti) {
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
            List<ReseptinNimi> nimet = new ArrayList<ReseptinNimi>();
            nimet.add(new ReseptinNimi(resepti.getId(), nimi, true));
            Resepti virheResepti = new Resepti(resepti.getId(), null, ohje, kuvaUrl, resepti.getTekija(), null, nimet, null, resepti.isEhdotus());
            lisaaVirheViesti(req, virheviestit.toString());
            lisaaSessioon(req, "action", "reseptinmuokkaus");
            lisaaSessioon(req, "resepti", virheResepti);
            siirrySivulle("/arkisto/reseptinlisays", req, resp);
        } else {
            int paaraakaAineId = Integer.parseInt(paaraakaAine);
            PaaraakaAine paaraakaAineOlio = null;
            List<PaaraakaAine> paaraakaAineet = TkResepti.haePaaraakaAineet();
            for (PaaraakaAine paaraakaAine1 : paaraakaAineet) {
                if (paaraakaAine1.getId() == paaraakaAineId) {
                    paaraakaAineOlio = paaraakaAine1;
                }
            }
            Resepti muokkausresepti = new Resepti(resepti.getId(), null, ohje, kuvaUrl, resepti.getTekija(), paaraakaAineOlio, null, null, resepti.isEhdotus());
            List<Integer> ruokalajiIdt = new ArrayList<Integer>();
            for (String ruokalajiId : ruokalajit) {
                ruokalajiIdt.add(Integer.parseInt(ruokalajiId));
            }
            TkResepti.muokkaaReseptia(muokkausresepti, ruokalajiIdt, nimi);
            siirrySivulle("/arkisto/reseptilistaus", req, resp);
        }
    }
}
