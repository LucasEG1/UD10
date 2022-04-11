package CRUD;

import java.sql.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author Mario Tomás Zanón
 */
public class Partida {
    private static Scanner leer = new Scanner(System.in);    
    
    public static void verTodasPartidas() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/scoreboard";
        String username = "root";
        String password = "";

        //CREAR CONEXION, STATEMENT, RESULT SET PARA USAR
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT * FROM partida");

        while (rs.next()) {

            int idPart = rs.getInt("idPartida");
            Date fecha = rs.getDate("fecha");
            int numJugadores = rs.getInt("numJugadores");

            System.out.println(String.format("%d, %s, %d", idPart, fecha, numJugadores));
        }

        rs.close();
        statement.close();
        connection.close();
}
    
    public static void detallesPartidaPorId(int idPartida) throws Exception {
        String url = "jdbc:mysql://localhost:3306/scoreboard";
        String username = "root";
        String password = "";

        //CREAR CONEXION, STATEMENT, RESULT SET PARA USAR
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement stmtSelect = connection.createStatement();
        Statement stmtUpdate = connection.createStatement();
        
        stmtUpdate.execute("update jugador set mvp = true where username='" + sacarMvp(idPartida) + "'");
        ResultSet rs = stmtSelect.executeQuery("select jg.username, jg.kills, jg.deaths, jg.mvp"
                + " from jugador_partida jp join jugador jg on jg.username=jp.username"
                + " where jp.idPartida=" + idPartida);
        
        
        System.out.println("RESULTADOS DE LA PARTIDA (ID: " + idPartida + ")");
        System.out.println("\nUSERNAME\tASESINATOS\tMUERTES \t MVP");
        while (rs.next()) {
            String nomJugador = rs.getString("username");
            int kills = rs.getInt("kills");
            int deaths = rs.getInt("deaths");
            boolean mvp = rs.getBoolean("mvp");
            
            System.out.println(String.format("%s,\t\t %d,\t\t %d,\t\t %s", nomJugador, kills, deaths, mvp));
        }

        rs.close();
        stmtSelect.close();
        stmtUpdate.close();
        connection.close();
}
    
    public static int elegirIDDePartida() throws Exception {
        String url = "jdbc:mysql://localhost:3306/scoreboard";
        String username = "root";
        String password = "";

        //CREAR CONEXION, STATEMENT, RESULT SET PARA USAR
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("SELECT DISTINCT * FROM partida");

        while (rs.next()) {

            int nJugador = rs.getInt("idPartida");
            Date fecha = rs.getDate("fecha");
            int numJugadores = rs.getInt("numJugadores");

            // String.format permite imprimir cada columna con su valor correspondiente:
            //   %d para mostrar como int
            //   %s para mostrar como string

            System.out.println(String.format("%s, %s, %d", nJugador, fecha, numJugadores));
        }
        rs.close();
        statement.close();
        connection.close();
        
        System.out.print("Elige uno: ");
        return leer.nextInt();
}
    
    public static boolean crearPartida(int cuantosJugadores) throws Exception {
        int idPart = -1;
        ArrayList<Jugador> jugadores = new ArrayList<>();
        
        String url = "jdbc:mysql://localhost:3306/scoreboard";
        String ConnUsername = "root";
        String password = "";

        //CREAR CONEXION, STATEMENT, RESULT SET PARA HACER CONSULTAS, 
        //EXECUTE() PARA OTRAS COSAS (NO GUARDAR EN ResultSet)
        Connection connection = DriverManager.getConnection(url, ConnUsername, password);
        Statement statement = connection.createStatement();
        
        //Primero, creamos la partida con hueco para cuantosJugadores
        boolean exitoso = statement.execute("insert into partida (numJugadores) values (" + cuantosJugadores + ")");
        
        //Obtenemos el id de la última partida creada
        ResultSet rs = statement.executeQuery("select idPartida from partida order by idPartida desc limit 1");
        while (rs.next()) {
            idPart = rs.getInt("idPartida");
            System.out.println( "EL ID DE TU PARTIDA ES: " + String.format("%d", idPart));
        }
        
        //Luego, creamos los jugadores y los insertamos en el arrayList.
        for (int i = 0; i < cuantosJugadores; i++) {
            System.out.println("JUGADOR " + (i+1));
            System.out.print("Nombre del jugador: ");
            String nomJug = leer.nextLine();
            System.out.print("Asesinatos: ");
            int kills = leer.nextInt();
            leer.nextLine();
            System.out.print("Muertes: ");
            int deaths = leer.nextInt(); 
            leer.nextLine();
            jugadores.add(new Jugador(nomJug, kills, deaths));
        }
        
        //Recorremos el arrayList de jugadores, agregando en la base de datos
        //cada jugador en la partida creada más arriba.
        for (int i = 0; i < jugadores.size(); i++) {
            String username = jugadores.get(i).getUsername();
            int asesinatos = jugadores.get(i).getKills();
            int muertes = jugadores.get(i).getDeaths();
            statement.execute("Insert into jugador (username, kills, deaths) VALUES ('" + username + "', " + asesinatos + ", " + muertes +")");
            statement.execute("Insert into jugador_partida (username, idPartida) VALUES ('" + username + "' ," + idPart + ")");
        }
           
        //Desconectar
        statement.close();
        connection.close();
        return exitoso;
    }
    
    public static String sacarMvp(int idPartida) throws Exception{
        String url = "jdbc:mysql://localhost:3306/scoreboard";
        String username = "root";
        String password = "";

        //CREAR CONEXION, STATEMENT, RESULT SET PARA USAR
        Connection connection = DriverManager.getConnection(url, username, password);
        Statement statement = connection.createStatement();
        ResultSet rs = statement.executeQuery("select jg.username, kills/deaths as 'KD' "
                + "from jugador jg join jugador_partida jp on jg.username=jp.username "
                + "where jp.idPartida=" + idPartida + " order by KD desc limit 1");
        
        String nomMvp = "";
        while (rs.next()) {
            nomMvp = rs.getString("username");
        }
        rs.close();
        statement.close();
        connection.close();
        
        return nomMvp;
    }
       
}
