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
                        <th>Käyttäjän poisto</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="kayttaja" items="${kayttajat}">
                        <tr>
                            <td><c:out value="${kayttaja.tunnus}"/></td>
                            <td><c:out value="${kayttaja.sahkoposti}"/></td>
                            <c:choose>
                                <c:when test="${kayttaja.vipOikeudet}">
                                    <td><form name="frm" method="post" action="">
                                            <input name="vippoistoaction" type="hidden" id="vippoistoaction" value="${kayttaja.id}">
                                            <button type="submit" class="btn btn-default">Poista oikeudet</button>                                         </form></td>                                
                                        </c:when>
                                        <c:otherwise>
                                    <td><form name="frm" method="post" action="">
                                            <input name="viplisaysaction" type="hidden" id="viplisaysaction" value="${kayttaja.id}">
                                            <button type="submit" class="btn btn-default">Anna oikeudet</button>                                         </form></td>
                                        </c:otherwise>
                                    </c:choose>
<td><form name="frm" method="post" action="">
                                            <input name="kayttajapoistoaction" type="hidden" id="kayttajapoistoaction" value="${kayttaja.id}">
                                            <button type="submit" class="btn btn-default">Poista käyttäjä</button>                                         </form></td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </t:pohja>
</body>
</html>
