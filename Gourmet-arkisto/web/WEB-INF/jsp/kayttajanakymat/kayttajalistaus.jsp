<%-- 
    Document   : kayttajalistaus
    Created on : Nov 22, 2013, 3:18:47 PM
    Author     : Valeria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <t:pohja>
            <div class="panel-heading">Käyttäjät</div>
            <table class="table table-hover">
                <thead>
                    <tr>
                        <th>Tunnus</th>
                        <th>Sähköposti</th>
                        <th>Vip-oikeudet</th>
                        <th></th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="kayttaja" items="${kayttajat}">
                        <tr>
                            <td><c:out value="${kayttaja.tunnus}"/></td>
                            <td><c:out value="${kayttaja.sahkoposti}"/></td>
  <%--                          <td><c:out value="${kayttaja.vipoikeudet}"/></td>
                            <td></td>
  --%>                  </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </t:pohja>
</body>
</html>
