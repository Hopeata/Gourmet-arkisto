<%-- 
    Document   : kirjautuminen
    Created on : Nov 13, 2013, 11:24:12 AM
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
        <title>Gourmet-arkisto</title>
    </head>
    <body>
        <div class="jumbotron">
            <h1>Kirjaudu Gourmet-arkistoon</h1>
            <c:if test="${virheViesti != null}">
                <div class="alert alert-danger">${virheViesti}</div>
                <c:remove var="virheViesti" scope="session"/>
            </c:if>
            <form class="form-horizontal" role="form" action="" method="POST">
                <input name="action" type="hidden" id="action" value="login">
                <div class="form-group">
                    <label for="inputText" class="col-md-2 control-label">Tunnus</label>
                    <div class="col-md-10">
                        <input name="username" type="text" class="form-control" id="username" placeholder="Username">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword1" class="col-md-2 control-label">Salasana</label>
                    <div class="col-md-10">
                        <input name="password" type="password" class="form-control" id="inputPassword1" placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Muista kirjautuminen
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <button type="submit" class="btn btn-default">Kirjaudu sisään</button>
                        <a class="btn btn-default" href="${pageContext.request.contextPath}/kayttajanlisays" role="button">Luo käyttäjätili</a>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
