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
import javax.servlet.http.HttpSession;

/**
 *
 * @author Administrador
 */
@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
public class LoginServlet extends HttpServlet {

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
            out.println("<title>Servlet LoginServlet</title>");            
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet LoginServlet at " + request.getContextPath() + "</h1>");
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
        processRequest(request, response);
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
        boolean ok = true;
        
        out.println(headerP);
         
        Connection cnx;
        try{    
            cnx=BDConexion.initializeDataBases();
            PreparedStatement st = null ;
            if(request.getParameter("Action").trim().equals("Login"))
            {
                String usuario = request.getParameter("txtUsuario");
                String clave = request.getParameter("txtClave");

                if ((usuario != null) && (clave!=null)) {
                    st=cnx.prepareStatement("SELECT clave FROM usuario WHERE usuario = ? AND clave = ?");
                    st.setString(1,usuario);
                    st.setString(2,clave);
                    ResultSet resultado = st.executeQuery();
                    if (resultado.next()){       // primer registro
                        String wclave = resultado.getString("clave");
                        if (wclave.equals(clave)){
                            HttpSession sesion=request.getSession();
                            sesion.setAttribute("usuario",usuario);
                        }
                        else {
                            ok = false;
                        }
                    }else{
                        ok = false;
                    }
                }else{
                    ok = false;
                }
            }else
                out.println("<h3>Error</h3>");
            
            st.close();
            cnx.close();
            
            //Imprimir HTML
            out.println("<DIV class=\"col-md-4\"></DIV><DIV class=\"col-md-4\"> ");
            if (ok){
                out.println("<h3>Login Validado</h3>");
                out.println("<a href='./EmpleadoServlet'><span>Listar Empleados</span></a>");
                out.println("<a href='./Login.jsp'><button class=\"btn btn-warning\">Regresar</button></a>");
            }else{
                out.println("<h3>Datos Incorrectos</h3>");
                out.println("<a href='./Login.jsp'><button class=\"btn btn-warning\">Regresar</button></a>");
            }
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
