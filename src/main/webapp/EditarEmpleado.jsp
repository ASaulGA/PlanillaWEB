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
                    <h2>Editar Empleado</h3>
                    <form class="form" action="./EmpleadoServlet" method="Post">
                        <div class="mb-1">
                        <label for="txtId" class="form-label">ID:</label> 
                        <input class="form-control" name="txtId" type="text" placeholder="Ingrese ID" width="5"
                               readonly="readonly" value="<%= request.getParameter("idEmp") %>"/>
                        </div>                        
                        <div class="mb-1">
                        <label for="Nombre" class="form-label">Nombre:</label> 
                        <input class="form-control" name="txtNombre" type="text" placeholder="Ingrese Nombre" width="100" 
                               value="<%= request.getParameter("nomEmp") %>"/>
                        </div>
                        <div class="mb-1">
                        <label for="txtDni" class="form-label">DNI:</label> 
                        <input class="form-control" name="txtDni" type="text" placeholder="Ingrese Dni" width="20" 
                               value="<%= request.getParameter("dni") %>"/>
                        </div>
                        <div class="mb-1">
                        <label for="txtSueldo" class="form-label">Sueldo:</label> 
                        <input class="form-control" name="txtSueldo" type="text" placeholder="Ingrese Sueldo" width="20" 
                               value="<%= request.getParameter("sueldo") %>"/>
                        </div>

                        <BUTTON type="submit" class="btn btn-success" >Grabar</BUTTON>
                        <a href="./EmpleadoServlet"><input class="btn btn-warning" type="button" value="Ir a Listado" /></a>
                        <input type="hidden" name="Action" value="Edit" />
                    </form>
                </DIV>
                <DIV class="col-md-4"></DIV>
            </DIV>
        </DIV>
    </BODY>
</HTML>
