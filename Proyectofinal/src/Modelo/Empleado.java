package Modelo;

public class Empleado {
	
	int idempleado, telefono;
	String nombre, domicilio, puesto, password;
	
	public Empleado() {
		
	}
	
	public Empleado(int idempleado, int telefono, String nombre, String domicilio, String puesto, String password) {
		super();
		this.idempleado = idempleado;
		this.telefono = telefono;
		this.nombre = nombre;
		this.domicilio = domicilio;
		this.puesto = puesto;
		this.password = password;
	}
	public int getIdempleado() {
		return idempleado;
	}
	public void setIdempleado(int idempleado) {
		this.idempleado = idempleado;
	}
	public int getTelefono() {
		return telefono;
	}
	public void setTelefono(int telefono) {
		this.telefono = telefono;
	}
	public String getNombre() {
		return nombre;
	}
	public void setNombre(String nombre) {
		this.nombre = nombre;
	}
	public String getDomicilio() {
		return domicilio;
	}
	public void setDomicilio(String domicilio) {
		this.domicilio = domicilio;
	}
	public String getPuesto() {
		return puesto;
	}
	public void setPuesto(String puesto) {
		this.puesto = puesto;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	

}
