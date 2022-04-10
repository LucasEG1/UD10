package CRUD;

import java.sql.*;
import java.util.Scanner;

/**
 *
 * @author Mario Tomás Zanón
 */
public class Jugador {

    private String username;
    private int kills;
    private int deaths;
    private boolean MVP = false;

    //Constructor
    /**
     * Función para agregar el jugador en la base de datos. Cumple la función del constructor.
     * @return True si se pudo agregar el jugador, False en caso contrario
     */
    public Jugador(String username, int kills, int deaths) {

        setUsername(username);
        setKills(kills);
        setDeaths(deaths);
    }
    
    //Getters - Setters
    public String getUsername() {
        return username;
    }
    public int getKills() {
        return kills;
    }
    public int getDeaths() {
        return deaths;
    }
    public boolean getMvp() {
        return MVP;
    }
    
    public void setUsername(String usn) {
        username = usn;
    }
    public void setKills(int klls) {
        kills = klls;
    }
    public void setDeaths(int dths) {
        deaths = dths;
    }
    public void setMVP(boolean mvp) {
        MVP = mvp;
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
