<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML>
<HTML>
    <HEAD> 
        <TITLE>Servlet usando GET y Bootstrap</TITLE>
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <link rel="stylesheet" href="webjars/bootstrap/5.2.2/css/bootstrap.css">
    </HEAD>
    <BODY class="container">
        <div class="container">
            <div class="row">
                <DIV class="col-md-4"></DIV>
                <DIV class="col-md-4"> 
                    <h2>Inicio de Sesion</h3>
                    <form class="form" action="./LoginServlet" method="Post">
                        <div class="mb-1">
                        <label for="txtUsuario" class="form-label">Usuario:</label> 
                        <input class="form-control" name="txtUsuario" type="text" placeholder="Ingrese Usuario" width="20"/>
                        </div>                        
                        <div class="mb-1">
                        <label for="txtClave" class="form-label">Clave:</label> 
                        <input class="form-control" name="txtClave" type="text" placeholder="Ingrese Clave" width="20"/>
                        </div>
                        <BUTTON type="submit" class="btn btn-success" >Aceptar</BUTTON>
                        <a href="./index.html"><input class="btn btn-warning" type="button" value="Volver" /></a>
                        <input type="hidden" name="Action" value="Login" />
                    </form>
                </DIV>
                <DIV class="col-md-4"></DIV>
            </DIV>
        </DIV>
    </BODY>
</HTML>
