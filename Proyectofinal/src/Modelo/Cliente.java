package Modelo;

public class Cliente {
	
	int idcliente, telefono;
	String domicilio, nombre;
	
	public Cliente() {
		
	}
	public Cliente(int idcliente, int telefono, String domicilio, String nombre) {
		super();
		this.idcliente = idcliente;
		this.telefono = telefono;
		this.domicilio = domicilio;
		this.nombre = nombre;
	}
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	

}
