package JDBCTests;

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
    
    public static void crearTabla() {
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
            System.out.println("¿Nombre de la columna?");
            String nomCol = leer.nextLine();

            System.out.println("¿Tipo de dato? (Ejemplo: int, boolean, varchar(número de caracteres)...");
            String tipoVar = leer.nextLine();

            System.out.println("¿NULL o NOT NULL?");
            String nulo = leer.nextLine();
            
            System.out.println("¿Es clave primaria? s/n");
            String pk = leer.nextLine();
            char Pk = pk.charAt(0);
            switch (Pk) {
                case 'y': case 'Y':
                                        
                    break;
                case 'n': case 'N':
                    break;
                default:
                    System.out.println("Se debe especificar si es clave primaria o no.");  
            }
            
        }
    }
}
