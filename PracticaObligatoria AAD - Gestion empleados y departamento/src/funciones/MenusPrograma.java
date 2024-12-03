package funciones;

public class MenusPrograma {

    public void mostrarMenuMainApp() {

        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════════════════════╗");
        System.out.println("║          🌟 SISTEMA DE GESTIÓN DE EMPLEADOS Y DEPARTAMENTOS 🌟         ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║                              📋 EMPLEADOS                              ║");
        System.out.println("║                                                                        ║");
        System.out.println("║   1. Dar de alta a un empleado                                         ║");
        System.out.println("║   2. Dar de baja a un empleado                                         ║");
        System.out.println("║   3. Modificar datos de un empleado                                    ║");
        System.out.println("║   4. Consultar empleado por ID                                         ║");
        System.out.println("║   5. Mostrar todos los empleados                                       ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║                            🏢 DEPARTAMENTOS                            ║");
        System.out.println("║                                                                        ║");
        System.out.println("║   6. Dar de alta a un departamento                                     ║");
        System.out.println("║   7. Dar de baja a un departamento                                     ║");
        System.out.println("║   8. Modificar datos de un departamento                                ║");
        System.out.println("║   9. Consultar departamento por ID                                     ║");
        System.out.println("║  10. Mostrar todos los departamentos                                   ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║                       ⚙️ FUNCIONALIDADES EXTRA                         ║");
        System.out.println("║                                                                        ║");
        System.out.println("║  11. Asignar un empleado a un departamento                             ║");
        System.out.println("║  12. Consultar empleados por departamento                              ║");
        System.out.println("╠════════════════════════════════════════════════════════════════════════╣");
        System.out.println("║  13. ❌ Salir                                                          ║");
        System.out.println("╚════════════════════════════════════════════════════════════════════════╝");
        System.out.println();
    }
    
    public void mostrarMenuIngresoDatosDepartamento() {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║           MENÚ DE INGRESO DE DATOS - DEPARTAMENTO      ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║   Por favor, ingrese los datos del nuevo departamento  ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    public void mostrarMenuIngresoDatosEmpleado() {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║            MENÚ DE INGRESO DE DATOS - EMPLEADO         ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║   Por favor, ingrese los datos del nuevo empleado      ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    public void mostrarMenuModificacionEmpleado() {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║           MENÚ DE MODIFICACIÓN DE DATOS - EMPLEADO     ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║   Seleccione el campo que desea modificar:             ║");
        System.out.println("║   1. Nombre completo                                   ║");
        System.out.println("║   2. Oficio                                            ║");
        System.out.println("║   3. Fecha de alta                                     ║");
        System.out.println("║   4. Salario                                           ║");
        System.out.println("║   5. Departamento                                      ║");
        System.out.println("║   6. Proyecto opcional                                 ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    public void mostrarMenuModificacionDepartamento() {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║        MENÚ DE MODIFICACIÓN DE DATOS - DEPARTAMENTO    ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║   Seleccione el campo que desea modificar:             ║");
        System.out.println("║   1. Nombre del departamento                           ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    public void mostrarMenuAsignarEmpleadoADepartamento() {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║        MENÚ ASIGNAR EMPLEADO A DEPARTAMENTO            ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║   Por favor, ingrese los datos para asignar:           ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println();
    }

    public void mostrarMenuConsultaEmpleadosPorDepartamento() {
        System.out.println("\n");
        System.out.println("╔════════════════════════════════════════════════════════╗");
        System.out.println("║      MENÚ CONSULTA EMPLEADOS POR DEPARTAMENTO          ║");
        System.out.println("╠════════════════════════════════════════════════════════╣");
        System.out.println("║   Ingrese el ID del departamento para ver empleados    ║");
        System.out.println("╚════════════════════════════════════════════════════════╝");
        System.out.println();
    }

} 
