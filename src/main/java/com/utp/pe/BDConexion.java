
package com.utp.pe;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.SQLException;

public class BDConexion {
    protected static Connection initializeDataBases() throws SQLException, ClassNotFoundException
    {
        String bdDriver ="com.mysql.jdbc.Driver";
        String bdUrl="jdbc:mysql://localhost:3306/";
        String bdDataBase="bd_boleta";
        String bdUser="root";
        String bdPass="";
        Class.forName(bdDriver);
        Connection cnx = DriverManager.getConnection(bdUrl+ bdDataBase, bdUser, bdPass) ;
        return cnx;
    }
            
}
