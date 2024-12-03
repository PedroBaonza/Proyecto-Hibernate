package funciones;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.SessionFactory;
import launcherAPP.ConexionHibernate;
import modelo.Departamentos;
import modelo.Empleados;

public class ProcesadoHibernateDepartamentos {

    private static final SessionFactory factory = ConexionHibernate.getSessionFactory();
    private final PeticionDatos entradaDatos = new PeticionDatos();
    private final MenusPrograma menusPrograma = new MenusPrograma();

    /**
     * Método para agregar un nuevo departamento a la base de datos.
     * Solicita los datos del departamento, los valida y luego guarda el departamento en la base de datos.
     */
    public void agregarDepartamento() {
    	
    	menusPrograma.mostrarMenuIngresoDatosDepartamento();
    	
        try (Session sesion = factory.openSession()) {
            /* Iniciar una transacción para garantizar la consistencia de los datos */
            Transaction transaccion = sesion.beginTransaction();

            /* Solicitar al usuario el ID del departamento y validar que sea positivo */
            byte idDept = (byte) entradaDatos.escribirNumero("Ingrese el ID del departamento: ");
            Validaciones.validarPositivo(idDept, "El ID del departamento debe ser mayor que cero.");

            /* Validar si el ID ya existe en la base de datos */
            if (sesion.get(Departamentos.class, idDept) != null) {
                System.err.println("Error: El departamento con ID " + idDept + " ya existe.");
                return;
            }

            /* Pedimos al usuario el nombre del nuevo departamento y lo validamos */
            String nombre = entradaDatos.escribirCadena("Ingrese el nombre del departamento: ");
            Validaciones.validarCadena(nombre, "El nombre del departamento no puede estar vacío.");

            /* Generamos un objeto vacío para poder settear los valores que ha introducido el usuario */
            Departamentos departamento = new Departamentos();
            departamento.setIdDept(idDept);
            departamento.setNombre(nombre);

            /* Guardamos el departamento en la base de datos */
            sesion.save(departamento);
            /* Guardamos los cambios hechos en la base de datos */
            transaccion.commit();
            System.out.println("Departamento agregado con éxito.");
        } catch (Exception error) {
            System.err.println("Error al agregar el departamento: " + error.getMessage());
        }
    }

    /**
     * Método para eliminar un departamento por su ID.
     * Solicita el ID del departamento, valida su existencia y lo elimina si no tiene empleados asignados.
     */
    public void eliminarDepartamento() {
        try (Session sesion = factory.openSession()) {
            /* Iniciar una transacción para garantizar la consistencia de los datos */
            Transaction transaccion = sesion.beginTransaction();
            
            /* Mostramos por consola los departamentos que hay disponible. */ 
            mostrarDepartamentosIdNombre();

            /* Solicitar al usuario el ID del departamento a eliminar y validar que sea positivo */
            byte id = (byte) entradaDatos.escribirNumero("Ingrese el ID del departamento a eliminar: ");
            Validaciones.validarPositivo((double) id, "El ID del departamento debe ser mayor que cero.");

            /* Verificar si el departamento existe en la base de datos */
            Departamentos departamento = Validaciones.validarExistenciaPorID(sesion, Departamentos.class, id, "El departamento con ID " + id + " no existe.");
            
            /* Verificar si el departamento no tiene empleados asignados antes de eliminar */
            if (departamento.getEmpleadoses().isEmpty()) {
                sesion.delete(departamento);
                transaccion.commit();
                System.out.println("Departamento eliminado con éxito.");
            } else {
                System.out.println("El departamento no se puede eliminar porque tiene empleados asignados.");
            }
        } catch (Exception error) {
            System.err.println("Error al eliminar el departamento: " + error.getMessage());
        }
    }

    /**
     * Modificar datos de un departamento.
     * Solicita al usuario los datos a modificar y actualiza la información del departamento.
     */
    public void modificarDepartamento() {
    	
    	menusPrograma.mostrarMenuModificacionDepartamento();
    	
        try (Session sesion = factory.openSession()) {
            /* Iniciar una transacción para garantizar la consistencia de los datos */
            Transaction transaccion = sesion.beginTransaction();

            /* Solicitar al usuario el ID del departamento a modificar y validar que sea positivo */
            byte id = (byte) entradaDatos.escribirNumero("Ingrese el ID del departamento a modificar: ");
            Validaciones.validarPositivo(id, "El ID del departamento debe ser mayor que cero.");

            /* Verificar si el departamento existe en la base de datos */
            Departamentos departamento = Validaciones.validarExistenciaPorID(sesion, Departamentos.class, id, "El departamento con ID " + id + " no existe.");
            
            /* Solicitar el nuevo nombre del departamento y validarlo */
            String nuevoNombre = entradaDatos.escribirCadena("Ingrese el nuevo nombre del departamento: ");
            Validaciones.validarCadena(nuevoNombre, "El nombre del departamento no puede estar vacío.");
            departamento.setNombre(nuevoNombre);

            /* Actualizar el departamento en la base de datos */
            sesion.update(departamento);
            transaccion.commit();
            System.out.println("Departamento modificado con éxito.");
        } catch (Exception error) {
            System.err.println("Error al modificar el departamento: " + error.getMessage());
        }
    }

    /**
     * Consultar un departamento por su ID.
     * Solicita el ID del departamento y muestra la información si se encuentra.
     */
    public void consultarDepartamentoPorID() {
        try (Session sesion = factory.openSession()) {
        	
        	/* Mostramos por consola los departamentos que hay disponible. */ 
            mostrarDepartamentosIdNombre();
            
            /* Solicitar al usuario el ID del departamento a consultar y validar que sea positivo */
            byte id = (byte) entradaDatos.escribirNumero("Ingrese el ID del departamento a consultar: ");
            Validaciones.validarPositivo(id, "El ID del departamento debe ser mayor que cero.");

            /* Verificar si el departamento existe en la base de datos */
            Departamentos departamento = Validaciones.validarExistenciaPorID(sesion, Departamentos.class, id, "El departamento con ID " + id + " no existe.");
            
            /* Mostrar la información del departamento encontrado */
            System.out.println("Departamento encontrado: " + departamento);
        } catch (Exception error) {
            System.err.println("Error al consultar el departamento: " + error.getMessage());
        }
    }

    /**
     * Mostrar todos los departamentos.
     * Recupera y muestra todos los departamentos registrados en la base de datos.
     */
    public void mostrarTodosLosDepartamentos() {
        try (Session sesion = factory.openSession()) {
            /* Recuperar la lista de todos los departamentos en la base de datos */
            List<Departamentos> listaDepartamentos = sesion.createQuery("FROM Departamentos", Departamentos.class).list();

            /* Verificar si hay departamentos registrados y mostrarlos */
            if (listaDepartamentos.isEmpty()) {
                System.out.println("No se encontraron departamentos.");
            } else {
                System.out.println("Lista de departamentos:");
                for (Departamentos departamento : listaDepartamentos) {
                    System.out.println(departamento.toString());
                }
            }
        } catch (Exception error) {
            System.err.println("Error al mostrar los departamentos: " + error.getMessage());
        }
    }
    
    /**
     * Asignar un empleado a un departamento.
     * Solicita el ID del empleado y el ID del departamento para asignar el empleado al departamento especificado.
     */
    public void asignarEmpleadoADepartamento() {
        try (Session sesion = factory.openSession()) {
            /* Iniciar una transacción para garantizar la consistencia de los datos */
            Transaction transaccion = sesion.beginTransaction();

            /* Solicitar al usuario el ID del empleado y validar que sea positivo */
            short idEmpleado = (short) entradaDatos.escribirNumero("Ingrese el ID del empleado: ");
            Validaciones.validarPositivo(idEmpleado, "El ID del empleado debe ser mayor que cero.");

            /* Verificar si el empleado existe en la base de datos */
            Empleados empleado = Validaciones.validarExistenciaPorID(sesion, Empleados.class, idEmpleado, "El empleado con ID " + idEmpleado + " no existe.");

            /* Solicitar al usuario el ID del departamento y validar que sea positivo */
            /* Mostramos por consola los departamentos que hay disponible. */ 
            mostrarDepartamentosIdNombre();
            byte idDept = (byte) entradaDatos.escribirNumero("Ingrese el ID del departamento al que desea asignar el empleado: ");
            Validaciones.validarPositivo(idDept, "El ID del departamento debe ser mayor que cero.");

            /* Verificar si el departamento existe en la base de datos */
            Departamentos departamento = Validaciones.validarExistenciaPorID(sesion, Departamentos.class, idDept, "El departamento con ID " + idDept + " no existe.");

            /* Asignar el departamento al empleado */
            empleado.setDepartamentos(departamento);

            /* Actualizar el empleado en la base de datos */
            sesion.update(empleado);
            transaccion.commit();
            System.out.println("Empleado asignado al departamento con éxito.");
        } catch (Exception error) {
            System.err.println("Error al asignar el empleado al departamento: " + error.getMessage());
        }
        
    }
    
    /**
     * Mostrar los empleados asignados a un departamento específico.
     * Solicita el ID del departamento y muestra los empleados asignados a ese departamento.
     */
    public void empleadosAsignadosDepartamentoPorID() {
        try (Session sesion = factory.openSession()) {
            /* Solicitar al usuario el ID del departamento y validar que sea positivo */
            byte idDept = (byte) entradaDatos.escribirNumero("Ingrese el ID del departamento: ");
            Validaciones.validarPositivo(idDept, "El ID del departamento debe ser mayor que cero.");

            /* Verificar si el departamento existe en la base de datos */
            Departamentos departamento = Validaciones.validarExistenciaPorID(sesion, Departamentos.class, idDept, "El departamento con ID " + idDept + " no existe.");
            
            /* Mostrar los empleados asignados al departamento */
            System.out.println("Empleados asignados al departamento: " + departamento.getNombre() + " (ID: " + departamento.getIdDept() + ")");
            if (departamento.getEmpleadoses().isEmpty()) {
                System.out.println("No hay empleados asignados a este departamento.");
            } else {
                for (Object empleadoObj : departamento.getEmpleadoses()) {
                    Empleados empleado = (Empleados) empleadoObj;
                    System.out.println("  - ID: " + empleado.getIdEmp() + ", Nombre: " + empleado.getNombreCompleto() + ", Oficio: " + empleado.getOficio());
                }
            }
        } catch (Exception error) {
            System.err.println("Error al mostrar los empleados asignados al departamento: " + error.getMessage());
        }
    }
    
    /**
     * Mostrar los departamentos con solo su ID y nombre.
     * Recupera y muestra el ID y nombre de todos los departamentos registrados en la base de datos.
     */
    private void mostrarDepartamentosIdNombre() {
        try (Session sesion = factory.openSession()) {
            /* Recuperar la lista de todos los departamentos en la base de datos */
            List<Departamentos> listaDepartamentos = sesion.createQuery("FROM Departamentos", Departamentos.class).list();

            /* Verificar si hay departamentos registrados y mostrarlos */
            if (listaDepartamentos.isEmpty()) {
                System.out.println("No se encontraron departamentos.");
            } else {
                System.out.println("Lista de departamentos (ID y Nombre):");
                for (Departamentos departamento : listaDepartamentos) {
                    System.out.println("ID: " + departamento.getIdDept() + ", Nombre: " + departamento.getNombre());
                }
            }
        } catch (Exception error) {
            System.err.println("Error al mostrar los departamentos: " + error.getMessage());
        }
    }
}
