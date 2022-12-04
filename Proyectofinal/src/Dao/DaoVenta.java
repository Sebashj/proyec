package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.Autos;
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
	
	public ArrayList<Venta> fecthBuscar(String palabra) {
		ArrayList<Venta> lista2 = new ArrayList<Venta>();
		try {
			String sql = "SELECT * FROM Venta WHERE " + "(Idcliente LIKE ?) OR " + "(Idempleado LIKE ?) OR " +"(Lugar LIKE ?) OR " +"(Fecha LIKE ?) OR " + "(Monto LIKE ?) OR " + "(Nopedido LIKE ?); ";
			PreparedStatement ps = cx.conectar().prepareStatement(sql);
			ps.setString(1, "%" + palabra + "%");
			ps.setString(2, "%" + palabra + "%");
			ps.setString(3, "%" + palabra + "%");
			ps.setString(4, "%" + palabra + "%");
			ps.setString(5, "%" + palabra + "%");
			ps.setString(6, "%" + palabra + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Venta p = new Venta();
				p.setIdventa(rs.getInt("Idventa"));
				p.setIdcliente(rs.getInt("Idcliente"));
				p.setIdempleado(rs.getInt("Idempleado"));
				p.setLugar(rs.getString("Lugar"));
				p.setFecha(rs.getInt("Fecha"));
				p.setMonto(rs.getInt("Monto"));
				p.setNopedido(rs.getInt("Nopedido"));
				lista2.add(p);
			}
			ps.close();
			ps = null;
			//cx.desconectar();
		} catch (SQLException ex) {
			ex.printStackTrace();
			System.out.println("Error en BUSCAR");
		}
		return lista2;

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
