package launcherAPP;

public class MainApp {

    public static void main(String[] args) {
    	
        try {
            /* Ejecutar el programa principal */
            new ControladorPrograma().iniciarPrograma();
        } catch (Exception error) {
            /* Manejar errores inesperados */
            System.err.println("Ha ocurrido un error: " + error.getMessage());
            error.printStackTrace();
        }
    }
}