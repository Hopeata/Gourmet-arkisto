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
                <form class="form-horizontal" role="form" action="paanakyma.jsp" method="POST">
                    <div class="form-group">
                        <label for="inputText" class="col-sm-2 control-label">Reseptin nimi:* </label>
                        <div class="col-sm-10">
                            <input name="nimi" type="text" class="form-control" id="nimi">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputText" class="col-sm-2 control-label">Kuvan URL:  </label>
                        <div class="col-sm-10">
                            <input name="kuvaURL" type="text" class="form-control" id="kuvaUrl">
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="checkbox" class="col-sm-2 control-label">Ruokalaji:* </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" id="inlineCheckbox1" value="option1"> Alkupala
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" id="inlineCheckbox1" value="option1"> Pääruoka
                        </label>
                        <label class="checkbox-inline">
                            <input type="checkbox" id="inlineCheckbox1" value="option1"> Jälkiruoka
                        </label>
                    </div>
                    <div class="form-group">
                        <label for="select" class="col-sm-2 control-label">Pääruoka-aine: </label>
                        <div class="col-sm-10">
                            <select class="form-control">
                                <option>Ei valintaa</option>
                                <option>Kana</option>
                                <option>Kala</option>
                                <option>Liha</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label for="inputText" class="col-sm-2 control-label">Reseptin kuvaus:* </label>
                        <div class="col-sm-10">
                            <textarea rows="6" name="ohje" class="form-control" id="ohje"></textarea>
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
