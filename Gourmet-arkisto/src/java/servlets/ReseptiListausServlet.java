/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import models.Kayttaja;

/**
 *
 * @author Valeria
 */
public class ReseptiListausServlet extends YleisServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession();
        if (onKirjautunut(session, req, resp)) {
            Kayttaja kayttaja = (Kayttaja) session.getAttribute("kirjautunut");
            resp.setContentType("text/html;charset=UTF-8");
            req.setAttribute("kayttajatunnus", kayttaja.getTunnus());
            avaaSivu("/WEB-INF/jsp/reseptinakymat/reseptilistaus.jsp", req, resp);
        }
    }
}
