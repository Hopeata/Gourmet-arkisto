<%-- 
    Document   : kayttajantiedot
    Created on : Nov 23, 2013, 2:12:44 PM
    Author     : Valeria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <body>
        <t:pohja>
        <panel>
            <div class="panel-heading"><h2>Omat tiedot</h2></div>
            <form class="form-horizontal" role="form" action="" method="POST">
                <input name="action" type="hidden" id="action" value="muokkaus">
                <div class="form-group">
                    <label for="inputText" class="col-md-2 control-label">Käyttäjätunnus</label>
                    <div class="col-md-10">
                        <p>${tunnus}</p>
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputText" class="col-md-2 control-label">Sähköposti</label>
                    <div class="col-md-10">
                        <p>${sahkoposti}</p>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">                        
                        <input type="submit" name="muokkaus" value="Muokkaa tietojasi">
                        <input type="submit" name="poisto" value="Poista tilisi">
                    </div>
                </div>
            </form>
        </panel>
    </t:pohja>
</body>
</html>
