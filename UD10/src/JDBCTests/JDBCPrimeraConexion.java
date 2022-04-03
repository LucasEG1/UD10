/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JDBCTests;

import java.sql.*;

/**
 *
 * @author casa
 */
public class JDBCPrimeraConexion {
    
    public static void main(String[] args) {
        
        try {
            Class.forName("com.mysql.cj.jdbc.Driver").newInstance();            
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        
        try {
            String url = "jdbc:mysql://localhost:3306/prueba";
            //DriveManager.getConnection(url);
            Connection conn = DriverManager.getConnection(url,"root","root");
            
            
            conn.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        
    }
}