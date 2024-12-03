package modelo;

import java.util.HashSet;
import java.util.Set;

/**
 * Departamentos generated by hbm2java
 */
public class Departamentos implements java.io.Serializable {

	private byte idDept;
	private String nombre;
	private Set empleadoses = new HashSet(0);

	public Departamentos() {
	}

	public Departamentos(byte idDept) {
		this.idDept = idDept;
	}

	public Departamentos(byte idDept, String nombre, Set empleadoses) {
		this.idDept = idDept;
		this.nombre = nombre;
		this.empleadoses = empleadoses;
	}
	
    @Override
    public String toString() {
        return "\n------------------------------------------\n" +
               "Departamento ID: " + idDept + "\n" +
               "Nombre: " + nombre + "\n" +
               "Número de Empleados: " + empleadoses.size() + "\n" +
               "------------------------------------------";
    }

	public byte getIdDept() {
		return this.idDept;
	}

	public void setIdDept(byte idDept) {
		this.idDept = idDept;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Set getEmpleadoses() {
		return this.empleadoses;
	}

	public void setEmpleadoses(Set empleadoses) {
		this.empleadoses = empleadoses;
	}

}
