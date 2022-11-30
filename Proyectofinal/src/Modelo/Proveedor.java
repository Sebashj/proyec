package Modelo;

public class Proveedor {
	
	int idproveedor, contacto;
	String nombreproveedor, descripcion;
	
	public Proveedor() {
		
	}
	public Proveedor(int idproveedor, int contacto, String nombreproveedor, String descripcion) {
		super();
		this.idproveedor = idproveedor;
		this.contacto = contacto;
		this.nombreproveedor = nombreproveedor;
		this.descripcion = descripcion;
	}
	public int getIdproveedor() {
		return idproveedor;
	}
	public void setIdproveedor(int idproveedor) {
		this.idproveedor = idproveedor;
	}
	public int getContacto() {
		return contacto;
	}
	public void setContacto(int contacto) {
		this.contacto = contacto;
	}
	public String getNombreproveedor() {
		return nombreproveedor;
	}
	public void setNombreproveedor(String nombreproveedor) {
		this.nombreproveedor = nombreproveedor;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
