<%-- 
    Document   : kayttajatietojenmuokkaus
    Created on : Nov 24, 2013, 12:27:55 PM
    Author     : Valeria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
    <body>
        <t:pohja>
        <c:if test="${virheViesti != null}">
            <div class="alert alert-danger">${virheViesti}</div>
            <c:remove var="virheViesti" scope="session"/>
        </c:if>
        <form class="form-horizontal" role="form" action="" method="POST">
            <input name="action" type="hidden" id="action" value="muokkaus">
            <div class="form-group">
                <label for="inputText" class="col-sm-2 control-label">Käyttäjätunnus </label>
                <div class="col-sm-10">
                    <input name="username" type="text" class="form-control" id="username" value="${tunnus}" placeholder="Username">
                </div>
            </div>
            <div class="form-group">
                <label for="inputEmail" class="col-sm-2 control-label">Sähköposti </label>
                <div class="col-sm-10">
                    <input name="email" type="email" class="form-control" id="email" value="${sahkoposti}"placeholder="Email">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">Salasana </label>
                <div class="col-sm-10">
                    <input name="password" type="password" class="form-control" id="password" placeholder="Password">
                </div>
            </div>
            <div class="form-group">
                <label for="inputPassword3" class="col-sm-2 control-label">Salasana uudelleen </label>
                <div class="col-sm-10">
                    <input name="password2" type="password" class="form-control" id="password2" placeholder="Password">
                </div>
            </div>
        </div>
        <div class="form-group">
            <div class="col-sm-offset-2 col-sm-10">
                <button type="submit" class="btn btn-default">Tallenna muutokset</button>
                <a class="btn btn-default" href="${pageContext.request.contextPath}/arkisto/kayttajantiedot" role="button">Peruuta</a>
            </div>
        </div>
    </form>
</t:pohja>
</body>
</html>
