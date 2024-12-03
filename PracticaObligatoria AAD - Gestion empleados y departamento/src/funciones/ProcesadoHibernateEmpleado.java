package funciones;

import java.sql.Date;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

import launcherAPP.ConexionHibernate;
import modelo.Empleados;
import modelo.Departamentos;

public class ProcesadoHibernateEmpleado {

    private static final SessionFactory factory = ConexionHibernate.getSessionFactory();
    private final PeticionDatos peticionDatos = new PeticionDatos();
    private final MenusPrograma menusPrograma = new MenusPrograma();

    /**
     * Método para agregar un nuevo empleado a la base de datos.
     * Solicita los datos del empleado, los valida y luego guarda el empleado en la base de datos.
     */
    public void agregarEmpleado() {
        menusPrograma.mostrarMenuIngresoDatosEmpleado();
        /* Abrir una nueva sesión con la base de datos utilizando Hibernate */
        try (Session sesion = factory.openSession()) {
            /* Iniciar una transacción para garantizar la consistencia de los datos */
            Transaction transaccion = sesion.beginTransaction();

            /* Crear un nuevo empleado con los datos proporcionados por el usuario */
            Empleados empleado = crearEmpleado(sesion);

            /* Guardar el nuevo empleado en la base de datos */
            sesion.save(empleado);

            /* Confirmar la transacción para que los cambios sean permanentes */
            transaccion.commit();
            System.out.println("Empleado agregado con éxito.");
        } catch (Exception error) {
            /* Manejar cualquier error que ocurra durante el proceso */
            System.err.println("Error al agregar el empleado: " + error.getMessage());
        }
    }

    /**
     * Método para eliminar un empleado de la base de datos utilizando su ID.
     * Solicita el ID del empleado, valida su existencia y lo elimina si se encuentra.
     */
    public void eliminarEmpleado() {
    	
        /* Abrir una nueva sesión con la base de datos utilizando Hibernate */
        try (Session sesion = factory.openSession()) {
            /* Iniciar una transacción para garantizar la consistencia de los datos */
            Transaction transaccion = sesion.beginTransaction();
            
            /* Metemos por pantalla los empleados que hay disponibles*/
            mostrarIdYNombreEmpleados();

            /* Solicitar al usuario el ID del empleado que desea eliminar */
            short idEmpleado = (short) peticionDatos.escribirNumero("Introduce el ID del empleado a eliminar: ");

            /* Validar que el empleado con el ID proporcionado exista en la base de datos */
            Empleados empleado = Validaciones.validarExistenciaPorID(
                sesion, Empleados.class, idEmpleado, "El empleado con ID " + idEmpleado + " no existe."
            );

            /* Eliminar el empleado de la base de datos */
            sesion.delete(empleado);

            /* Confirmar la transacción para que los cambios sean permanentes */
            transaccion.commit();
            System.out.println("Empleado eliminado con éxito.");
        } catch (Exception error) {
            /* Manejar cualquier error que ocurra durante el proceso */
            System.err.println("Error al eliminar el empleado: " + error.getMessage());
        }
    }


    /**
     * Modificar datos de un empleado.
     * Solicita al usuario los datos a modificar y actualiza la información del empleado.
     */
    public void modificarEmpleado() {
    	
        try (Session sesion = factory.openSession()) {
            Transaction transaccion = sesion.beginTransaction();

            /* Metemos por pantalla los empleados que hay disponibles*/
            mostrarIdYNombreEmpleados();
            
            // Solicitar el ID del empleado a modificar
            short idEmpleado = (short) peticionDatos.escribirNumero("Introduce el ID del empleado a modificar: ");

            // Buscar el empleado en la base de datos
            Empleados empleado = Validaciones.validarExistenciaPorID(
                    sesion, Empleados.class, idEmpleado, "El empleado con ID " + idEmpleado + " no existe."
            );

            // Mostrar las opciones de modificación al usuario
            menusPrograma.mostrarMenuModificacionEmpleado();
            int opcion = peticionDatos.escribirNumero("Introduce la opción: ");

            /* Con la opcion elegida del usuario lo llevamos a un swich en otro metodo para una mejor encapsulacion. */
            logicaModificacionEmpleado(sesion, empleado, opcion);

            // Guardar los cambios en la base de datos
            sesion.update(empleado);
            transaccion.commit();
            System.out.println("Empleado modificado con éxito.");
        } catch (Exception error) {
            System.err.println("Error al modificar el empleado: " + error.getMessage());
        }
    }

    /**
     * Consultar un empleado por su ID.
     * Solicita el ID del empleado y muestra la información si se encuentra.
     */
    public void consultarEmpleadoPorID() {
        try (Session sesion = factory.openSession()) {
        	
        	/* Metemos por pantalla los empleados que hay disponibles*/
            mostrarIdYNombreEmpleados();
            
            // Solicitar el ID del empleado al usuario
            short idEmpleado = (short) peticionDatos.escribirNumero("Introduce el ID del empleado a consultar: ");

            // Buscar el empleado en la base de datos
            Empleados empleado = Validaciones.validarExistenciaPorID(
                    sesion, Empleados.class, idEmpleado, "El empleado con ID " + idEmpleado + " no existe."
            );

            // Mostrar la información del empleado
            System.out.println(empleado);
        } catch (Exception error) {
            System.err.println("Error al consultar el empleado: " + error.getMessage());
        }
    }

    /**
     * Mostrar todos los empleados.
     * Recupera y muestra todos los empleados registrados en la base de datos.
     */
    public void mostrarTodosLosEmpleados() {
        try (Session sesion = factory.openSession()) {
            // Recuperar todos los empleados
            List<Empleados> empleados = sesion.createQuery("FROM Empleados", Empleados.class).list();

            // Verificar si hay empleados registrados
            if (empleados.isEmpty()) {
                System.out.println("No hay empleados registrados en la base de datos.");
            } else {
            	// Recorrer la lista de empleados y mostrar su información
                for (Empleados empleado : empleados) {
                    System.out.println(empleado.toString());
                }
            }
        } catch (Exception error) {
            System.err.println("Error al mostrar los empleados: " + error.getMessage());
        }
    }
    
    /* Métodos propios de la clase para realizar funciones necesarias. */
    /**
     * Método de creación de un empleado solicitando datos al usuario.
     *
     * @param session La sesión actual de Hibernate.
     * @return Empleado creado.
     * @throws Exception Si ocurre algún error durante el proceso.
     */
    private Empleados crearEmpleado(Session session) throws Exception {
        Empleados empleado = new Empleados();

        // Validar y asignar datos
        empleado.setIdEmp((short) peticionDatos.escribirNumero("Introduce el ID del empleado: "));
        
        String nombre = peticionDatos.escribirCadena("Introduce el nombre completo del empleado: ");
        Validaciones.validarCadena(nombre, "El nombre no puede estar vacío.");
        empleado.setNombreCompleto(nombre);

        String oficio = peticionDatos.escribirCadena("Introduce el oficio del empleado: ");
        Validaciones.validarCadena(oficio, "El oficio no puede estar vacío.");
        empleado.setOficio(oficio);

        String fechaAlta = peticionDatos.escribirCadena("Introduce la fecha de alta (yyyy-mm-dd): ");
        Validaciones.validarFecha(fechaAlta, "La fecha de alta no es válida.");
        empleado.setFechaAlt(Date.valueOf(fechaAlta));

        float salario = peticionDatos.escribirFloat("Introduce el salario del empleado: ");
        Validaciones.validarPositivo(salario, "El salario debe ser mayor que cero.");
        empleado.setSalario(salario);

        // Asociar departamento
        byte idDepartamento = (byte) peticionDatos.escribirNumero("Introduce el ID del departamento del empleado: ");
        Departamentos departamento = Validaciones.validarExistenciaPorID(
            session, Departamentos.class, idDepartamento, "El departamento con ID " + idDepartamento + " no existe."
        );
        empleado.setDepartamentos(departamento);

        // Proyecto opcional
        Byte idProyecto = (byte) peticionDatos.escribirNumero("Introduce el ID del proyecto (opcional, 0 para omitir): ");
        if (idProyecto != 0) {
            empleado.setProyecto(idProyecto);
        }

        return empleado;
    }
    
    private void logicaModificacionEmpleado(Session sesion, Empleados empleado, int opcion) {
		// Modificar el campo seleccionado
		switch (opcion) {
		    case 1 -> {
		    	String nuevoNombre = peticionDatos.escribirCadena("Introduce el nuevo nombre completo: ");
		        Validaciones.validarCadena(nuevoNombre, "El nombre no puede estar vacío.");
		        empleado.setNombreCompleto(nuevoNombre);
		    }
		    case 2 -> {
		        String nuevoOficio = peticionDatos.escribirCadena("Introduce el nuevo oficio: ");
		        Validaciones.validarCadena(nuevoOficio, "El oficio no puede estar vacío.");
		        empleado.setOficio(nuevoOficio);
		    }
		    case 3 -> {
		        String nuevaFechaAlta = peticionDatos.escribirCadena("Introduce la nueva fecha de alta (yyyy-mm-dd): ");
		        Validaciones.validarFecha(nuevaFechaAlta, "La fecha de alta no es válida.");
		        empleado.setFechaAlt(Date.valueOf(nuevaFechaAlta));
		    }
		    case 4 -> {
		        float nuevoSalario = peticionDatos.escribirFloat("Introduce el nuevo salario: ");
		        Validaciones.validarPositivo(nuevoSalario, "El salario debe ser mayor que cero.");
		        empleado.setSalario(nuevoSalario);
		    }
		    case 5 -> {
		        byte nuevoIdDepartamento = (byte) peticionDatos.escribirNumero("Introduce el nuevo ID del departamento: ");
		        Departamentos nuevoDepartamento = Validaciones.validarExistenciaPorID(
		                sesion, Departamentos.class, nuevoIdDepartamento, "El departamento con ID " + nuevoIdDepartamento + " no existe."
		        );
		        empleado.setDepartamentos(nuevoDepartamento);
		    }
		    case 6 -> {
		        Byte nuevoIdProyecto = (byte) peticionDatos.escribirNumero("Introduce el nuevo ID del proyecto (opcional, 0 para omitir): ");
		        empleado.setProyecto(nuevoIdProyecto != 0 ? nuevoIdProyecto : null);
		    }
		    default -> System.out.println("Opción no válida.");
		}
	}
    
    private void mostrarIdYNombreEmpleados() {
        try (Session sesion = factory.openSession()) {
            /* Crear una consulta para obtener el ID y el nombre de todos los empleados */
            List<Empleados> empleados = sesion.createQuery("FROM Empleados", Empleados.class).list();

            /* Verificar si la lista de empleados está vacía */
            if (empleados.isEmpty()) {
                System.out.println("No se encontraron empleados.");
            } else {
                System.out.println("Lista de Empleados (ID y Nombre):");
                
                /* Recorrer la lista de empleados y mostrar solo el ID y el nombre */
                for (Empleados empleado : empleados) {
                    System.out.println("ID: " + empleado.getIdEmp() + ", Nombre: " + empleado.getNombreCompleto());
                }
                System.out.println();
            }
        } catch (Exception error) {
            /* Manejar cualquier excepción que ocurra durante el proceso */
            System.err.println("Error al listar los empleados: " + error.getMessage());
        }
    }
}
