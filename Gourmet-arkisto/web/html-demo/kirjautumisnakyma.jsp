<%-- 
    Document   : kirjautumisnakyma
    Created on : Nov 4, 2013, 11:14:59 PM
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
        <div class="jumbotron">
            <h1>Kirjaudu Gourmet-arkistoon</h1>
            <form class="form-horizontal" role="form" action="paanakyma.jsp" method="POST">
                <div class="form-group">
                    <label for="inputEmail1" class="col-md-2 control-label">Sähköposti</label>
                    <div class="col-md-10">
                        <input type="email" class="form-control" id="inputEmail1" placeholder="Email">
                    </div>
                </div>
                <div class="form-group">
                    <label for="inputPassword1" class="col-md-2 control-label">Salasana</label>
                    <div class="col-md-10">
                        <input type="password" class="form-control" id="inputPassword1" placeholder="Password">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <div class="checkbox">
                            <label>
                                <input type="checkbox"> Muista kirjautuminen
                            </label>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-md-offset-2 col-md-10">
                        <button type="submit" class="btn btn-default">Kirjaudu sisään</button>
                        <button type="submit" class="btn btn-default">Luo uusi käyttäjätili</button>
                    </div>
                </div>
            </form>
        </div>
    </body>
</html>
