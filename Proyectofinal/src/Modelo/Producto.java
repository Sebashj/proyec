package Modelo;

public class Producto {
	
	int idproducto, idproveedor, codigo;
	double precio, precioventa;
	String descripcion;
	
	public Producto() {
		
	}
	public Producto(int idproducto, int idproveedor, int codigo, double precio, double precioventa, String descripcion) {
		super();
		this.idproducto = idproducto;
		this.idproveedor = idproveedor;
		this.codigo = codigo;
		this.precio = precio;
		this.precioventa = precioventa;
		this.descripcion = descripcion;
	}
	public int getIdproducto() {
		return idproducto;
	}
	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}
	public int getIdproveedor() {
		return idproveedor;
	}
	public void setIdproveedor(int idproveedor) {
		this.idproveedor = idproveedor;
	}
	public int getCodigo() {
		return codigo;
	}
	public void setCodigo(int codigo) {
		this.codigo = codigo;
	}
	public double getPrecio() {
		return precio;
	}
	public void setPrecio(double precio) {
		this.precio = precio;
	}
	public double getPrecioventa() {
		return precioventa;
	}
	public void setPrecioventa(double precioventa) {
		this.precioventa = precioventa;
	}
	public String getDescripcion() {
		return descripcion;
	}
	public void setDescripcion(String descripcion) {
		this.descripcion = descripcion;
	}
	

}
