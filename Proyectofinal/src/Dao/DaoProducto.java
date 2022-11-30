package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.Producto;

public class DaoProducto {
	Conexion cx =null;
	
	public DaoProducto() {
		cx=new Conexion();
	}
	
	public boolean insertarProducto(Producto user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO Producto VALUES(null,?,?,?,?,?)");
			ps.setInt(1, user.getIdproveedor());
			ps.setInt(2, user.getCodigo());
			ps.setDouble(3, user.getPrecio());
			ps.setDouble(4, user.getPrecioventa());
			ps.setString(5, user.getDescripcion());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public ArrayList<Producto> fetchProductos(){
		ArrayList<Producto> lista=new ArrayList<Producto>();
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM Producto");
			rs=ps.executeQuery();
			while(rs.next()) {
				Producto u=new Producto();
				u.setIdproducto(rs.getInt("Idproducto"));
				u.setIdproveedor(rs.getInt("Idproveedor"));
				u.setCodigo(rs.getInt("Codigo"));
				u.setPrecio(rs.getDouble("Precio"));
				u.setPrecioventa(rs.getDouble("Precioventa"));
				u.setDescripcion(rs.getString("Descripcion"));
				lista.add(u);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lista;
		
	}

	public boolean eliminarProducto(int idproducto) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("DELETE FROM Producto WHERE idproducto=?");
			ps.setInt(1, idproducto);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean editarProducto(Producto user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("UPDATE Producto SET Idprovedor=?, codigo=?, precio=?, precioventa=?, descripcion=? WHERE idproducto=?");
			ps.setInt(1, user.getIdproveedor());
			ps.setInt(2, user.getCodigo());
			ps.setDouble(3, user.getPrecio());
			ps.setDouble(4, user.getPrecioventa());
			ps.setString(5, user.getDescripcion());
			ps.setInt(6, user.getIdproducto());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
}
