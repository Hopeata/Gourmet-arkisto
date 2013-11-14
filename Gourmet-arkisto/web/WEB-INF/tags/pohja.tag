<%-- 
    Document   : pohja
    Created on : Nov 13, 2013, 11:30:33 AM
    Author     : Valeria
--%>

<%@tag description="Generic template for Gourmet-arkisto pages" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%-- The list of normal or fragment attributes can be specified here: --%>
<%@attribute name="pageTitle"%>
<!DOCTYPE html>
<html>
    <head>
        <link href="../css/bootstrap.css" rel="stylesheet">
        <link href="../css/bootstrap-theme.css" rel="stylesheet">
        <link href="../css/main.css" rel="stylesheet">
        <script src="http://code.jquery.com/jquery.js"></script>
        <script src="js/bootstrap.min.js"></script>
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
            <li><a href="tilinmuokkausnakyma.jsp">Omat tiedot</a></li>
            <form class="navbar-form navbar-left" role="search">
                <div class="form-group">
                    <input type="text" class="form-control" placeholder="Search">
                </div>
                <button type="submit" class="btn btn-default"><span class="glyphicon glyphicon-search"></span> Hae</button>
            </form>
        </ul>