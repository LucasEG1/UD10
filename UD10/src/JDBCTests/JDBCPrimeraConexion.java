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
            /*ESENCIAL PARA CONECTARSE A LA BASE DE DATOS*/
            String url = "jdbc:mysql://localhost:3306/prueba";
            String username = "root";
            String password = "";

            //CREAR CONEXION, STATEMENT, RESULT SET PARA USAR
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM persona");

            while (rs.next()) {

                int id = rs.getInt("id");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                Date fecha = rs.getDate("fecha");

                /* String.format permite imprimir cada columna con su valor correspondiente:
                    %d para mostrar como int
                    %s para mostrar como string
                */
                System.out.println(String.format("%d, %s %s, %s", id, nombre, apellido, fecha));
            }

            rs.close();
            statement.close();
            connection.close();

        } catch (SQLException ex) {
            System.out.println(ex);
        }

        }
}