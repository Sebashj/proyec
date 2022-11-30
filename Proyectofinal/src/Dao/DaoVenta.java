package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.Venta;

public class DaoVenta {
	Conexion cx =null;
	
	public DaoVenta() {
		cx=new Conexion();
	}
	
	public boolean insertarVenta(Venta user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO Venta VALUES(null,?,?,?,?,?,?)");
			ps.setInt(1, user.getIdcliente());
			ps.setInt(2, user.getIdempleado());
			ps.setString(3, user.getLugar());
			ps.setInt(4, user.getFecha());
			ps.setInt(5, user.getMonto());
			ps.setInt(6, user.getNopedido());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public ArrayList<Venta> fetchVentas(){
		ArrayList<Venta> lista=new ArrayList<Venta>();
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM Venta");
			rs=ps.executeQuery();
			while(rs.next()) {
				Venta u=new Venta();
				u.setIdventa(rs.getInt("Idventa"));
				u.setIdcliente(rs.getInt("Idcliente"));
				u.setIdempleado(rs.getInt("Idempleado"));
				u.setLugar(rs.getString("Lugar"));
				u.setFecha(rs.getInt("Fecha"));
				u.setMonto(rs.getInt("Monto"));
				u.setNopedido(rs.getInt("Nopedido"));
				lista.add(u);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lista;
		
	}

	public boolean eliminarVenta(int idVenta) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("DELETE FROM Venta WHERE idventa=?");
			ps.setInt(1, idVenta);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean editarVenta(Venta user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("UPDATE Venta SET idcliente=?, idempleado=?, lugar=?, fecha=?, monto=?, nopedido=?  WHERE idventa=?");
			ps.setInt(1, user.getIdcliente());
			ps.setInt(2, user.getIdempleado());
			ps.setString(3, user.getLugar());
			ps.setInt(4, user.getFecha());
			ps.setInt(5, user.getMonto());
			ps.setInt(6, user.getNopedido());
			ps.setInt(7, user.getIdventa());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
}
