package modelo;
// Generated 18 nov 2024, 14:26:33 by Hibernate Tools 6.5.1.Final

import java.util.HashSet;
import java.util.Set;

/**
 * Clientes generated by hbm2java
 */
public class Clientes implements java.io.Serializable {

	private byte idCliente;
	private String nombre;
	private String ciudad;
	private Set proyectoses = new HashSet(0);

	public Clientes() {
	}

	public Clientes(byte idCliente) {
		this.idCliente = idCliente;
	}

	public Clientes(byte idCliente, String nombre, String ciudad, Set proyectoses) {
		this.idCliente = idCliente;
		this.nombre = nombre;
		this.ciudad = ciudad;
		this.proyectoses = proyectoses;
	}

	public byte getIdCliente() {
		return this.idCliente;
	}

	public void setIdCliente(byte idCliente) {
		this.idCliente = idCliente;
	}

	public String getNombre() {
		return this.nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getCiudad() {
		return this.ciudad;
	}

	public void setCiudad(String ciudad) {
		this.ciudad = ciudad;
	}

	public Set getProyectoses() {
		return this.proyectoses;
	}

	public void setProyectoses(Set proyectoses) {
		this.proyectoses = proyectoses;
	}

}