package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.DetalleV;

public class DaoDetalleV {
	Conexion cx =null;
	
	public DaoDetalleV() {
		cx=new Conexion();
	}
	
	public boolean insertarDetalleV(DetalleV user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO DetalleV VALUES(null,?,?,?,?,?)");
			ps.setInt(1, user.getIdproducto());
			ps.setInt(2, user.getIdventa());
			ps.setInt(3, user.getCosto());
			ps.setInt(4, user.getCantidad());
			ps.setInt(5, user.getImporte());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public ArrayList<DetalleV> fetchDetalleVs(){
		ArrayList<DetalleV> lista=new ArrayList<DetalleV>();
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM DetalleV");
			rs=ps.executeQuery();
			while(rs.next()) {
				DetalleV u=new DetalleV();
				u.setIddetalle(rs.getInt("Iddetalle"));
				u.setIdproducto(rs.getInt("Idproducto"));
				u.setIdventa(rs.getInt("Idventa"));
				u.setCosto(rs.getInt("Costo"));
				u.setCantidad(rs.getInt("Cantidad"));
				u.setImporte(rs.getInt("Importe"));
				lista.add(u);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lista;
		
	}

	public boolean eliminarDetalleV(int idDetalleV) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("DELETE FROM DetalleV WHERE iddetalle=?");
			ps.setInt(1, idDetalleV);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean editarDetalleV(DetalleV user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("UPDATE DetalleV SET idproducto=?, idventa=?, costo=?, cantidad=?, importe=? WHERE iddetalle=?");
			ps.setInt(1, user.getIdproducto());
			ps.setInt(2, user.getIdventa());
			ps.setInt(3, user.getCosto());
			ps.setInt(4, user.getCantidad());
			ps.setInt(5, user.getImporte());
			ps.setInt(6, user.getIddetalle());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
}
