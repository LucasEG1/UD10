package JDBCTests;

import java.sql.*;

/**
 *
 * @author Mario Tomás Zanón
 */
public class Partida {
    private int idPartida;
    private Date fecha;
    private int numJugadores;

    
    public Partida(int idPartida, int numJugadores) {
        this.idPartida = idPartida;
        this.numJugadores = numJugadores;
    }

    public int getIdPartida() {
        return idPartida;
    }

    public void setIdPartida(int idPartida) {
        this.idPartida = idPartida;
    }

    public Date getFecha() {
        return fecha;
    }

    public int getNumJugadores() {
        return numJugadores;
    }

    public void setNumJugadores(int numJugadores) {
        this.numJugadores = numJugadores;
    }
    
    public static void selectPartidaId(int idPartida){
    try {
            String url = "jdbc:mysql://localhost:3306/scoreboard";
            String username = "root";
            String password = "";

            //CREAR CONEXION, STATEMENT, RESULT SET PARA USAR
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM partida WHERE idPartida = " + idPartida);

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

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
}
    public static void selectTodoPartida(){
    try {
            String url = "jdbc:mysql://localhost:3306/scoreboard";
            String username = "root";
            String password = "";

            //CREAR CONEXION, STATEMENT, RESULT SET PARA USAR
            Connection connection = DriverManager.getConnection(url, username, password);
            Statement statement = connection.createStatement();
            ResultSet rs = statement.executeQuery("SELECT * FROM partida");

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

        } catch (SQLException sqle) {
            sqle.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
}
    
}
