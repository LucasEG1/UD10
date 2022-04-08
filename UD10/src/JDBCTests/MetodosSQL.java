package JDBCTests;

import java.sql.*;
import java.util.InputMismatchException;
import java.util.Scanner;

public class MetodosSQL {

    private static Scanner leer = new Scanner(System.in);

    // CLASE DE PRUEBA - NO TOCAR NADA
    // CLASE DE PRUEBA - NO TOCAR NADA
    // CLASE DE PRUEBA - NO TOCAR NADA
    // CLASE DE PRUEBA - NO TOCAR NADA
    // CLASE DE PRUEBA - NO TOCAR NADA
    // CLASE DE PRUEBA - NO TOCAR NADA
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
            System.out.println(sqle.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /*public static void crearTabla() {
        System.out.println("Nombre de la tabla:");
        String nTabla = leer.nextLine();

        System.out.println("Cuántas columnas tendrá?");
        int nCols = 0;
        boolean comprobarInt = true;
        while (!comprobarInt) {
            try {
                nCols = leer.nextInt();
                leer.nextLine();
                comprobarInt = false;
            } catch (InputMismatchException e) {
                System.out.println("Se debe especificar un número de tipo entero.");
            }
        }

        for (int i = 0; i < nCols; i++) {
            System.out.println("¿Nombre de la columna " + i + "?");
            String nomCol = leer.nextLine();

            System.out.println("¿Tipo de dato? (Ejemplo: int, boolean, varchar(número de caracteres)...");
            String tipoVar = leer.nextLine();

            System.out.println("¿NULL o NOT NULL?");
            String nulo = leer.nextLine();
            
            System.out.println("¿Es clave primaria? s/n");
            boolean esPrimaria = false;
            String pk = leer.nextLine();
            char Pk = pk.charAt(0);
            switch (Pk) {
                case 's': case 'S':
                    esPrimaria = true;
                    break;
                case 'n': case 'N':
                    esPrimaria = false;
                    break;
                default:
                    System.out.println("Se debe especificar si es clave primaria o no.");  
            }
            
            //ESENCIAL PARA CONECTARSE A LA BASE DE DATOS
            try {
                String url = "jdbc:mysql://localhost:3306/";
                String username = "root";
                String password = "";

                //CREAR CONEXION, STATEMENT, RESULT SET PARA USAR
                Connection connection = DriverManager.getConnection(url, username, password);
                Statement statement = connection.createStatement();
                ResultSet rs = statement.executeQuery("Create table if not exists " + nTabla + "("
                        + "");
            } catch (SQLException sqle) {
                System.out.println(sqle.getMessage());
            } catch (Exception e) {
                e.printStackTrace();
            }
            
        }
    }*/
}
