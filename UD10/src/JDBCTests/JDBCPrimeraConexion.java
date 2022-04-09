package JDBCTests;

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
          
        
        MetodosSQL.selectEnTabla("jugador");
        System.out.println("---------");
        MetodosSQL.agregarJugador();
        System.out.println("---------");
        MetodosSQL.selectEnTabla("jugador");
        }
}