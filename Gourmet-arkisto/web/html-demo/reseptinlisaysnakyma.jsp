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
            <li><a href="#">Omat tiedot</a></li>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span> Hae</button>
            </form>
        </ul>
        <h1>Ehdota reseptiä!</h1>
        
    </body>
</html>
