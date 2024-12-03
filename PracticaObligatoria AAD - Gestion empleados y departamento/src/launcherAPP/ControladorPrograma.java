package launcherAPP;

import funciones.MenusPrograma;
import funciones.PeticionDatos;
import funciones.ProcesadoHibernateDepartamentos;
import funciones.ProcesadoHibernateEmpleado;

public class ControladorPrograma {

    private final MenusPrograma menu;
    private final PeticionDatos entradaDatos;
    private final ProcesadoHibernateEmpleado empleadoFunciones;
    private final ProcesadoHibernateDepartamentos departamentoFunciones;

    /* Constructor: inicializa las clases necesarias */
    public ControladorPrograma() {
        /* Inicializar las clases utilizadas para manejar el menú, la entrada de datos y las operaciones de empleados y departamentos */
        this.menu = new MenusPrograma();
        this.entradaDatos = new PeticionDatos();
        this.empleadoFunciones = new ProcesadoHibernateEmpleado();
        this.departamentoFunciones = new ProcesadoHibernateDepartamentos();
    }

    /* Método principal para iniciar el programa. */
    public void iniciarPrograma() {
        boolean programaActivo = true;

        /* Ciclo que mantiene el programa en ejecución mientras sea necesario */
        while (programaActivo) {
            /* Mostrar el menú principal al usuario y procesar la opción seleccionada */
            menu.mostrarMenuMainApp();
            int opcion = entradaDatos.escribirNumero("Selecciona una opción del 1 al 13: ");
            programaActivo = procesarOpcion(opcion);
        }
    }

    /**
     * Procesa la opción seleccionada por el usuario.
     * Este método procesa las diferentes opciones seleccionadas por el usuario, llamando
     * a los métodos correspondientes según la opción.
     */
    private boolean procesarOpcion(int opcion) {
        switch (opcion) {
            case 1:
                /* Agregar un nuevo empleado */
                empleadoFunciones.agregarEmpleado();
                break;
            case 2:
                /* Eliminar un empleado */
                empleadoFunciones.eliminarEmpleado();
                break;
            case 3:
                /* Modificar un empleado */
                empleadoFunciones.modificarEmpleado();
                break;
            case 4:
                /* Consultar un empleado por ID */
                empleadoFunciones.consultarEmpleadoPorID();
                break;
            case 5:
                /* Mostrar todos los empleados */
                empleadoFunciones.mostrarTodosLosEmpleados();
                break;
            case 6:
                /* Agregar un nuevo departamento */
                departamentoFunciones.agregarDepartamento();
                break;
            case 7:
                /* Eliminar un departamento */
                departamentoFunciones.eliminarDepartamento();
                break;
            case 8:
                /* Modificar un departamento */
                departamentoFunciones.modificarDepartamento();
                break;
            case 9:
                /* Consultar un departamento por ID */
                departamentoFunciones.consultarDepartamentoPorID();
                break;
            case 10:
                /* Mostrar todos los departamentos */
                departamentoFunciones.mostrarTodosLosDepartamentos();
                break;
            case 11:
                /* Funcionalidad no implementada: Asignar empleado a departamento */
                departamentoFunciones.asignarEmpleadoADepartamento();
                break;
            case 12:
                /* Funcionalidad no implementada: Consultar empleados por departamento */
                departamentoFunciones.consultarDepartamentoPorID();
                break;
            case 13:
                /* Finalizar el programa */
                System.out.println("Saliendo del sistema...");
                return false;
            default:
                /* Opción no válida */
                System.out.println("Opción no válida. Por favor, selecciona una opción del 1 al 13.");
        }
        return true; // Continuar el programa
    }
}