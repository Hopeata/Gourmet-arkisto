<%-- 
    Document   : reseptilistaus
    Created on : Nov 15, 2013, 3:41:12 PM
    Author     : Valeria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <t:pohja>
            <h1>Tervetuloa, ${kayttajatunnus}!</h1>
            <br>
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">
                    <form class="form-horizontal" name="perushakuform" id="perushakuform" role="form" action="" method="POST">
                        <input name="perushakuaction" type="hidden" id="perushakuaction" value="perushaku">
                        <div class="form-group">
                            <label for="checkbox" class="col-sm-2 control-label">Hakusana </label>
                            <div class="col-sm-2">
                                <input type="text" name="perushaku" class="form-control" id="perushaku" value="${perushakusana}" placeholder="Search">
                                <c:remove var="perushakusana" scope="session"/>   
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="checkbox" class="col-sm-2 control-label">Ruokalajit </label>
                            <div class="col-sm-5">
                                <c:forEach var="ruokalaji" items="${ruokalajit}">                    
                                    <label class="checkbox-inline">
                                        <input name="ruokalajiCheckbox" type="checkbox" id="ruokalajiCheckbox" value="<c:out value="${ruokalaji.id}"/>"><c:out value="${ruokalaji.ruokalaji}"/>
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="form-group">
                            <label for="checkbox" class="col-sm-2 control-label">Pääraaka-aineet </label>
                            <div class="col-sm-5">
                                <c:forEach var="paaraakaAine" items="${paaraakaAineet}">                    
                                    <label class="checkbox-inline">
                                        <input name="paaraakaAineCheckbox" type="checkbox" id="paaraakaAineCheckbox" value="<c:out value="${paaraakaAine.id}"/>"><c:out value="${paaraakaAine.paaraakaAine}"/>
                                    </label>
                                </c:forEach>
                            </div>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-offset-2 col-sm-10">                        
                                <button type="submit" name="perushakubtn" id="perushakubtn" class="btn btn-default" onclick="document.getElementById('pikahaku').value = '';"><span class="glyphicon glyphicon-search"></span> Hae</button>
                            </div>
                        </div>
                    </form>
                </div>
                <br>
                <br>
                <div class="panel-heading">Reseptit</div>
                <!-- Table -->
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th><a>Nimi</a></th>
                            <th>Ruokalaji</th>
                            <th>Pääraaka-aine</th>
                            <th>Lisätty</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach var="resepti" items="${reseptit}">
                            <tr>
                                <td><a href="${pageContext.request.contextPath}/arkisto/reseptintiedot?action=<c:out value="${resepti.id}"/>"><c:out value="${resepti.paanimi}"/></a></td>
                                <td>
                                    <c:forEach var="ruokalaji" items="${resepti.ruokalajit}">
                                        <c:out value="${ruokalaji.ruokalaji}"/>
                                    </c:forEach>
                                </td>
                                <td><c:out value="${resepti.paaraakaAineNimi}"/></td>
                                <td><c:out value="${resepti.lisaysaika}"/></td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
            <ul class="pagination">
                <li><a href="#">&laquo;</a></li>
                <li><a href="#">1</a></li>
                <li><a href="#">2</a></li>
                <li><a href="#">3</a></li>
                <li><a href="#">4</a></li>
                <li><a href="#">5</a></li>
                <li><a href="#">&raquo;</a></li>
            </ul>
        </t:pohja>
    </body>
</html>
