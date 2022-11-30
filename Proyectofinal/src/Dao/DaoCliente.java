package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import Conexion.Conexion;
import Modelo.Cliente;

public class DaoCliente {
	Conexion cx =null;
	
	public DaoCliente() {
		cx=new Conexion();
	}
	
	public boolean insertarCliente(Cliente user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("INSERT INTO Cliente VALUES(null,?,?,?)");
			ps.setString(1, user.getDomicilio());
			ps.setInt(2, user.getTelefono());
			ps.setString(3, user.getNombre());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	public ArrayList<Cliente> fetchClientes(){
		ArrayList<Cliente> lista=new ArrayList<Cliente>();
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM Cliente");
			rs=ps.executeQuery();
			while(rs.next()) {
				Cliente u=new Cliente();
				u.setIdcliente(rs.getInt("Idcliente"));
				u.setDomicilio(rs.getString("Domicilio"));
				u.setTelefono(rs.getInt("Telefono"));
				u.setNombre(rs.getString("Nombre"));
				lista.add(u);
			}
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
		return lista;
		
	}
	
	//public boolean loginCliente(Cliente user) {PreparedStatement ps=null;ResultSet rs=null;try {ps=cx.conectar().prepareStatement("SELECT * FROM Cliente WHERE user=? AND password=?");ps.setString(1, user.getUser());ps.setString(2, convertirSHA256(user.getPassword()));rs=ps.executeQuery();if(rs.next()) {return true;}else {			return false;}} catch (SQLException e) {e.printStackTrace();return false;}}
	
	public boolean eliminarCliente(int id) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("DELETE FROM Cliente WHERE idcliente=?");
			ps.setInt(1, id);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean editarCliente(Cliente user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("UPDATE Cliente SET domicilio=?, telefono=?, nombre=? WHERE idcliente=?");
			ps.setString(1, user.getDomicilio());
			ps.setInt(2, user.getTelefono());
			ps.setString(3, user.getNombre());
			ps.setInt(4, user.getIdcliente());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			
			e.printStackTrace();
			return false;
		}
		
	}

}
