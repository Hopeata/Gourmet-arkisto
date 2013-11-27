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
import javax.servlet.http.HttpSession;
import models.Kayttaja;
import models.PaaraakaAine;
import models.Ruokalaji;
import models.Resepti;

/**
 *
 * @author Valeria
 */
public class ReseptiListausServlet extends YleisServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");
        resp.setContentType("text/html;charset=UTF-8");
        req.setAttribute("kayttajatunnus", kayttaja.getTunnus());
        List<Ruokalaji> ruokalajit = TkResepti.haeRuokalajit();
        req.setAttribute("ruokalajit", ruokalajit);
        for (Ruokalaji ruokalaji : ruokalajit) {
            req.setAttribute("ruokalaji.ruokalaji", ruokalaji.getRuokalaji());
        }
        List<PaaraakaAine> paaraakaAineet = TkResepti.haePaaraakaAineet();
        req.setAttribute("paaraakaAineet", paaraakaAineet);
        for (PaaraakaAine paaraakaAine : paaraakaAineet) {
            req.setAttribute("paaraakaAine.paaraakaAine", paaraakaAine.getPaaraakaAine());
        }
        List<Resepti> reseptit = TkResepti.haeReseptit();
        req.setAttribute("reseptit", reseptit);
        for (Resepti resepti : reseptit) {
            req.setAttribute("resepti.paanimi", resepti.getPaanimi());
            //           StringBuilder ruokalajiNimet = new StringBuilder();
            //           List<Ruokalaji> ruokaljilista = resepti.getRuokalajit();
            //           for (Ruokalaji ruokalaji : ruokaljilista) {
            //               ruokalajiNimet.append(ruokalaji.getRuokalaji());
            //           }
            req.setAttribute("resepti.ruokalajit", resepti.getRuokalajit());
            List<Ruokalaji> reseptinRuokalajit = resepti.getRuokalajit();
            for (Ruokalaji ruokalaji : reseptinRuokalajit) {
                req.setAttribute("ruokalaji.ruokalaji", ruokalaji.getRuokalaji());
            }
            req.setAttribute("resepti.paaraakaAineNimi", resepti.getPaaraakaAineNimi());
            req.setAttribute("resepti.lisaysaika", resepti.getLisaysaika());
        }
        avaaSivu("/WEB-INF/jsp/reseptinakymat/reseptilistaus.jsp", req, resp);
    }
}
