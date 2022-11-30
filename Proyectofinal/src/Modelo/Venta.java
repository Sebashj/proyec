package Modelo;

public class Venta {
	
	int idventa, idcliente, idempleado, fecha, monto, nopedido;
	String lugar;
	public Venta() {
		
	}
	
	public Venta(int idventa, int idcliente, int idempleado, int fecha, int monto, int nopedido, String lugar) {
		super();
		this.idventa = idventa;
		this.idcliente = idcliente;
		this.idempleado = idempleado;
		this.fecha = fecha;
		this.monto = monto;
		this.nopedido = nopedido;
		this.lugar = lugar;
	}
	
	public int getIdventa() {
		return idventa;
	}
	public void setIdventa(int idventa) {
		this.idventa = idventa;
	}
	public int getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(int idcliente) {
		this.idcliente = idcliente;
	}
	public int getIdempleado() {
		return idempleado;
	}
	public void setIdempleado(int idempleado) {
		this.idempleado = idempleado;
	}
	public int getFecha() {
		return fecha;
	}
	public void setFecha(int fecha) {
		this.fecha = fecha;
	}
	public int getMonto() {
		return monto;
	}
	public void setMonto(int monto) {
		this.monto = monto;
	}
	public int getNopedido() {
		return nopedido;
	}
	public void setNopedido(int nopedido) {
		this.nopedido = nopedido;
	}
	public String getLugar() {
		return lugar;
	}
	public void setLugar(String lugar) {
		this.lugar = lugar;
	}
	
	

}
