package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.Autos;
import Modelo.Inventario;

public class DaoInventario {
	Conexion cx =null;
	
	public DaoInventario() {
		cx=new Conexion();
	}
	
	public boolean insertarInventario(Inventario user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO Inventario VALUES(null,?,?,?,?)");
			ps.setInt(1, user.getIdproducto());
			ps.setInt(2, user.getFecha());
			ps.setInt(3, user.getCantidad());
			ps.setString(4, user.getTipodemovimiento());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public ArrayList<Inventario> fetchInventarios(){
		ArrayList<Inventario> lista=new ArrayList<Inventario>();
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM Inventario");
			rs=ps.executeQuery();
			while(rs.next()) {
				Inventario u=new Inventario();
				u.setIdinventario(rs.getInt("IdInventario"));
				u.setIdproducto(rs.getInt("Idproducto"));
				u.setFecha(rs.getInt("Fecha"));
				u.setCantidad(rs.getInt("Cantidad"));
				u.setTipodemovimiento(rs.getString("Tipodemovimiento"));
				lista.add(u);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lista;
		
	}
	
	public ArrayList<Inventario> fecthBuscar(String palabra) {
		ArrayList<Inventario> lista2 = new ArrayList<Inventario>();
		try {
			String sql = "SELECT * FROM Inventario WHERE " + "(Idproducto LIKE ?) OR " + "(Fecha LIKE ?) OR " +"(Cantidad LIKE ?) OR " + "(Tipodemovimiento LIKE ?); ";
			PreparedStatement ps = cx.conectar().prepareStatement(sql);
			ps.setString(1, "%" + palabra + "%");
			ps.setString(2, "%" + palabra + "%");
			ps.setString(3, "%" + palabra + "%");
			ps.setString(4, "%" + palabra + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Inventario p = new Inventario();
				p.setIdinventario(rs.getInt("IdInventario"));
				p.setIdproducto(rs.getInt("Idproducto"));
				p.setFecha(rs.getInt("Fecha"));
				p.setCantidad(rs.getInt("Cantidad"));
				p.setTipodemovimiento(rs.getString("Tipodemovimiento"));
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

	public boolean eliminarInventario(int idinventario) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("DELETE FROM Inventario WHERE idinventario=?");
			ps.setInt(1, idinventario);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean editarInventario(Inventario user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("UPDATE Inventario SET Idproducto=?, Fecha=?, Cantidad=?, Tipodemovimiento=? WHERE idinventario=?");
			ps.setInt(1, user.getIdproducto());
			ps.setInt(2, user.getFecha());
			ps.setInt(3, user.getCantidad());
			ps.setString(4, user.getTipodemovimiento());
			ps.setInt(5, user.getIdinventario());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
}
