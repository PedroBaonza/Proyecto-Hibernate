package launcherAPP;

import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

public class ConexionHibernate {
	
    /* Declaración de la variable SessionFactory que será utilizada para gestionar las sesiones con la base de datos */
    private static final SessionFactory sessionFactory;

    static {
        try {
            /*
             * Cargar la configuración desde el archivo "hibernate.cfg.xml" y construir la fábrica de sesiones.
             * Si la configuración es correcta, se creará la fábrica para iniciar sesiones con la base de datos.
             */
            sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();
        } catch (Throwable ex) {
        	
            /* Manejar cualquier excepción que ocurra durante la creación de la fábrica de sesiones. */
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }

    /*
     * Método público que devuelve la instancia de SessionFactory creada.
     * La SessionFactory se utiliza para abrir sesiones con la base de datos y realizar transacciones.
     */
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}