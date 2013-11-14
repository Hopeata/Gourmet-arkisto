/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package servlets;

import exceptions.GourmetException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Valeria
 */
public class YleisServlet extends HttpServlet {

    public static void avaaSivu(String sivu, HttpServletRequest request, HttpServletResponse response) {
        RequestDispatcher dispatcher = request.getRequestDispatcher(sivu);
        try {
            dispatcher.forward(request, response);
        } catch (Exception ex) {
            Logger.getLogger(YleisServlet.class.getName()).log(Level.SEVERE, null, ex);
            throw new GourmetException("Sivun " + sivu + " avaaminen epäonnistui: " + ex.getMessage());
        }
    }
}
