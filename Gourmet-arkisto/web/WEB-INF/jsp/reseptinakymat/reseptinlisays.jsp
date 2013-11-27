<%-- 
    Document   : reseptinlisays
    Created on : Nov 19, 2013, 7:14:15 PM
    Author     : Valeria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <t:pohja>
            <div>
                <h1>Ehdota reseptiä!</h1>
                <c:if test="${virheViesti != null}">
                    <div class="alert alert-danger">${virheViesti}</div>
                    <c:remove var="virheViesti" scope="session"/>
                </c:if>
                <form class="form-horizontal" role="form" action="" method="POST">
                    <input name="action" type="hidden" id="action" value="reseptinlisays">
                    <div class="form-group">
                        <label for="inputText" class="col-sm-2 control-label">Reseptin nimi:* </label>
                        <div class="col-sm-10">
                            <input name="nimi" type="text" class="form-control" id="nimi" value="${nimi}">
                            <c:remove var="nimi" scope="session"/>                        
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputText" class="col-sm-2 control-label">Kuvan URL:  </label>
                        <div class="col-sm-10">
                            <input name="kuvaUrl" type="text" class="form-control" id="kuvaUrl" value="${kuvaUrl}">
                            <c:remove var="kuvaUrl" scope="session"/>                        
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="checkbox" class="col-sm-2 control-label">Ruokalaji:* </label>
                        <c:forEach var="ruokalaji" items="${ruokalajit}">                    
                            <label class="checkbox-inline">
                                <input type="checkbox" name="ruokalajiCheckbox" value="<c:out value="${ruokalaji.id}"/>"><c:out value="${ruokalaji.ruokalaji}"/>
                            </label>
                        </c:forEach>
                    </div>
                    <div class="form-group">
                        <label for="radio" class="col-sm-2 control-label">Pääraaka-aine: </label>
                        <label class="radio-inline">
                            <input type="radio" name="paaraakaAineRadio" id="eivalintaa" value="-1" checked>Ei valintaa
                        </label>
                        <c:forEach var="paaraakaAine" items="${paaraakaAineet}">                    
                            <label class="radio-inline">
                                <input type="radio" name="paaraakaAineRadio" id="<c:out value="${paaraakaAine.id}"/>" value="<c:out value="${paaraakaAine.id}"/>"><c:out value="${paaraakaAine.paaraakaAine}"/>
                            </label>
                        </c:forEach>
                    </div>
                    <div class="form-group">
                        <label for="inputText" class="col-sm-2 control-label">Reseptin kuvaus:* </label>
                        <div class="col-sm-10">
                            <textarea rows="6" name="ohje" class="form-control" id="ohje">${ohje}</textarea>
                            <c:remove var="ohje" scope="session"/>                        
                        </div>
                    </div>               
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Ehdota reseptiä!</button>
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <h5>* Tähdellä merkityt kentät pakollisia!</h5>
                </div>
            </div>
        </form>
    </div>
</t:pohja>
</body>
</html>
