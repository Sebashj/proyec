package Modelo;

public class Venta {
	
	int idventa, monto, nopedido;
	String lugar, idcliente, idempleado, fecha ;
	public Venta() {
		
	}
	
	public Venta(int idventa, int monto, int nopedido, String lugar, String idcliente, String idempleado, String fecha) {
		super();
		this.idventa = idventa;
		this.monto = monto;
		this.nopedido = nopedido;
		this.lugar = lugar;
		this.idcliente = idcliente;
		this.idempleado = idempleado;
		this.fecha = fecha;
	}
	
	public int getIdventa() {
		return idventa;
	}
	public void setIdventa(int idventa) {
		this.idventa = idventa;
	}
	public String getIdcliente() {
		return idcliente;
	}
	public void setIdcliente(String idcliente) {
		this.idcliente = idcliente;
	}
	public String getIdempleado() {
		return idempleado;
	}
	public void setIdempleado(String idempleado) {
		this.idempleado = idempleado;
	}
	public String getFecha() {
		return fecha;
	}
	public void setFecha(String fecha) {
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
