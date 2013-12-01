/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import exceptions.GourmetException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Kayttaja;

/**
 *
 * @author Valeria
 */
public class YleisServlet extends HttpServlet {

    public static void avaaSivu(String osoite, HttpServletRequest request, HttpServletResponse response) {
        try {
            RequestDispatcher dispatcher = request.getRequestDispatcher(osoite);
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(YleisServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Sivun " + osoite + " avaaminen epäonnistui: " + ex.getMessage());
        }
    }

    public static void siirrySivulle(String osoite, HttpServletRequest request, HttpServletResponse response) {
        try {
            response.sendRedirect(request.getContextPath() + osoite);
        } catch (Exception ex) {
            Logger.getLogger(YleisServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Sivulle " + osoite + " siirtyminen epäonnistui: " + ex.getMessage());
        }
    }

    public static void lisaaVirheViesti(HttpServletRequest request, String viesti) {
        HttpSession session = request.getSession(false);
        session.setAttribute("virheViesti", viesti);
    }

    public static void lisaaSessioon(HttpServletRequest request, String avain, String arvo) {
        HttpSession session = request.getSession(false);
        session.setAttribute(avain, arvo);
    }

    public static void lisaaSessioon(HttpServletRequest request, String avain, Object arvo) {
        HttpSession session = request.getSession(false);
        session.setAttribute(avain, arvo);
    }    
    
    public static boolean onKirjautunut(HttpSession session, HttpServletRequest request, HttpServletResponse response) {
        if (session.getAttribute("kirjautunut") != null) {
            return true;
        } else {
            request.setAttribute("virheViesti", "Et ole kirjautunut");
            avaaSivu("/WEB-INF/jsp/kayttajanakymat/kirjautuminen.jsp", request, response);
            return false;
        }
    }

    public static boolean onAdminOikeudet(HttpSession session) {
        Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");
        return kayttaja.isAdminOikeudet();
    }

    public static boolean onVipOikeudet(HttpSession session) {
        Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");
        return kayttaja.isVipOikeudet();
    }
}
