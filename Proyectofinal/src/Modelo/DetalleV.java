package Modelo;

public class DetalleV {
	
	int iddetalle, idproducto, idventa, costo, cantidad, importe;
	
	public DetalleV() {
		
	}

	public DetalleV(int iddetalle, int idproducto, int idventa, int costo, int cantidad, int importe) {
		super();
		this.iddetalle = iddetalle;
		this.idproducto = idproducto;
		this.idventa = idventa;
		this.costo = costo;
		this.cantidad = cantidad;
		this.importe = importe;
	}

	public int getIddetalle() {
		return iddetalle;
	}

	public void setIddetalle(int iddetalle) {
		this.iddetalle = iddetalle;
	}

	public int getIdproducto() {
		return idproducto;
	}

	public void setIdproducto(int idproducto) {
		this.idproducto = idproducto;
	}

	public int getIdventa() {
		return idventa;
	}

	public void setIdventa(int idventa) {
		this.idventa = idventa;
	}

	public int getCosto() {
		return costo;
	}

	public void setCosto(int costo) {
		this.costo = costo;
	}

	public int getCantidad() {
		return cantidad;
	}

	public void setCantidad(int cantidad) {
		this.cantidad = cantidad;
	}

	public int getImporte() {
		return importe;
	}

	public void setImporte(int importe) {
		this.importe = importe;
	}
	
	

}
