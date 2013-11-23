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
            <li class="active"><a href="#">Pääsivu</a></li>
            <%--          <c:choose>
                           <c:when test="${kayttaja.vipOikeudet}">
            --%>                 <li><a href="${pageContext.request.contextPath}/arkisto/reseptinlisays?action=lisays">Lisää resepti</a></li>
            <%--               </c:when>
                           <c:otherwise>
                               <li><a href="${pageContext.request.contextPath}/arkisto/reseptinlisays?action=ehdotus">Ehdota reseptiä</a></li>       
                           </c:otherwise>
                       </c:choose>
            --%>           <li><a href="tilinmuokkausnakyma.jsp">Omat tiedot</a></li>
            <li><a href="${pageContext.request.contextPath}/arkisto/kayttajalistaus">Käyttäjät</a></li>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span> Hae</button>
            </form>
        </ul>
        <jsp:doBody/>
    </body>
</html>