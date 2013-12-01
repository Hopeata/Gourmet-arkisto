<%-- 
    Document   : kayttajanlisays
    Created on : Nov 21, 2013, 10:27:11 PM
    Author     : Valeria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <link href="css/bootstrap.css" rel="stylesheet">
        <link href="css/bootstrap-theme.css" rel="stylesheet">
        <link href="css/main.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Gourmet-arkisto</title>
    </head>
    <body>
        <div class="container">
            <h1>Luo käyttäjätili</h1>
            <c:if test="${virheViesti != null}">
                <div class="alert alert-danger">${virheViesti}</div>
                <c:remove var="virheViesti" scope="session"/>
            </c:if>
            <form class="form-horizontal" role="form" action="" method="POST">
                <input name="action" type="hidden" id="action" value="kayttajanlisays">
                <div class="form-group">
                    <label for="inputText" class="col-sm-2 control-label">Käyttäjätunnus* </label>
                    <div class="col-sm-5">
                        <input name="username" type="text" class="form-control" id="username" value="${tunnus}" placeholder="Username">
                        <c:remove var="tunnus" scope="session"/>                        
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputEmail" class="col-sm-2 control-label">Sähköposti </label>
                    <div class="col-sm-5">
                        <input name="email" type="email" class="form-control" id="email" value="${sahkoposti}"placeholder="Email">
                        <c:remove var="sahkoposti" scope="session"/>                        
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">Salasana* </label>
                    <div class="col-sm-5">
                        <input name="password" type="password" class="form-control" id="password" placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword3" class="col-sm-2 control-label">Salasana uudelleen* </label>
                    <div class="col-sm-5">
                        <input name="password2" type="password" class="form-control" id="password2" placeholder="Password">
                    </div>
                </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Luo käyttäjätili</button>
                <a class="btn btn-default" href="${pageContext.request.contextPath}/kirjautuminen" role="button">Peruuta</a>
            </div>
        </div>
    </form>
</div>
</body>
</html>
