/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package com.utp.pe;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "EmpleadoServlet", urlPatterns = {"/EmpleadoServlet"})
public class EmpleadoServlet extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet EmpleadoServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet EmpleadoServlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //processRequest(request, response);
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        out.println(headerP);
        Connection cnx;
        String comando;
        try{
            cnx=BDConexion.initializeDataBases();
            PreparedStatement st = cnx.prepareStatement("SELECT idEmp, nomEmp, dni, sueldo FROM empleado");
            ResultSet rs = st.executeQuery();
            //Imprimir HTML
            out.println("<DIV class=\"col-md-3\"></DIV><DIV class=\"col-md-6\"> ");
            out.println("<h3>Lista de Empleados</h3>");
            out.println("<a href='./InsertarEmpleado.jsp'><button class=\"btn btn-primary\">Nuevo</button></a></br>");
            out.println("<table class=\"table table-striped\"><tr><td>ID Emp.</td><td>Nombre Emp.</td><td>DNI</td><td>Sueldo</td><td>Editar</td><td>Borrar</td></tr>");
            while(rs.next()){
                comando = "<tr><td>"+rs.getInt(1)+"</td><td>"+rs.getString(2)+"</td>"
                       +"<td>"+rs.getString(3)+"</td><td>"+rs.getDouble(4)+"</td>"
                       + "<td><a href=\"./EditarEmpleado.jsp?idEmp="+rs.getInt(1)+"&nomEmp="+rs.getString(2)
                       +"&dni="+rs.getString(3)+"&sueldo="+rs.getDouble(4)+  " \">Editar</a></td>"
                       + "<td><a href=\"./EliminarEmpleado.jsp?idEmp="+rs.getInt(1)+"&nomEmp="+rs.getString(2)
                       +"&dni="+rs.getString(3)+"&sueldo="+rs.getDouble(4)+  " \">Borrar</a></td>"
                               + "</tr>";
                
                out.println(comando);
            }
            out.println("</table>");
            out.println("</DIV><DIV class=\"col-md-3\"></DIV>");
        }catch(SQLException | ClassNotFoundException ex){
            out.println("Error: "+ex.toString());
        }
        out.println(footerP);
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        
        out.println(headerP);
         
        Connection cnx;
        try{    
            cnx=BDConexion.initializeDataBases();
            PreparedStatement st = null ;
            if(request.getParameter("Action").trim().equals("Insert"))
            {
                st=cnx.prepareStatement("INSERT INTO empleado(idEmp,nomEmp,dni,sueldo,vacacAcum,idSuc,idUsu) values(?,?,?,?,0,1,1)");
                st.setInt(1,Integer.parseInt(request.getParameter("txtId")));
                st.setString(2, request.getParameter("txtNombre"));
                st.setString(3, request.getParameter("txtDni"));
                st.setDouble(4, Double.parseDouble(request.getParameter("txtSueldo")));
                st.executeUpdate();
            }else if(request.getParameter("Action").trim().equals("Edit"))
            {
                st=cnx.prepareStatement("UPDATE empleado SET nomEmp=?, dni=?, sueldo=? WHERE idEmp= ?");
                st.setString(1, request.getParameter("txtNombre"));
                st.setString(2, request.getParameter("txtDni"));
                st.setDouble(3, Double.parseDouble(request.getParameter("txtSueldo")));
                st.setInt(4,Integer.parseInt(request.getParameter("txtId")));
                st.executeUpdate();
            }else if(request.getParameter("Action").trim().equals("Delete"))
            {
                st=cnx.prepareStatement("DELETE FROM empleado WHERE idEmp=?");
                st.setInt(1,Integer.parseInt(request.getParameter("txtId")));
                st.executeUpdate();
            }else
                out.println("<h3>Error</h3>");
            st.close();
            cnx.close();
            
            //Imprimir HTML
            out.println("<DIV class=\"col-md-4\"></DIV><DIV class=\"col-md-4\"> ");
            out.println("<h3>Datos Registrados</h3>");
            out.println("<a href='./EmpleadoServlet'><button class=\"btn btn-primary\">Regresar</button></a>");
            out.println("</DIV><DIV class=\"col-md-4\"></DIV>");
        }catch(SQLException | ClassNotFoundException ex){
            out.println("Error: "+ex.toString());
        }
        out.println(footerP);
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>
    
    static String headerP="<!DOCTYPE HTML>\n" +
                            "<HTML>\n" +
                            "    <HEAD> \n" +
                            "        <TITLE>Servlet usando GET y Bootstrap</TITLE>\n" +
                            "        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n" +
                            "        <link rel=\"stylesheet\" href=\"webjars/bootstrap/5.2.2/css/bootstrap.css\">\n" +
                            "    </HEAD>\n" +
                            "    <BODY class=\"container\">\n" +
                            "        <div class=\"container\">\n" +
                            "            <div class=\"row\">";
    static String footerP="</DIV>\n" +
                            "        </DIV>\n" +
                            "    </BODY>\n" +
                            "</HTML>";

}
