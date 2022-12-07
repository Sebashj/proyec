package Dao;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.swing.JOptionPane;

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
			ps=cx.conectar().prepareStatement("INSERT INTO cliente VALUES(null,?,?,?)");
			ps.setString(1, user.getDomicilio());
			ps.setInt(2, user.getTelefono());
			ps.setString(3, user.getNombre());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
        	JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
			return false;
		}
		
	}
	public ArrayList<Cliente> fetchClientes(){
		ArrayList<Cliente> lista=new ArrayList<Cliente>();
		PreparedStatement ps=null;
		ResultSet rs =null;
		try {
			ps=cx.conectar().prepareStatement("SELECT * FROM cliente");
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
			JOptionPane.showMessageDialog(null, e.getMessage());
        	JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, e.toString());
			e.printStackTrace();
		}
		return lista;
		
	}
	
	
	public ArrayList<Cliente> fecthBuscar(String palabra) {
		ArrayList<Cliente> lista2 = new ArrayList<Cliente>();
		try {
			String sql = "SELECT * FROM cliente WHERE " + "(Domicilio LIKE ?) OR " + "(Telefono LIKE ?) OR " + "(Nombre LIKE ?); ";
			PreparedStatement ps = cx.conectar().prepareStatement(sql);
			ps.setString(1, "%" + palabra + "%");
			ps.setString(2, "%" + palabra + "%");
			ps.setString(3, "%" + palabra + "%");

			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Cliente p = new Cliente();
				p.setDomicilio(rs.getString("Domicilio"));
				p.setTelefono(rs.getInt("Telefono"));
				p.setNombre(rs.getString("Nombre"));
				lista2.add(p);
			}
			ps.close();
			ps = null;
			//cx.desconectar();
		} catch (SQLException ex) {
			JOptionPane.showMessageDialog(null, ex.getMessage());
        	JOptionPane.showMessageDialog(null, ex.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, ex.toString());
			ex.printStackTrace();
			System.out.println("Error en BUSCAR");
		}
		return lista2;

	}

	public boolean eliminarCliente(int idcliente) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("DELETE FROM cliente WHERE idcliente=?");
			ps.setInt(1, idcliente);
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
        	JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, e.toString());
			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean editarCliente(Cliente user) {
		PreparedStatement ps=null;
		try {
			ps=cx.conectar().prepareStatement("UPDATE cliente SET domicilio=?, telefono=?, nombre=? WHERE idcliente=?");
			ps.setString(1, user.getDomicilio());
			ps.setInt(2, user.getTelefono());
			ps.setString(3, user.getNombre());
			ps.setInt(4, user.getIdcliente());
			ps.executeUpdate();
			return true;
		} catch (SQLException e) {
			JOptionPane.showMessageDialog(null, e.getMessage());
        	JOptionPane.showMessageDialog(null, e.getLocalizedMessage());
        	JOptionPane.showMessageDialog(null, e.toString());
			
			e.printStackTrace();
			return false;
		}
		
	}

}
