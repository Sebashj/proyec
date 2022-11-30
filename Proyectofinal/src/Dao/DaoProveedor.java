package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.Proveedor;

public class DaoProveedor {
	Conexion cx =null;
	
	public DaoProveedor() {
		cx=new Conexion();
	}
	
	public boolean insertarProveedor(Proveedor user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO Proveedor VALUES(null,?,?,?)");
			ps.setString(1, user.getNombreproveedor());
			ps.setInt(2, user.getContacto());
			ps.setString(3, user.getDescripcion());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public ArrayList<Proveedor> fetchProveedor(){
		ArrayList<Proveedor> lista=new ArrayList<Proveedor>();
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM Proveedor");
			rs=ps.executeQuery();
			while(rs.next()) {
				Proveedor u=new Proveedor();
				u.setIdproveedor(rs.getInt("IdProveedor"));
				u.setNombreproveedor(rs.getString("Nombreproveedor"));
				u.setContacto(rs.getInt("Contacto"));
				u.setDescripcion(rs.getString("Descripcion"));
				lista.add(u);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lista;
		
	}

	public boolean eliminarProveedor(int idproveedor) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("DELETE FROM Proveedor WHERE idproveedor=?");
			ps.setInt(1, idproveedor);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean editarProveedor(Proveedor user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("UPDATE Proveedor SET nombreproveedor=?, contacto=?, descripcion=? WHERE idproveedor=?");
			ps.setString(1, user.getNombreproveedor());
			ps.setInt(2, user.getContacto());
			ps.setString(3, user.getDescripcion());
			ps.setInt(4, user.getIdproveedor());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}

}
