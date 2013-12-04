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
            <%--            <div class="row">
                            <c:if test="${tekija != null}">
                                <div class="col-md-2">Tekija: ${tekija}</div>                
                            </c:if>
                            <div class="col-md-2">Lisätty: ${lisaysAika}</div>
                            <c:if test="${paaraakaAine != null}">
                                <div class="col-md-2">Pääraaka-aine: ${paaraakaAine}</div>
                            </c:if>
                            <div class="col-md-2">Ruokalaji: </div> 
                        </div>
            --%>         <div class="row">
                <div class="col-md-7">
                    <div class="panel panel-default">
                        <div class="panel-heading">Kuvaus</div>
                        <div class="panel-body">
                            ${ohje}
                        </div>
                    </div>
                </div>
                <div class="col-md-3">
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
                            <div class="col-md-3">Ruokalaji: ${ruokalajit}</div> 
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
