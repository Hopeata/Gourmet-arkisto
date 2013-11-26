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
                    <div class="form-group">
                        <label for="checkbox" class="col-sm-2 control-label">Ruokalajit </label>
                        <c:forEach var="ruokalaji" items="${ruokalajit}">                    
                            <label class="checkbox-inline">
                                <input type="checkbox" id="ruokalajiCheckbox" value="option1"><c:out value="${ruokalaji.ruokalaji}"/>
                            </label>
                        </c:forEach>
                    </div>
                    <div class="form-group">
                        <label for="checkbox" class="col-sm-2 control-label">Pääraaka-aineet </label>
                        <c:forEach var="paaraakaAine" items="${paaraakaAineet}">                    
                            <label class="checkbox-inline">
                                <input type="checkbox" id="paaraakaAineCheckbox" value="option1"><c:out value="${paaraakaAine.paaraakaAine}"/>
                            </label>
                        </c:forEach>
                    </div>
                </div>
                <br>
                <br>
                <div class="panel-heading">Reseptit</div>
                <!-- Table -->
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Nimi</th>
                            <th>Ruokalaji</th>
                            <th>Pääraaka-aine</th>
                            <th>Lisätty</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%--                     <c:forEach var="resepti" items="${reseptit}">
                            <tr>
                                <td><c:out value="${resepti.tunnus}"/></td>
                                <td><c:out value="${resepti.ruokalaji}"/></td>
                                <td><c:out value="${resepti.paaruoka-aine}"/></td>
                                <td><c:out value="${resepti.tunnus}"/></td>
                            </tr>
                        </c:forEach>
                        --%>               </tbody>
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
