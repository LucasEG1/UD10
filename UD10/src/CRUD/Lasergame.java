package CRUD;

import java.util.InputMismatchException;
import java.util.Scanner;
import java.sql.*;

/**
 * Clase Lasergame, funciona como Main.
 * @author Mario Tomás Zanón
 */
public class Lasergame {
    //al borrar un jugador, se tiene que ajustas numJugaodres de su partida
    private static Scanner leer = new Scanner(System.in);
    
    public static void main(String[] args) {
        
        System.out.println("=|BIENVENIDO/A AL SISTEMA DE GESTIÓN |=");
        System.out.println("-- =|DE PUNTUACIONES DEL LASERGAME|= --");
        
        int opcion = 0;
        do {
            boolean valido = false;
            do {
                try {
                    opcion = menu();
                    leer.nextLine();
                    valido = true;
                } catch (InputMismatchException e) {
                    leer.nextLine();
                    System.err.println("Introduce un número entero");
                    valido = false;
                }catch (Exception e) {
                    e.printStackTrace();
                    valido = false;
                }
            } while (!valido);

            switch (opcion) {
                case 1:
                    try {
                        System.out.println("¿Cuántos jugadores habrá?");
                        int cuantosJugadores = leer.nextInt();
                        leer.nextLine();
                        Partida.crearPartida(cuantosJugadores);
                    } catch (InputMismatchException e) {
                        System.out.println("Introduzca un numero entero. La operación se ha cancelado.");
                    } catch (SQLException sqle) {
                        System.out.print("Error en la sintaxis SQL: ");
                        System.out.println(sqle.getMessage());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;

                case 2: // Ver partidas guardadas
                    try {
                        Partida.verTodasPartidas();
                    } catch (InputMismatchException e) {
                        System.out.println("Introduzca un numero entero. La operación se ha cancelado.");
                    } catch (SQLException sqle) {
                        System.out.print("Error en la sintaxis SQL:");
                        System.out.println(sqle.getMessage());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                    
                case 3: // Buscar partida por id
                    try {
                        Partida.detallesPartidaPorId(Partida.elegirIDDePartida());
                    } catch (InputMismatchException e) {
                        System.out.println("Introduzca un numero entero. La operación se ha cancelado.");
                    } catch (SQLException sqle) {
                        System.out.print("Error en la sintaxis SQL:");
                        System.out.println(sqle.getMessage());
                    } catch (Exception ex) {
                        System.out.println(ex.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("¡Gracias! ¡Hasta la próxima!");
                    break;
                default:
                    System.out.println("Opción incorrecta.");
                    break;
            }

            System.out.println("");
        } while (opcion != 4);
        
        
        
    }
    
    public static int menu() throws Exception {
                
        System.out.println(" 1. Crear partida");
        System.out.println(" 2. Ver partidas guardadas");
        System.out.println(" 3. Buscar partida por ID");
        System.out.println(" 4. Salir del programa");
        
        System.out.println("\n Por favor, introduzca a continuación la acción"
                + "\nque desee realizar");
        int opcion = leer.nextInt();
        return opcion;
    }
}
