package modelo;
// Generated 18 nov 2024, 14:26:33 by Hibernate Tools 6.5.1.Final

/**
 * Proyectos generated by hbm2java
 */
public class Proyectos implements java.io.Serializable {

	private byte idProyecto;
	private Clientes clientes;
	private String nombre;

	public Proyectos() {
	}

	public Proyectos(byte idProyecto) {
		this.idProyecto = idProyecto;
	}

	public Proyectos(byte idProyecto, Clientes clientes, String nombre) {
		this.idProyecto = idProyecto;
		this.clientes = clientes;
		this.nombre = nombre;
	}

	public byte getIdProyecto() {
		return this.idProyecto;
	}

	public void setIdProyecto(byte idProyecto) {
		this.idProyecto = idProyecto;
	}

	public Clientes getClientes() {
		return this.clientes;
	}

	public void setClientes(Clientes clientes) {
		this.clientes = clientes;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

}
