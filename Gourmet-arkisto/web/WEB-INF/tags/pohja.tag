<%-- 
    Document   : pohja
    Created on : Nov 13, 2013, 11:30:33 AM
    Author     : Valeria
--%>

<%@tag description="Generic template for Gourmet-arkisto pages" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%--@attribute name="Gourmet-arkisto"--%>
<!DOCTYPE html>
<html>
    <head>        
        <link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/bootstrap-theme.css" rel="stylesheet">
        <link href="${pageContext.request.contextPath}/css/main.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
    </head>
    <body>
        <div class="panel panel-default">
            <div class="panel-body">
                <img src="${pageContext.request.contextPath}/kuvat/ruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="${pageContext.request.contextPath}/kuvat/toinenruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="${pageContext.request.contextPath}/kuvat/kolmasruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="${pageContext.request.contextPath}/kuvat/kolmasruokakuva.jpg" alt="Ruokakuva"width="100" height="100">
                <img src="${pageContext.request.contextPath}/kuvat/neljasruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="${pageContext.request.contextPath}/kuvat/viidesruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="${pageContext.request.contextPath}/kuvat/kuudesruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="${pageContext.request.contextPath}/kuvat/tokaruokakuva.jpg" alt="Ruokakuva"width="100" height="100">
                <img src="${pageContext.request.contextPath}/kuvat/kahdeksasruoka.jpg" alt="Ruokakuva"width="150" height="100">
            </div>
        </div>
        <ul class="nav nav-tabs">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="${pageContext.request.contextPath}/kirjautuminen?action=logout"><span class="glyphicon glyphicon-log-out"></span> Kirjaudu ulos </a></li>
            </ul>
            <li><a href="${pageContext.request.contextPath}/arkisto/reseptilistaus">Pääsivu</a></li>
            <c:choose>
                <c:when test="${onVipOikeudet == true}">
                    <li><a href="${pageContext.request.contextPath}/arkisto/reseptinlisays">Lisää resepti</a></li>
                </c:when>
                <c:otherwise>
                    <li><a href="${pageContext.request.contextPath}/arkisto/reseptinlisays">Ehdota reseptiä</a></li>                           </c:otherwise>
            </c:choose>
            <li><a href="${pageContext.request.contextPath}/arkisto/kayttajantiedot">Omat tiedot</a></li>
            <li><a href="${pageContext.request.contextPath}/arkisto/kayttajalistaus">Käyttäjät</a></li>
            <form class="navbar-form navbar-left" action="${pageContext.request.contextPath}/arkisto/reseptilistaus" name="pikahakuform" id="pikahakuform" role="search">
                <input name="reseptinetsintaaction" type="hidden" id="reseptinetsintaaction" value="pikahaku">
                <div class="form-group">
                    <input type="text" name="pikahaku" class="form-control" id="pikahaku" value="${pikahakusana}" placeholder="Search">
                    <c:remove var="pikahakusana" scope="session"/>   
                </div>
                <button type="submit" name="pikahakubtn" id="pikahakubtn" class="btn btn-default"><span class="glyphicon glyphicon-search"></span> Hae</button>
            </form>
        </ul>
        <jsp:doBody/>
    </body>
</html>