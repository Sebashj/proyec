package Modelo;

public class Autos {
	
	int idauto, año;
	String marca, tipodeauto, cilindros, imagen;
	public Autos() {
		
	}
	
	public Autos(int idauto, int año, String marca, String tipodeauto, String cilindros, String imagen) {
		super();
		this.idauto = idauto;
		this.año = año;
		this.marca = marca;
		this.tipodeauto = tipodeauto;
		this.cilindros = cilindros;
		this.imagen = imagen;
	}
	
	public int getIdauto() {
		return idauto;
	}
	public void setIdauto(int idauto) {
		this.idauto = idauto;
	}
	public int getAño() {
		return año;
	}
	public void setAño(int año) {
		this.año = año;
	}
	public String getMarca() {
		return marca;
	}
	public void setMarca(String marca) {
		this.marca = marca;
	}
	public String getTipodeauto() {
		return tipodeauto;
	}
	public void setTipodeauto(String tipodeauto) {
		this.tipodeauto = tipodeauto;
	}
	public String getCilindros() {
		return cilindros;
	}
	public void setCilindros(String cilindros) {
		this.cilindros = cilindros;
	}
	public String getImagen() {
		return imagen;
	}
	public void setImagen(String imagen) {
		this.imagen = imagen;
	}

}
