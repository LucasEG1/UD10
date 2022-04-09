package JDBCTests;

import java.sql.*;
import java.util.Scanner;

public class MetodosSQL {

    private static Scanner leer = new Scanner(System.in);

    // CLASE DE PRUEBA - NO TOCAR NADA
    // CLASE DE PRUEBA - NO TOCAR NADA
    
    /**
     * Ejecuta la orden SELECT * FROM de la tabla que se pasa como argumento.
     * @param tabla 
     */
    public static void selectEnTabla(String tabla) {

        /*ESENCIAL PARA CONECTARSE A LA BASE DE DATOS*/
        try {
            String url = "jdbc:mysql://localhost:3306/scoreboard";
            String username = "root";
            String password = "";

            //CREAR CONEXION, STATEMENT, RESULT SET PARA USAR
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM " + tabla);

            while (rs.next()) {

                String nJugador = rs.getString("username");
                int kills = rs.getInt("kills");
                int deaths = rs.getInt("deaths");
                boolean mvp = rs.getBoolean("mvp");

                /* String.format permite imprimir cada columna con su valor correspondiente:
                    %d para mostrar como int
                    %s para mostrar como string
                 */
                System.out.println(String.format("%s, %d, %d, %s", nJugador, kills, deaths, mvp));
            }

            rs.close();
            statement.close();
            connection.close();
            
        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Función para agregar un jugador con un nombre que se pasa por teclado, el resto de campos
     * se rellenan por defecto a 0 o false, en caso de la columna MVP
     * @return TRUE si se pudo agregar el jugador correctamente, FALSE en caso contrario
     */
    public static boolean agregarJugador(){
        
        System.out.println("Nombre del jugador a agregar");
        String nJugador = leer.nextLine();
        int kills = 0;
        int deaths = 0;
        int mvp = 0;
        
        try {
            String url = "jdbc:mysql://localhost:3306/scoreboard";
            String username = "root";
            String password = "";

            //CREAR CONEXION, STATEMENT, RESULT SET PARA HACER CONSULTAS, EXECUTE() PARA OTRAS COSAS (NO GUARDAR EN ResultSet)
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            
            statement.execute("Insert into jugador values(" + "'" + nJugador + "'" + "," + kills + "," + deaths+ "," + mvp + ")");
            
            statement.close();
            connection.close();
            return true;
        } catch (SQLException sqle) {
            System.out.println(sqle.getMessage());
            return false;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
    
}
