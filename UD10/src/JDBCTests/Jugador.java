package JDBCTests;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author Mario Tomás Zanón
 */
public class Jugador {

    private static Scanner leer = new Scanner(System.in);
    private String username;
    private int kills;
    private int deaths;
    private int mvp;

    //Constructor
    /**
     * Función para agregar el jugador en la base de datos. Cumple la función del constructor.
     * @return True si se pudo agregar el jugador, False en caso contrario
     */
    public static boolean agregarJugador() {

        //Contructor de jugadores por parte del empleado del lasergame
        System.out.println("Nombre del jugador a agregar");
        String nJugador = leer.nextLine();
        int kills = 0;
        int deaths = 0;
        int mvp = 0;

        try {
            String url = "jdbc:mysql://localhost:3306/scoreboard";
            String username = "root";
            String password = "";

            //CREAR CONEXION, STATEMENT, RESULT SET PARA HACER CONSULTAS, 
            //EXECUTE() PARA OTRAS COSAS (NO GUARDAR EN ResultSet)
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();

            statement.execute("Insert into jugador values(" + "'" + nJugador
                    + "'" + "," + kills + "," + deaths + "," + mvp + ")");

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

    
    //Getters - Setters
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public int getKills() {
        return kills;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public int getDeaths() {
        return deaths;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public int getMvp() {
        return mvp;
    }

    public void setMvp(int mvp) {
        this.mvp = mvp;
    }

    //Otras funciones
    
    /**
     * Quita un jugador de la base de datos
     * @param nomJugador
     * @return TRUE si se pudo eliminar el jugador, FALSE en caso contrario
     */
    public static boolean quitarjugador(String nomJugador){
            try {
                String url = "jdbc:mysql://localhost:3306/scoreboard";
                String username = "root";
                String password = "";

                //CREAR CONEXION, STATEMENT, RESULT SET PARA HACER CONSULTAS, 
                //EXECUTE() PARA OTRAS COSAS (NO GUARDAR EN ResultSet)
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();

                statement.execute("DELETE FROM jugador_partida WHERE username='" + nomJugador + "'");
                statement.execute("DELETE FROM Jugador WHERE username='" + nomJugador +  "'");

                //Desconectar
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
