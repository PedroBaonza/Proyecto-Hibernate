package modelo;
// Generated 18 nov 2024, 14:26:33 by Hibernate Tools 6.5.1.Final

import java.sql.Date;

/**
 * Empleados generated by hbm2java
 */
public class Empleados implements java.io.Serializable {

	private short idEmp;
	private Departamentos departamentos;
	private String nombreCompleto;
	private String oficio;
	private Date fechaAlt;
	private Float salario;
	private Byte proyecto;

	public Empleados() {
	}

	public Empleados(short idEmp, Departamentos departamentos) {
		this.idEmp = idEmp;
		this.departamentos = departamentos;
	}

	public Empleados(short idEmp, Departamentos departamentos, String nombreCompleto, String oficio, Date fechaAlt,
			Float salario, Byte proyecto) {
		this.idEmp = idEmp;
		this.departamentos = departamentos;
		this.nombreCompleto = nombreCompleto;
		this.oficio = oficio;
		this.fechaAlt = fechaAlt;
		this.salario = salario;
		this.proyecto = proyecto;
	}
	
	@Override
	public String toString() {
		return "Empleado ID: " + idEmp + "\n" +
				"Departamento: " + (departamentos != null ? departamentos.getNombre() : "No asignado") + "\n" +
				"Nombre Completo: " + nombreCompleto + "\n" +
				"Oficio: " + oficio + "\n" +
				"Fecha de Alta: " + (fechaAlt != null ? fechaAlt.toString() : "No especificada") + "\n" +
				"Salario: " + (salario != null ? String.format("%.2f", salario) : "No especificado") + "\n" +
				"Proyecto: " + (proyecto != null ? proyecto : "No asignado") + "\n";
	}
	
	public String mostrarNombreYId() {
	    return "Empleado [ID: " + idEmp + " - Nombre: " + (nombreCompleto != null ? nombreCompleto : "No especificado") + "]";
	}

	public short getIdEmp() {
		return this.idEmp;
	}

	public void setIdEmp(short idEmp) {
		this.idEmp = idEmp;
	}

	public Departamentos getDepartamentos() {
		return this.departamentos;
	}

	public void setDepartamentos(Departamentos departamentos) {
		this.departamentos = departamentos;
	}

	public String getNombreCompleto() {
		return this.nombreCompleto;
	}

	public void setNombreCompleto(String nombreCompleto) {
		this.nombreCompleto = nombreCompleto;
	}

	public String getOficio() {
		return this.oficio;
	}

	public void setOficio(String oficio) {
		this.oficio = oficio;
	}

	public Date getFechaAlt() {
		return this.fechaAlt;
	}

	public void setFechaAlt(Date fechaAlt) {
		this.fechaAlt = fechaAlt;
	}

	public Float getSalario() {
		return this.salario;
	}

	public void setSalario(Float salario) {
		this.salario = salario;
	}

	public Byte getProyecto() {
		return this.proyecto;
	}

	public void setProyecto(Byte proyecto) {
		this.proyecto = proyecto;
	}

}
