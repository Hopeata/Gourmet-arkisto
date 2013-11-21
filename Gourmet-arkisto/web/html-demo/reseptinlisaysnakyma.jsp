<%-- 
    Document   : reseptinlisaysnakyma
    Created on : Nov 4, 2013, 11:41:17 PM
    Author     : Valeria
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/bootstrap-theme.css" rel="stylesheet">
        <link href="../css/main.css" rel="stylesheet">
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <div class="panel panel-default">
            <div class="panel-body">
                <img src="kuvat/ruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="kuvat/toinenruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="kuvat/kolmasruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="kuvat/neljasruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="kuvat/viidesruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="kuvat/kuudesruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="kuvat/seitsemasruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="kuvat/kahdeksasruoka.jpg" alt="Ruokakuva"width="150" height="100">
            </div>
        </div>

        <ul class="nav nav-tabs">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="kirjautumisnakyma.jsp"><span class="glyphicon glyphicon-log-out"></span> Kirjaudu ulos </a></li>
            </ul>
            <li><a href="paanakyma.jsp">Pääsivu</a></li>
            <li class="active"><a href="#">Ehdota reseptiä</a></li>
            <li><a href="tilinmuokkausnakyma.jsp">Omat tiedot</a></li>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span> Hae</button>
            </form>
        </ul>
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
</body>
</html>
