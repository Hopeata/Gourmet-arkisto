<%-- 
    Document   : reseptintiedot
    Created on : Nov 28, 2013, 1:07:49 PM
    Author     : Valeria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <t:pohja>
            <h1>${nimi}</h1>
            <c:forEach var="reseptinNimi" items="${resepti.nimet}">
                <c:if test="${reseptinNimi.onPaanimi == false}"> 
                    <div>
                        ${reseptinNimi.nimi}
                        <a href="${pageContext.request.contextPath}/arkisto/reseptintiedot?action=${resepti.id}&nimi=${reseptinNimi.nimi}&nimenpoistoaction='nimenpoisto'">&times;</a>
                    </div>
                </c:if> 
            </c:forEach>
            <form class="form-inline" name="nimenlisaysform" id="nimenlisaysform">
                <input name="nimenlisaysaction" type="hidden" id="nimenlisaysaction" value="nimenlisays">
                <input name="action" type="hidden" id="action" value="${resepti.id}">
                <div class="form-group">
                    <input type="text" name="nimenlisays" class="form-control" id="nimenlisays" placeholder="Add">
                </div>
                <button type="submit" name="nimenlisaysbtn" id="nimenlisaysbtn" class="btn btn-default">Lisää</button>
            </form>
            <br/>
            <br/>
            <div class="row">
                <div class="col-md-7">
                    <div class="panel panel-default">
                        <div class="panel-heading">Kuvaus</div>
                        <div class="panel-body">
                            ${ohje}
                        </div>
                    </div>
                </div>
                <div class="col-md-4">
                    <div class="panel panel-default">
                        <div class="panel-heading">Reseptin tiedot</div>                        
                        <div class="panel-body">
                            <c:if test="${tekija != null}">
                                <div class="col-md-3">Tekija: ${tekija}</div>
                            </c:if>
                            <div class="col-md-3">Lisätty: ${lisaysAika}</div>
                            <c:if test="${paaraakaAine != null}">
                                <div class="col-md-3">Pääraaka-aine: ${paaraakaAine}</div>
                            </c:if>
                            <div class="col-md-3">Ruokalaji: ${resepti.valittujenRuokalajienNimet}</div> 
                        </div>
                    </div>
                </div>
            </div>
            <c:if test="${onAdmin == true}">
                <form class="form" role="form" action="" method="POST">
                    <div class="form-group">
                        <div class="col-sm-10">
                            <c:if test="${ehdotus == true}">
                                <input type="submit" name="lisays" value="Lisää resepti"> 
                            </c:if> 
                            <input type="submit" name="muokkaus" value="Muokkaa reseptiä"> 
                            <input type="submit" name="poisto" value="Poista resepti">
                        </div>                
                    </div>
                </form>
            </c:if>
        </t:pohja>
    </body>
</html>
