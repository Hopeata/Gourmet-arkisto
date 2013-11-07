<%-- 
    Document   : paanakyma
    Created on : Nov 4, 2013, 8:44:11 PM
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
                <img src="kuvat/kolmasruokakuva.jpg" alt="Ruokakuva"width="100" height="100">
                <img src="kuvat/neljasruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="kuvat/viidesruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="kuvat/kuudesruoka.jpg" alt="Ruokakuva"width="150" height="100">
                <img src="kuvat/tokaruokakuva.jpg" alt="Ruokakuva"width="100" height="100">
                <img src="kuvat/kahdeksasruoka.jpg" alt="Ruokakuva"width="150" height="100">
            </div>
        </div>

        <ul class="nav nav-tabs">
            <ul class="nav navbar-nav navbar-right">
                <li><a href="kirjautumisnakyma.jsp"><span class="glyphicon glyphicon-log-out"></span> Kirjaudu ulos </a></li>
            </ul>
            <li class="active"><a href="#">Pääsivu</a></li>
            <li><a href="reseptinlisaysnakyma.jsp">Ehdota reseptiä</a></li>
            <li><a href="#">Omat tiedot</a></li>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span> Hae</button>
            </form>
        </ul>

        <h1>Tervetuloa Gourmet-arkistoon!</h1>

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
    </body>
</html>
