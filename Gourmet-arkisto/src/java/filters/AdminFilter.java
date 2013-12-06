/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package filters;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import servlets.YleisServlet;

public class AdminFilter implements Filter {

    public void init(FilterConfig fc) throws ServletException {
    }

    public void doFilter(ServletRequest sr, ServletResponse sr1, FilterChain fc) throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) sr;
        HttpServletResponse response = (HttpServletResponse) sr1;

        HttpSession session = request.getSession(false);
        if (!YleisServlet.onAdminOikeudet(session)) {
            YleisServlet.lisaaVirheViesti(request, "Sinulla ei ole oikeuksia");
            response.sendRedirect(request.getContextPath() + "/arkisto/reseptilistaus");
        } else {
            fc.doFilter(request, response);
        }
    }

    public void destroy() {
    }
}
