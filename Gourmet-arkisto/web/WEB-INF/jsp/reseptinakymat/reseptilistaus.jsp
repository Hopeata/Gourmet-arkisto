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
            <div class="panel panel-default">
                <!-- Default panel contents -->
                <div class="panel-heading">Reseptit</div>

                <!-- Table -->
                <table class="table table-hover">
                    <thead>
                        <tr>
                            <th>Nimi</th>
                            <th>Ruokalaji</th>
                            <th>Pääruoka-aine</th>
                            <th>Lisätty</th>
                        </tr>
                    </thead>
                    <tbody>
                        <tr>
                            <td><a href="reseptinakyma.jsp">Banaanikana intialaisittain</a></td>
                            <td>Pääruoka</td>
                            <td>Kana</td>
                            <td>16.08.13</td>
                        </tr>
                        <tr>
                            <td>Taivaallinen mangosorbetti</td>
                            <td>Jälkiruoka</td>
                            <td>Mango</td>
                            <td>17.07.13</td>
                        </tr>
                        <tr>
                            <td>Täydelliset mozzarellaleivät</td>
                            <td>Välipala</td>
                            <td>Juusto</td>
                            <td>16.07.13</td>
                        </tr>
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
