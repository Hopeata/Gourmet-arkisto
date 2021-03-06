<%-- 
    Document   : reseptinlisays
    Created on : Nov 19, 2013, 7:14:15 PM
    Author     : Valeria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>

<!DOCTYPE html>
<html>
    <body>
        <t:pohja>
            <div>
                <c:choose>
                    <c:when test="${onVipOikeudet == true}">
                        <h1>Lisää resepti!</h1>
                    </c:when>
                    <c:otherwise>
                        <h1>Ehdota reseptiä!</h1>
                    </c:otherwise>
                </c:choose>
                <form class="form-horizontal" role="form" action="" method="POST">
                    <c:choose>
                        <c:when test="${action == 'reseptinmuokkaus'}">
                            <input name="action" type="hidden" id="action" value="reseptinmuokkaus">
                        </c:when>
                        <c:otherwise>
                            <input name="action" type="hidden" id="action" value="reseptinlisays">
                        </c:otherwise>
                    </c:choose>
                    <c:remove var="action" scope="session"/>
                    <div class="form-group">
                        <label for="inputText" class="col-sm-2 control-label">Reseptin nimi:* </label>
                        <div class="col-sm-5">
                            <input name="nimi" type="text" class="form-control" id="nimi" value="${resepti.paanimi}">
                            <c:remove var="nimi" scope="session"/>                        
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputText" class="col-sm-2 control-label">Kuvan URL:  </label>
                        <div class="col-sm-5">
                            <input name="kuvaUrl" type="text" class="form-control" id="kuvaUrl" value="${resepti.kuvaUrl}">
                            <c:remove var="kuvaUrl" scope="session"/>                        
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="checkbox" class="col-sm-2 control-label">Ruokalaji:* </label>
                        <c:forEach var="ruokalaji" items="${ruokalajit}">                    
                            <label class="checkbox-inline">
                                <input type="checkbox" name="ruokalajiCheckbox" <c:if test="${fn:contains(resepti.valitutRuokalajit, ruokalaji.vertailuId)}"> checked </c:if>
                                       value="<c:out value="${ruokalaji.id}"/>"><c:out value="${ruokalaji.ruokalaji}"/>
                            </label>
                        </c:forEach>                        
                    </div>
                    <div class="form-group">
                        <label for="radio" class="col-sm-2 control-label">Pääraaka-aine: </label>
                        <label class="radio-inline">
                            <input type="radio" name="paaraakaAineRadio" id="eivalintaa" value="-1" <c:if test="${resepti == null || resepti.paaraakaAine == null}"> checked </c:if> >Ei valintaa
                            </label>
                        <c:forEach var="paaraakaAine" items="${paaraakaAineet}">                    
                            <label class="radio-inline">
                                <input type="radio" name="paaraakaAineRadio" id="<c:out value="${paaraakaAine.id}"/>" value="<c:out value="${paaraakaAine.id}"/>" <c:if test="${resepti != null && resepti.paaraakaAine != null && resepti.paaraakaAine.id == paaraakaAine.id}"> checked </c:if>><c:out value="${paaraakaAine.paaraakaAine}"/>
                                </label>
                        </c:forEach>
                    </div>
                    <div class="form-group">
                        <label for="inputText" class="col-sm-2 control-label">Reseptin kuvaus:* </label>
                        <div class="col-sm-8">
                            <textarea rows="6" name="ohje" class="form-control" id="ohje">${resepti.ohje}</textarea>
                            <c:remove var="ohje" scope="session"/>                        
                        </div>
                    </div>           
                    <c:remove var="resepti" scope="session"/>
            </div>
            <c:choose>
                <c:when test="${onVipOikeudet == true}">
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Lisää resepti!</button>
                        </div>
                    </div>               
                </c:when>
                <c:otherwise>
                    <div class="form-group">
                        <div class="col-sm-offset-2 col-sm-10">
                            <button type="submit" class="btn btn-default">Ehdota reseptiä!</button>
                        </div>
                    </div>                
                </c:otherwise>
            </c:choose>

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
