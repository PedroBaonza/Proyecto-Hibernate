package funciones;

import java.io.Serializable;

import org.hibernate.Session;

public class Validaciones {

    /**
     * Verifica si un valor numérico es positivo.
     *
     * @param valor El valor a verificar.
     * @param mensaje Mensaje para la excepción en caso de que no sea válido.
     * 
     * Si el valor proporcionado no es positivo, lanza una excepción con el mensaje indicado.
     */
    public static void validarPositivo(double valor, String mensaje) {
        if (valor <= 0) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    /**
     * Valida si un objeto existe en la base de datos.
     * Si el objeto es null, lanza una excepción con el mensaje especificado.
     */
    public static void validarExistencia(Object objeto, String mensaje) {
        if (objeto == null) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    /**
     * Valida si una cadena no está vacía o nula.
     * Si la cadena está vacía o es null, lanza una excepción con el mensaje indicado.
     */
    public static void validarCadena(String cadena, String mensaje) {
        if (cadena == null || cadena.trim().isEmpty()) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    /**
     * Valida si una fecha es válida y en formato correcto.
     * Intenta convertir la cadena en una fecha. Si falla, lanza una excepción con el mensaje proporcionado.
     */
    public static void validarFecha(String fecha, String mensaje) {
        try {
            java.sql.Date.valueOf(fecha);
        } catch (Exception e) {
            throw new IllegalArgumentException(mensaje);
        }
    }

    /**
     * Verifica si un objeto existe en la base de datos por ID.
     *
     * @param session Sesión de Hibernate.
     * @param clase Clase de la entidad.
     * @param id ID a buscar.
     * @param mensaje Mensaje para la excepción si no existe.
     * @return El objeto encontrado en la base de datos.
     * 
     * Utiliza la sesión de Hibernate para buscar un objeto por su ID. Si el objeto no existe, lanza una excepción con el mensaje proporcionado.
     */
    public static <T> T validarExistenciaPorID(Session session, Class<T> clase, Serializable id, String mensaje) {
        T idObjeto = session.get(clase, id);
        validarExistencia(idObjeto, mensaje);
        return idObjeto;
    }

}
